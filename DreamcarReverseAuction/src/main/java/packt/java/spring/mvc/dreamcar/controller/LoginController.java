package packt.java.spring.mvc.dreamcar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import packt.java.spring.mvc.dreamcar.interfaces.ICompanyService;
import packt.java.spring.mvc.dreamcar.interfaces.IUserInterface;
import packt.java.spring.mvc.dreamcar.viewmodels.LoggedUserViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.LoginViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.RegisterViewModel;

@Controller
public class LoginController {
	
	@Autowired
	IUserInterface userService;
	
	@Autowired
	ICompanyService companyService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeLogin() {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLogin() {
		return "login";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String validateLogin(@ModelAttribute("loginViewModel") LoginViewModel loginViewModel, 
			ModelMap model, HttpSession session) {
 
		LoggedUserViewModel loggedUser = userService.validateLogin(loginViewModel);
		
		if (loggedUser == null)
		{
			model.addAttribute("loginValidationMessage", "Wrong username or password");
			
			return "login";
		}

		model.remove("loginValidationMessage");
		session.setAttribute("loggedUser", loggedUser);
		return "redirect:/auctions/home";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String displayRegister(ModelMap model) {

		model.addAttribute("companyList", companyService.getCompanyList());
		
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("registerViewModel") RegisterViewModel registerViewModel, ModelMap model) {
		
		boolean isSuccess = userService.validateRegister(registerViewModel);
		if (!isSuccess){
			model.addAttribute("usernameExistsMessage", "Username already exists!");
			return displayRegister(model);
		}
		
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.setAttribute("loggedUser", null);
		return "login";
	}
}
