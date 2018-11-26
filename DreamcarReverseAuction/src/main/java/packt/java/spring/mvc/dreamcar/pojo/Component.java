package packt.java.spring.mvc.dreamcar.pojo;

// Generated Nov 18, 2018 5:23:14 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "component", catalog = "dreamcar_reverse_auction")
public class Component implements java.io.Serializable {

	private int componentId;
	private String name;
	private Set<Auction> auctions = new HashSet<Auction>(0);

	public Component() {
	}

	public Component(String name) {
		this.name = name;
	}

	public Component(int componentId, String name, Set<Auction> auctions) {
		this.componentId = componentId;
		this.name = name;
		this.auctions = auctions;
	}

	@Id
	@Column(name = "componentId", unique = true, nullable = false)
	public int getComponentId() {
		return this.componentId;
	}

	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "component")
	public Set<Auction> getAuctions() {
		return this.auctions;
	}

	public void setAuctions(Set<Auction> auctions) {
		this.auctions = auctions;
	}

}
