package it.mdnv.facade;

import it.mdnv.dao.ParametriDAO;
import it.mdnv.model.Parametri;

public class ParametriFacade {
	
	private ParametriDAO parametriDAO = new ParametriDAO();
	
	public Parametri findValoreByParametro(String param){
		parametriDAO.beginTransaction();
		return parametriDAO.findValoreByParametro(param);
	}

} // end class