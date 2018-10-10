package ni.org.ics.lab.inventario.web.controller.catalogs;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.AliquotType;
import ni.org.ics.lab.inventario.domain.audit.AuditTrail;
import ni.org.ics.lab.inventario.domain.relationships.AlicTypeStudy;
import ni.org.ics.lab.inventario.language.MessageResource;
import ni.org.ics.lab.inventario.service.AliquotTypeService;
import ni.org.ics.lab.inventario.service.AuditTrailService;
import ni.org.ics.lab.inventario.service.MessageResourceService;
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
@RequestMapping("/catalog/alics/*")
public class CatalogAliquotTypeController {
	private static final Logger logger = LoggerFactory.getLogger(CatalogAliquotTypeController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="aliquotTypeService")
	private AliquotTypeService aliquotTypeService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerTiposAlicuota(Model model) throws ParseException { 	
    	logger.debug("Mostrando Tipos Alicuota en JSP");
    	List<AliquotType> tiposalicuota = aliquotTypeService.getAliquotTypes();
    	model.addAttribute("tiposalicuota", tiposalicuota);
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
    	return "catalogos/alic/list";
	}
    
    @RequestMapping(value = "viewAlicType/{alicTypeCode}/", method = RequestMethod.GET)
    public String initView(Model model, @PathVariable(value = "alicTypeCode") String alicTypeCode){
    	AliquotType alicType = aliquotTypeService.getAliquotType(alicTypeCode);
    	if (alicType!=null){
    		model.addAttribute("alicType", alicType);
        	List<AuditTrail> bitacoraTipoAlicuota = auditTrailService.getBitacora(alicTypeCode);
        	model.addAttribute("bitacora", bitacoraTipoAlicuota);
        	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	model.addAttribute("usuario", usuario);
    		return "catalogos/alic/viewData";
    	}
    	else{
    		return "404";
        }
    }   
    
    @RequestMapping(value = "newAlicType", method = RequestMethod.GET)
	public String initCreation(Model model) {
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
    	List<MessageResource> equipUseCat = this.messageResourceService.getCatalogo("equipUseCat"); 
    	model.addAttribute("usosalicuota", equipUseCat);
    	List<MessageResource> tipoMuestraCat = this.messageResourceService.getCatalogo("tipoMuestraCat"); 
    	model.addAttribute("muestrasalicuota", tipoMuestraCat);
		return "catalogos/alic/enterForm";
	}
    
