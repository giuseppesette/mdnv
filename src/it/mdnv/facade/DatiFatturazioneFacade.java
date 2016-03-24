package it.mdnv.facade;

import it.mdnv.dao.DatiFatturazioneDAO;
import it.mdnv.model.DatiFatturazione;

public class DatiFatturazioneFacade {
	
	private DatiFatturazioneDAO datiFatturazioneDAO = new DatiFatturazioneDAO();
	
	public DatiFatturazione createDatiFatturazione(DatiFatturazione datiFatturazione){
		datiFatturazioneDAO.beginTransaction();
		DatiFatturazione newDatiFatturazione = datiFatturazioneDAO.create(datiFatturazione);
		if(newDatiFatturazione != null && newDatiFatturazione.getId() != 0)
			datiFatturazioneDAO.commitAndCloseTransaction();
		return newDatiFatturazione;
	}// END createDatiFatturazione
	
	public DatiFatturazione updateDatiFatturazione(DatiFatturazione datiFatturazione){
		datiFatturazioneDAO.beginTransaction();
		DatiFatturazione datiFatturazioneUpdated = datiFatturazioneDAO.update(datiFatturazione);
		datiFatturazioneDAO.commitAndCloseTransaction();
		return datiFatturazioneUpdated;
	}// END updateDatiFatturazione
	
	public DatiFatturazione findDatiFatturazioneByIdCliente(int idCliente){
		datiFatturazioneDAO.beginTransaction();
		return datiFatturazioneDAO.findDatiFatturazioneByIdCliente(idCliente);
	} // END findDatiFatturazioneByIdCliente


	public DatiFatturazione findDatiFatturazioneByIdFornitore(int idFornitore){
		datiFatturazioneDAO.beginTransaction();
		return datiFatturazioneDAO.findDatiFatturazioneByIdFornitore(idFornitore);
	} // END findDatiFatturazioneByIdFornitore

} // end class