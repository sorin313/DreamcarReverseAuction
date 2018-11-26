package packt.java.spring.mvc.dreamcar.pojo;

// Generated Nov 18, 2018 5:23:14 PM by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "company", catalog = "dreamcar_reverse_auction")
public class Company implements java.io.Serializable {

	private int companyId;
	private String name;
	private Set<User> users = new HashSet<User>(0);

	public Company() {
	}

	public Company(int companyId, String name) {
		this.companyId = companyId;
		this.name = name;
	}

	public Company(int companyId, String name, Set<User> users) {
		this.companyId = companyId;
		this.name = name;
		this.users = users;
	}

	@Id
	@Column(name = "companyId", unique = true, nullable = false)
	public int getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
