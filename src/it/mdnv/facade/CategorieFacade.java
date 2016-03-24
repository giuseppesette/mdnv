package it.mdnv.facade;

import it.mdnv.dao.CategorieDAO;
import it.mdnv.model.Categorie;

import java.util.List;

public class CategorieFacade {
	
	private CategorieDAO categorieDAO = new CategorieDAO();
	
	public Categorie createCategorie(Categorie categoria){
		categorieDAO.beginTransaction();
		Categorie newCategoria = categorieDAO.create(categoria);
		if(newCategoria != null && newCategoria.getId() != 0)
			categorieDAO.commitAndCloseTransaction();
		return newCategoria;
	}// END createCategorie
	
	public Categorie updateCategorie(Categorie categoria){
		categorieDAO.beginTransaction();
		Categorie categoriaUpdated = categorieDAO.update(categoria);
		categorieDAO.commitAndCloseTransaction();
		return categoriaUpdated;
	}// END updateCategorie
	
	public Categorie findCategoriaByIdCategoria(int id) {
		categorieDAO.beginTransaction();
		Categorie categoria = categorieDAO.findCategoriaByIdCategoria(id);

		if (categoria == null || categoria.getId() != id) {
			return null;
		}

		return categoria;
	} // END findCategoriaByIdCategoria
	
	public List<Categorie> getListaCategorie() {
		categorieDAO.beginTransaction();
		return categorieDAO.findAll();
	} // END getListaCategorie
	
	@Deprecated
	public boolean cancellaCategoria(Categorie categoria){
		boolean isCancelled = false;
		try{
			categorieDAO.beginTransaction();
			Categorie categoriaToBeRemoved = categorieDAO.find(categoria.getId());
			categorieDAO.delete(categoriaToBeRemoved);
			categorieDAO.commitAndCloseTransaction();
			isCancelled = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isCancelled;
	} // END cancellaCategoria
	
} // end class