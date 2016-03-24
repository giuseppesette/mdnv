package it.mdnv.dao;
 
import it.mdnv.model.ProdFornitori;
import it.mdnv.utils.Constants;

import java.util.HashMap;
import java.util.Map;
 
public class ProdFornitoriDAO extends GenericDAO<ProdFornitori> {
 
	private static final long serialVersionUID = 1L;

	public ProdFornitoriDAO() {
        super(ProdFornitori.class);
    }

	public ProdFornitori findProdottoFornitoreById(int idProdFornitore){
        Map<String, Object> parameters = new HashMap<String, Object>();
        // id_prodotto
        parameters.put(Constants.SQL_ID, idProdFornitore); 
        return super.findOneResult(ProdFornitori.FIND_PRODFOR_BY_ID, parameters);
	} // END findProdottoFornitoreByIdProdotto
}// end class