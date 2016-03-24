package it.mdnv.facade;


import java.util.List;

import it.mdnv.dao.ReferenteDAO;
import it.mdnv.model.Referenti;

public class ReferentiFacade {
	
	private ReferenteDAO referenteDAO = new ReferenteDAO();
	
	

	public List<Referenti> getListaReferentiByIdFornitore(int idFornitore){
		referenteDAO.beginTransaction();
		return referenteDAO.getListaReferentiByIdFornitore(idFornitore);
	} // END getListaReferentiByIdLavoro
	
	public List<Referenti> getListaReferentiByIdLavoro(int idLavoro){
		referenteDAO.beginTransaction();
		return referenteDAO.getListaReferentiByIdLavoro(idLavoro);
	} // END getListaReferentiByIdLavoro
	
	
	public List<Referenti> getListaReferentiByIdCliente(int idCliente){
		//System.out.println("[ReferentiFacade][getListaReferentiByIdCliente] - idCliente: " + idCliente);
		referenteDAO.beginTransaction();
		return referenteDAO.getListaReferentiByIdCliente(idCliente);
	} // END getListaReferentiByIdCliente
	
	public Referenti createReferente(Referenti referente){
		referenteDAO.beginTransaction();
		Referenti newReferente = referenteDAO.create(referente);
		if(newReferente != null && newReferente.getId() != 0)
			referenteDAO.commitAndCloseTransaction();
		return newReferente;
	}// END createReferente
	
	public Referenti updateReferente(Referenti referente){
		referenteDAO.beginTransaction();
		Referenti referenteUpdated = referenteDAO.update(referente);
		referenteDAO.commitAndCloseTransaction();
		return referenteUpdated;
	}// END updateReferente

	
	public Referenti findReferenteByIdReferente(int idreferente){
		referenteDAO.beginTransaction();
		Referenti referente = referenteDAO.findReferenteByIdReferente(idreferente);

		if (referente == null || referente.getId() != idreferente) {
			return null;
		}

		return referente;
	} // END findReferenteByIdReferente
		
} // end class