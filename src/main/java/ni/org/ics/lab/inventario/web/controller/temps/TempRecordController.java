package ni.org.ics.lab.inventario.web.controller.temps;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Equipment;
import ni.org.ics.lab.inventario.domain.TempRecord;
import ni.org.ics.lab.inventario.domain.audit.AuditTrail;
import ni.org.ics.lab.inventario.service.AuditTrailService;
import ni.org.ics.lab.inventario.service.EquipoService;
import ni.org.ics.lab.inventario.service.TempRecordService;
import ni.org.ics.lab.inventario.service.UsuarioService;
import ni.org.ics.lab.inventario.users.model.UserSistema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

/**
 * Controlador web de peticiones relacionadas a super administrador
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/temps/*")
public class TempRecordController {
	private static final Logger logger = LoggerFactory.getLogger(TempRecordController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="tempRecordService")
	private TempRecordService tempRecordService;
	@Resource(name="equipoService")
	private EquipoService equipoService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerTemperaturas(Model model) throws ParseException { 	
    	logger.debug("Mostrando Temperaturas en JSP");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
	    	List<Equipment> equipos = equipoService.getEquipos(usuario.getUserCenter().getCenterCode());
	    	model.addAttribute("equipos", equipos);    	
	    	model.addAttribute("usuario", usuario);
	    	return "temps/list";
    	}
    	else{
    		return "403";
        }
	}
	
	/**
     * Retorna una lista de temps. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
	 * @throws ParseException 
     */
    @RequestMapping(value = "temps", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<TempRecord> fetchTempsJson(@RequestParam(value = "equipCode", required = true) String equipCode,
    		@RequestParam(value = "fecDesde", required = false, defaultValue="") String fecDesde,
    		@RequestParam(value = "fecHasta", required = false, defaultValue="") String fecHasta) throws ParseException {
        logger.info("Obteniendo los temps en JSON");
        List<TempRecord> temps = null; 
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Equipment equipment = equipoService.getEquipment(equipCode, usuario.getUsername());
        if (equipment!=null){
        	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        	Date date1 = null;
            Date date2 = null;
            if (!fecDesde.equals("")) date1 = formatter.parse(fecDesde);
    		if (!fecHasta.equals("")) date2 = formatter.parse(fecHasta);
        	temps = tempRecordService.getTempRecords(equipCode, date1, date2);
        }
        return temps;	
    }
    
    @RequestMapping(value = "viewTemp/{tempCode}/", method = RequestMethod.GET)
    public String initView(Model model, @PathVariable(value = "tempCode") String tempCode){
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	TempRecord temp = tempRecordService.getTempRecord(tempCode, usuario.getUsername());
    	if (temp!=null){
    		model.addAttribute("temp", temp);
        	List<AuditTrail> bitacoraTemp = auditTrailService.getBitacora(tempCode);
        	model.addAttribute("bitacora", bitacoraTemp);
        	model.addAttribute("usuario", usuario);
    		return "temps/viewData";
    	}
    	else{
    		return "404";
        }
    }   
    
    @RequestMapping(value = "newTemp", method = RequestMethod.GET)
	public String initCreation(Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<Equipment> equipos = equipoService.getActiveEquipos(usuario.getUserCenter().getCenterCode());    	
    	model.addAttribute("equipos", equipos);    	
    	model.addAttribute("usuario", usuario);
		return "temps/enterForm";
	}
    
    @RequestMapping(value = "editTemp/{tempCode}/", method = RequestMethod.GET)
    public String initEdition(Model model, @PathVariable(value = "tempCode") String tempCode){
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	TempRecord temp = tempRecordService.getTempRecord(tempCode, usuario.getUsername());
    	if (temp!=null){
    		model.addAttribute("temp", temp);
    		List<Equipment> equipos = equipoService.getActiveEquipos(usuario.getUserCenter().getCenterCode());
        	model.addAttribute("equipos", equipos);
        	model.addAttribute("usuario", usuario);
    		return "temps/enterForm";
    	}
    	else{
    		return "404";
        }
    }    
    
    
    @RequestMapping( value="saveTemp", method=RequestMethod.POST)
	public ResponseEntity<String> processRoomForm( @RequestParam(value="tempCode", required=false, defaultValue="" ) String tempCode
			, @RequestParam( value="equipCode", required=true ) String equipCode
			, @RequestParam( value="malEstado", required=false, defaultValue="" ) String malEstado
			, @RequestParam( value="fueraRango", required=false, defaultValue="" ) String fueraRango
			, @RequestParam( value="tempRecord", required=true ) float tempRecord
			, @RequestParam( value="tempDate", required=true ) String tempDate
			, @RequestParam( value="tempObs", required=false, defaultValue="" ) String tempObs
	        )
	{
    	try{
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			TempRecord temp = new TempRecord();
			//Si el tempCode viene en blanco es un nuevo medicion
			if (tempCode.equals("")){
				//Crear un nuevo tempCode
				tempCode = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
				temp.setTempCode(tempCode);
				temp.setRecordUser(usuario.getUsername());
				temp.setRecordDate(new Date());
			}
			//Si el tempCode no viene en blanco hay que editar el medicion
			else{
				//Recupera el medicion de la base de datos
				temp = this.tempRecordService.getTempRecord(tempCode, usuario.getUsername());
			}
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        	Date date1 = formatter.parse(tempDate);
			
			if (malEstado.matches("on")) temp.setMalEstado('1');
			if (fueraRango.matches("on")) temp.setFueraRango('1');
			temp.setTempEquip(this.equipoService.getEquipment(equipCode, usuario.getUsername()));
			temp.setTempDate(date1);
			temp.setTempRecord(tempRecord);
			temp.setTempObs(tempObs);
			//Actualiza el medicion
			this.tempRecordService.saveTempRecord(temp);
			return createJsonResponse(temp);
    	}
    	catch (DataIntegrityViolationException e){
    		String message = e.getMostSpecificCause().getMessage();
    		Gson gson = new Gson();
    	    String json = gson.toJson(message);
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
		
	}       

	/**
     * Custom handler for enabling/disabling a medicion.
     *
     * @param tempCode the ID of the medicion
     * @return a String
     */
    @RequestMapping("/act/{accion}/{tempCode}/")
    public String habDeshabTemp(@PathVariable("tempCode") String tempCode, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	char estado;
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/temps/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("tempHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/temps/viewTemp/{tempCode}/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("tempHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/temps/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("tempDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/temps/viewTemp/{tempCode}/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("tempDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	TempRecord tempaEditar = this.tempRecordService.getTempRecord(tempCode);
    	if(tempaEditar==null){
    		redirecTo="404";
		}
		else{
	    	tempaEditar.setPasive(estado);
	    	this.tempRecordService.saveTempRecord(tempaEditar);
	    	redirectAttributes.addFlashAttribute("nombreTemp", tempaEditar.getTempCode());
		}
    	return redirecTo;	
    }	
    
    private ResponseEntity<String> createJsonResponse( Object o )
	{
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson(o);
	    return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
	}
	
}
