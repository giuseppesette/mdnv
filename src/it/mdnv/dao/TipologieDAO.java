package it.mdnv.dao;
 
import it.mdnv.model.Tipologie;
 
public class TipologieDAO extends GenericDAO<Tipologie> {
 
	private static final long serialVersionUID = 1L;

	public TipologieDAO() {
        super(Tipologie.class);
    }
/**
    public List<Lavori> getListaTipologieForIdFamiglia(Integer idFamiglia){
        Map<String, Object> parameters = new HashMap<String, Object>();
        //"id_famiglia"
        parameters.put(Constants.SQL_ID_CLIENTE, idFamiglia);     

        return super.findAllReferencedById(Tipologie.FIND_TIPOLOGIA_BY_ID_FAMIGLIA, parameters);
    } // END getListaTipologieForIdFamiglia
**/
}// end class