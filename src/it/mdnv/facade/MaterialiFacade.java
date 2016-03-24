package it.mdnv.facade;

import it.mdnv.dao.MaterialiDAO;
import it.mdnv.model.Materiali;

public class MaterialiFacade {
	
	private MaterialiDAO materialiDAO = new MaterialiDAO();
	
	public Materiali createMateriali(Materiali materiali){
		materialiDAO.beginTransaction();
		Materiali newMateriali = materialiDAO.create(materiali);
		if(newMateriali != null && newMateriali.getId() != 0)
			materialiDAO.commitAndCloseTransaction();
		return newMateriali;
		
	}// END createMateriali
	
	public Materiali updateMateriali(Materiali materiali){
		materialiDAO.beginTransaction();
		Materiali materialiUpdated = materialiDAO.update(materiali);
		materialiDAO.commitAndCloseTransaction();
		return materialiUpdated;
		
	}// END updateMateriali
	
	public Materiali findMaterialiByIdFornitore(int idFornitore){
		materialiDAO.beginTransaction();
		return materialiDAO.findMaterialiByIdFornitore(idFornitore);
	}
	
	/*** 
	public Integer getMaxId(){
		folderLavoriDAO.beginTransaction();
		Integer maxIdIncrementato = folderLavoriDAO.getMaxId();
		if(maxIdIncrementato != null)
			return maxIdIncrementato;
		else
			return new Integer(1);
	}// END getMaxId
	
	
	public Lavori createFolderLavori(Lavori folderLavori){
		folderLavoriDAO.beginTransaction();
		Lavori newFolderLavori = folderLavoriDAO.create(folderLavori);
		if(newFolderLavori != null && newFolderLavori.getId() != 0)
			folderLavoriDAO.commitAndCloseTransaction();
		return newFolderLavori;
	}// END createFolderLavori
	
	public Lavori updateFolderLavori(Lavori folderLavori){
		folderLavoriDAO.beginTransaction();
		Lavori folderLavoriUpdated = folderLavoriDAO.update(folderLavori);
		folderLavoriDAO.commitAndCloseTransaction();
		return folderLavoriUpdated;
	}// END updateFolderLavori

	public List<Lavori> getListaFolderLavoriByIdCliente(int idCliente) {
		folderLavoriDAO.beginTransaction();
		return folderLavoriDAO.getListaFolderLavoriByIdCliente(idCliente);
	} // END getListaFolderLavoriByIdCliente
	
	public Lavori findFolderLavoriById(int id) {
		folderLavoriDAO.beginTransaction();
		Lavori folderLavori = folderLavoriDAO.findFolderLavoriById(id);
		if (folderLavori == null || folderLavori.getId() != id) {
			return null;
		}
		return folderLavori;
	} // END findFolderLavoriById
	
	public List<Lavori> getListaLavori() {
		folderLavoriDAO.beginTransaction();
		return folderLavoriDAO.findAll();
	} // END getListaLavori
	
	public boolean cancellaLavoro(Lavori folderLavori){
		boolean isCancelled = false;
		try{
			folderLavoriDAO.beginTransaction();
			Lavori lavoroToBeRemoved = folderLavoriDAO.find(folderLavori.getId());
			folderLavoriDAO.delete(lavoroToBeRemoved);
			folderLavoriDAO.commitAndCloseTransaction();
			isCancelled = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isCancelled;
	} // END cancellaLavoro
	
	
	public List<Lavori> getListaFolderLavoriByIdFornitore(int idFornitore) {
		folderLavoriDAO.beginTransaction();
		return folderLavoriDAO.getListaFolderLavoriByIdFornitore(idFornitore);
	} // END getListaFolderLavoriByIdFornitore
	
	****/
} // end class