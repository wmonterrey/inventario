package ni.org.ics.lab.inventario.web.controller.admin;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Equipment;
import ni.org.ics.lab.inventario.domain.Rack;
import ni.org.ics.lab.inventario.domain.Room;
import ni.org.ics.lab.inventario.domain.audit.AuditTrail;
import ni.org.ics.lab.inventario.language.MessageResource;
import ni.org.ics.lab.inventario.service.AuditTrailService;
import ni.org.ics.lab.inventario.service.CuartoService;
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
@RequestMapping("/admin/equips/*")
public class AdminEquiposController {
	private static final Logger logger = LoggerFactory.getLogger(AdminEquiposController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="equipoService")
	private EquipoService equipoService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="cuartoService")
	private CuartoService cuartoService;
	@Resource(name="rackService")
	private RackService rackService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerEquipos(Model model) throws ParseException { 	
    	logger.debug("Mostrando Equipos en JSP");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
	    	List<Equipment> equipos = equipoService.getEquipos(usuario.getUserCenter().getCenterCode());
	    	model.addAttribute("equipos", equipos);    	
	    	model.addAttribute("usuario", usuario);
	    	return "admin/equips/list";
    	}
    	else{
    		return "403";
        }
	}
    
    @RequestMapping(value = "viewEquip/{equipCode}/", method = RequestMethod.GET)
    public String initView(Model model, @PathVariable(value = "equipCode") String equipCode){
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
	    	Equipment equipment = equipoService.getEquipment(equipCode, usuario.getUsername());
	    	if (equipment!=null){
	    		model.addAttribute("equipment", equipment);
	        	List<AuditTrail> bitacoraEquipo = auditTrailService.getBitacora(equipCode);
	        	model.addAttribute("bitacora", bitacoraEquipo);
	        	model.addAttribute("usuario", usuario);
	        	List<Rack> racks = rackService.getActiveRacks(equipCode);
	        	model.addAttribute("totalespacios", equipment.getEquipCapacity());
	        	model.addAttribute("espaciosusados", racks.size());
	        	model.addAttribute("racks", racks);
	    		return "admin/equips/viewData";
	    	}
	    	else{
	    		return "404";
	        }
    	}
    	else{
    		return "403";
        }
    }   
    
    @RequestMapping(value = "newEquip", method = RequestMethod.GET)
	public String initCreation(Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
	    	model.addAttribute("usuario", usuario);
	    	List<MessageResource> equipTypeCat = this.messageResourceService.getCatalogo("equipTypeCat"); 
	    	model.addAttribute("tiposequipo", equipTypeCat);
	    	List<MessageResource> equipUseCat = this.messageResourceService.getCatalogo("equipUseCat"); 
	    	model.addAttribute("usosequipo", equipUseCat);
	    	List <Room> rooms = this.cuartoService.getCuartosActivosCentro(usuario.getUserCenter().getCenterCode());
	    	model.addAttribute("cuartos", rooms);
			return "admin/equips/enterForm";
    	}
    	else{
    		return "403";
        }
	}
    
    /**
     * Retorna un equipo. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
	 * @throws ParseException 
     */
    @RequestMapping(value = "equipo", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Equipment fetchEquipmentJson(@RequestParam(value = "equipCode", required = true) String equipCode) throws ParseException {
        logger.info("Obteniendo el equipo en JSON");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Equipment equipment = equipoService.getEquipment(equipCode, usuario.getUsername());
        return equipment;	
    }
    
    
    @RequestMapping(value = "editEquip/{equipCode}/", method = RequestMethod.GET)
    public String initEdition(Model model, @PathVariable(value = "equipCode") String equipCode){
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
	    	Equipment equipment = equipoService.getEquipment(equipCode, usuario.getUsername());
	    	if (equipment!=null){
	    		model.addAttribute("equipment", equipment);
	        	model.addAttribute("usuario", usuario);
	        	List<MessageResource> equipTypeCat = this.messageResourceService.getCatalogo("equipTypeCat"); 
	        	model.addAttribute("tiposequipo", equipTypeCat);
	        	List<MessageResource> equipUseCat = this.messageResourceService.getCatalogo("equipUseCat"); 
	        	model.addAttribute("usosequipo", equipUseCat);
	        	List <Room> rooms = this.cuartoService.getCuartosActivosCentro(usuario.getUserCenter().getCenterCode());
	        	model.addAttribute("cuartos", rooms);
	    		return "admin/equips/enterForm";
	    	}
	    	else{
	    		return "404";
	        }
    	}
    	else{
    		return "403";
        }
    }    
    
    
    @RequestMapping( value="saveEquip", method=RequestMethod.POST)
	public ResponseEntity<String> processEquipmentForm( @RequestParam(value="equipCode", required=false, defaultValue="" ) String equipCode
			, @RequestParam( value="equipName", required=true ) String equipName
			, @RequestParam( value="equipUse", required=true ) String equipUse
			, @RequestParam( value="equipType", required=true ) String equipType
			, @RequestParam( value="equipRoom", required=true ) String equipRoom
			, @RequestParam( value="equipRows", required=true ) int equipRows
			, @RequestParam( value="equipColumns", required=true ) int equipColumns
			, @RequestParam( value="equipCapacity", required=true ) int equipCapacity
			, @RequestParam( value="equipTempMin", required=true ) float equipTempMin
			, @RequestParam( value="equipTempMax", required=true ) float equipTempMax
			, @RequestParam( value="equipPriority", required=true ) int equipPriority
			, @RequestParam( value="equipBrand", required=false ) String equipBrand
			, @RequestParam( value="equipModel", required=false ) String equipModel
			, @RequestParam( value="equipSerie", required=false ) String equipSerie
			, @RequestParam( value="equipResp", required=false ) String equipResp
			, @RequestParam( value="equipObs", required=false ) String equipObs			
	        )
	{
    	try{
    		String equipNameActual = "";
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			Room room = this.cuartoService.getCuarto(equipRoom);
			if(this.equipoService.validateCenter(room,usuario.getUsername())){
				Equipment equipment = new Equipment();
				//Si el equipCode viene en blanco es un nuevo equipo
				if (equipCode.equals("")){
					//Crear un nuevo equipCode
					equipCode = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
					equipment.setEquipCode(equipCode);
					equipment.setRecordUser(usuario.getUsername());
					equipment.setRecordDate(new Date());
				}
				//Si el equipCode no viene en blanco hay que editar el equipo
				else{
					//Recupera el equipo de la base de datos
					equipment = this.equipoService.getEquipment(equipCode,usuario.getUsername());
					equipNameActual = equipment.getEquipName();
				}
				if (equipCode.equals("") || !equipNameActual.equals(equipName)){
					//Validar que el nombre del equipo no exista en el centro
					Equipment equipmentValidation = equipoService.getEquipmentxName(equipName, room.getRoomCenter().getCenterCode());
					if(equipmentValidation!=null){
						MessageResource repetido = messageResourceService.getMensaje("equipDuplicated");
						Gson gson = new Gson();
			    	    String json = gson.toJson(repetido.getSpanish());
						return new ResponseEntity<String>( json, HttpStatus.CREATED);
			    	}
				}
				equipment.setEquipName(equipName);
				equipment.setEquipUse(equipUse);
				equipment.setEquipType(equipType);
				equipment.setEquipRoom(room);
				equipment.setEquipRows(equipRows);
				equipment.setEquipColumns(equipColumns);
				equipment.setEquipCapacity(equipCapacity);
				equipment.setEquipTempMin(equipTempMin);
				equipment.setEquipTempMax(equipTempMax);
				equipment.setEquipPriority(equipPriority);
				equipment.setEquipBrand(equipBrand);
				equipment.setEquipModel(equipModel);
				equipment.setEquipSerie(equipSerie);
				equipment.setEquipResp(equipResp);
				equipment.setEquipObs(equipObs);
				//Guardar el equipo
				this.equipoService.saveEquipment(equipment);
				return createJsonResponse(equipment);
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
     * Custom handler for enabling/disabling a equipment.
     *
     * @param equipCode the ID of the equipment
     * @return a String
     */
    @RequestMapping("/act/{accion}/{equipCode}/")
    public String habDeshabEquipment(@PathVariable("equipCode") String equipCode, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	List<Rack> racks = rackService.getActiveRacks(equipCode);
    	if(!racks.isEmpty()){
    		redirecTo = "redirect:/admin/equips/viewEquip/{equipCode}/";
    		redirectAttributes.addFlashAttribute("equipNotEmpty", true);
    		return redirecTo;
    	}
    	char estado;
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/admin/equips/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("equipoHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/admin/equips/viewEquip/{equipCode}/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("equipoHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/admin/equips/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("equipoDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/admin/equips/viewEquip/{equipCode}/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("equipoDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Equipment equipoaEditar = this.equipoService.getEquipment(equipCode,usuario.getUsername());
    	if(equipoaEditar==null){
    		redirecTo="404";
		}
		else{
	    	equipoaEditar.setPasive(estado);
	    	this.equipoService.saveEquipment(equipoaEditar);
	    	redirectAttributes.addFlashAttribute("nombreEquipo", equipoaEditar.getEquipName());
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
    
    
    /**
     * Retorna una lista de posiciones disponibles en el equipo. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de integers
	 * @throws ParseException 
     */
    @RequestMapping(value = "equiposactivos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Equipment> fetchEquiposJson(@RequestParam(value = "boxAlicUse", required = true) String boxAlicUse
    		, @RequestParam( value="boxTemp", required=true) float boxTemp) throws ParseException {
        logger.info("Obteniendo los equipos en JSON");
        List<Equipment> equipos = null; 
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        equipos = equipoService.getActiveEquiposUseTemp(usuario.getUserCenter().getCenterCode(), boxAlicUse, boxTemp);
        return equipos;	
    }
	
}
