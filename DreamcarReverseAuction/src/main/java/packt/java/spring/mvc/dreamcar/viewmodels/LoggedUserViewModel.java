package packt.java.spring.mvc.dreamcar.viewmodels;

import packt.java.spring.mvc.dreamcar.enums.UserTypeEnum;

public class LoggedUserViewModel {
	private int userId;
	private String username;
	private UserTypeEnum userTypeId;
	private int companyId;
	private String companyName;
	
	public LoggedUserViewModel(int userId, String username, UserTypeEnum userTypeId, int companyId, String companyName){
		this.userId = userId;
		this.username = username;
		this.userTypeId = userTypeId;
		this.companyId = companyId;
		this.companyName = companyName;
	}
	
	public int getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public UserTypeEnum getUserTypeId() {
		return userTypeId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}
}
