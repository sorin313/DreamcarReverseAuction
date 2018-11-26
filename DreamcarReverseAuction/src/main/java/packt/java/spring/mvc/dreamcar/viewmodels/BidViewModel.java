package packt.java.spring.mvc.dreamcar.viewmodels;

import java.util.Date;

public class BidViewModel {

	private int bidId;
	private float amount;
	private String bidderUsername;
	private String companyName;
	private Date createdDate;
	private AuctionDetailViewModel auctionDetail;
	
	public BidViewModel(){
	}

	public int getBidId() {
		return bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	public String getBidderUsername() {
		return bidderUsername;
	}

	public void setBidderUsername(String bidderUsername) {
		this.bidderUsername = bidderUsername;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public AuctionDetailViewModel getAuctionDetail() {
		return auctionDetail;
	}

	public void setAuctionDetail(AuctionDetailViewModel auctionDetail) {
		this.auctionDetail = auctionDetail;
	}
}
