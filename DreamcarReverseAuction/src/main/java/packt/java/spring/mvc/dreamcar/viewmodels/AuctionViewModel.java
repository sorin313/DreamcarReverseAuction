package packt.java.spring.mvc.dreamcar.viewmodels;

import java.util.Date;

import packt.java.spring.mvc.dreamcar.enums.AuctionStatusEnum;
import packt.java.spring.mvc.dreamcar.pojo.Component;
import packt.java.spring.mvc.dreamcar.pojo.User;

public class AuctionViewModel {
	private int auctionId;
	private User owner;
	private Component component;
	private String currencyType;
	private float targetPrice;
	private Date startDate;
	private Date endDate;
	private int quantity;
	private User winner;
	private AuctionStatusEnum statusId;
	
	public AuctionViewModel(){
	}

	public AuctionViewModel(int auctionId, Component component, User owner, String currencyType,
			float targetPrice, Date startDate, Date endDate, int quantity, AuctionStatusEnum statusId){
		this.auctionId = auctionId;
		this.setComponent(component);
		this.setOwner(owner);
		this.currencyType = currencyType;
		this.targetPrice = targetPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.quantity = quantity;
		this.statusId = statusId;
	}
	
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public String getCurrencyType() {
		return currencyType;
	}
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}
	public float getTargetPrice() {
		return targetPrice;
	}
	public void setTargetPrice(float targetPrice) {
		this.targetPrice = targetPrice;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public AuctionStatusEnum getStatusId() {
		return statusId;
	}
	public void setStatusId(AuctionStatusEnum statusId) {
		this.statusId = statusId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}
	
}
