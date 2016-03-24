package it.mdnv.dao;
 

import it.mdnv.model.DatiFatturazione;
import it.mdnv.utils.Constants;

import java.util.HashMap;
import java.util.Map;
 
public class DatiFatturazioneDAO extends GenericDAO<DatiFatturazione> {
 
	private static final long serialVersionUID = 1L;

	public DatiFatturazioneDAO() {
        super(DatiFatturazione.class);
    }
	
    public DatiFatturazione create(DatiFatturazione datiFatturazione){
    	return super.create(datiFatturazione);
    }
    
    public DatiFatturazione findDatiFatturazioneByIdCliente(Integer idCliente){
        Map<String, Object> parameters = new HashMap<String, Object>();
        //"id_cliente"
        parameters.put(Constants.SQL_ID_CLIENTE, idCliente);     
 
        return super.findOneResult(DatiFatturazione.FIND_DATIFATT_BY_ID_CLIENTE, parameters);
    }

    public DatiFatturazione findDatiFatturazioneByIdFornitore(Integer idFornitore){
        Map<String, Object> parameters = new HashMap<String, Object>();
        //"id_fornitore"
        parameters.put(Constants.SQL_ID_FORNITORE, idFornitore);
        
        return super.findOneResult(DatiFatturazione.FIND_DATIFATT_BY_ID_FORNITORE, parameters);
    }
    
}// end class