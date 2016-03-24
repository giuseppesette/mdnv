package it.mdnv.facade;

import it.mdnv.dao.TipologieDAO;
import it.mdnv.model.Tipologie;

import java.util.List;

public class TipologieFacade {
	
	private TipologieDAO tipologieDAO = new TipologieDAO();

	public List<Tipologie> getListaTipologie() {
		tipologieDAO.beginTransaction();
		return tipologieDAO.findAll();
	} // END getListaTipologie
	
	public Tipologie getTipologiaFromId(int idTipologia){
		tipologieDAO.beginTransaction();
		return tipologieDAO.find(idTipologia);
	} // END getTipologiaFromId
} // end class