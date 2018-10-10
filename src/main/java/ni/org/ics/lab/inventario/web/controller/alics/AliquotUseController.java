package ni.org.ics.lab.inventario.web.controller.alics;

import com.google.gson.Gson;
import ni.org.ics.lab.inventario.domain.Aliquot;
import ni.org.ics.lab.inventario.domain.AliquotUse;
import ni.org.ics.lab.inventario.domain.relationships.StudyCenter;
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
import java.util.Date;
import java.util.List;


/**
 * Controlador web de peticiones relacionadas a uso de Alicuotas
 *
 * @author ics
 */

@Controller
@RequestMapping("/alicUse/*")
public class AliquotUseController {

    private static final Logger logger = LoggerFactory.getLogger(AliquotUseController.class);
    @Resource(name="usuarioService")
    private UsuarioService usuarioService;
    @Resource(name="aliquotService")
    private AliquotService aliquotService;
    @Resource(name="aliquotUseService")
    private AliquotUseService aliquotUseService;
    @Resource(name="studyCenterService")
    private StudyCenterService studyCenterService;

    @RequestMapping(value = "enterForm", method = RequestMethod.GET)
    public String initCreation(Model model) {
        logger.debug("Creando uso de Muestra");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (usuario.getUserCenter()!=null){
            List<StudyCenter> estudios = studyCenterService.getActiveStudyCenter(usuario.getUserCenter().getCenterCode());
            model.addAttribute("usuario", usuario);
            model.addAttribute("estudios", estudios);
            return "alics/enterFormAlicUse";
        }
        else{
            return "403";
        }
    }

    @RequestMapping(value = "getAlic", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Aliquot fetchBoxJson(@RequestParam(value = "aliCode", required = false) String aliCode) {
        logger.info("Obteniendo informacion de alicuota en JSON");
        Aliquot alic = null;
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        alic = aliquotService.getAliquot(aliCode, usuario.getUsername()  ) ;
        return alic;
    }

    @RequestMapping( value="saveUse", method=RequestMethod.POST)
    public ResponseEntity<String> processSaveForm(@RequestParam( value="aliCode1", required=true ) String aliCode
            , @RequestParam( value="use", required=false ) String use
            , @RequestParam( value="usedVol", required=true ) float usedVol
    )
    {
        try{
            UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
            AliquotUse alicUse = new AliquotUse();
            Aliquot alic = aliquotService.getAliquot(aliCode, usuario.getUsername());
            float res = 0;

            if (alic != null){
                    alicUse.setAliCode(alic);
                    alicUse.setRecordUser(usuario.getUsername());
                    alicUse.setAliCondition(alic.getAliCond());
                    alicUse.setAliBox(alic.getAliBox());
                    alicUse.setAliPosition(alic.getAliPosition());
                    alicUse.setAliVol(alic.getAliVol());
                    alicUse.setAliUsedVol(usedVol);
                    alicUse.setAliUse(use);
                    alicUse.setRecordDate(new Date());

                    //Save aliquot alicUse
                    this.aliquotUseService.saveAliquotUse(alicUse);


                    //update volume aliquot
                    res = alic.getAliVol() - usedVol;

                    if(res != 0){
                        alic.setAliVol(res);
                    }else{
                        alic.setAliVol(res);
                        alic.setPasive( '1' );
                    }

                    this.aliquotService.updateAliquot(alic);


                    return createJsonResponse(alicUse);

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

    private ResponseEntity<String> createJsonResponse( Object o )
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        Gson gson = new Gson();
        String json = gson.toJson(o);
        return new ResponseEntity<String>( json, headers, HttpStatus.CREATED );
    }


}
