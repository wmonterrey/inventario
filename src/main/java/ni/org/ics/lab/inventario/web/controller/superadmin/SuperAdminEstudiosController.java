package ni.org.ics.lab.inventario.web.controller.superadmin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.AliquotType;
import ni.org.ics.lab.inventario.domain.Study;
import ni.org.ics.lab.inventario.domain.audit.AuditTrail;
import ni.org.ics.lab.inventario.domain.relationships.AlicTypeStudy;
import ni.org.ics.lab.inventario.domain.relationships.AlicTypeStudyId;
import ni.org.ics.lab.inventario.service.AlicTypeStudyService;
import ni.org.ics.lab.inventario.service.AliquotTypeService;
import ni.org.ics.lab.inventario.service.AuditTrailService;
import ni.org.ics.lab.inventario.service.EstudioService;
import ni.org.ics.lab.inventario.service.StudyCenterService;
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
@RequestMapping("/super/studies/*")
public class SuperAdminEstudiosController {
	private static final Logger logger = LoggerFactory.getLogger(SuperAdminEstudiosController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="estudioService")
	private EstudioService estudioService;
	@Resource(name="studyCenterService")
	private StudyCenterService studyCenterService;
	@Resource(name="alicTypeStudyService")
	private AlicTypeStudyService alicTypeStudyService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="aliquotTypeService")
	private AliquotTypeService aliquotTypeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerEstudios(Model model) throws ParseException { 	
    	logger.debug("Mostrando Estudios en JSP");
    	List<Study> estudios = estudioService.getEstudios();
    	model.addAttribute("estudios", estudios);
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
    	return "super/studies/list";
	}
    
    @RequestMapping(value = "viewStudy/{studyCode}/", method = RequestMethod.GET)
    public String initView(Model model, @PathVariable(value = "studyCode") String studyCode){
    	Study study = estudioService.getStudy(studyCode);
    	if (study!=null){
    		model.addAttribute("study", study);
    		List<AlicTypeStudy> tiposalicestudio = this.alicTypeStudyService.getAllAlicTypeStudy(studyCode);
        	model.addAttribute("tiposalicestudio", tiposalicestudio);
        	List<AuditTrail> bitacoraEstudio = auditTrailService.getBitacora(studyCode);
        	model.addAttribute("bitacora", bitacoraEstudio);
        	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	model.addAttribute("usuario", usuario);
    		return "super/studies/viewData";
    	}
    	else{
    		return "404";
        }
    }   
    
    @RequestMapping(value = "newStudy", method = RequestMethod.GET)
	public String initCreation(Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
    	List<AliquotType> tiposalicuota = this.aliquotTypeService.getActiveAliquotTypes();
    	model.addAttribute("tiposalicuota", tiposalicuota);
		return "super/studies/enterForm";
	}
    
    @RequestMapping(value = "editStudy/{studyCode}/", method = RequestMethod.GET)
    public String initEdition(Model model, @PathVariable(value = "studyCode") String studyCode){
    	Study study = estudioService.getStudy(studyCode);
    	if (study!=null){
    		model.addAttribute("study", study);
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	model.addAttribute("usuario", usuario);
        	List<AliquotType> tiposalicuota = this.aliquotTypeService.getActiveAliquotTypes();
        	model.addAttribute("tiposalicuota", tiposalicuota);
        	List<AlicTypeStudy> tiposalicestudio = this.alicTypeStudyService.getActiveAlicTypeStudy(studyCode);
        	model.addAttribute("tiposalicestudio", tiposalicestudio);
    		return "super/studies/enterForm";
    	}
    	else{
    		return "404";
        }
    }    
    
    
    @RequestMapping( value="saveStudy", method=RequestMethod.POST)
	public ResponseEntity<String> processStudyForm( @RequestParam(value="studyCode", required=false, defaultValue="" ) String studyCode
			, @RequestParam( value="studyName", required=true ) String studyName
			, @RequestParam( value="studyPattern", required=true ) String studyPattern
			, @RequestParam( value="studyFormat", required=true ) String studyFormat
			, @RequestParam( value="alicuotas", required=false, defaultValue="" ) List<String> alicuotas
			, @RequestParam( value="studyObs", required=false, defaultValue="" ) String studyObs			
	        )
	{
    	try{
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			Study study = new Study();
			//Si el studyCode viene en blanco es un nuevo estudio
			if (studyCode.equals("")){
				//Crear un nuevo studyCode
				studyCode = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
				study.setStudyCode(studyCode);
				study.setRecordUser(usuario.getUsername());
				study.setRecordDate(new Date());
				study.setStudyName(studyName);
				study.setStudyObs(studyObs);
				study.setStudyPattern(studyPattern);
				study.setStudyFormat(studyFormat);
				//Guardar el nuevo estudio
				this.estudioService.saveStudy(study);
				//Agregar los tipos de alicuotas seleccionados al estudio
				for(String a:alicuotas){
					AlicTypeStudy ats = new AlicTypeStudy();
					ats.setTipoAlicuotaEstudioId(new AlicTypeStudyId(studyCode, a));
					ats.setRecordUser(usuario.getUsername());
					ats.setRecordDate(new Date());
					this.alicTypeStudyService.saveAlicTypeStudy(ats);
				}
			}
			//Si el studyCode no viene en blanco hay que editar el centro
			else{
				//Recupera el centro de la base de datos
				study = this.estudioService.getStudy(studyCode);
				study.setStudyName(studyName);
				study.setStudyPattern(studyPattern);
				study.setStudyFormat(studyFormat);
				study.setStudyObs(studyObs);
				//Actualiza el centro
				this.estudioService.saveStudy(study);
				
				//Recupera los tipos de alicuota activos de este estudio de la base de datos y pone el codigo en una lista
				List<String> alicsEstudio = new ArrayList<String>();
				List<AlicTypeStudy> alicuotasestudio = this.alicTypeStudyService.getActiveAlicTypeStudy(studyCode);
				for(AlicTypeStudy taActual:alicuotasestudio){
					alicsEstudio.add(taActual.getTipoAlicuotaEstudioId().getTipoAlicuota());
				}
				//Recorre los tipos de alicuota seleccionados en el formulario
				for(String a:alicuotas){
					boolean encontreTipoBD = false;
					//Recorre los tipos de alicuota actuales del estudio
					for(String tasActual:alicsEstudio){
						if(tasActual.equals(a)){
							encontreTipoBD=true;
							break;
						}
					}
					//Si no encuentra el tipo de alicuota seleccionado en los actuales ingresa un nuevo tipo o lo actualiza
					if (!encontreTipoBD){
						AliquotType alicType = aliquotTypeService.getAliquotType(a);
						Study stu = this.estudioService.getStudy(studyCode);
						AlicTypeStudy ats = new AlicTypeStudy(new AlicTypeStudyId(studyCode,a), stu, alicType, usuario.getUsername(), new Date());
						this.alicTypeStudyService.saveAlicTypeStudy(ats);
					}
				}
				//Recorre los tipos de alicuotas actuales
				for(String tasActual:alicsEstudio){
					boolean encontreTipoForm = false;
					//Recorre los tipos de alicuotas seleccionados en el formulario
					for(String a:alicuotas){
						if(tasActual.equals(a)){
							encontreTipoForm=true;
							break;
						}
					}
					//Si no encuentra el usuario actual en los usuarios seleccionados lo pone en pasivo
					if (!encontreTipoForm){
						AlicTypeStudy ats = this.alicTypeStudyService.getAlicTypeStudy(studyCode, tasActual);
						ats.setPasive('1');
						this.alicTypeStudyService.saveAlicTypeStudy(ats);
					}
				}
			}
			return createJsonResponse(study);
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
     * Custom handler for enabling/disabling a study.
     *
     * @param studyCode the ID of the study
     * @return a String
     */
    @RequestMapping("/act/{accion}/{studyCode}/")
    public String habDeshabStudy(@PathVariable("studyCode") String studyCode, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	char estado;
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/super/studies/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("estudioHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/super/studies/viewStudy/{studyCode}/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("estudioHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/super/studies/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("estudioDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/super/studies/viewStudy/{studyCode}/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("estudioDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	Study estudioaEditar = this.estudioService.getStudy(studyCode);
    	if(estudioaEditar==null){
    		redirecTo="404";
		}
		else{
	    	estudioaEditar.setPasive(estado);
	    	this.estudioService.saveStudy(estudioaEditar);
	    	redirectAttributes.addFlashAttribute("nombreEstudio", estudioaEditar.getStudyName());
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
