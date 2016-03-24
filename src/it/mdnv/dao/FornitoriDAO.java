package it.mdnv.dao;
 
import it.mdnv.model.Fornitori;
import it.mdnv.utils.Constants;

import java.util.HashMap;
import java.util.Map;
 
public class FornitoriDAO extends GenericDAO<Fornitori> {
 
	private static final long serialVersionUID = 1L;

	public FornitoriDAO() {
        super(Fornitori.class);
    }
	//
    public Fornitori findFornitoreByIdFornitore(Integer idFornitore){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constants.SQL_ID_FORNITORE, idFornitore);     
 
        return super.findOneResult(Fornitori.FIND_BY_ID_FORNITORE, parameters);
    }
	
	/**

	public Integer getMaxId(){
		Integer maxIdIncrementato = super.findIDResult(Clienti.FIND_MAX_ID_CLIENTE, null);
		System.out.println("[ClientiDAO][getMaxId] - maxIdIncrementato: " + maxIdIncrementato);
		return maxIdIncrementato;
	}// END getMaxId
 
    public Clienti findClienteByCodCliente(String cod_cliente){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("cod_cliente", cod_cliente);     
 
        return super.findOneResult(Clienti.FIND_BY_COD_CLIENTE, parameters);
    }
    

    
    public List<Clienti> getListaClienti(){
        return super.findAll();
    }
    
    public Clienti create(Clienti cliente){
    	return super.create(cliente);
    }
    **/
}// end class