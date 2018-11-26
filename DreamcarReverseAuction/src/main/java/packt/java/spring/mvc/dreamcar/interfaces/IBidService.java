package packt.java.spring.mvc.dreamcar.interfaces;

import java.util.List;

import packt.java.spring.mvc.dreamcar.viewmodels.BidViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.CreateBidViewModel;

public interface IBidService {
	public List<BidViewModel> getAuctionBids(int auctionId);

	public boolean createBid(CreateBidViewModel bidViewModel);
}
