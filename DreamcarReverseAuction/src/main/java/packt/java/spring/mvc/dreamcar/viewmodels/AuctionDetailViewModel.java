package packt.java.spring.mvc.dreamcar.viewmodels;

import java.util.Date;
import packt.java.spring.mvc.dreamcar.enums.AuctionStatusEnum;
import packt.java.spring.mvc.dreamcar.pojo.User;

public class AuctionDetailViewModel {
	private float targetPrice;
	private String currencyType;
	private String componentName;
	private AuctionStatusEnum statusId;
	private int quantity;
	private Date startDate;
	private Date endDate;
	private User winner;
	
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
	public String getComponentName() {
		return componentName;
	}
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public User getWinner() {
		return winner;
	}
	public void setWinner(User winner) {
		this.winner = winner;
	}
	public AuctionStatusEnum getStatusId() {
		return statusId;
	}
	public void setStatusId(AuctionStatusEnum statusId) {
		this.statusId = statusId;
	}
}
