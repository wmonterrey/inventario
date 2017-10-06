package ni.org.ics.lab.inventario.web.controller.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import ni.org.ics.lab.inventario.domain.Center;
import ni.org.ics.lab.inventario.domain.audit.AuditTrail;
import ni.org.ics.lab.inventario.domain.relationships.UserCenter;
import ni.org.ics.lab.inventario.domain.relationships.UserCenterId;
import ni.org.ics.lab.inventario.service.AuditTrailService;
import ni.org.ics.lab.inventario.service.CentroService;
import ni.org.ics.lab.inventario.service.UserCenterService;
import ni.org.ics.lab.inventario.service.UsuarioService;
import ni.org.ics.lab.inventario.users.model.Authority;
import ni.org.ics.lab.inventario.users.model.AuthorityId;
import ni.org.ics.lab.inventario.users.model.Rol;
import ni.org.ics.lab.inventario.users.model.UserAccess;
import ni.org.ics.lab.inventario.users.model.UserSistema;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

/**
 * Controlador web de peticiones relacionadas a usuarios
 * 
 * @author William Avilés
 */
@Controller
@RequestMapping("/admin/users/*")
public class AdminUsuariosController {
	private static final Logger logger = LoggerFactory.getLogger(AdminUsuariosController.class);
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	@Resource(name="userCenterService")
	private UserCenterService userCenterService;
	@Resource(name="auditTrailService")
	private AuditTrailService auditTrailService;	
	@Resource(name="centroService")
	private CentroService centroService;
    
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String obtenerUsuarios(Model model) throws ParseException { 	
    	logger.debug("Mostrando Usuarios en JSP");
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<UserSistema> usuarios = usuarioService.getUsersbyCenter(usuarioActual);
    	model.addAttribute("usuarios", usuarios);
    	model.addAttribute("usuario", usuarioActual);
    	return "admin/users/list";
	}
	
