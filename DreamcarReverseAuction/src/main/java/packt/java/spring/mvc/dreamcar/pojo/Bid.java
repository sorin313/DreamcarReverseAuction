package packt.java.spring.mvc.dreamcar.pojo;

// Generated Nov 18, 2018 5:23:14 PM by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bid", catalog = "dreamcar_reverse_auction")
public class Bid implements java.io.Serializable {

	private int bidId;
	private Auction auction;
	private User user;
	private float amount;
	private Date createdDate;

	public Bid() {
	}

	public Bid(int bidId, Auction auction, User user, float amount) {
		this.bidId = bidId;
		this.auction = auction;
		this.user = user;
		this.amount = amount;
	}

	@Id
	@Column(name = "bidId", unique = true, nullable = false)
	public int getBidId() {
		return this.bidId;
	}

	public void setBidId(int bidId) {
		this.bidId = bidId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auctionId", nullable = false)
	public Auction getAuction() {
		return this.auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "amount", nullable = false, precision = 12, scale = 0)
	public float getAmount() {
		return this.amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
