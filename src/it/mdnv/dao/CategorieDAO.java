package it.mdnv.dao;
 
import it.mdnv.model.Categorie;
import it.mdnv.utils.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class CategorieDAO extends GenericDAO<Categorie> {
 
	private static final long serialVersionUID = 1L;

	public CategorieDAO() {
        super(Categorie.class);
    }
    
    public Categorie findCategoriaByIdCategoria(Integer id){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(Constants.SQL_ID, id);     
        return super.findOneResult(Categorie.FIND_BY_ID, parameters);
    }
    
    public List<Categorie> getListaCategorie(){
        return super.findAll();
    }
    
    public Categorie create(Categorie categoria){
    	return super.create(categoria);
    }
    
}// end class