	/**
     * Custom handler for displaying an user.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/{username}/")
    public ModelAndView showUser(@PathVariable("username") String username) {
    	ModelAndView mav;
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	UserSistema user = this.usuarioService.getUsuarioEditar(username,usuarioActual);
        if(user==null){
        	mav = new ModelAndView("403");
        }
        else{
        	mav = new ModelAndView("admin/users/user");
            List<UserAccess> accesosUsuario = usuarioService.getUserAccess(username);
            List<AuditTrail> bitacoraUsuario = auditTrailService.getBitacora(username);
            mav.addObject("user",user);
            mav.addObject("accesses",accesosUsuario);
            mav.addObject("bitacora",bitacoraUsuario);
            List<Authority> rolesusuario = this.usuarioService.getRolesUsuarioTodos(username);
            mav.addObject("rolesusuario", rolesusuario);
            List<UserCenter> centrosusuario = this.userCenterService.getAllCentersUser(username);
            mav.addObject("centrosusuario", centrosusuario);
            mav.addObject("usuario", usuarioActual);
        }
        return mav;
    }
    
	
	/**
     * Custom handler for adding an user.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "newUser", method = RequestMethod.GET)
	public String initAddUserForm(Model model) {
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	List<Rol> roles = usuarioService.getRoles(usuarioActual.getUsername());
	    model.addAttribute("roles", roles);
	    List<UserCenter> centros = this.userCenterService.getActiveCentersUser(usuarioActual.getUsername());
    	model.addAttribute("centros", centros);
	    model.addAttribute("agregando",true);
	    model.addAttribute("usuario", usuarioActual);
		return "admin/users/enterForm";
	} 
    
	/**
     * Custom handler for editing an user.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "editUser/{username}/", method = RequestMethod.GET)
	public String initUpdateUserForm(@PathVariable("username") String username, Model model) {
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		UserSistema usuarioEditar = this.usuarioService.getUsuarioEditar(username, usuarioActual);
		if(usuarioEditar!=null){
			model.addAttribute("user",usuarioEditar);
			List<Rol> roles = usuarioService.getRoles(usuarioActual.getUsername());
			List<UserCenter> centros = this.userCenterService.getActiveCentersUser(usuarioActual.getUsername());
	    	model.addAttribute("roles", roles);
	    	model.addAttribute("centros", centros);
	    	List<Authority> rolesusuario = this.usuarioService.getRolesUsuario(username);
	    	List<UserCenter> centrosusuario = this.userCenterService.getActiveCentersUser(username);
	    	model.addAttribute("rolesusuario", rolesusuario);
	    	model.addAttribute("centrosusuario", centrosusuario);
	    	model.addAttribute("editando",true);
            model.addAttribute("agregando",false);
            model.addAttribute("usuario", usuarioActual);
			return "admin/users/enterForm";
		}
		else{
			return "403";
		}
	}   
    
    
    @RequestMapping( value="saveUser", method=RequestMethod.POST)
	public ResponseEntity<String> processUpdateUserForm( @RequestParam(value="username", required=true ) String userName
	        , @RequestParam( value="completeName", required=true ) String completeName
	        , @RequestParam( value="confirm_password", required=false ) String confirmPassword
	        , @RequestParam( value="password", required=false, defaultValue="" ) String password
	        , @RequestParam( value="email", required=true, defaultValue="" ) String email
	        , @RequestParam( value="authorities", required=false, defaultValue="") List<String> authorities
	        , @RequestParam( value="centers", required=false, defaultValue="") List<String> centers
	        )
	{
    	try{
	    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
			//UserSistema user = this.usuarioService.getUsuarioEditar(userName,usuarioActual);
	    	UserSistema user = this.usuarioService.getUser(userName);
	    	if (user==null){
	    		user = new UserSistema();
	    		user.setUsername(userName);
	    		user.setCompleteName(completeName);
	    		user.setEmail(email);
	    		user.setCreated(new Date());
	    		user.setCreatedBy(usuarioActual.getUsername());
	    		user.setModified(new Date());
	    		user.setModifiedBy(usuarioActual.getUsername());
	    		user.setLastCredentialChange(new Date());
	    		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
	    		String encodedPass = encoder.encode(password);
	    		user.setPassword(encodedPass);
	    		this.usuarioService.saveUser(user);
	    		for(String a:authorities){
	    			Authority auth = new Authority();
	    			auth.setAuthId(new AuthorityId(userName,a));
					auth.setRecordUser(usuarioActual.getUsername());
					auth.setRecordDate(new Date());
					this.usuarioService.saveRoleUser(auth);
				}
	    		for(String c:centers){
	    			UserCenter uc = new UserCenter();
	    			uc.setUserCenterId(new UserCenterId(userName,c));
					uc.setRecordUser(usuarioActual.getUsername());
					uc.setRecordDate(new Date());
					this.userCenterService.saveUserCenter(uc);
				}
	    	}
	    	else{
				user.setModifiedBy(usuarioActual.getUsername());
				user.setCompleteName(completeName);
				user.setEmail(email);
				user.setModified(new Date());
				this.usuarioService.saveUser(user);
				//Recupera los roles activos de este usuario de la base de datos y pone el username en una lista
				List<String> rolesUsuario = new ArrayList<String>();
				List<Authority> rolesusuario = this.usuarioService.getRolesUsuario(userName);
				for(Authority rolActual:rolesusuario){
					rolesUsuario.add(rolActual.getAuthId().getAuthority());
				}
				//Recorre los roles seleccionados en el formulario
				for(String a:authorities){
					boolean encontreRolBD = false;
					//Recorre los roles actuales del usuario
					for(String rActual:rolesUsuario){
						if(rActual.equals(a)){
							encontreRolBD=true;
							break;
						}
					}
					//Si no encuentra el rol seleccionado en los roles actuales ingresa un nuevo rol o lo actualiza
					if (!encontreRolBD){
						Authority nuevoRol = new Authority(new AuthorityId(userName,a), new Date(), usuarioActual.getUsername());
						this.usuarioService.saveRoleUser(nuevoRol);
					}
				}
				//Recorre los roles actuales
				for(String rActual:rolesUsuario){
					boolean encontreRolForm = false;
					//Recorre los roles seleccionados en el formulario
					for(String r:authorities){
						if(rActual.equals(r)){
							encontreRolForm=true;
							break;
						}
					}
					//Si no encuentra el rol actual en los roles seleccionados lo pone en pasivo
					if (!encontreRolForm){
						Authority rol = this.usuarioService.getRolUsuario(userName,rActual);
						rol.setPasive('1');
						this.usuarioService.saveRoleUser(rol);
					}
				}
				//Recupera los centros activos de este usuario de la base de datos y pone el centro en una lista
				List<String> centrosUsuario = new ArrayList<String>();
				List<UserCenter> centrosusuario = this.userCenterService.getActiveCentersUser(userName);
				for(UserCenter centroActual:centrosusuario){
					centrosUsuario.add(centroActual.getUserCenterId().getCenter());
				}
				//Recorre los centros seleccionados en el formulario
				for(String c:centers){
					boolean encontreCentroBD = false;
					//Recorre los centros actuales del usuario
					for(String cActual:centrosUsuario){
						if(cActual.equals(c)){
							encontreCentroBD=true;
							break;
						}
					}
					//Si no encuentra el centro seleccionado en los centros actuales ingresa un nuevo centro o lo actualiza
					if (!encontreCentroBD){
						Center centro = centroService.getCentro(c);
						UserCenter nuevoCentro = new UserCenter(new UserCenterId(userName,c), user, centro, usuarioActual.getUsername(), new Date());
						this.userCenterService.saveUserCenter(nuevoCentro);
					}
				}
				//Recorre los centros actuales
				for(String cActual:centrosUsuario){
					boolean encontreCentroForm = false;
					//Recorre los centros seleccionados en el formulario
					for(String c:centers){
						if(cActual.equals(c)){
							encontreCentroForm=true;
							break;
						}
					}
					//Si no encuentra el centro actual en los centros seleccionados lo pone en pasivo
					if (!encontreCentroForm){
						UserCenter centro = this.userCenterService.getUserCenter(userName, cActual);
						centro.setPasive('1');
						this.userCenterService.saveUserCenter(centro);
					}
				}
	    	}
			
			return createJsonResponse(user);
    	}
    	catch(Exception e){
    		Gson gson = new Gson();
    	    String json = gson.toJson(e.toString());
    		return new ResponseEntity<String>( json, HttpStatus.CREATED);
    	}
	}
    
    /**
     * Custom handler for enabling/disabling an user.
     *
     * @param username the ID of the user to enable
     * @return a String
     */
    @RequestMapping("/habdes/{accion}/{username}/")
    public String enableUser(@PathVariable("username") String username, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	boolean hab;
    	if (accion.matches("enable1")){
    		redirecTo = "redirect:/admin/users/";
    		hab = true;
    		redirectAttributes.addFlashAttribute("usuarioHabilitado", true);
        }
        else if (accion.matches("enable2")){
        	redirecTo = "redirect:/admin/users/{username}/";
    		hab = true;
    		redirectAttributes.addFlashAttribute("usuarioHabilitado", true);
        }
        else if(accion.matches("disable1")){
        	redirecTo = "redirect:/admin/users/";
    		hab = false;
    		redirectAttributes.addFlashAttribute("usuarioDeshabilitado", true);
        }
        else if(accion.matches("disable2")){
        	redirecTo = "redirect:/admin/users/{username}/";
    		hab = false;
    		redirectAttributes.addFlashAttribute("usuarioDeshabilitado", true);
        }
        else{
        	return redirecTo;
        }
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		UserSistema user = this.usuarioService.getUsuarioEditar(username, usuarioActual);
    	if(user!=null){
    		user.setModified(new Date());
    		user.setModifiedBy(usuarioActual.getUsername());
    		user.setEnabled(hab);
    		this.usuarioService.saveUser(user);
    		redirectAttributes.addFlashAttribute("nombreUsuario", username);
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }
    
    /**
     * Custom handler for locking/unlocking an user.
     *
     * @param username the ID of the user to lock/unlock
     * @return a String
     */
    @RequestMapping("/lockunl/{accion}/{username}/")
    public String lockUnlockUser(@PathVariable("username") String username, 
    		@PathVariable("accion") String accion, RedirectAttributes redirectAttributes) {
    	String redirecTo="404";
    	boolean lock;
    	if (accion.matches("lock1")){
    		redirecTo = "redirect:/admin/users/";
    		lock = true;
    		redirectAttributes.addFlashAttribute("usuarioBloqueado", true);
        }
        else if (accion.matches("lock2")){
        	redirecTo = "redirect:/admin/users/{username}/";
    		lock = true;
    		redirectAttributes.addFlashAttribute("usuarioBloqueado", true);
        }
        else if(accion.matches("unlock1")){
        	redirecTo = "redirect:/admin/users/";
    		lock = false;
    		redirectAttributes.addFlashAttribute("usuarioNoBloqueado", true);
        }
        else if(accion.matches("unlock2")){
        	redirecTo = "redirect:/admin/users/{username}/";
    		lock = false;
    		redirectAttributes.addFlashAttribute("usuarioNoBloqueado", true);
        }
        else{
        	return redirecTo;
        }
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
		UserSistema user = this.usuarioService.getUsuarioEditar(username, usuarioActual);
    	if(user!=null){
    		user.setModified(new Date());
    		user.setModifiedBy(usuarioActual.getUsername());
    		user.setAccountNonLocked(!lock);
    		this.usuarioService.saveUser(user);
    		redirectAttributes.addFlashAttribute("nombreUsuario", username);
    	}
    	else{
    		redirecTo = "403";
    	}
    	return redirecTo;	
    }    
    
    /**
     * Custom handler for changing an user password.
     *
     * @param username the ID of the user to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping(value = "chgpass/{username}/", method = RequestMethod.GET)
	public String initChangePassForm(@PathVariable("username") String username, Model model) {
    	UserSistema usuarioActual = this.usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	UserSistema usertoChange = this.usuarioService.getUsuarioEditar(username, usuarioActual);
		if(usertoChange!=null){
			model.addAttribute("user",usertoChange);
			model.addAttribute("usuario",usuarioActual);
			return "admin/users/chgpass";
		}
		else{
			return "403";
		}
	}
    
    @RequestMapping( value="chgPass", method=RequestMethod.POST)
	public ResponseEntity<String> processChangePassForm( @RequestParam(value="username", required=true ) String userName
			, @RequestParam( value="password", required=true ) String password
	        )
	{
    	UserSistema usuario = usuarioService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
    	UserSistema user = usuarioService.getUsuarioEditar(userName, usuario);
    	try{
			user.setModifiedBy(usuario.getUsername());
			user.setModified(new Date());
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			String encodedPass = encoder.encode(password);
			user.setPassword(encodedPass);
			user.setLastCredentialChange(new Date());
			this.usuarioService.saveUser(user);
			return createJsonResponse(user);
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
