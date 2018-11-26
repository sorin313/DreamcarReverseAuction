package packt.java.spring.mvc.dreamcar.interfaces;

import packt.java.spring.mvc.dreamcar.viewmodels.LoggedUserViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.LoginViewModel;
import packt.java.spring.mvc.dreamcar.viewmodels.RegisterViewModel;

public interface IUserInterface {
	public LoggedUserViewModel validateLogin(LoginViewModel loginViewModel);
	public boolean validateRegister(RegisterViewModel registerViewModel);
}
