package packt.java.spring.mvc.dreamcar.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import packt.java.spring.mvc.dreamcar.interfaces.IAuctionService;
import packt.java.spring.mvc.dreamcar.interfaces.IBidService;
import packt.java.spring.mvc.dreamcar.viewmodels.*;

@Controller
public class BidController {

	@Autowired
	IBidService bidService;
	
	@Autowired
	IAuctionService auctionService;
	
	@RequestMapping(value = "/auctions/bids", method = RequestMethod.GET)
	public String goToBids(HttpSession session, ModelMap model, int selectedAuctionId) {
		if (session.getAttribute("loggedUser") == null && selectedAuctionId > 0) {
			return "unauthorizedView";
		}
		
		model.addAttribute("selectedAuctionId", selectedAuctionId);
		model.addAttribute("bids", bidService.getAuctionBids(selectedAuctionId));
		model.addAttribute("auctionDetail", auctionService.getAuctionDetails(selectedAuctionId));
		
		return "bidView";
	}
	
	@RequestMapping(value = "/auctions/bids/createBid", method = RequestMethod.POST)
	public String createBid(HttpSession session, ModelMap model, float bidAmount, int selectedAuctionId) {
		if (session.getAttribute("loggedUser") == null) {
			return "unauthorizedView";
		}
		
		int userId = (int)((LoggedUserViewModel)session.getAttribute("loggedUser")).getUserId();

		CreateBidViewModel bidViewModel = new CreateBidViewModel(bidAmount, userId, selectedAuctionId);
		
		bidService.createBid(bidViewModel);
		
		return goToBids(session, model, selectedAuctionId);
	}
}
