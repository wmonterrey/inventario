package ni.org.ics.lab.inventario.web.controller.alics;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ni.org.ics.lab.inventario.domain.Aliquot;
import ni.org.ics.lab.inventario.domain.AliquotOutput;
import ni.org.ics.lab.inventario.domain.relationships.StudyCenter;
import ni.org.ics.lab.inventario.domain.utils.FileData;
import ni.org.ics.lab.inventario.language.MessageResource;
import ni.org.ics.lab.inventario.service.*;
import ni.org.ics.lab.inventario.users.model.UserSistema;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controlador web de peticiones relacionadas a salida de Alicuotas
 * @author ics
 */
@Controller
@RequestMapping("/alicoutput/*")
public class AliquotOutputController {

    private static final Logger logger = LoggerFactory.getLogger(AliquotUseController.class);
    @Resource(name="usuarioService")
    private UsuarioService usuarioService;
    @Resource(name="studyCenterService")
    private StudyCenterService studyCenterService;
    @Resource(name="messageResourceService")
    private MessageResourceService messageResourceService;
    @Resource(name="aliquotService")
    private AliquotService aliquotService;
    @Resource(name="alicOutputService")
    private AliquotOutputService alicOutputService;

    LinkedList<FileData> files = null;
    FileData fileData = null;



    @RequestMapping(value = "exitForm", method = RequestMethod.GET)
    public String initCreation(Model model) {
        logger.debug("Creando salida de Alicuotas");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<StudyCenter> estudios = studyCenterService.getActiveStudyCenter(usuario.getUserCenter().getCenterCode());
        List<MessageResource> msj = messageResourceService.getCatalogo("medioCat");
        if (usuario.getUserCenter()!=null){
            model.addAttribute("usuario", usuario);
            model.addAttribute("estudios", estudios);
            model.addAttribute("msj", msj);
            return "alics/exitFormAlic";
        }
        else{
            return "403";
        }
    }


    @RequestMapping( value="saveExit", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void processSaveForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String json;
        String resultado ="";
        String codes ="";
        String codes2 ="";
        String enic ="";
        String transportation ="";
        String containerNum ="";
        String carrier ="";
        Date sendDate =null;
        String destination ="";
        String searchManager ="";
        String packingManager="";
        String boxNum="";
        String requestBy ="";
        String approveBy ="";
        String purpose ="";

        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF8"));
            json = br.readLine();
            //Recuperando Json enviado desde el cliente
            JsonObject jsonpObject = new Gson().fromJson(json, JsonObject.class);

            if (jsonpObject.get("codes") != null && !jsonpObject.get("codes").getAsString().isEmpty())
                codes = jsonpObject.get("codes").getAsString();

            if (jsonpObject.get("codes2") != null && !jsonpObject.get("codes2").getAsString().isEmpty())
                codes2 = jsonpObject.get("codes2").getAsString();

            if (jsonpObject.get("enic") != null && !jsonpObject.get("enic").getAsString().isEmpty())
                enic = jsonpObject.get("enic").getAsString();

            if (jsonpObject.get("transportation") != null && !jsonpObject.get("transportation").getAsString().isEmpty())
                transportation = jsonpObject.get("transportation").getAsString();

            if (jsonpObject.get("containerNum") != null && !jsonpObject.get("containerNum").getAsString().isEmpty())
                containerNum = jsonpObject.get("containerNum").getAsString();

            if (jsonpObject.get("carrier") != null && !jsonpObject.get("carrier").getAsString().isEmpty())
                carrier = jsonpObject.get("carrier").getAsString();

            if (jsonpObject.get("sendDate") != null && !jsonpObject.get("sendDate").getAsString().isEmpty())
                sendDate = StringToDate(jsonpObject.get("sendDate").getAsString()+" 00:00:00");

            if (jsonpObject.get("destination") != null && !jsonpObject.get("destination").getAsString().isEmpty())
                destination = jsonpObject.get("destination").getAsString();

            if (jsonpObject.get("searchManager") != null && !jsonpObject.get("searchManager").getAsString().isEmpty())
                searchManager = jsonpObject.get("searchManager").getAsString();

            if (jsonpObject.get("packingManager") != null && !jsonpObject.get("packingManager").getAsString().isEmpty())
                packingManager = jsonpObject.get("packingManager").getAsString();

            if (jsonpObject.get("boxNum") != null && !jsonpObject.get("boxNum").getAsString().isEmpty())
                boxNum = jsonpObject.get("boxNum").getAsString();

            if (jsonpObject.get("requestBy") != null && !jsonpObject.get("requestBy").getAsString().isEmpty())
                requestBy = jsonpObject.get("requestBy").getAsString();

            if (jsonpObject.get("approveBy") != null && !jsonpObject.get("approveBy").getAsString().isEmpty())
                approveBy = jsonpObject.get("approveBy").getAsString();

            if (jsonpObject.get("purpose") != null && !jsonpObject.get("purpose").getAsString().isEmpty())
                purpose = jsonpObject.get("purpose").getAsString();

            UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());


