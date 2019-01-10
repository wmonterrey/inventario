package ni.org.ics.lab.inventario.web.controller.alics;

import ni.org.ics.lab.inventario.domain.Aliquot;
import ni.org.ics.lab.inventario.domain.AliquotOutput;
import ni.org.ics.lab.inventario.domain.AliquotUse;
import ni.org.ics.lab.inventario.domain.utils.AliquotData;
import ni.org.ics.lab.inventario.domain.utils.SearchResponseAliquots;
import ni.org.ics.lab.inventario.service.AliquotService;
import ni.org.ics.lab.inventario.service.MessageResourceService;
import ni.org.ics.lab.inventario.service.StudyCenterService;
import ni.org.ics.lab.inventario.service.UsuarioService;
import ni.org.ics.lab.inventario.users.model.UserSistema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * Controlador web de peticiones relacionadas a la busqueda de alicuotas
 * @author ics
 */

@Controller
@RequestMapping("/alics/search*")
public class SearchAliquotController {

    private static final Logger logger = LoggerFactory.getLogger(SearchAliquotController.class);
    @Resource(name="usuarioService")
    private UsuarioService usuarioService;
    @Resource(name="studyCenterService")
    private StudyCenterService studyCenterService;
    @Resource(name="aliquotService")
    private AliquotService aliquotService;
    @Resource(name="messageResourceService")
    private MessageResourceService messageResourceService;

    LinkedList<AliquotData> files = null;
    AliquotData alicData = null;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String initCreation(Model model) {
        logger.debug("Buscando Muestras");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (usuario.getUserCenter()!=null){
            model.addAttribute("usuario", usuario);
            return "alics/searchAliquotForm";
        }
        else{
            return "403";
        }
    }
    
    
    @RequestMapping(value = "/getAlics/", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    SearchResponseAliquots fetchBoxJson(@RequestParam(value = "aliCode") String aliCode) {
        logger.info("Obteniendo informacion de alicuota en JSON");
        List<Aliquot> alics = null;
        List<AliquotUse> alicsused = null;
        List<AliquotOutput> alicssent = null;
        alics = aliquotService.getAliquotsSearch(aliCode);
        alicsused = aliquotService.getAliquotsUseSearch(aliCode);
        alicssent = aliquotService.getAliquotsOutputSearch(aliCode);
        SearchResponseAliquots results = new SearchResponseAliquots();
        results.setAlics(alics);
        results.setAlicsused(alicsused);
        results.setAlicssent(alicssent);
        return results;
    }

}
