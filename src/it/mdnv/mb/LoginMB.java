package it.mdnv.mb;

import it.mdnv.facade.UserFacade;
import it.mdnv.model.Utenti;
import it.mdnv.utils.Constants;
import it.mdnv.utils.SessionUtil;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

@RequestScoped
@ManagedBean(name = "loginbean")
public class LoginMB extends AbstractMB {
	
	@ManagedProperty(value = UserMB.INJECTION_NAME)
	private UserMB userMB;

	private String email;
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		UserFacade userFacade = new UserFacade();

		Utenti user = userFacade.isValidLogin(email, password);
		
		if(user != null){
			userMB.setUser(user);
			//FacesContext context = FacesContext.getCurrentInstance();
			//HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			//request.getSession().setAttribute(Constants.USER, user);
			
			HttpSession session = SessionUtil.getSession();
			session.setAttribute(Constants.USER, user);
			
			return "/welcome.xhtml";
		}

		displayErrorMessageToUser("Attenzione! Username o Password Errate.");
		
		return null;
	}

	public void setUserMB(UserMB userMB) {
		this.userMB = userMB;
	}	
}