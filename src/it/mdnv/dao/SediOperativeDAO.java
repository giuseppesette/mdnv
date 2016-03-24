package it.mdnv.dao;
 
import it.mdnv.model.SedeOperativa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class SediOperativeDAO extends GenericDAO<SedeOperativa> {
 
	private static final long serialVersionUID = 1L;

	public SediOperativeDAO() {
        super(SedeOperativa.class);
    }

    public SedeOperativa findSedeOperativaByIdSedeOperativa(Integer idSedeOp){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_sedeoperativa", idSedeOp);     
 
        return super.findOneResult(SedeOperativa.FIND_BY_ID_SEDEOP, parameters);
    }
    
    public List<SedeOperativa> getListaSediOperativeByIdCliente(Integer idCliente){
    	
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_cliente", idCliente);     
 
        //System.out.println("[SediOperativeDAO][getListaSediOperativeByIdCliente] - parameters: " + parameters);
        
        return super.findAllReferencedById(SedeOperativa.FIND_BY_ID_CLIENTE, parameters);
    }
    
    
    public List<SedeOperativa> getListaSediOperative(){
        return super.findAll();
    }
    
    public SedeOperativa create(SedeOperativa sedeOperativa){
    	return super.create(sedeOperativa);
    }
    
	/**
	public Integer getMaxId(){
		Integer maxIdIncrementato = super.findIDResult(SedeOperativa.FIND_MAX_ID_SEDEOP, null);
		System.out.println("[SediOperativeDAO][getMaxId] - maxIdIncrementato: " + maxIdIncrementato);
		return maxIdIncrementato;
	}// END getMaxId
    **/
    
}// end class