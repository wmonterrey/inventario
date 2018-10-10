package ni.org.ics.lab.inventario.web.controller.alics;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Aliquot;
import ni.org.ics.lab.inventario.domain.AliquotId;
import ni.org.ics.lab.inventario.domain.Box;
import ni.org.ics.lab.inventario.domain.Equipment;
import ni.org.ics.lab.inventario.domain.Rack;
import ni.org.ics.lab.inventario.domain.complex.BoxAliquots;
import ni.org.ics.lab.inventario.domain.relationships.AlicTypeStudy;
import ni.org.ics.lab.inventario.domain.relationships.StudyCenter;
import ni.org.ics.lab.inventario.language.MessageResource;
import ni.org.ics.lab.inventario.service.AliquotService;
import ni.org.ics.lab.inventario.service.AliquotTypeService;
import ni.org.ics.lab.inventario.service.BoxService;
import ni.org.ics.lab.inventario.service.EquipoService;
import ni.org.ics.lab.inventario.service.MessageResourceService;
import ni.org.ics.lab.inventario.service.RackService;
import ni.org.ics.lab.inventario.service.StudyCenterService;
import ni.org.ics.lab.inventario.service.UsuarioService;
import ni.org.ics.lab.inventario.users.model.UserSistema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
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
	@Resource(name="aliquotService")
	private AliquotService aliquotService;
	@Resource(name="boxService")
	private BoxService boxService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="equipoService")
    private EquipoService equipoService;
	@Resource(name="rackService")
	private RackService rackService;

    @RequestMapping(value = "newAlicSug", method = RequestMethod.GET)
	public String initCreation(Model model) {
    	logger.debug("Creando nueva Alicuota");
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	if (usuario.getUserCenter()!=null){
	    	model.addAttribute("usuario", usuario);
	    	List<StudyCenter> estudios = studyCenterService.getActiveStudyCenter(usuario.getUserCenter().getCenterCode());
	    	model.addAttribute("estudios", estudios);
	    	List<MessageResource> tipoResultadoCat = this.messageResourceService.getCatalogo("tipoResultadoCat"); 
	    	List<MessageResource> condiciones = messageResourceService.getCatalogo("condicionCat");
	    	model.addAttribute("resultados", tipoResultadoCat);
	    	model.addAttribute("condiciones", condiciones);
			return "alics/newFormSug";
    	}
    	else{
    		return "403";
    	}
	}
    
    @RequestMapping(value = "location", method = RequestMethod.GET)
    public String initCreationMan(Model model) {
        logger.debug("Creando ubicacion de Muestra");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (usuario.getUserCenter()!=null){
            model.addAttribute("usuario", usuario);
            List<Equipment> equipos = equipoService.getEquipos(usuario.getUserCenter().getCenterCode());
            List<MessageResource> msj = messageResourceService.getCatalogo("condicionCat");
            model.addAttribute("equipos", equipos);
            model.addAttribute("msj", msj);

            return "alics/newFormLocation";
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
     * @return Un arreglo JSON de cajas
	 * @throws ParseException 
     */
    @RequestMapping(value = "getPosAlic", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<BoxAliquots> fetchPosJson(@RequestParam(value = "boxStudy", required = true) String boxStudy,
    		@RequestParam(value = "alicTypeName", required = true) String alicTypeName,
    		@RequestParam(value = "alicTypeUse", required = true) String alicTypeUse,
    		@RequestParam(value = "alicTypeTemp", required = true) String alicTypeTemp,
    		@RequestParam(value = "boxResultType", required = true) String boxResultType) throws ParseException {
    	logger.info("Obteniendo la posicion en JSON");
    	Box cajaEncontrada;
    	List<BoxAliquots> cajas = new ArrayList<BoxAliquots>();
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Object[]> resultados = this.boxService.getBoxSugerida(boxStudy, usuario.getUserCenter().getCenterCode(),alicTypeName,alicTypeUse,alicTypeTemp,boxResultType);
        for(Object[] res:resultados) {
        	cajaEncontrada = this.boxService.getBox(res[0].toString(), usuario.getUsername());
        	cajas.add(new BoxAliquots(cajaEncontrada, Integer.parseInt(res[1].toString())));
        }
        return cajas;	
    }
    
    /**
     * Retorna una caja. Acepta una solicitud GET para JSON
     * @return  JSON Caja
     */
    @RequestMapping(value = "getCajaSeleccionada", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody BoxAliquots fetchCajaJson(@RequestParam(value = "boxCode", required = true) String boxCode) {
    	logger.info("Obteniendo la posicion en JSON");
    	Box cajaSeleccionada;
    	boolean posOcupada= false;
    	MessageResource mr = null;
		String descCatalogo = null;
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        cajaSeleccionada = this.boxService.getBox(boxCode, usuario.getUsername());
        BoxAliquots cajaDatos = new BoxAliquots();
        cajaDatos.setBox(cajaSeleccionada);
        List<Aliquot> alicuotas = this.aliquotService.getActiveAliquots(boxCode);
        for(Aliquot alic:alicuotas) {
    		mr = this.messageResourceService.getMensaje(alic.getAliCond(),"condicionCat");
    		descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
    		alic.setAliCond(descCatalogo);
    		mr = this.messageResourceService.getMensaje(alic.getAliRes(),"tipoResultadoCat");
    		descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
    		alic.setAliRes(descCatalogo);
        }
        cajaDatos.setAliquots(alicuotas);
        for(Integer i = 1; i<=cajaSeleccionada.getBoxCapacity(); i++){
        	posOcupada = false;
			for(Aliquot alic:alicuotas) {
				if(alic.getAliPosition()==i) {
					posOcupada = true;
					break;
				}
			}
			if(!posOcupada) {
				cajaDatos.setPrimeraDisponible(i);
				break;
			}
		}
        return cajaDatos;	
    }
    
    
    @RequestMapping( value="saveAlic", method=RequestMethod.POST)
	public ResponseEntity<String> processAlicForm( @RequestParam(value="aliCode", required=true) String aliCode
			, @RequestParam( value="boxResults", required=true ) String boxResults
			, @RequestParam(value="aliPosition", required=true) int aliPosition
			, @RequestParam( value="aliVol", required=true ) float aliVol
			, @RequestParam( value="alicTypeName", required=true ) String alicTypeName
			, @RequestParam( value="aliCond", required=true ) String aliCond
			, @RequestParam( value="aliObs", required=false, defaultValue = "") String aliObs
	        )
	{
    	try{
			UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			Box box = this.boxService.getBox(boxResults, usuario.getUsername());
			AliquotId aliId = new AliquotId(aliCode,box.getBoxStudy().getStudyCode());
			Aliquot aliquot = new Aliquot();
			aliquot.setAliId(aliId);
			aliquot.setAliBox(box);
			aliquot.setAliPosition(aliPosition);
			aliquot.setAliVol(aliVol);
			aliquot.setAlicTypeName(alicTypeName);
			aliquot.setAliRes(box.getBoxResult());
			aliquot.setAliCond(aliCond);
			aliquot.setAliSep(usuario.getUsername());
			aliquot.setAliObs(aliObs);
			aliquot.setRecordDate(new Date());
			aliquot.setRecordUser(usuario.getUsername());
			this.aliquotService.saveAliquot(aliquot);
			MessageResource mr = null;
    		String descCatalogo = null;
    		mr = this.messageResourceService.getMensaje(aliquot.getAliCond(),"condicionCat");
    		descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
    		aliquot.setAliCond(descCatalogo);
    		mr = this.messageResourceService.getMensaje(aliquot.getAliRes(),"tipoResultadoCat");
    		descCatalogo = (LocaleContextHolder.getLocale().getLanguage().equals("en")) ? mr.getEnglish(): mr.getSpanish();
    		aliquot.setAliRes(descCatalogo);
			return createJsonResponse(aliquot);
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
