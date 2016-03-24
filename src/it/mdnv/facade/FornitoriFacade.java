package it.mdnv.facade;

import it.mdnv.dao.FornitoriDAO;
import it.mdnv.model.Fornitori;

import java.util.List;

public class FornitoriFacade {
	
	private FornitoriDAO fornitoriDAO = new FornitoriDAO();

	public Fornitori findFornitoreByIdFornitore(int id) {
		fornitoriDAO.beginTransaction();
		Fornitori fornitore = fornitoriDAO.findFornitoreByIdFornitore(id);
		System.out.println("[FornitoriFacade][createFornitore] findFornitoreByIdFornitore["+id+"]: " + fornitore);
		if (fornitore == null || fornitore.getId() != id) {
			return null;
		}

		return fornitore;
	} // END findFornitoreByIdFornitore
	
	public Fornitori createFornitore(Fornitori fornitore){
		System.out.println("[FornitoriFacade][createFornitore] START");
		fornitoriDAO.beginTransaction();
		Fornitori newfrn = fornitoriDAO.create(fornitore);
		System.out.println("[FornitoriFacade][createFornitore] newfrn: " + newfrn);
		if(newfrn != null && newfrn.getId() != 0)
			fornitoriDAO.commitAndCloseTransaction();
		System.out.println("[FornitoriFacade][createFornitore] END");
		return newfrn;
	}// END createFornitore
	
	public Fornitori updateFornitore(Fornitori fornitore){
		fornitoriDAO.beginTransaction();
		Fornitori fornitoreUpdated = fornitoriDAO.update(fornitore);
		fornitoriDAO.commitAndCloseTransaction();
		return fornitoreUpdated;
	}// END updateFornitore
	
	public List<Fornitori> getListaFornitori() {
		fornitoriDAO.beginTransaction();
		return fornitoriDAO.findAll();
	} // END getListaFornitori
	
	public boolean cancellaFornitore(Fornitori fornitore){
		boolean isCancelled = false;
		try{
			fornitoriDAO.beginTransaction();
			Fornitori cliToBeRemoved = fornitoriDAO.find(fornitore.getId());
			fornitoriDAO.delete(cliToBeRemoved);
			fornitoriDAO.commitAndCloseTransaction();
			isCancelled = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isCancelled;
	} // END cancellaFornitore
	
	
	public Fornitori getFornitoreFromId(int idFornitore){
		fornitoriDAO.beginTransaction();
		return fornitoriDAO.find(idFornitore);
	} // END getFornitoreFromId
	
} // end class