package it.mdnv.dao;
 
import it.mdnv.model.Lavori;
import it.mdnv.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class FolderLavoriDAO extends GenericDAO<Lavori> {
 
	private static final long serialVersionUID = 1L;

	public FolderLavoriDAO() {
        super(Lavori.class);
    }
	
	public Integer getMaxId(){
		Integer maxIdIncrementato = super.findIDResult(Lavori.FIND_MAX_ID_LAVORO, null);
		System.out.println("[FolderLavoriDAO][getMaxId] - maxIdIncrementato: " + maxIdIncrementato);
		return maxIdIncrementato;
	}// END getMaxId

	
    public List<Lavori> getListaFolderLavoriByIdFornitore(Integer idFornitore){
        Map<String, Object> parameters = new HashMap<String, Object>();
        //"id_fornitore"
        parameters.put(Constants.SQL_ID_FORNITORE, idFornitore);     
       //System.out.println("[FolderLavoriDAO][getListaFolderLavoriByIdFornitore] - parameters: " + parameters);
        return super.findAllReferencedById(Lavori.FIND_LAVORO_BY_ID_FORNITORE, parameters);
    } // END getListaFolderLavoriByIdFornitore
	
    public List<Lavori> getListaFolderLavoriByIdCliente(Integer idCliente){
        Map<String, Object> parameters = new HashMap<String, Object>();
        //"id_cliente"
        parameters.put(Constants.SQL_ID_CLIENTE, idCliente);     
       //System.out.println("[FolderLavoriDAO][getListaFolderLavoriByIdCliente] - parameters: " + parameters);
        return super.findAllReferencedById(Lavori.FIND_LAVORO_BY_ID_CLIENTE, parameters);
    }
	
	
    public Lavori findFolderLavoriById(Integer idFolderLavori){
        Map<String, Object> parameters = new HashMap<String, Object>();
        //"id_lavoro"
        parameters.put(Constants.SQL_ID_LAVORO, idFolderLavori);     
         return super.findOneResult(Lavori.FIND_BY_ID_LAVORO, parameters);
    }
    
}// end class