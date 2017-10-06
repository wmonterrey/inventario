package ni.org.ics.lab.inventario.web.controller.alics;

import java.text.ParseException;
import java.util.List;
import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Box;
import ni.org.ics.lab.inventario.domain.complex.BoxAliquots;
import ni.org.ics.lab.inventario.domain.relationships.AlicTypeStudy;
import ni.org.ics.lab.inventario.domain.relationships.StudyCenter;
import ni.org.ics.lab.inventario.service.AliquotTypeService;
import ni.org.ics.lab.inventario.service.BoxService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

/**
 * Controlador web de peticiones relacionadas a alicuotas
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/addalic/*")
public class NewAliquotController {
	private static final Logger logger = LoggerFactory.getLogger(NewAliquotController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="studyCenterService")
	private StudyCenterService studyCenterService;
	@Resource(name="aliquotTypeService")
	private AliquotTypeService aliquotTypeService;
	@Resource(name="boxService")
	private BoxService boxService;

    @RequestMapping(value = "newAlicSug", method = RequestMethod.GET)
	public String initCreation(Model model) {
    	logger.debug("Creando nueva Alicuota");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
	    	model.addAttribute("usuario", usuario);
	    	List<StudyCenter> estudios = studyCenterService.getActiveStudyCenter(usuario.getUserCenter().getCenterCode());
	    	model.addAttribute("estudios", estudios);
			return "alics/newFormSug";
    	}
    	else{
    		return "403";
    	}
	}
    
    @RequestMapping(value = "newAlicMan", method = RequestMethod.GET)
	public String initCreation2(Model model) {
    	logger.debug("Creando nueva Alicuota");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
	    	model.addAttribute("usuario", usuario);
	    	List<StudyCenter> estudios = studyCenterService.getActiveStudyCenter(usuario.getUserCenter().getCenterCode());
	    	model.addAttribute("estudios", estudios);
			return "alics/newFormMan";
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
    @RequestMapping(value = "getAlicStudy", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<AlicTypeStudy> fetchAlicsJson(@RequestParam(value = "boxStudy", required = true) String boxStudy) throws ParseException {
    	logger.info("Obteniendo los alicType en JSON");
        List<AlicTypeStudy> alics = null; 
        alics = aliquotTypeService.getActiveAliquotTypesxStudy(boxStudy);
        return alics;	
    }
    
    
    /**
     * Retorna una lista de cajas. Acepta una solicitud GET para JSON
     * @return Un arreglo JSON de racks
	 * @throws ParseException 
     */
    @RequestMapping(value = "getPosAlic", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody BoxAliquots fetchPosJson(@RequestParam(value = "boxStudy", required = true) String boxStudy) throws ParseException {
    	logger.info("Obteniendo la posicion en JSON");
    	Box box = null;
        //List<Aliquot> alics = null; 
        BoxAliquots results = new BoxAliquots();
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        box = this.boxService.getBoxSugerida(boxStudy, usuario.getUserCenter().getCenterCode());
        results.setBox(box);
        return results;	
    }
    
    
    @RequestMapping( value="saveAlic", method=RequestMethod.POST)
	public ResponseEntity<String> processAlicForm( @RequestParam(value="aliCode", required=false, defaultValue="" ) String aliCode
			, @RequestParam( value="aliObs", required=false, defaultValue = "") String aliObs
	        )
	{
    	try{
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			return createJsonResponse(usuario);
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
