package eb.onlinedc.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import eb.dcbackend.model.User;
import eb.dcbackend.model.UserProfile;
import eb.dcbackend.service.UserProfileService;
import eb.dcbackend.service.UserService;
import eb.dcbackend.dao.SecondEntityDAO;

@Controller
@RequestMapping("/")
public class AppController {


	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	@Autowired
	SecondEntityDAO secondEntityDAO;

	/**
	 * Este metodo maneja las peticiones GET de inicio de sesion Si los usarios se
	 * encuentran autenticados e intentan ir a la pagina de login.jsp los devuelve
	 * al main.jsp
	 */

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/page";
		}
	}

	@RequestMapping(value = { "/", "page", "/home" }, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", "Welcome!");
		return mv;
	}

	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public ModelAndView contentPage() {
		ModelAndView mv = new ModelAndView("common/content");
		return mv;
	}

	@RequestMapping(value = "/common/navigation", method = RequestMethod.GET)
	public ModelAndView contentNavigationPage() {
		ModelAndView mv = new ModelAndView("common/navigation");
		return mv;
	}

	@RequestMapping(value = "/common/topnavbar", method = RequestMethod.GET)
	public ModelAndView contentTopnavbarPage() {
		ModelAndView mv = new ModelAndView("common/topnavbar");
		return mv;
	}

	@RequestMapping(value = "/common/footer", method = RequestMethod.GET)
	public ModelAndView contentFooterPage() {
		ModelAndView mv = new ModelAndView("common/footer");
		return mv;
	}
	
	@RequestMapping(value = "/common/tools", method = RequestMethod.GET)
	public ModelAndView contentToolPage() {
		ModelAndView mv = new ModelAndView("common/tools");
		return mv;
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView body1Page() {
		ModelAndView mv = new ModelAndView("welcome");		
		return mv;
	}

	@RequestMapping(value = "/page1", method = RequestMethod.GET)
	public ModelAndView body2Page() {
		ModelAndView mv = new ModelAndView("page1");
		//passing the list of entities
		mv.addObject("entities", secondEntityDAO.list());
		return mv;
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public ModelAndView accessDeniedPage() {
		ModelAndView mv = new ModelAndView("accessdenied");
		return mv;
	}
	
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation 
		 * and applying it on field [sso] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}
		
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "registrationsuccess";
	}

	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}
	
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
			ModelMap model, @PathVariable String ssoId) {

		if (result.hasErrors()) {
			return "registration";
		}
		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "registrationsuccess";
	}
	
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}
	
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userslist";
	}
	
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}
	
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	/*
	 * @Autowired UserService userService;
	 * 
	 * @Autowired UserProfileService userProfileService;
	 * 
	 * @Autowired MessageSource messageSource;
	 * 
	 * @Autowired PersistentTokenBasedRememberMeServices
	 * persistentTokenBasedRememberMeServices;
	 * 
	 * @Autowired AuthenticationTrustResolver authenticationTrustResolver;
	 */

	/*
	 * @ModelAttribute("Profiles") public List<UserProfile> initializeProfiles() {
	 * return userProfileService.findAll(); }
	 */

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public String
	 * loginPage() { if (isCurrentAuthenticationAnonymous()) { return "login"; }
	 * else { return "redirect:/page"; } }
	 * 
	 * @RequestMapping(value = { "/", "/page" }, method = RequestMethod.GET) public
	 * String mainPage() { return "page"; }
	 * 
	 * 
	 * 
	 * private String userName() { String userName = null; Object principal =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * 
	 * if (principal instanceof UserDetails) { userName = ((UserDetails)
	 * principal).getUsername(); } else { userName = principal.toString(); } return
	 * userName; }
	 * 
	 * @RequestMapping(value = "/logout", method = RequestMethod.GET) public String
	 * logoutPage(HttpServletRequest request, HttpServletResponse response) {
	 * Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 * if (auth != null) { // new SecurityContextLogoutHandler().logout(request,
	 * response, auth); persistentTokenBasedRememberMeServices.logout(request,
	 * response, auth); SecurityContextHolder.getContext().setAuthentication(null);
	 * } return "redirect:/login?logout"; }
	 * 
	 * private boolean isCurrentAuthenticationAnonymous() { final Authentication
	 * authentication = SecurityContextHolder.getContext().getAuthentication();
	 * return authenticationTrustResolver.isAnonymous(authentication); }
	 */

}