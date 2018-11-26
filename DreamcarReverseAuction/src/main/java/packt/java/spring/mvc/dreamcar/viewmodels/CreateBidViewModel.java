package packt.java.spring.mvc.dreamcar.viewmodels;

public class CreateBidViewModel {
	private float bidAmount;
	private int userId;
	private int auctionId;
	
	public CreateBidViewModel(){
	}
	
	public CreateBidViewModel(float bidAmount, int userId, int auctionId){
		this.bidAmount = bidAmount;
		this.userId = userId;
		this.auctionId = auctionId;
	}
	
	public float getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(float bidAmount) {
		this.bidAmount = bidAmount;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
}
