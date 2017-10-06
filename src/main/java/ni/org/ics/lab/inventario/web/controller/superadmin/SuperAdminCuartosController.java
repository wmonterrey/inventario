package ni.org.ics.lab.inventario.web.controller.superadmin;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Center;
import ni.org.ics.lab.inventario.domain.Equipment;
import ni.org.ics.lab.inventario.domain.Room;
import ni.org.ics.lab.inventario.domain.audit.AuditTrail;
import ni.org.ics.lab.inventario.service.AuditTrailService;
import ni.org.ics.lab.inventario.service.CentroService;
import ni.org.ics.lab.inventario.service.CuartoService;
import ni.org.ics.lab.inventario.service.EquipoService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

/**
 * Controlador web de peticiones relacionadas a super administrador
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/super/rooms/*")
public class SuperAdminCuartosController {
	private static final Logger logger = LoggerFactory.getLogger(SuperAdminCuartosController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="cuartoService")
	private CuartoService cuartoService;
	@Resource(name="equipoService")
	private EquipoService equipoService;
	@Resource(name="centroService")
	private CentroService centroService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerCuartos(Model model) throws ParseException { 	
    	logger.debug("Mostrando Cuartos en JSP");
    	List<Room> cuartos = cuartoService.getCuartos();
    	model.addAttribute("cuartos", cuartos);
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
    	return "super/rooms/list";
	}
    
    @RequestMapping(value = "viewRoom/{roomCode}/", method = RequestMethod.GET)
    public String initView(Model model, @PathVariable(value = "roomCode") String roomCode){
    	Room room = cuartoService.getCuarto(roomCode);
    	if (room!=null){
    		model.addAttribute("room", room);
        	List<AuditTrail> bitacoraCuarto = auditTrailService.getBitacora(roomCode);
        	model.addAttribute("bitacora", bitacoraCuarto);
        	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	model.addAttribute("usuario", usuario);
    		return "super/rooms/viewData";
    	}
    	else{
    		return "404";
        }
    }   
    
    @RequestMapping(value = "newRoom", method = RequestMethod.GET)
	public String initCreation(Model model) {
    	List<Center> centros = centroService.getCentrosActivos();
    	model.addAttribute("centros", centros);
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
		return "super/rooms/enterForm";
	}
    
    @RequestMapping(value = "editRoom/{roomCode}/", method = RequestMethod.GET)
    public String initEdition(Model model, @PathVariable(value = "roomCode") String roomCode){
    	Room room = cuartoService.getCuarto(roomCode);
    	if (room!=null){
    		model.addAttribute("room", room);
        	List<Center> centros = centroService.getCentrosActivos();
        	model.addAttribute("centros", centros);
        	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	model.addAttribute("usuario", usuario);
    		return "super/rooms/enterForm";
    	}
    	else{
    		return "404";
        }
    }    
    
    
    @RequestMapping( value="saveRoom", method=RequestMethod.POST)
	public ResponseEntity<String> processRoomForm( @RequestParam(value="roomCode", required=false, defaultValue="" ) String roomCode
			, @RequestParam( value="roomName", required=true ) String roomName
			, @RequestParam( value="roomCenter", required=true ) String roomCenter
			, @RequestParam( value="roomObs", required=false, defaultValue="" ) String roomObs
	        )
	{
    	try{
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			Room room = new Room();
			//Si el roomCode viene en blanco es un nuevo cuarto
			if (roomCode.equals("")){
				//Crear un nuevo roomCode
				roomCode = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
				room.setRoomCode(roomCode);
				room.setRecordUser(usuario.getUsername());
				room.setRecordDate(new Date());
				room.setRoomName(roomName);
				room.setRoomCenter(centroService.getCentro(roomCenter));
				room.setRoomObs(roomObs);
				//Guardar el nuevo cuarto
				this.cuartoService.saveRoom(room);
			}
			//Si el roomCode no viene en blanco hay que editar el cuarto
			else{
				//Recupera el centro de la base de datos
				room = this.cuartoService.getCuarto(roomCode);
				room.setRoomName(roomName);
				room.setRoomCenter(centroService.getCentro(roomCenter));
				room.setRoomObs(roomObs);
				//Actualiza el cuarto
				this.cuartoService.saveRoom(room);
			}
			return createJsonResponse(room);
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
     * Custom handler for enabling/disabling a room.
     *
     * @param roomCode the ID of the room
     * @return a String
     */
    @RequestMapping("/act/{accion}/{roomCode}/")
    public String habDeshabRoom(@PathVariable("roomCode") String roomCode, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	List<Equipment> equips = equipoService.getActiveEquiposCuarto(roomCode);
    	if(!equips.isEmpty()){
    		redirecTo = "redirect:/super/rooms/";
    		redirectAttributes.addFlashAttribute("roomNotEmpty", true);
    		return redirecTo;
    	}
    	char estado;
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/super/rooms/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("cuartoHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/super/rooms/viewRoom/{roomCode}/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("cuartoHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/super/rooms/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("cuartoDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/super/rooms/viewRoom/{roomCode}/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("cuartoDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	Room cuartoaEditar = this.cuartoService.getCuarto(roomCode);
    	if(cuartoaEditar==null){
    		redirecTo="404";
		}
		else{
	    	cuartoaEditar.setPasive(estado);
	    	this.cuartoService.saveRoom(cuartoaEditar);
	    	redirectAttributes.addFlashAttribute("nombreCuarto", cuartoaEditar.getRoomName());
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
