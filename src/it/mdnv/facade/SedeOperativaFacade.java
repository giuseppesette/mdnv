package it.mdnv.facade;


import it.mdnv.dao.SediOperativeDAO;
import it.mdnv.model.SedeOperativa;

import java.util.List;

public class SedeOperativaFacade {
	
	private SediOperativeDAO sediOperativeDAO = new SediOperativeDAO();
	
	
	public SedeOperativa createSedeOperativa(SedeOperativa sedeOperativa){
		sediOperativeDAO.beginTransaction();
		SedeOperativa newSedeOperativa = sediOperativeDAO.create(sedeOperativa);
		if(newSedeOperativa != null && newSedeOperativa.getId() != 0)
			sediOperativeDAO.commitAndCloseTransaction();
		return newSedeOperativa;
	}// END createSedeOperativa
	
	public SedeOperativa updateSedeOperativa(SedeOperativa sedeOperativa){
		sediOperativeDAO.beginTransaction();
		SedeOperativa sedeOperativaUpdated = sediOperativeDAO.update(sedeOperativa);
		sediOperativeDAO.commitAndCloseTransaction();
		return sedeOperativaUpdated;
	}// END updateSedeOperativa

	
	public SedeOperativa findSedeOperativaByIdSedeOperativa(int idSedeOp){
		sediOperativeDAO.beginTransaction();
		SedeOperativa sedeOperativa = sediOperativeDAO.findSedeOperativaByIdSedeOperativa(idSedeOp);

		if (sedeOperativa == null || sedeOperativa.getId() != idSedeOp) {
			return null;
		}

		return sedeOperativa;
	} // END findSedeOperativaByIdSedeOperativa
	
	public List<SedeOperativa> getListaSediOperativeByIdCliente(int idCliente){
		//System.out.println("[SedeOperativaFacade][getListaSediOperativeByIdCliente] - idCliente: " + idCliente);
		sediOperativeDAO.beginTransaction();
		return sediOperativeDAO.getListaSediOperativeByIdCliente(idCliente);
	} // END getListaSediOperative
	
	/**
	public Integer getMaxId(){
		sediOperativeDAO.beginTransaction();
		return sediOperativeDAO.getMaxId();
	}// END getMaxId
	
	
	
	 **/
} // end class