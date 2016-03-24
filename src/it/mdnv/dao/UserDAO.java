package it.mdnv.dao;
 
import java.util.HashMap;
import java.util.Map;
 
import it.mdnv.model.Utenti;
 
public class UserDAO extends GenericDAO<Utenti> {
 
	private static final long serialVersionUID = 1L;

	public UserDAO() {
        super(Utenti.class);
    }
 
    public Utenti findUserByEmail(String email){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("email", email);     
 
        return super.findOneResult(Utenti.FIND_BY_EMAIL, parameters);
    }
}