            if(codes != ""){

                String[] arrayCodes = codes.split(",");
                for (String aliCode : arrayCodes) {

                    //search alicode
                    Aliquot alic = aliquotService.getAliquot(aliCode, usuario.getUsername());
                    AliquotOutput alicOut = new AliquotOutput();

                    alicOut.setAliCode(alic.getAliCode());
                    alicOut.setRecordUser(usuario.getUsername());
                    alicOut.setRecordDate(new Date());
                    alicOut.setAliCond(alic.getAliCond());
                    alicOut.setAliDestination(destination);
                    alicOut.setAliPosition(alic.getAliPosition());
                    alicOut.setAliRes(alic.getAliRes());
                    alicOut.setAliSep(alic.getAliSep());
                    alicOut.setAliVol(alic.getAliVol());
                    alicOut.setAlicTypeName(alic.getAlicTypeName());
                    alicOut.setApprove(approveBy);
                    alicOut.setBoxNum(boxNum);
                    alicOut.setCarrier(carrier);
                    alicOut.setCodUser(alic.getRecordUser());
                    alicOut.setContainerNum(containerNum);
                    alicOut.setDepartureDate(sendDate);
                    alicOut.seteNic(enic);
                    alicOut.setPackingManager(packingManager);
                    alicOut.setSearchManager(searchManager);
                    alicOut.setPurpose(purpose);
                    alicOut.setRequest(requestBy);
                    alicOut.setTransportation(transportation);
                    alicOut.setAliBox(alic.getAliBox().getBoxCode());
                    alicOut.setAliRack(alic.getAliBox().getBoxRack().getRackCode());
                    alicOut.setAliEquip(alic.getAliBox().getBoxRack().getRackEquip().getEquipCode());

                    alicOutputService.saveAliquotUse(alicOut);

                    //update alic
                    alic.setPasive( '1' );
                    aliquotService.saveAliquot(alic);

                }

            }

            if(codes2 != ""){

                String[] arrayCodes = codes2.split(",");
                for (String aliCode : arrayCodes) {

                    //search alicode
                    AliquotOutput alicOut = new AliquotOutput();

                    alicOut.setAliCode(aliCode);
                    alicOut.setRecordUser(usuario.getUsername());
                    alicOut.setRecordDate(new Date());
                    alicOut.setAliDestination(destination);
                    alicOut.setApprove(approveBy);
                    alicOut.setBoxNum(boxNum);
                    alicOut.setCarrier(carrier);
                    alicOut.setContainerNum(containerNum);
                    alicOut.setDepartureDate(sendDate);
                    alicOut.seteNic(enic);
                    alicOut.setPackingManager(packingManager);
                    alicOut.setSearchManager(searchManager);
                    alicOut.setPurpose(purpose);
                    alicOut.setRequest(requestBy);
                    alicOut.setTransportation(transportation);
                    alicOutputService.saveAliquotUse(alicOut);

                }

            }


    } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            ex.printStackTrace();
            resultado = String.valueOf( messageResourceService.getMensaje("alicDuplicated" ) );
            resultado=resultado+". \n "+ex.getMessage();

        }finally {
            Map<String, String> map = new HashMap<>();
            map.put("mensaje",resultado);
            String jsonResponse = new Gson().toJson(map);
            response.getOutputStream().write(jsonResponse.getBytes());
            response.getOutputStream().close();
        }
    }


    /***************************************************
     * upload(): receives files
     * @param request : MultipartHttpServletRequest auto passed
     * @param response : HttpServletResponse auto passed
     * @return LinkedList<FileData> as json format
     ****************************************************/
    @RequestMapping(value="/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    LinkedList<FileData> uploadFile(MultipartHttpServletRequest request, HttpServletResponse response, @RequestParam(value="rows") int rows, @RequestParam(value="columns") int col) throws IOException {
        logger.info("Importando archivo excel");

        //1. build an iterator
        Iterator<String> itr =  request.getFileNames();
        MultipartFile mpf = null;
        files = new LinkedList<>();

        //2. get each file
        while(itr.hasNext()){
            mpf = request.getFile(itr.next());
            InputStream input = new ByteArrayInputStream(mpf.getBytes());

            try {
                Workbook workbook = new XSSFWorkbook( input );
                Sheet firstSheet = workbook.getSheetAt( 0 );

                if (rows > 0 && col > 0) {
                    for (int j = 0; j <= rows - 1; j++) {
                        Row row = firstSheet.getRow(j);

                        fileData = new FileData();
                        if (row != null) {
                            for (int i = 0; i <= col - 1; i++) {
                                Cell cell = row.getCell(i);
                                if (cell != null) {
                                    fileData.setAliCode( cell.toString() );
                                }
                            }
                        }
                        files.add( fileData );
                    }
                }
                workbook.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

        }

        return files;
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




}
