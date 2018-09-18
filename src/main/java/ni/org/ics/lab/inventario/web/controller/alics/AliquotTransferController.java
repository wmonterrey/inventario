package ni.org.ics.lab.inventario.web.controller.alics;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import ni.org.ics.lab.inventario.domain.Aliquot;
import ni.org.ics.lab.inventario.domain.AliquotTransfers;
import ni.org.ics.lab.inventario.domain.Center;
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
@RequestMapping("/alictransfer*")
public class AliquotTransferController {

    private static final Logger logger = LoggerFactory.getLogger(AliquotUseController.class);
    @Resource(name="usuarioService")
    private UsuarioService usuarioService;
    @Resource(name="studyCenterService")
    private StudyCenterService studyCenterService;
    @Resource(name="messageResourceService")
    private MessageResourceService messageResourceService;
    @Resource(name="aliquotService")
    private AliquotService aliquotService;
    @Resource(name="alicTransferService")
    private AliquotTransferService alicTransferService;
    @Resource(name="centroService")
    private CentroService centroService;

    LinkedList<FileData> files = null;
    FileData fileData = null;



    @RequestMapping(value = "transferAlic", method = RequestMethod.GET)
    public String initCreation(Model model) {
        logger.debug("Creando transferencia de Alicuotas");
        UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<StudyCenter> estudios = studyCenterService.getActiveStudyCenter(usuario.getUserCenter().getCenterCode());
        List<Center> centers = centroService.getCentrosActivos2(usuario.getUserCenter().getCenterCode());
        List<MessageResource> msj = messageResourceService.getCatalogo("medioCat");
        if (usuario.getUserCenter()!=null){
            model.addAttribute("usuario", usuario);
            model.addAttribute("estudios", estudios);
            model.addAttribute("centers", centers);
            model.addAttribute("msj", msj);
            return "alics/transferFormAlic";
        }
        else{
            return "403";
        }
    }


    @RequestMapping( value="saveTransfer", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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
        String center = "";

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

            if (jsonpObject.get("center") != null && !jsonpObject.get("center").getAsString().isEmpty())
                center = jsonpObject.get("center").getAsString();

            UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());


            if(codes != ""){

                String[] arrayCodes = codes.split(",");
                for (String aliCode : arrayCodes) {

                    //search alicode
                    Aliquot alic = aliquotService.getAliquot(aliCode, usuario.getUsername());
                    AliquotTransfers alicTransfer = new AliquotTransfers();

                    alicTransfer.setAliCode(alic.getAliCode());
                    alicTransfer.setRecordUser(usuario.getUsername());
                    alicTransfer.setRecordDate(new Date());
                    alicTransfer.setAliCond(alic.getAliCond());
                    alicTransfer.setAliDestination(destination);
                    alicTransfer.setAliPosition(alic.getAliPosition());
                    alicTransfer.setAliRes(alic.getAliRes());
                    alicTransfer.setAliSep(alic.getAliSep());
                    alicTransfer.setAliVol(alic.getAliVol());
                    alicTransfer.setAlicTypeName(alic.getAlicTypeName());
                    alicTransfer.setApprove(approveBy);
                    alicTransfer.setBoxNum(boxNum);
                    alicTransfer.setCarrier(carrier);
                    alicTransfer.setCodUser(alic.getRecordUser());
                    alicTransfer.setContainerNum(containerNum);
                    alicTransfer.setDepartureDate(sendDate);
                    alicTransfer.seteNic(enic);
                    alicTransfer.setPackingManager(packingManager);
                    alicTransfer.setSearchManager(searchManager);
                    alicTransfer.setPurpose(purpose);
                    alicTransfer.setRequest(requestBy);
                    alicTransfer.setTransportation(transportation);
                    alicTransfer.setAliBox(alic.getAliBox().getBoxCode());
                    alicTransfer.setAliRack(alic.getAliBox().getBoxRack().getRackCode());
                    alicTransfer.setAliEquip(alic.getAliBox().getBoxRack().getRackEquip().getEquipCode());
                    alicTransfer.setCenterCode(center);

                    alicTransferService.saveAliquotTransfer(alicTransfer);

                    //update alic
                    alic.setPasive( '1' );
                    aliquotService.saveAliquot(alic);

                }

            }

            if(codes2 != ""){

                String[] arrayCodes = codes2.split(",");
                for (String aliCode : arrayCodes) {

                    AliquotTransfers alicTransf = new AliquotTransfers();

                    alicTransf.setAliCode(aliCode);
                    alicTransf.setRecordUser(usuario.getUsername());
                    alicTransf.setRecordDate(new Date());
                    alicTransf.setAliDestination(destination);
                    alicTransf.setApprove(approveBy);
                    alicTransf.setBoxNum(boxNum);
                    alicTransf.setCarrier(carrier);
                    alicTransf.setContainerNum(containerNum);
                    alicTransf.setDepartureDate(sendDate);
                    alicTransf.seteNic(enic);
                    alicTransf.setPackingManager(packingManager);
                    alicTransf.setSearchManager(searchManager);
                    alicTransf.setPurpose(purpose);
                    alicTransf.setRequest(requestBy);
                    alicTransf.setTransportation(transportation);
                    alicTransf.setCenterCode(center);
                    alicTransferService.saveAliquotTransfer(alicTransf);

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
     * @throws ParseException
     */
    public static Date StringToDate(String strFecha) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return simpleDateFormat.parse(strFecha);
    }




}
