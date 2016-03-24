package it.mdnv.dao;
 
import it.mdnv.model.Prodotti;
import it.mdnv.utils.Constants;

import java.util.HashMap;
import java.util.Map;
 
public class ProdottiDAO extends GenericDAO<Prodotti> {
 
	private static final long serialVersionUID = 1L;

	public ProdottiDAO() {
        super(Prodotti.class);
    }
	
	/**
	 * 
	 * @param String nomeProdotto
	 * @return Prodotti
	 */
	public Prodotti getProdottoFromNomeProdotto(String nomeProdotto){
        Map<String, Object> parameters = new HashMap<String, Object>();
        //"Prodotto"
        parameters.put(Constants.SQL_NOME_PRODOTTO, nomeProdotto);     
 
        return super.findOneResult(Prodotti.FIND_BY_NOME_PRODOTTO, parameters);
	} // END getProdottoFromNomeProdotto
	
	/**
	 * 
	 * @param Integer idProdotto
	 * @return Prodotti
	 */
    public Prodotti findProdottoByIdProdotto(Integer idProdotto){
        Map<String, Object> parameters = new HashMap<String, Object>();
        // id_prodotto
        parameters.put(Constants.SQL_ID_PRODOTTO, idProdotto); 

        return super.findOneResult(Prodotti.FIND_BY_ID_PRODOTTO, parameters);
        
    } // END findProdottoByIdProdotto
	
}// end class