package packt.java.spring.mvc.dreamcar.pojo;

// Generated Nov 26, 2018 11:56:04 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "dreamcar_reverse_auction")
public class User implements java.io.Serializable {

	private Integer userId;
	private Company company;
	private String username;
	private String password;
	private String email;
	private String phoneNumber;
	private int userTypeId;
	private Set<Auction> auctionsForOwnerId = new HashSet<Auction>(0);
	private Set<Bid> bids = new HashSet<Bid>(0);
	private Set<Auction> auctionsForWinnerId = new HashSet<Auction>(0);

	public User() {
	}

	public User(Company company, String username, String password,
			String email, String phoneNumber, int userTypeId) {
		this.company = company;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userTypeId = userTypeId;
	}

	public User(Company company, String username, String password,
			String email, String phoneNumber, int userTypeId,
			Set<Auction> auctionsForOwnerId, Set<Bid> bids,
			Set<Auction> auctionsForWinnerId) {
		this.company = company;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.userTypeId = userTypeId;
		this.auctionsForOwnerId = auctionsForOwnerId;
		this.bids = bids;
		this.auctionsForWinnerId = auctionsForWinnerId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "userId", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "companyId", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "username", nullable = false, length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "email", nullable = false, length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phoneNumber", nullable = false, length = 15)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "userTypeId", nullable = false)
	public int getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByOwnerId")
	public Set<Auction> getAuctionsForOwnerId() {
		return this.auctionsForOwnerId;
	}

	public void setAuctionsForOwnerId(Set<Auction> auctionsForOwnerId) {
		this.auctionsForOwnerId = auctionsForOwnerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Bid> getBids() {
		return this.bids;
	}

	public void setBids(Set<Bid> bids) {
		this.bids = bids;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userByWinnerId")
	public Set<Auction> getAuctionsForWinnerId() {
		return this.auctionsForWinnerId;
	}

	public void setAuctionsForWinnerId(Set<Auction> auctionsForWinnerId) {
		this.auctionsForWinnerId = auctionsForWinnerId;
	}

}
