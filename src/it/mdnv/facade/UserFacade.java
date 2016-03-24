package it.mdnv.facade;

import it.mdnv.dao.UserDAO;
import it.mdnv.model.Utenti;

public class UserFacade {
	private UserDAO userDAO = new UserDAO();

	public Utenti isValidLogin(String email, String password) {
		userDAO.beginTransaction();
		Utenti user = userDAO.findUserByEmail(email);

		if (user == null || !user.getPassword().equals(password)) {
			return null;
		}

		return user;
	}
}