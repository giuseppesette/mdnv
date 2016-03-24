package it.mdnv.facade;

import it.mdnv.dao.FamigliaDAO;
import it.mdnv.model.Famiglia;

import java.util.List;

public class FamiglieFacade {
	
	private FamigliaDAO famigliaDAO = new FamigliaDAO();

	public List<Famiglia> getListaFamiglie() {
		famigliaDAO.beginTransaction();
		return famigliaDAO.findAll();
	} // END getListaFamiglie
	
	public Famiglia getFamigliaFromId(int idFamiglia){
		famigliaDAO.beginTransaction();
		return famigliaDAO.find(idFamiglia);
	} // END getFamigliaFromId
	
} // end class