package it.mdnv.dao;
 
import it.mdnv.model.Parametri;
import it.mdnv.utils.Constants;

import java.util.HashMap;
import java.util.Map;
 
public class ParametriDAO extends GenericDAO<Parametri> {
 
	private static final long serialVersionUID = 1L;

	public ParametriDAO() {
        super(Parametri.class);
    }
	
    public Parametri findValoreByParametro(String param){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constants.SQL_PARAMETRO, param);
        
        return super.findOneResult(Parametri.FIND_VALORE_BY_PARAMETRO, parameters);
    }
	
}// end class