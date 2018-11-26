package packt.java.spring.mvc.dreamcar.interfaces;

import java.util.List;

import packt.java.spring.mvc.dreamcar.viewmodels.AuctionDetailViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.AuctionViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.CreateAuctionViewModel;

public interface IAuctionService {
	public boolean createAuction(CreateAuctionViewModel auctionViewModel);
	public List<AuctionViewModel> getAuctions();
	public AuctionDetailViewModel getAuctionDetails(int auctionId);
	public void checkEndedAuctions();
}
