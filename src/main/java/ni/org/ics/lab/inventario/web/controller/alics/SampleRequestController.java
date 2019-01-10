package ni.org.ics.lab.inventario.web.controller.alics;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ni.org.ics.lab.inventario.domain.*;
import ni.org.ics.lab.inventario.domain.relationships.StudyCenter;
import ni.org.ics.lab.inventario.service.*;
import ni.org.ics.lab.inventario.users.model.UserSistema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ics
 */
@Controller
@RequestMapping("/alics/request/samplerequest/*")
public class SampleRequestController {

    private static final Logger logger = LoggerFactory.getLogger(SampleRequestController.class);
    @Resource(name="usuarioService")
    private UsuarioService usuarioService;
    @Resource(name="studyCenterService")
    private StudyCenterService studyCenterService;
    @Resource(name="messageResourceService")
    private MessageResourceService messageResourceService;
    @Resource(name="sampleRequestService")
    private SampleRequestService sampleRequestService;
    @Resource(name="estudioService")
    private EstudioService estudioService;


    @RequestMapping(value = "enterForm", method = RequestMethod.GET)
    public String initCreation(Model model) {
        logger.debug("Creando Solicitud de Muestra");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        if (usuario.getUserCenter()!=null){
            List<StudyCenter> estudios = studyCenterService.getActiveStudyCenter(usuario.getUserCenter().getCenterCode());
            model.addAttribute("estudios", estudios);
            model.addAttribute("usuario", usuario);
            return "alics/sampleRequestForm";
        }
        else{
            return "403";
        }
    }

    @RequestMapping( value="saveRequest", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void processSaveForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String json;
        String resultado ="";
        String codes ="";
        Date requestDate = null;
        String approvedBy ="";
        String respRequest ="";

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF8"));
            json = br.readLine();
            //Recuperando Json enviado desde el cliente
            JsonObject jsonpObject = new Gson().fromJson(json, JsonObject.class);

            if (jsonpObject.get("codes") != null && !jsonpObject.get("codes").toString().isEmpty())
                codes = jsonpObject.get("codes").toString();

            if (jsonpObject.get("approvedBy") != null && !jsonpObject.get("approvedBy").getAsString().isEmpty())
                approvedBy = jsonpObject.get("approvedBy").getAsString();

            if (jsonpObject.get("respRequest") != null && !jsonpObject.get("respRequest").getAsString().isEmpty())
                respRequest = jsonpObject.get("respRequest").getAsString();

            if (jsonpObject.get("requestDate") != null && !jsonpObject.get("requestDate").getAsString().isEmpty())
                requestDate = StringToDate(jsonpObject.get("requestDate").getAsString()+" 00:00:00");

            UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());


            if(codes != ""){

                //adding sample request
                SampleRequest req = new SampleRequest();
                req.setAuthorizedBy(approvedBy);
                req.setRequestDate(requestDate);
                req.setRespRequest(respRequest);
                req.setRecordUser(usuario.getUsername());
                req.setRecordDate(new Date());
                sampleRequestService.saveSampleRequest(req);

                String sampleReqDet = codes.replace("[[", "" );
                String sampleReqDet1 = sampleReqDet.replace("]]", "" );
                String sampleReqDet2 = sampleReqDet1.replace("\"", "" );

                String[] sample = sampleReqDet2.split( "],"  );




                for (int i = 0; i < sample.length; i++ ) {
                    String[] sam = sample[i].split( "," );
                    SampleRequestDetail detail = new SampleRequestDetail();
                    detail.setIdRequest(req);
                    detail.setRecordUser(usuario.getUsername());
                    detail.setRecordDate(new Date());

                    if(sam[0] != null){
                        if (sam[0].contains("[")){
                           detail.setCode(sam[0].replace("[", "") );
                        }else{
                            detail.setCode(sam[0]);
                        }

                    }

                    if(sam[1] != null){
                        detail.setAliCode(sam[1]);
                    }

                    if(!sam[2].isEmpty()){
                        detail.setAliVol(Float.parseFloat( sam[2]) );
                    }

                    if(!sam[3].isEmpty()){
                        detail.setSubAliVol(Float.parseFloat( sam[3]) );
                    }

                    if(sam[4] != null){
                        Study study = estudioService.getStudy( sam[4]);
                        detail.setStudy(study);
                    }

                    if(sam[5] != null){
                        detail.setAlicTypeName(sam[5]);
                    }

                    if(sam[6] != null){
                        detail.setSamplingDate(StringToDate2(sam[6],"dd/MM/yyyy" ));
                    }

                    if(sam[7] != null){
                        detail.setPurposeRequest(sam[7]);
                    }

                    if(sam[8] != null){
                        detail.setDestination(sam[8]);
                    }


                    if(!sam[9].equals("")){
                            detail.setComments(sam[9]);
                        }



                    sampleRequestService.saveSampleReqDetail(detail);

                }

            }



        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            ex.printStackTrace();
            resultado = String.valueOf( messageResourceService.getMensaje("process.errors" ) );
            resultado=resultado+". \n "+ex.getMessage();

        }finally {
            Map<String, String> map = new HashMap<>();
            map.put("mensaje",resultado);
            String jsonResponse = new Gson().toJson(map);
            response.getOutputStream().write(jsonResponse.getBytes());
            response.getOutputStream().close();
        }
    }

    /**
     * Convierte un string a Date con formato dd/MM/yyyy HH:mm:ss
     * @param strFecha cadena a convertir
     * @return Fecha
     * @throws java.text.ParseException
     */
    public static Date StringToDate(String strFecha) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return simpleDateFormat.parse(strFecha);
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
