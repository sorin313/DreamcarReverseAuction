package packt.java.spring.mvc.dreamcar.viewmodels;

public class CreateAuctionViewModel {
	private int ownerId;
	private int componentId;
	private String currencyType;
	private float targetPrice;  
	private String endDate;
	private int quantity;
	
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public int getComponentId() {
		return componentId;
	}
	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}
	public float getTargetPrice() {
		return targetPrice;
	}
	public void setTargetPrice(float targetPrice) {
		this.targetPrice = targetPrice;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
