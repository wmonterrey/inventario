package ni.org.ics.lab.inventario.web.controller.superadmin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Center;
import ni.org.ics.lab.inventario.domain.Room;
import ni.org.ics.lab.inventario.domain.Study;
import ni.org.ics.lab.inventario.domain.audit.AuditTrail;
import ni.org.ics.lab.inventario.domain.relationships.StudyCenter;
import ni.org.ics.lab.inventario.domain.relationships.StudyCenterId;
import ni.org.ics.lab.inventario.domain.relationships.UserCenter;
import ni.org.ics.lab.inventario.domain.relationships.UserCenterId;
import ni.org.ics.lab.inventario.service.AuditTrailService;
import ni.org.ics.lab.inventario.service.CentroService;
import ni.org.ics.lab.inventario.service.CuartoService;
import ni.org.ics.lab.inventario.service.EstudioService;
import ni.org.ics.lab.inventario.service.StudyCenterService;
import ni.org.ics.lab.inventario.service.UserCenterService;
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
@RequestMapping("/super/centers/*")
public class SuperAdminCentrosController {
	private static final Logger logger = LoggerFactory.getLogger(SuperAdminCentrosController.class);
	@Resource(name="centroService")
	private CentroService centroService;
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="estudioService")
	private EstudioService estudioService;
	@Resource(name="userCenterService")
	private UserCenterService userCenterService;
	@Resource(name="cuartoService")
	private CuartoService cuartoService;
	@Resource(name="studyCenterService")
	private StudyCenterService studyCenterService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerCentros(Model model) throws ParseException { 	
    	logger.debug("Mostrando Centros en JSP");
    	List<Center> centros = centroService.getCentros();
    	model.addAttribute("centros", centros);
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
    	return "super/centers/list";
	}
    
    @RequestMapping(value = "viewCenter/{centerCode}/", method = RequestMethod.GET)
    public String initView(Model model, @PathVariable(value = "centerCode") String centerCode){
    	Center center = centroService.getCentro(centerCode);
    	if (center!=null){
    		model.addAttribute("center", center);
        	List<UserCenter> usuarioscentro = this.userCenterService.getAllUsersCenter(centerCode);
        	model.addAttribute("usuarioscentro", usuarioscentro);
        	List<StudyCenter> estudioscentro = this.studyCenterService.getAllStudyCenter(centerCode);
        	model.addAttribute("estudioscentro", estudioscentro);
        	List<AuditTrail> bitacoraCentro = auditTrailService.getBitacora(centerCode);
        	model.addAttribute("bitacora", bitacoraCentro);
        	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	model.addAttribute("usuario", usuario);
    		return "super/centers/viewData";
    	}
    	else{
    		return "404";
        }
    }
    
    @RequestMapping(value = "newCenter", method = RequestMethod.GET)
	public String initCreation(Model model) {
    	List<UserSistema> usuarios = usuarioService.getActiveUsers();
    	model.addAttribute("usuarios", usuarios);
    	List<Study> estudios = estudioService.getActiveEstudios();
    	model.addAttribute("estudios", estudios);
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
		return "super/centers/enterForm";
	}
    
    @RequestMapping(value = "editCenter/{centerCode}/", method = RequestMethod.GET)
    public String initEdition(Model model, @PathVariable(value = "centerCode") String centerCode){
    	Center center = centroService.getCentro(centerCode);
    	if (center!=null){
    		model.addAttribute("center", center);
    		List<UserSistema> usuarios = usuarioService.getActiveUsers();
        	model.addAttribute("usuarios", usuarios);
        	List<Study> estudios = estudioService.getActiveEstudios();
        	model.addAttribute("estudios", estudios);
        	List<UserCenter> usuarioscentro = this.userCenterService.getActiveUsersCenter(centerCode);
        	model.addAttribute("usuarioscentro", usuarioscentro);
        	List<StudyCenter> estudioscentro = this.studyCenterService.getActiveStudyCenter(centerCode);
        	model.addAttribute("estudioscentro", estudioscentro);
        	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	model.addAttribute("usuario", usuario);
    		return "super/centers/enterForm";
    	}
    	else{
    		return "404";
        }
    }
    
	@RequestMapping( value="saveCenter", method=RequestMethod.POST)
	public ResponseEntity<String> processCenterForm( @RequestParam(value="centerCode", required=false, defaultValue="" ) String centerCode
			, @RequestParam( value="centerName", required=true ) String centerName
			, @RequestParam( value="centerContact", required=true ) String centerContact
			, @RequestParam( value="centerAddress", required=true ) String centerAddress
			, @RequestParam( value="centerPhoneNumber", required=false, defaultValue="" ) String centerPhoneNumber
			, @RequestParam( value="centerEmail", required=false, defaultValue="" ) String centerEmail
			, @RequestParam( value="centerObs", required=false, defaultValue="" ) String centerObs
			, @RequestParam( value="users", required=false, defaultValue="" ) List<String> users
			, @RequestParam( value="studies", required=false, defaultValue="" ) List<String> studies
	        )
	{
    	try{
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			Center center = new Center();
			//Si el idCentro viene en blanco es un nuevo centro
			if (centerCode.equals("")){
				//Crear un nuevo idCentro
				centerCode = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
				center.setCenterCode(centerCode);
				center.setRecordUser(usuario.getUsername());
				center.setRecordDate(new Date());
				center.setCenterName(centerName);
				center.setCenterContact(centerContact);
				center.setCenterAddress(centerAddress);
				center.setCenterPhoneNumber(centerPhoneNumber);
				center.setCenterEmail(centerEmail);
				center.setCenterObs(centerObs);
				//Guardar el nuevo centro
				this.centroService.saveCenter(center);
				//Agregar los usuarios seleccionados al centro
				for(String u:users){
					UserCenter uc = new UserCenter();
					uc.setUserCenterId(new UserCenterId(u,centerCode));
					uc.setRecordUser(usuario.getUsername());
					uc.setRecordDate(new Date());
					this.userCenterService.saveUserCenter(uc);
				}
				//Agregar los estudios seleccionados al centro
				for(String s:studies){
					StudyCenter sc = new StudyCenter();
					sc.setEstudioCentroId(new StudyCenterId(s,centerCode));
					sc.setRecordUser(usuario.getUsername());
					sc.setRecordDate(new Date());
					this.studyCenterService.saveStudyCenter(sc);
				}
			}
			//Si el idCentro no viene en blanco hay que editar el centro
			else{
				//Recupera el centro de la base de datos
				center = this.centroService.getCentro(centerCode);
				center.setCenterName(centerName);
				center.setCenterContact(centerContact);
				center.setCenterAddress(centerAddress);
				center.setCenterPhoneNumber(centerPhoneNumber);
				center.setCenterEmail(centerEmail);
				center.setCenterObs(centerObs);
				//Actualiza el centro
				this.centroService.saveCenter(center);
				//Recupera los usuarios activos de este centro de la base de datos y pone el username en una lista
				List<String> usersCentro = new ArrayList<String>();
				List<UserCenter> usuarioscentro = this.userCenterService.getActiveUsersCenter(centerCode);
				for(UserCenter ucActual:usuarioscentro){
					usersCentro.add(ucActual.getUserCenterId().getUsername());
				}
				//Recupera los estudios activos de este centro de la base de datos y pone el id en una lista
				List<String> studiesCentro = new ArrayList<String>();
				List<StudyCenter> estudioscentro = this.studyCenterService.getActiveStudyCenter(centerCode);
				for(StudyCenter scActual:estudioscentro){
					studiesCentro.add(scActual.getEstudioCentroId().getEstudio());
				}
				
				//Recorre los usuarios seleccionados en el formulario
				for(String u:users){
					boolean encontreUsuarioBD = false;
					//Recorre los usuarios actuales del centro
					for(String uActual:usersCentro){
						if(uActual.equals(u)){
							encontreUsuarioBD=true;
							break;
						}
					}
					//Si no encuentra el usuario seleccionado en los usuarios actuales ingresa un nuevo usuario o lo actualiza
					if (!encontreUsuarioBD){
						UserSistema us = usuarioService.getUser(u);
						Center cen = centroService.getCentro(centerCode);
						UserCenter uc = new UserCenter(new UserCenterId(u,centerCode), us, cen, usuario.getUsername(), new Date());
						this.userCenterService.saveUserCenter(uc);
					}
				}
				//Recorre los usuarios actuales
				for(String uActual:usersCentro){
					boolean encontreUsuarioForm = false;
					//Recorre los usuarios seleccionados en el formulario
					for(String u:users){
						if(uActual.equals(u)){
							encontreUsuarioForm=true;
							break;
						}
					}
					//Si no encuentra el usuario actual en los usuarios seleccionados lo pone en pasivo
					if (!encontreUsuarioForm){
						UserCenter uc = this.userCenterService.getUserCenter(uActual, centerCode);
						uc.setPasive('1');
						this.userCenterService.saveUserCenter(uc);
					}
				}
				
				//Recorre los estudios seleccionados en el formulario
				for(String s:studies){
					boolean encontreEstudioBD = false;
					//Recorre los estudios actuales del centro
					for(String sActual:studiesCentro){
						if(sActual.equals(s)){
							encontreEstudioBD=true;
							break;
						}
					}
					//Si no encuentra el estudio seleccionado en los estudios actuales ingresa un nuevo estudio o lo actualiza
					if (!encontreEstudioBD){
						Study stu = estudioService.getStudy(s);
						Center cen = centroService.getCentro(centerCode);
						StudyCenter sc = new StudyCenter(new StudyCenterId(s,centerCode), stu, cen, usuario.getUsername(), new Date());
						this.studyCenterService.saveStudyCenter(sc);
					}
				}
				//Recorre los estudios actuales
				for(String sActual:studiesCentro){
					boolean encontreEstudioForm = false;
					//Recorre los estudios seleccionados en el formulario
					for(String s:studies){
						if(sActual.equals(s)){
							encontreEstudioForm=true;
							break;
						}
					}
					//Si no encuentra el estudio actual en los estudios seleccionados lo pone en pasivo
					if (!encontreEstudioForm){
						StudyCenter sc = this.studyCenterService.getStudyCenter(sActual, centerCode);
						sc.setPasive('1');
						this.studyCenterService.saveStudyCenter(sc);
					}
				}
			}
			return createJsonResponse(center);
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
     * Custom handler for enabling/disabling a center.
     *
     * @param centerCode the ID of the center
     * @return a String
     */
    @RequestMapping("/act/{accion}/{centerCode}/")
    public String habDeshabCenter(@PathVariable("centerCode") String centerCode, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	List<Room> rooms = cuartoService.getCuartosActivosCentro(centerCode);
    	if(!rooms.isEmpty()){
    		redirecTo = "redirect:/super/centers/";
    		redirectAttributes.addFlashAttribute("centerNotEmpty", true);
    		return redirecTo;
    	}
    	char estado;
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/super/centers/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("centroHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/super/centers/viewCenter/{centerCode}/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("centroHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/super/centers/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("centroDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/super/centers/viewCenter/{centerCode}/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("centroDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	Center centroaEditar = this.centroService.getCentro(centerCode);
    	if(centroaEditar==null){
    		redirecTo="404";
		}
		else{
	    	centroaEditar.setPasive(estado);
	    	this.centroService.saveCenter(centroaEditar);
	    	redirectAttributes.addFlashAttribute("nombreCentro", centroaEditar.getCenterName());
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
