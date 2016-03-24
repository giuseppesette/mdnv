package it.mdnv.dao;
 
import it.mdnv.model.Assemblati;
import it.mdnv.utils.Constants;

import java.util.HashMap;
import java.util.Map;
 
public class AssemblatiDAO extends GenericDAO<Assemblati> {
 
	private static final long serialVersionUID = 1L;

	public AssemblatiDAO() {
        super(Assemblati.class);
    }
	
	/**
	 * 
	 * @param String nomeAssemblato
	 * @return Assemblati
	 */
	public Assemblati getAssemblatoFromNomeAssemblato(String nomeAssemblato){
        Map<String, Object> parameters = new HashMap<String, Object>();
        //"assemblato"
        parameters.put(Constants.SQL_NOME_ASSEMBLATO, nomeAssemblato);     
 
        
        return super.findOneResult(Assemblati.FIND_BY_NOME_ASSEMBLATO, parameters);
	} // END getProdottoFromNomeProdotto
	
	/**
	 * 
	 * @param Integer idAssemblato
	 * @return Assemblati
	 */
    public Assemblati findAssemblatoByIdAssemblato(Integer idAssemblato) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        // id_assemblato
        parameters.put(Constants.SQL_ID, idAssemblato); 

        return super.findOneResult(Assemblati.FIND_BY_ID_ASSEMBLATO, parameters);
        
        
    } // END findAssemblatoByIdAssemblato
	
}// end class