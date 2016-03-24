package it.mdnv.dao;
 
import it.mdnv.model.Contatti;
import it.mdnv.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class ContattiDAO extends GenericDAO<Contatti> {
 
	private static final long serialVersionUID = 1L;

	public ContattiDAO() {
        super(Contatti.class);
    }
    
    public Contatti findContattoByIdContatto(Integer id){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constants.SQL_ID, id);     
        return super.findOneResult(Contatti.FIND_BY_ID, parameters);
    }
    
    public List<Contatti> getListaContatti(){
        return super.findAll();
    }
    
    public Contatti create(Contatti contatto){
    	return super.create(contatto);
    }
    
}// end class