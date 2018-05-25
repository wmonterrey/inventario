package ni.org.ics.lab.inventario.web.controller.translations;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.language.DatabaseDrivenMessageSource;
import ni.org.ics.lab.inventario.language.MessageResource;
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

import com.google.gson.Gson;

/**
 * Controlador web de peticiones relacionadas a traducciones
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/translation/*")
public class TranslationController {
	private static final Logger logger = LoggerFactory.getLogger(TranslationController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	@Resource(name="messageSource")
	private DatabaseDrivenMessageSource messageSource;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerMensajes(Model model) throws ParseException { 	
    	logger.debug("Mostrando Mensajes en JSP");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute("usuario", usuario);
    	return "translation/list";
	}
	
	/**
     * Retorna una lista de mensajes. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de mensajes
	 * @throws ParseException 
     */
    @RequestMapping(value = "messages", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<MessageResource> fetchMessageResourcesJson(@RequestParam(value = "messageParameter", required = true) String messageParameter) throws ParseException {
        logger.info("Obteniendo los MessageResource en JSON");
        List<MessageResource> messages = null; 
        messages = this.messageResourceService.loadMessages(messageParameter);
        return messages;	
    }
 
	@RequestMapping(value = "editTrans/{messageKey}/", method = RequestMethod.GET)
    public String initEdition(Model model, @PathVariable(value = "messageKey") String messageKey){
		MessageResource message = messageResourceService.getMensaje(messageKey);
    	if (message!=null){
    		model.addAttribute("message", message);
    		UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        	model.addAttribute("usuario", usuario);
    		return "translation/enterForm";
    	}
    	else{
    		return "404";
        }
    }    
    
    
    @RequestMapping( value="saveTrans", method=RequestMethod.POST)
	public ResponseEntity<String> processTransForm( @RequestParam(value="messageKey", required=true) String messageKey
			, @RequestParam( value="spanish", required=true ) String spanish
			, @RequestParam( value="english", required=true ) String english
	        )
	{
    	try{
			MessageResource message = messageResourceService.getMensaje(messageKey);
			//Actualiza la traduccion
			message.setSpanish(spanish);
			message.setEnglish(english);
			this.messageResourceService.saveMensaje(message);
			messageSource.reload();
			return createJsonResponse(message);
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

    private ResponseEntity<String> createJsonResponse( Object o )
	{
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");
	    Gson gson = new Gson();
	    String json = gson.toJson(o);
	    return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
	}
	
}
