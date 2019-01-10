package ni.org.ics.lab.inventario.web.controller.catalogs;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.service.AliquotTypeService;
import ni.org.ics.lab.inventario.service.AuditTrailService;
import ni.org.ics.lab.inventario.service.MessageResourceService;
import ni.org.ics.lab.inventario.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controlador web de peticiones relacionadas a super administrador
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/catalog/all/*")
public class CatalogGeneralController {
	private static final Logger logger = LoggerFactory.getLogger(CatalogGeneralController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="aliquotTypeService")
	private AliquotTypeService aliquotTypeService;
	@Resource(name="messageResourceService")
	private MessageResourceService messageResourceService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;
	
	/**
     * Controlador para presentar catalogos.
     * @param model Modelo enlazado a la vista
     * @return String con la vista
     */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerCatalogos(Model model){ 	
    	logger.debug("Catalogos");
    	return "catalogos/general/list";
	}
	
}
