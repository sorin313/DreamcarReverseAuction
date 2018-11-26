package packt.java.spring.mvc.dreamcar.service;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import packt.java.spring.mvc.dreamcar.enums.UserTypeEnum;
import packt.java.spring.mvc.dreamcar.interfaces.IUserInterface;
import packt.java.spring.mvc.dreamcar.pojo.Company;
import packt.java.spring.mvc.dreamcar.pojo.User;
import packt.java.spring.mvc.dreamcar.viewmodels.LoggedUserViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.LoginViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.RegisterViewModel;

@Service
public class UserService implements IUserInterface {

	public UserService() {
	}

	private static SessionFactory factory = SessionFactoryHelper
			.getSessionFactory();

	public LoggedUserViewModel validateLogin(LoginViewModel loginViewModel) {
		Session session = factory.openSession();
		LoggedUserViewModel loggedUser = null;
		User user = null;
		try {
			Query query = session
					.createQuery(
							"from User where username=:username and password=:password")
					.setParameter("username", loginViewModel.getUsername())
					.setParameter("password", loginViewModel.getPassword());

			user = (User) query.uniqueResult();

			if (user == null) {
				return null;
			}
			Company company = user.getCompany();
			int userType = user.getUserTypeId();
			UserTypeEnum userTypeId = UserTypeEnum.getType(userType);

			loggedUser = new LoggedUserViewModel(user.getUserId(),
					user.getUsername(), userTypeId, company.getCompanyId(),
					company.getName());

			return loggedUser;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return loggedUser;
	}

	public boolean validateRegister(RegisterViewModel registerViewModel) {
		Session session = factory.openSession();
		boolean isSuccess = false;
		session.beginTransaction();

		User newUser = new User();
		newUser.setEmail(registerViewModel.getEmail());
		newUser.setUsername(registerViewModel.getUsername());

		Company c = new Company();
		c.setCompanyId(registerViewModel.getCompanyId());
		newUser.setUserTypeId(UserTypeEnum.Provider.getId());
		newUser.setCompany(c);
		newUser.setPassword(registerViewModel.getPassword());
		newUser.setPhoneNumber(registerViewModel.getPhoneNumber());
		newUser.setUsername(registerViewModel.getUsername());

		try {
			Query query = session.createQuery(
					"select userId from User where username=:username")
					.setParameter("username", registerViewModel.getUsername());
			boolean userExists = query.uniqueResult() != null;

			if (!userExists) {
				session.save(newUser);
				session.getTransaction().commit();
				isSuccess = true;
			}

			return isSuccess;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}

		return isSuccess;
	}

}
