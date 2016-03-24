package it.mdnv.facade;

import it.mdnv.dao.ContattiDAO;
import it.mdnv.model.Contatti;

import java.util.List;

public class ContattiFacade {
	
	private ContattiDAO contattiDAO = new ContattiDAO();
	
	public Contatti createContatto(Contatti contatto){
		contattiDAO.beginTransaction();
		Contatti newcontatto = contattiDAO.create(contatto);
		if(newcontatto != null && newcontatto.getId() != 0)
			contattiDAO.commitAndCloseTransaction();
		return newcontatto;
	}// END createContatto
	
	public Contatti updateContatto(Contatti contatto){
		contattiDAO.beginTransaction();
		Contatti contattoUpdated = contattiDAO.update(contatto);
		contattiDAO.commitAndCloseTransaction();
		return contattoUpdated;
	}// END updateContatto
	
	public Contatti findContattoByIdContatto(int id) {
		contattiDAO.beginTransaction();
		Contatti contatto = contattiDAO.findContattoByIdContatto(id);

		if (contatto == null || contatto.getId() != id) {
			return null;
		}

		return contatto;
	} // END findContattoByIdContatto
	
	public List<Contatti> getListaContatti() {
		contattiDAO.beginTransaction();
		return contattiDAO.findAll();
	} // END getListaContatti
	
	@Deprecated
	public boolean cancellaContatto(Contatti contatto){
		boolean isCancelled = false;
		try{
			contattiDAO.beginTransaction();
			Contatti contattoToBeRemoved = contattiDAO.find(contatto.getId());
			contattiDAO.delete(contattoToBeRemoved);
			contattiDAO.commitAndCloseTransaction();
			isCancelled = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isCancelled;
	} // END cancellaContatto
	
} // end class