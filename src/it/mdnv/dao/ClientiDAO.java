package it.mdnv.dao;
 
import it.mdnv.model.Clienti;
import it.mdnv.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class ClientiDAO extends GenericDAO<Clienti> {
 
	private static final long serialVersionUID = 1L;

	public ClientiDAO() {
        super(Clienti.class);
    }
	/**
	public Integer getNextAutoincrementId(){
		Integer nextAutoincrementId = null; 
		List<Cliente> lcli = super.createSQLQuery(Cliente.FIND_NEXT_AUTO_INCREMENT_ID_CLIENTE);
		System.out.println("[ClientiDAO][getNextAutoincrementId] - List<Cliente>: " + lcli);
		if(lcli != null && lcli.size() > 0){
			
		}
		return nextAutoincrementId;
	}// END getMaxId
	**/
	public Integer getMaxId(){
		Integer maxIdIncrementato = super.findIDResult(Clienti.FIND_MAX_ID_CLIENTE, null);
		System.out.println("[ClientiDAO][getMaxId] - maxIdIncrementato: " + maxIdIncrementato);
		return maxIdIncrementato;
	}// END getMaxId
 
    public Clienti findClienteByCodCliente(String cod_cliente){
        Map<String, Object> parameters = new HashMap<String, Object>();
        //"cod_cliente"
        parameters.put(Constants.SQL_COD_CLIENTE, cod_cliente);     
 
        return super.findOneResult(Clienti.FIND_BY_COD_CLIENTE, parameters);
    }
    
    public Clienti findClienteByIdCliente(Integer idcliente){
        Map<String, Object> parameters = new HashMap<String, Object>();
        //"id_cliente"
        parameters.put(Constants.SQL_ID_CLIENTE, idcliente);     
 
        return super.findOneResult(Clienti.FIND_BY_ID_CLIENTE, parameters);
    }
    
    public List<Clienti> getListaClienti(){
        return super.findAll();
    }
    
    public Clienti create(Clienti cliente){
    	return super.create(cliente);
    }
    
}// end class