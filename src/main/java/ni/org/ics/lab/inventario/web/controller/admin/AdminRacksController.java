package ni.org.ics.lab.inventario.web.controller.admin;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Box;
import ni.org.ics.lab.inventario.domain.Equipment;
import ni.org.ics.lab.inventario.domain.Rack;
import ni.org.ics.lab.inventario.domain.audit.AuditTrail;
import ni.org.ics.lab.inventario.language.MessageResource;
import ni.org.ics.lab.inventario.service.AuditTrailService;
import ni.org.ics.lab.inventario.service.BoxService;
import ni.org.ics.lab.inventario.service.EquipoService;
import ni.org.ics.lab.inventario.service.MessageResourceService;
import ni.org.ics.lab.inventario.service.RackService;
import ni.org.ics.lab.inventario.service.UsuarioService;
import ni.org.ics.lab.inventario.users.model.UserSistema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/admin/racks/*")
public class AdminRacksController {
	private static final Logger logger = LoggerFactory.getLogger(AdminRacksController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="equipoService")
	private EquipoService equipoService;
	@Resource(name="boxService")
	private BoxService boxService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="rackService")
	private RackService rackService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerRacks(Model model) throws ParseException { 	
    	logger.debug("Mostrando Racks en JSP");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
	    	List<Equipment> equipos = equipoService.getEquipos(usuario.getUserCenter().getCenterCode());
	    	model.addAttribute("equipos", equipos);    	
	    	model.addAttribute("usuario", usuario);
	    	return "admin/racks/list";
    	}
    	else{
    		return "403";
        }
	}
	
	/**
     * Retorna una lista de racks. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
	 * @throws ParseException 
     */
    @RequestMapping(value = "racks", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Rack> fetchRacksJson(@RequestParam(value = "equipCode", required = true) String equipCode) throws ParseException {
        logger.info("Obteniendo los racks en JSON");
        List<Rack> racks = null; 
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Equipment equipment = equipoService.getEquipment(equipCode, usuario.getUsername());
        if (equipment!=null){
        	racks = rackService.getRacks(equipCode);
        }
        return racks;	
    }
    
    /**
     * Retorna una lista de racks. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
	 * @throws ParseException 
     */
    @RequestMapping(value = "racksxname", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Rack> fetchRacksxNameJson(@RequestParam(value = "rackName", required = true) String rackName) throws ParseException {
        logger.info("Obteniendo los racks en JSON");
        List<Rack> racks = null; 
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        racks = rackService.getRacksxName(rackName,usuario.getUserCenter().getCenterCode());
        return racks;	
    }
    
    /**
     * Retorna una lista de racks. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
	 * @throws ParseException 
     */
    @RequestMapping(value = "racksactivos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Rack> fetchRacksActivosJson(@RequestParam(value = "equipCode", required = true) String equipCode) throws ParseException {
        logger.info("Obteniendo los racks en JSON");
        List<Rack> racks = null; 
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Equipment equipment = equipoService.getEquipment(equipCode, usuario.getUsername());
        if (equipment!=null){
        	racks = rackService.getActiveRacks(equipCode);
        }
        return racks;	
    }
    
    @RequestMapping(value = "viewRack/{rackCode}/", method = RequestMethod.GET)
    public String initView(Model model, @PathVariable(value = "rackCode") String rackCode){
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Rack rack = rackService.getRack(rackCode, usuario.getUsername());
    	if (rack!=null){
    		model.addAttribute("rack", rack);
    		List<AuditTrail> bitacoraRack = auditTrailService.getBitacora(rackCode);
    		model.addAttribute("bitacora", bitacoraRack);
    		model.addAttribute("usuario", usuario);
    		List<Box> boxes = boxService.getActiveBoxes(rackCode);
    		model.addAttribute("totalespacios", rack.getRackCapacity());
    		model.addAttribute("espaciosusados", boxes.size());
    		model.addAttribute("boxes", boxes);
    		return "admin/racks/viewData";
    	}	
		else{
    		return "404";
        }	
    }   
    
    @RequestMapping(value = "newRack", method = RequestMethod.GET)
	public String initCreation(Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
    	List<Equipment> equipos = equipoService.getActiveEquipos(usuario.getUserCenter().getCenterCode());
    	model.addAttribute("equipos", equipos);
    	return "admin/racks/enterForm";
	}
    
    /**
     * Retorna una lista de posiciones disponibles en el equipo. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de integers
	 * @throws ParseException 
     */
    @RequestMapping(value = "positions", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<String> fetchPositionsJson(@RequestParam(value = "equipCode", required = true) String equipCode) throws ParseException {
        logger.info("Obteniendo los racks en JSON");
        List<String> posiciones = null; 
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Equipment equipment = equipoService.getEquipment(equipCode, usuario.getUsername());
        if (equipment!=null){
        	posiciones = rackService.getAvailablePos(equipCode);
        }
        return posiciones;	
    }
    
    @RequestMapping(value = "editRack/{rackCode}/", method = RequestMethod.GET)
    public String initEdition(Model model, @PathVariable(value = "rackCode") String rackCode){
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
    		Rack rackToEdit = rackService.getRack(rackCode, usuario.getUsername());
	    	if (rackToEdit!=null){
	    		model.addAttribute("rack", rackToEdit);
	        	model.addAttribute("usuario", usuario);
	        	List<String> posiciones = rackService.getAvailablePos(rackToEdit.getRackEquip().getEquipCode());
	        	model.addAttribute("posiciones", posiciones);
	        	List<Equipment> equipos = equipoService.getActiveEquipos(usuario.getUserCenter().getCenterCode());
	        	model.addAttribute("equipos", equipos);
	    		return "admin/racks/enterForm";
	    	}
	    	else{
	    		return "404";
	        }
    	}
    	else{
    		return "403";
        }
    }    
    
    
    @RequestMapping( value="saveRack", method=RequestMethod.POST)
	public ResponseEntity<String> processRackForm( @RequestParam(value="rackCode", required=false, defaultValue="" ) String rackCode
			, @RequestParam( value="rackEquip", required=true ) String rackEquip
			, @RequestParam( value="rackPosition", required=true ) String rackPosition
			, @RequestParam( value="rackName", required=true ) String rackName
			, @RequestParam( value="rackObs", required=false ) String rackObs
			, @RequestParam( value="rackRows", required=true ) int rackRows
			, @RequestParam( value="rackColumns", required=true ) int rackColumns
			, @RequestParam( value="rackCapacity", required=true ) int rackCapacity
			, @RequestParam( value="rackPriority", required=true ) int rackPriority
	        )
	{
    	try{
    		String rackNameActual = "";
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			Equipment equipment = equipoService.getEquipment(rackEquip, usuario.getUsername());
			if(equipment!=null){
				Rack rack = new Rack();
				//Si el rackCode viene en blanco es un nuevo equipo
				if (rackCode.equals("")){
					//Crear un nuevo rackCode
					rackCode = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
					rack.setRackCode(rackCode);
					rack.setRecordUser(usuario.getUsername());
					rack.setRecordDate(new Date());
				}
				//Si el rackCode no viene en blanco hay que editar el equipo
				else{
					//Recupera el rack de la base de datos
					rack = this.rackService.getRack(rackCode, usuario.getUsername());
					rackNameActual = rack.getRackName();
				}
				if (rackCode.equals("") || !rackNameActual.equals(rackName)){
					//Validar que el nombre del equipo no exista en el centro
					Rack rackValidation = rackService.getRackxName(rackName, equipment.getEquipRoom().getRoomCenter().getCenterCode());
					if(rackValidation!=null){
						MessageResource repetido = messageResourceService.getMensaje("rackDuplicated");
						Gson gson = new Gson();
			    	    String json = gson.toJson(repetido.getSpanish()+ " Equip: " + rackValidation.getRackEquip().getEquipName());
						return new ResponseEntity<String>( json, HttpStatus.CREATED);
			    	}
				}
				rack.setRackName(rackName);
				rack.setRackPosition(Integer.valueOf(rackPosition));
				rack.setRackEquip(equipment);
				rack.setRackCapacity(rackCapacity);
				rack.setRackPriority(rackPriority);
				rack.setRackRows(rackRows);
				rack.setRackColumns(rackColumns);
				rack.setRackObs(rackObs);
				//Guardar el rack
				this.rackService.saveRack(rack);
				return createJsonResponse(rack);
			}
			else{
				Gson gson = new Gson();
	    	    String json = gson.toJson("403 Forbidden");
				return new ResponseEntity<String>( json, HttpStatus.CREATED);
			}
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
		
	}       

	/**
     * Custom handler for enabling/disabling a rack.
     *
     * @param equipCode the ID of the rack
     * @return a String
     */
    @RequestMapping("/act/{accion}/{rackCode}/")
    public String habDeshabEquipment(@PathVariable("rackCode") String rackCode, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	char estado;
    	List<Box> boxes = boxService.getActiveBoxes(rackCode);
    	if(!boxes.isEmpty()){
    		redirecTo = "redirect:/admin/racks/viewRack/{rackCode}/";
    		redirectAttributes.addFlashAttribute("rackNotEmpty", true);
    		return redirecTo;
    	}
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/admin/racks/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("rackHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/admin/racks/viewRack/{rackCode}/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("rackHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/admin/racks/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("rackDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/admin/racks/viewRack/{rackCode}/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("rackDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Rack rackToEditar = rackService.getRack(rackCode, usuario.getUsername());
    	if(rackToEditar==null){
    		redirecTo="404";
		}
		else{
			rackToEditar.setPasive(estado);
	    	this.rackService.saveRack(rackToEditar);
	    	redirectAttributes.addFlashAttribute("nombreRack", rackToEditar.getRackName());
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
