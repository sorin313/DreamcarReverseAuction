package packt.java.spring.mvc.dreamcar.pojo;

// Generated Nov 18, 2018 5:23:14 PM by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "auction", catalog = "dreamcar_reverse_auction")
public class Auction implements java.io.Serializable {

	private int auctionId;
	private Component component;
	private User owner;
	private User winner;
	private String currencyType;
	private float targetPrice;
	private Date startDate;
	private Date endDate;
	private int quantity;
	private int statusId;
	private Set<Bid> bids = new HashSet<Bid>(0);

	public Auction() {
	}

	public Auction(int auctionId, Component component, User user,
			String currencyType, float targetPrice, Date endDate, Date startDate, int quantity) {
		this.auctionId = auctionId;
		this.component = component;
		this.owner = user;
		this.currencyType = currencyType;
		this.targetPrice = targetPrice;
		this.endDate = endDate;
		this.startDate = startDate;
		this.quantity = quantity;
	}

	public Auction(int auctionId, Component component, User user,
			String currencyType, float targetPrice, Date endDate, int quantity,
			Set<Bid> bids) {
		this.auctionId = auctionId;
		this.component = component;
		this.owner = user;
		this.currencyType = currencyType;
		this.targetPrice = targetPrice;
		this.endDate = endDate;
		this.quantity = quantity;
		this.bids = bids;
	}

	@Id
	@Column(name = "auctionId", unique = true, nullable = false)
	public int getAuctionId() {
		return this.auctionId;
	}

	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "componentId", nullable = false)
	public Component getComponent() {
		return this.component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ownerId", nullable = false)
	public User getOwner() {
		return this.owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Column(name = "currencyType", nullable = false, length = 10)
	public String getCurrencyType() {
		return this.currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	@Column(name = "targetPrice", nullable = false, precision = 12, scale = 0)
	public float getTargetPrice() {
		return this.targetPrice;
	}

	public void setTargetPrice(float targetPrice) {
		this.targetPrice = targetPrice;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "endDate", nullable = false, length = 19)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "auction")
	public Set<Bid> getBids() {
		return this.bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "winnerId", nullable = true)
	public User getWinner() {
		return winner;
	}

	public void setWinner(User winner) {
		this.winner = winner;
	}

}
