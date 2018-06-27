package ni.org.ics.lab.inventario.web.controller.alics;

import com.google.gson.Gson;
import ni.org.ics.lab.inventario.domain.Aliquot;
import ni.org.ics.lab.inventario.domain.Box;
import ni.org.ics.lab.inventario.domain.Equipment;
import ni.org.ics.lab.inventario.domain.Rack;
import ni.org.ics.lab.inventario.domain.complex.BoxAliquots;
import ni.org.ics.lab.inventario.domain.relationships.AlicTypeStudy;
import ni.org.ics.lab.inventario.domain.relationships.StudyCenter;
import ni.org.ics.lab.inventario.language.MessageResource;
import ni.org.ics.lab.inventario.service.*;
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

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Controlador web de peticiones relacionadas a ubicación de Alicuotas
 *
 * @author ics
 */

@Controller
@RequestMapping("/addloc/*")
public class NewLocationController {

    private static final Logger logger = LoggerFactory.getLogger(NewLocationController.class);
    @Resource(name="usuarioService")
    private UsuarioService usuarioService;
    @Resource(name="studyCenterService")
    private StudyCenterService studyCenterService;
    @Resource(name="aliquotTypeService")
    private AliquotTypeService aliquotTypeService;
    @Resource(name="boxService")
    private BoxService boxService;
    @Resource(name="equipoService")
    private EquipoService equipoService;
    @Resource(name="aliquotService")
    private AliquotService aliquotService;

    @RequestMapping(value = "location", method = RequestMethod.GET)
    public String initCreation(Model model) {
        logger.debug("Creando ubicacion de Muestra");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (usuario.getUserCenter()!=null){
            model.addAttribute("usuario", usuario);
            List<StudyCenter> estudios = studyCenterService.getActiveStudyCenter(usuario.getUserCenter().getCenterCode());
            List<Equipment> equipos = equipoService.getEquipos(usuario.getUserCenter().getCenterCode());
            model.addAttribute("equipos", equipos);
            model.addAttribute("estudios", estudios);
            return "alics/newFormLocation";
        }
        else{
            return "403";
        }
    }

    @RequestMapping(value = "getBox", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody Box fetchBoxJson(@RequestParam(value = "boxCode", required = false) String boxCode) {
        logger.info("Obteniendo informacion de caja en JSON");
        Box box = null;
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        box = boxService.getBox1(boxCode);
        return box;
    }

    @RequestMapping( value="saveAliLoc", method=RequestMethod.POST)
    public ResponseEntity<String> processSaveForm(@RequestParam(value="pos", required=true) int pos
            , @RequestParam( value="type", required=true ) String type
            , @RequestParam( value="aliCode", required=true ) String aliCode
            , @RequestParam( value="condition", required=true ) String condition
            , @RequestParam( value="obs", required=false ) String obs
            , @RequestParam( value="vol", required=true ) float vol
            , @RequestParam( value="boxId", required=true ) String boxId
    )
    {
        try{
            UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            Aliquot alic = new Aliquot();
            Box box = boxService.getBox1(boxId);

            if (box != null){
                alic.setAliCode(aliCode);
                alic.setRecordUser(usuario.getUsername());
                alic.setAliCond(condition);
                alic.setAliObs(obs);
                alic.setAliPosition(pos);
                alic.setAliVol(vol);
                alic.setAlicTypeName(type);
                alic.setAliBox(box);

                //Save aliquot
                this.aliquotService.saveAliquot(alic);
                return createJsonResponse(alic);
            }else{
                Gson gson = new Gson();
                String json = gson.toJson("403 Forbidden");
                return new ResponseEntity<String>( json, HttpStatus.CREATED);
            }

        }   catch(Exception e){
            Gson gson = new Gson();
            String json = gson.toJson(e.toString());
            return new ResponseEntity<String>( json, HttpStatus.CREATED);
        }

    }

    @RequestMapping(value = "getActiveAliquots", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody List<Aliquot> fetchActiveAliquotsJson(@RequestParam(value = "boxCode", required = false) String boxCode) {
        logger.info("Obteniendo informacion de stock no disponible");
        List<Aliquot> alic = null;
        alic = aliquotService.getActiveAliquots(boxCode);
        return alic;
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
