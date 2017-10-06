package ni.org.ics.lab.inventario.web.controller.admin;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Aliquot;
import ni.org.ics.lab.inventario.domain.Box;
import ni.org.ics.lab.inventario.domain.Equipment;
import ni.org.ics.lab.inventario.domain.Rack;
import ni.org.ics.lab.inventario.domain.Study;
import ni.org.ics.lab.inventario.domain.audit.AuditTrail;
import ni.org.ics.lab.inventario.domain.relationships.AlicTypeStudy;
import ni.org.ics.lab.inventario.domain.relationships.StudyCenter;
import ni.org.ics.lab.inventario.language.MessageResource;
import ni.org.ics.lab.inventario.service.AliquotService;
import ni.org.ics.lab.inventario.service.AliquotTypeService;
import ni.org.ics.lab.inventario.service.AuditTrailService;
import ni.org.ics.lab.inventario.service.BoxService;
import ni.org.ics.lab.inventario.service.EquipoService;
import ni.org.ics.lab.inventario.service.EstudioService;
import ni.org.ics.lab.inventario.service.MessageResourceService;
import ni.org.ics.lab.inventario.service.RackService;
import ni.org.ics.lab.inventario.service.StudyCenterService;
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
@RequestMapping("/admin/boxes/*")
public class AdminBoxesController {
	private static final Logger logger = LoggerFactory.getLogger(AdminBoxesController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="equipoService")
	private EquipoService equipoService;
	@Resource(name="estudioService")
	private EstudioService estudioService;
	@Resource(name="rackService")
	private RackService rackService;
	@Resource(name="boxService")
	private BoxService boxService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="studyCenterService")
	private StudyCenterService studyCenterService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="aliquotTypeService")
	private AliquotTypeService aliquotTypeService;
	@Resource(name="aliquotService")
	private AliquotService aliquotService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerBoxes(Model model) throws ParseException { 	
    	logger.debug("Mostrando Cajas en JSP");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
	    	List<Equipment> equipos = equipoService.getEquipos(usuario.getUserCenter().getCenterCode());
	    	model.addAttribute("equipos", equipos);
	    	model.addAttribute("usuario", usuario);
	    	return "admin/boxes/list";
    	}
    	else{
    		return "403";
        }
	}
	
	/**
     * Retorna una lista de cajas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
	 * @throws ParseException 
     */
    @RequestMapping(value = "boxes", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Box> fetchBoxesJson(@RequestParam(value = "rackCode", required = true) String rackCode) throws ParseException {
        logger.info("Obteniendo los boxes en JSON");
        List<Box> boxes = null; 
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Rack rack = rackService.getRack(rackCode, usuario.getUsername());
        if (rack!=null){
        	boxes = boxService.getBoxes(rackCode);
        }
        return boxes;	
    }
    
    /**
     * Retorna una lista de cajas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
	 * @throws ParseException 
     */
    @RequestMapping(value = "boxesxname", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Box> fetchBoxesxNameJson(@RequestParam(value = "boxName", required = true) String boxName) throws ParseException {
        logger.info("Obteniendo los boxes en JSON");
        List<Box> boxes = null; 
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        boxes = boxService.getBoxesxName(boxName,usuario.getUserCenter().getCenterCode());
        return boxes;	
    }
    
    @RequestMapping(value = "viewBox/{boxCode}/", method = RequestMethod.GET)
    public String initView(Model model, @PathVariable(value = "boxCode") String boxCode){
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Box box = boxService.getBox(boxCode, usuario.getUsername());
    	if (box!=null){
    		model.addAttribute("box", box);
    		List<AuditTrail> bitacoraBox = auditTrailService.getBitacora(boxCode);
    		model.addAttribute("bitacora", bitacoraBox);
    		model.addAttribute("totalespacios", box.getBoxCapacity());
    		List<Aliquot> alics = aliquotService.getActiveAliquots(boxCode);
    		model.addAttribute("espaciosusados", alics.size());
    		model.addAttribute("alics", alics);
    		model.addAttribute("usuario", usuario);
    		return "admin/boxes/viewData";
    	}	
		else{
    		return "404";
        }	
    }   
    
    @RequestMapping(value = "newBox", method = RequestMethod.GET)
	public String initCreation(Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
    	List<StudyCenter> estudios = studyCenterService.getActiveStudyCenter(usuario.getUserCenter().getCenterCode());
    	model.addAttribute("estudios", estudios);
    	List<MessageResource> equipUseCat = this.messageResourceService.getCatalogo("equipUseCat"); 
    	model.addAttribute("usosequipo", equipUseCat);
    	List<MessageResource> tipoResultadoCat = this.messageResourceService.getCatalogo("tipoResultadoCat"); 
    	model.addAttribute("resultados", tipoResultadoCat);
    	return "admin/boxes/enterForm";
	}
    
    /**
     * Retorna una lista de posiciones disponibles en el equipo. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de integers
	 * @throws ParseException 
     */
    @RequestMapping(value = "positions", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<String> fetchPositionsJson(@RequestParam(value = "rackCode", required = true) String rackCode) throws ParseException {
        logger.info("Obteniendo las posiciones en JSON");
        List<String> posiciones = null; 
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Rack rack = rackService.getRack(rackCode, usuario.getUsername());
        if (rack!=null){
        	posiciones = boxService.getAvailablePos(rackCode);
        }
        return posiciones;	
    }
    
    @RequestMapping(value = "editBox/{boxCode}/", method = RequestMethod.GET)
    public String initEdition(Model model, @PathVariable(value = "boxCode") String boxCode){
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
    		Box boxToEdit = boxService.getBox(boxCode, usuario.getUsername());
	    	if (boxToEdit!=null){
	    		model.addAttribute("box", boxToEdit);
	        	model.addAttribute("usuario", usuario);
	        	List<StudyCenter> estudios = studyCenterService.getActiveStudyCenter(usuario.getUserCenter().getCenterCode());
	        	model.addAttribute("estudios", estudios);
	        	List<String> posiciones = boxService.getAvailablePos(boxToEdit.getBoxRack().getRackCode());
	        	model.addAttribute("posiciones", posiciones);
	        	List<Equipment> equipos = equipoService.getActiveEquiposUseTemp(usuario.getUserCenter().getCenterCode(), boxToEdit.getBoxAlicUse(), boxToEdit.getBoxTemp());
	        	model.addAttribute("equipos", equipos);
	        	List<Rack> racks = rackService.getActiveRacks(boxToEdit.getBoxRack().getRackEquip().getEquipCode());
	        	model.addAttribute("racks", racks);
	        	List<MessageResource> equipUseCat = this.messageResourceService.getCatalogo("equipUseCat"); 
	        	model.addAttribute("usosequipo", equipUseCat);
	        	List<MessageResource> tipoResultadoCat = this.messageResourceService.getCatalogo("tipoResultadoCat"); 
	        	model.addAttribute("resultados", tipoResultadoCat);
	        	List<AlicTypeStudy> alicuotas = aliquotTypeService.getActiveAliquotTypes(boxToEdit.getBoxStudy().getStudyCode(), boxToEdit.getBoxAlicUse(), boxToEdit.getBoxTemp());
	        	model.addAttribute("tiposalicuota", alicuotas);
	    		return "admin/boxes/enterForm";
	    	}
	    	else{
	    		return "404";
	        }
    	}
    	else{
    		return "403";
        }
    }    
    
    
    @RequestMapping( value="saveBox", method=RequestMethod.POST)
	public ResponseEntity<String> processBoxForm( @RequestParam(value="boxCode", required=false, defaultValue="" ) String boxCode
			, @RequestParam( value="boxStudy", required=true ) String boxStudy
			, @RequestParam( value="boxAlicUse", required=true ) String boxAlicUse
			, @RequestParam( value="boxTemp", required=true ) float boxTemp
			, @RequestParam( value="boxAlicType", required=true ) String boxAlicType
			, @RequestParam( value="rackEquip", required=true ) String rackEquip
			, @RequestParam( value="boxRack", required=true ) String boxRack
			, @RequestParam( value="boxPosition", required=true ) String rackPosition
			, @RequestParam( value="boxName", required=true ) String boxName
			, @RequestParam( value="boxResult", required=true ) String boxResult
			, @RequestParam( value="boxRows", required=true ) int boxRows
			, @RequestParam( value="boxColumns", required=true ) int boxColumns
			, @RequestParam( value="boxCapacity", required=true ) int boxCapacity
			, @RequestParam( value="boxPriority", required=true ) int boxPriority
			, @RequestParam( value="boxObs", required=false, defaultValue = "") String boxObs
	        )
	{
    	try{
    		String boxNameActual = "";
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			Rack rack = rackService.getRack(boxRack, usuario.getUsername());
			if(rack!=null){
				Box box = new Box();
				//Si el boxCode viene en blanco es una nueva caja
				if (boxCode.equals("")){
					//Crear un nuevo boxCode
					boxCode = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
					box.setBoxCode(boxCode);
					box.setRecordUser(usuario.getUsername());
					box.setRecordDate(new Date());
				}
				//Si el boxCode no viene en blanco hay que editar la caja
				else{
					//Recupera la caja de la base de datos
					box = this.boxService.getBox(boxCode, usuario.getUsername());
					boxNameActual = box.getBoxName();
				}
				if (boxCode.equals("") || !boxNameActual.equals(boxName)){
					//Validar que el nombre del caja no exista en el centro
					Box boxValidation = boxService.getBoxxName(boxName, rack.getRackEquip().getEquipRoom().getRoomCenter().getCenterCode());
					if(boxValidation!=null){
						MessageResource repetido = messageResourceService.getMensaje("boxDuplicated");
						Gson gson = new Gson();
			    	    String json = gson.toJson(repetido.getSpanish()+ " Equip: " + boxValidation.getBoxRack().getRackEquip().getEquipName()
			    	    		+ " Rack: " + boxValidation.getBoxRack().getRackName());
						return new ResponseEntity<String>( json, HttpStatus.CREATED);
			    	}
				}
				box.setBoxName(boxName);
				box.setBoxPosition(Integer.valueOf(rackPosition));
				box.setBoxRack(rack);
				box.setBoxRows(boxRows);
				box.setBoxColumns(boxColumns);
				box.setBoxCapacity(boxCapacity);
				box.setBoxPriority(boxPriority);
				box.setBoxObs(boxObs);
				Study study = estudioService.getStudy(boxStudy);
				box.setBoxStudy(study);
				box.setBoxAlicType(boxAlicType);
				box.setBoxAlicUse(boxAlicUse);
				box.setBoxTemp(boxTemp);
				box.setBoxResult(boxResult);
				//Guardar la caja
				this.boxService.saveBox(box);
				return createJsonResponse(box);
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
     * Custom handler for enabling/disabling a box.
     *
     * @param boxCode the ID of the box
     * @return a String
     */
    @RequestMapping("/act/{accion}/{boxCode}/")
    public String habDeshabCajat(@PathVariable("boxCode") String boxCode, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	char estado;
    	List<Aliquot> alics = this.aliquotService.getAliquots(boxCode);
    	if(!alics.isEmpty()){
    		redirecTo = "redirect:/admin/boxes/viewBox/{boxCode}/";
    		redirectAttributes.addFlashAttribute("boxNotEmpty", true);
    		return redirecTo;
    	}
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/admin/boxes/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("cajaHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/admin/boxes/viewBox/{boxCode}/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("cajaHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/admin/boxes/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("cajaDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/admin/boxes/viewBox/{boxCode}/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("cajaDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	Box boxToEditar = boxService.getBox(boxCode, usuario.getUsername());
    	if(boxToEditar==null){
    		redirecTo="404";
		}
		else{
			boxToEditar.setPasive(estado);
	    	this.boxService.saveBox(boxToEditar);
	    	redirectAttributes.addFlashAttribute("nombreCaja", boxToEditar.getBoxName());
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
