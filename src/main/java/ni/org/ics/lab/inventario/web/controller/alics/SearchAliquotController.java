package ni.org.ics.lab.inventario.web.controller.alics;

import ni.org.ics.lab.inventario.domain.RegAlic;
import ni.org.ics.lab.inventario.domain.RegSalida;
import ni.org.ics.lab.inventario.domain.RegUso;
import ni.org.ics.lab.inventario.domain.utils.AliquotData;
import ni.org.ics.lab.inventario.service.MessageResourceService;
import ni.org.ics.lab.inventario.service.SearchAliquotService;
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
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Controlador web de peticiones relacionadas a la búsqueda de alicuotas en simlab
 * @author ics
 */

@Controller
@RequestMapping("/searchalic/*")
public class SearchAliquotController {

    private static final Logger logger = LoggerFactory.getLogger(SearchAliquotController.class);
    @Resource(name="usuarioService")
    private UsuarioService usuarioService;
    @Resource(name="studyCenterService")
    private StudyCenterService studyCenterService;
    @Resource(name="searchAliquotService")
    private SearchAliquotService searchAliquotService;
    @Resource(name="messageResourceService")
    private MessageResourceService messageResourceService;

    LinkedList<AliquotData> files = null;
    AliquotData alicData = null;

    @RequestMapping(value = "enterForm", method = RequestMethod.GET)
    public String initCreation(Model model) {
        logger.debug("Buscando Muestra en Simlab");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (usuario.getUserCenter()!=null){
            model.addAttribute("usuario", usuario);
            return "alics/searchAliquotForm";
        }
        else{
            return "403";
        }
    }


    /***************************************************
     * @param aliCode : Aliquot Code
     * @return LinkedList<AliquotData> as json format
     ****************************************************/
    @RequestMapping(value="getAlic", method = RequestMethod.GET)
    public @ResponseBody
    LinkedList<AliquotData> searchAliquot(@RequestParam(value="aliCode") String aliCode) throws IOException {
        logger.info("Realizando busqueda en simlab");
        List<RegAlic> alic = null;
        List<RegUso> uList = null;
        RegSalida exitRec = null;
        alic = searchAliquotService.getAliquot(aliCode) ;
        uList = searchAliquotService.getAllUses(aliCode);
        exitRec = searchAliquotService.getExitRecord(aliCode);

        files = new LinkedList<>();

            try {
            if (alic != null){
            	for (RegAlic alicEnc: alic){
	                alicData = new AliquotData();
	                alicData.setCodAlic(alicEnc.getId());
	                alicData.setPosBox(alicEnc.getPosBox());
	                alicData.setCodFreezer(alicEnc.getCodFreezer());
	                if (alicEnc.getCodRack() != null) alicData.setCodRack(alicEnc.getCodRack());
	                alicData.setCodBox(alicEnc.getCodBox());
	                if (alicEnc.getNegPos() != null) alicData.setPosNeg(alicEnc.getNegPos());
	                if (alicEnc.getFehorReg() != null) alicData.setRegDate(alicEnc.getFehorReg().toString());
	                if (alicEnc.getCodUser() != null) alicData.setCodUser(alicEnc.getCodUser());
	                if (alicEnc.getVolAlic() != null) alicData.setAliVol(alicEnc.getVolAlic());
	                if (alicEnc.getPesoAlic() != null) alicData.setWeight(alicEnc.getPesoAlic());
	                if (alicEnc.getTipo() != null) alicData.setType(alicEnc.getTipo());
	                if (alicEnc.getCondicion() != null) alicData.setCondition(alicEnc.getCondicion());
	                if (alicEnc.getSeparada() != null) alicData.setSeparated(alicEnc.getSeparada());
	                alicData.setNumDesc(alicEnc.getNumDes());
	                alicData.setTable(messageResourceService.getMensaje("aliquot_reg").getSpanish());
	
	                files.add(alicData);
            	}
            }

            if (uList != null){
                for (RegUso reg: uList){
                    alicData = new AliquotData();
                    alicData.setCodAlic(reg.getCodigoAlic());
                    alicData.setPosBox(reg.getPosBox());
                    alicData.setCodFreezer(reg.getCodFreezer());
                    if (reg.getCodRack() != null) alicData.setCodRack(reg.getCodRack());
                    alicData.setCodBox(reg.getCodBox());
                    if (reg.getNegPos() != null) alicData.setPosNeg(reg.getNegPos());
                    if (reg.getFehorReg() != null) alicData.setRegDate(reg.getFehorReg().toString());
                    if (reg.getCodUser() != null) alicData.setCodUser(reg.getCodUser());
                    if (reg.getVolAlic() != null) alicData.setAliVol(reg.getVolAlic());
                    if (reg.getPesoAlic() != null) alicData.setWeight( reg.getPesoAlic() );
                    if (reg.getTipo() != null) alicData.setType( reg.getTipo() );
                    if (reg.getCondicion() != null) alicData.setCondition( reg.getCondicion() );
                    if (reg.getSeparada() != null) alicData.setSeparated(reg.getSeparada());
                    alicData.setNumDesc(reg.getNumDes());
                    if (reg.getVolUsado() != null) alicData.setUsedVol(reg.getVolUsado());
                    if (reg.getUsoAlic() != null) alicData.setUse(reg.getUsoAlic());
                    if (reg.getUsuarioUso() != null) alicData.setUserUse(reg.getUsuarioUso());
                    if (reg.getFechaUso() != null) alicData.setUseDate(reg.getFechaUso().toString());

                    alicData.setTable(messageResourceService.getMensaje("reg_use_alic").getSpanish());

                    files.add(alicData);

                }
            }

                if (exitRec != null){
                    alicData = new AliquotData();
                    alicData.setCodAlic(exitRec.getCodigoAlic());
                    alicData.setPosBox(exitRec.getPosBox());
                    alicData.setCodFreezer(exitRec.getCodFreezer());
                    if (exitRec.getCodRack() != null) alicData.setCodRack(exitRec.getCodRack());
                    alicData.setCodBox(exitRec.getCodBox());
                    if (exitRec.getNegPos() != null) alicData.setPosNeg(exitRec.getNegPos());
                    if (exitRec.getFehorReg() != null) alicData.setRegDate(exitRec.getFehorReg().toString());
                    if (exitRec.getCodUser() != null) alicData.setCodUser(exitRec.getCodUser());
                    if (exitRec.getVolAlic() != null) alicData.setAliVol(exitRec.getVolAlic());
                    if (exitRec.getPesoAlic() != null) alicData.setWeight(exitRec.getPesoAlic());
                    if (exitRec.getTipo() != null) alicData.setType(exitRec.getTipo());
                    if (exitRec.getCondicion() != null) alicData.setCondition(exitRec.getCondicion());
                    if (exitRec.getSeparada() != null) alicData.setSeparated(exitRec.getSeparada());
                    alicData.setNumDesc(exitRec.getNumDes());
                    alicData.setTable(messageResourceService.getMensaje("alic_output2").getSpanish());
                    if (exitRec.getDestAlic() != null) alicData.setDestination(exitRec.getDestAlic());
                    if (exitRec.getUserOutput() != null) alicData.setUser(exitRec.getUserOutput());
                    if (exitRec.getOutputDate() != null) alicData.setOutputDate(exitRec.getOutputDate().toString());
                    files.add(alicData);
                }

            } catch (Exception e) {
                e.printStackTrace();

            }


        return files;
    }



}