    @RequestMapping(value = "editAlicType/{alicTypeCode}/", method = RequestMethod.GET)
    public String initEdition(Model model, @PathVariable(value = "alicTypeCode") String alicTypeCode){
    	AliquotType alicType = aliquotTypeService.getAliquotType(alicTypeCode);
    	if (alicType!=null){
    		model.addAttribute("alicType", alicType);
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	model.addAttribute("usuario", usuario);
        	List<MessageResource> equipUseCat = this.messageResourceService.getCatalogo("equipUseCat"); 
        	model.addAttribute("usosalicuota", equipUseCat);
        	List<MessageResource> tipoMuestraCat = this.messageResourceService.getCatalogo("tipoMuestraCat"); 
        	model.addAttribute("muestrasalicuota", tipoMuestraCat);
    		return "catalogos/alic/enterForm";
    	}
    	else{
    		return "404";
        }
    }    
    
    
    @RequestMapping( value="saveAlicType", method=RequestMethod.POST)
	public ResponseEntity<String> processAlicTypeForm( @RequestParam(value="alicTypeCode", required=false, defaultValue="" ) String alicTypeCode
			, @RequestParam( value="alicTypeName", required=true ) String alicTypeName
			, @RequestParam( value="alicTypeUse", required=true ) String alicTypeUse
			, @RequestParam( value="alicTypeSample", required=true ) String alicTypeSample
			, @RequestParam( value="alicTypeTemp", required=true ) float alicTypeTemp
			, @RequestParam( value="alicTypeVol", required=true ) float alicTypeVol
			, @RequestParam( value="alicTypeVolMin", required=true ) float alicTypeVolMin
			, @RequestParam( value="alicTypeVolMax", required=true ) float alicTypeVolMax
			, @RequestParam( value="alicTypeObs", required=false, defaultValue="" ) String alicTypeObs			
	        )
	{
    	try{
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			AliquotType alicType = new AliquotType();
			//Si el alicTypeCode viene en blanco es un nuevo tipo de alicuota
			if (alicTypeCode.equals("")){
				//Crear un nuevo alicTypeCode
				alicTypeCode = new UUID(usuario.getUsername().hashCode(),new Date().hashCode()).toString();
				alicType.setAlicTypeCode(alicTypeCode);
				alicType.setRecordUser(usuario.getUsername());
				alicType.setRecordDate(new Date());
				alicType.setAlicTypeName(alicTypeName);
				alicType.setAlicTypeObs(alicTypeObs);
				alicType.setAlicTypeUse(alicTypeUse);
				alicType.setAlicTypeTemp(alicTypeTemp);
				alicType.setAlicTypeVol(alicTypeVol);
				alicType.setAlicTypeVolMin(alicTypeVolMin);
				alicType.setAlicTypeVolMax(alicTypeVolMax);
				alicType.setAlicTypeSample(alicTypeSample);
				//Guardar el nuevo tipo de alicuota
				this.aliquotTypeService.saveAliquotType(alicType);
			}
			//Si el alicTypeCode no viene en blanco hay que editar el tipo de alicuota
			else{
				//Recupera el tipo de alicuota de la base de datos
				alicType = aliquotTypeService.getAliquotType(alicTypeCode);
				alicType.setAlicTypeName(alicTypeName);
				alicType.setAlicTypeObs(alicTypeObs);
				alicType.setAlicTypeUse(alicTypeUse);
				alicType.setAlicTypeTemp(alicTypeTemp);
				alicType.setAlicTypeVol(alicTypeVol);
				alicType.setAlicTypeVolMin(alicTypeVolMin);
				alicType.setAlicTypeVolMax(alicTypeVolMax);
				alicType.setAlicTypeSample(alicTypeSample);
				//Actualiza el tipo de alicuota
				this.aliquotTypeService.saveAliquotType(alicType);
			}
			return createJsonResponse(alicType);
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
     * Custom handler for enabling/disabling a AlicType.
     *
     * @param alicTypeCode the ID of the AlicType
     * @return a String
     */
    @RequestMapping("/act/{accion}/{alicTypeCode}/")
    public String habDeshabAlicType(@PathVariable("alicTypeCode") String alicTypeCode, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	char estado;
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/catalog/alics/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("tipoAlicHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/catalog/alics/viewAlicType/{alicTypeCode}/";
    		estado = '0';
    		redirectAttributes.addFlashAttribute("tipoAlicHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/catalog/alics/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("tipoAlicDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/catalog/alics/viewAlicType/{alicTypeCode}/";
    		estado = '1';
    		redirectAttributes.addFlashAttribute("tipoAlicDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	AliquotType alicTypeToEdit = aliquotTypeService.getAliquotType(alicTypeCode);
    	if(alicTypeToEdit==null){
    		redirecTo="404";
		}
		else{
			alicTypeToEdit.setPasive(estado);
			this.aliquotTypeService.saveAliquotType(alicTypeToEdit);
	    	redirectAttributes.addFlashAttribute("nombreTipoAlic", alicTypeToEdit.getAlicTypeName());
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
     * Retorna una lista de racks. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
	 * @throws ParseException 
     */
    @RequestMapping(value = "alictypeactivos", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<AlicTypeStudy> fetchalicTypeActivosJson(@RequestParam(value = "boxStudy", required = true, defaultValue="") String boxStudy
    		, @RequestParam( value="boxAlicUse", required=true, defaultValue="" ) String boxAlicUse
    		, @RequestParam( value="boxTemp", required=true) float boxTemp) throws ParseException {
        logger.info("Obteniendo los alicType en JSON");
        List<AlicTypeStudy> alicuotas = null; 
        alicuotas = aliquotTypeService.getActiveAliquotTypes(boxStudy, boxAlicUse, boxTemp);
        return alicuotas;	
    }
	
}
