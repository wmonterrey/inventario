package ni.org.ics.lab.inventario.web.controller.alics;

import ni.org.ics.lab.inventario.domain.SampleRequest;
import ni.org.ics.lab.inventario.domain.SampleRequestDetail;
import ni.org.ics.lab.inventario.service.*;
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
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ics
 */
@Controller
@RequestMapping("/alics/request/requestsheet/*")
public class RequestSheetController {


    private static final Logger logger = LoggerFactory.getLogger(RequestSheetController.class);
    @Resource(name="usuarioService")
    private UsuarioService usuarioService;
    @Resource(name="requestSheetService")
    private RequestSheetService requestSheetService;
    @Resource(name="messageResourceService")
    private MessageResourceService messageResource;
    @Resource(name="sampleRequestService")
    private SampleRequestService requestService;
    @Resource(name="estudioService")
    private EstudioService estudioService;

    @RequestMapping(value = "enterForm", method = RequestMethod.GET)
    public String initCreation(Model model) {
        logger.debug("Búsqueda de Solicitud de Muestra");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (usuario.getUserCenter()!=null){
            model.addAttribute("usuario", usuario);
            return "alics/searchRequestForm";
        }
        else{
            return "403";
        }
    }

    @RequestMapping(value = "searchRequests", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SampleRequest> fetchRequestJson(@RequestParam(value = "initDate") String initDate
            , @RequestParam( value="endDate" ) String endDate) throws ParseException {
        logger.info("Obteniendo informacion de Solicitudes en JSON");
        List<SampleRequest> reqList;
        reqList = requestSheetService.getAllRequestByDates(StringToDate2(initDate, "dd/MM/yyyy" ), StringToDate2( endDate, "dd/MM/yyyy" ))  ;
        return reqList;
    }

    @RequestMapping(value = "searchRequest", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<SampleRequestDetail>fetchReqJson(@RequestParam(value = "code") String code) {
        logger.info("Obteniendo informacion de Solicitud en JSON");
        List<SampleRequestDetail> req;
        req = requestSheetService.getRequestByCode(code);
        return req;
    }

    @RequestMapping(value = "requestSheet", method = RequestMethod.GET)
    public ModelAndView downloadExcelRequestSheet(@RequestParam(value = "code") String code) {
        // create some sample data
        logger.info("Obteniendo los datos para Reporte de Solicitudes de Muestras ");
        SampleRequest request = requestService.getRequestById(code);
        List<SampleRequestDetail> req = requestSheetService.getRequestByCode(code);
        List<String> columnas = new ArrayList<>();
        ModelAndView excelView = new ModelAndView("excelView");
        setNombreColumnasReqSheet(columnas);
        excelView.addObject("header1", messageResource.getMensaje("request_date" ).getSpanish());
        excelView.addObject("header2", messageResource.getMensaje("resp_request" ).getSpanish());
        excelView.addObject("header3", messageResource.getMensaje("approvedBy" ).getSpanish());
        excelView.addObject("title", messageResource.getMensaje("reqTitle" ).getSpanish());
        excelView.addObject("detail", req);
        excelView.addObject("columns", columnas);
        excelView.addObject("request", request);
        excelView.addObject("report", messageResource.getMensaje("requestMxICS").getSpanish());
        excelView.addObject("detailTitle", messageResource.getMensaje("detailTitle").getSpanish());

        return excelView;
    }


    private void setNombreColumnasReqSheet(List<String> columnas){
        columnas.add(messageResource.getMensaje("numLabel" ).getSpanish());
        columnas.add(messageResource.getMensaje("code" ).getSpanish());
        columnas.add(messageResource.getMensaje("aliCode" ).getSpanish());
        columnas.add(messageResource.getMensaje("aliVol2" ).getSpanish());
        columnas.add(messageResource.getMensaje("subAliVol" ).getSpanish());
        columnas.add(messageResource.getMensaje("boxStudy" ).getSpanish());
        columnas.add(messageResource.getMensaje("alicTypeSample" ).getSpanish());
        columnas.add(messageResource.getMensaje("sampling_date" ).getSpanish());
        columnas.add(messageResource.getMensaje("purpose_request" ).getSpanish());
        columnas.add(messageResource.getMensaje("username" ).getSpanish());
        columnas.add(messageResource.getMensaje("destination" ).getSpanish());
        columnas.add(messageResource.getMensaje("centerObs" ).getSpanish());
    }

    /**
     * Convierte un string a Date según el formato indicado
     * @param strFecha cadena a convertir
     * @param formato formato solicitado
     * @return Fecha
     * @throws java.text.ParseException
     */
    public static Date StringToDate2(String strFecha, String formato) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
        return simpleDateFormat.parse(strFecha);
    }

}
