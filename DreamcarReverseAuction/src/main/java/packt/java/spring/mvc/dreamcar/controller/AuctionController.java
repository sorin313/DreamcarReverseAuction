package packt.java.spring.mvc.dreamcar.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import packt.java.spring.mvc.dreamcar.enums.UserTypeEnum;
import packt.java.spring.mvc.dreamcar.interfaces.IAuctionService;
import packt.java.spring.mvc.dreamcar.interfaces.IComponentService;
import packt.java.spring.mvc.dreamcar.pojo.Component;
import packt.java.spring.mvc.dreamcar.viewmodels.CreateAuctionViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.LoggedUserViewModel;

@Controller
public class AuctionController {

	@Autowired
	IComponentService componentService;
	
	@Autowired
	IAuctionService auctionService;
	
	@RequestMapping(value = "/auctions", method = RequestMethod.GET)
	public String auctionView(HttpSession session, ModelMap model) {
		if (session.getAttribute("loggedUser") == null) {
			return "unauthorizedView";
		}

		auctionService.checkEndedAuctions();
		List<String> currencies = Arrays.asList("EUR", "USD",
				"GBP", "CHF", "CNY");

		model.addAttribute("currencies", currencies);
		model.addAttribute("components", componentService.getComponentList());
		
		if(!model.containsKey("auctions")){
			model.addAttribute("auctions", auctionService.getAuctions());
		}
		
		return "auctionView";
	}
	
	@RequestMapping(value = "/auctions/home", method = RequestMethod.GET)
	public String welcomeHome(HttpSession session, ModelMap model) {
		if (session.getAttribute("loggedUser") == null) {
			return "unauthorizedView";
		}

		List<String> currencies = Arrays.asList("EUR", "USD",
				"GBP", "CHF", "CNY");

		model.addAttribute("currencies", currencies);
		model.addAttribute("components", componentService.getComponentList());
		
		if(!model.containsKey("auctions")){
			model.addAttribute("auctions", auctionService.getAuctions());
		}
		
		return "auctionView";
	}
	
	@RequestMapping(value = "/auctions/createAuction", method = RequestMethod.POST)
	public String createAuction(HttpSession session, ModelMap model, CreateAuctionViewModel viewModel) {
		
		LoggedUserViewModel loggedUser = (LoggedUserViewModel)session.getAttribute("loggedUser");
		if (loggedUser == null || loggedUser.getUserTypeId() != UserTypeEnum.AuctionOwner) {
			return "unauthorizedView";
		}
		
		viewModel.setOwnerId(loggedUser.getUserId());
		boolean isSuccess = auctionService.createAuction(viewModel);
		
		if(!isSuccess){
			model.addAttribute("createAuctionError", "Error in auction creation");
			return "auctionView";
		}

		model.addAttribute("auctions", auctionService.getAuctions());
		return auctionView(session, model);
	}

	@RequestMapping(value = "/auctions/createComponent", method = RequestMethod.POST)
	public String createComponent(@ModelAttribute("componentName") String componentName, HttpSession session, ModelMap model) {
		LoggedUserViewModel loggedUser = (LoggedUserViewModel)session.getAttribute("loggedUser");
		if (loggedUser == null || loggedUser.getUserTypeId() != UserTypeEnum.AuctionOwner) {
			return "unauthorizedView";
		}
		
		Component newComponent = componentService.createComponent(componentName);
		
		if(newComponent == null){
			model.addAttribute("componentExists", "Component name already exists!");
			return "auctionView";
		}
		model.addAttribute("components", componentService.getComponentList());
		
		return auctionView(session, model);
	}
	

}
