package it.mdnv.dao;
 
import it.mdnv.model.Fornitori;
import it.mdnv.model.Lavori;
import it.mdnv.model.Referenti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 
public class ReferenteDAO extends GenericDAO<Referenti> {
 
	private static final long serialVersionUID = 1L;

	public ReferenteDAO() {
        super(Referenti.class);
    }

	
    public List<Referenti> getListaReferentiByIdFornitore(Integer idFornitore){
    	
    	List<Referenti> listRefFrn = new ArrayList<Referenti>();
    	
    	List<Referenti> listaRef = super.findAll();
    	Iterator<Referenti> ir = listaRef.iterator();
    	while(ir.hasNext()){
    		Referenti ref = ir.next();
    		List<Fornitori> listFrn = ref.getFornitoris();
    		Iterator<Fornitori> il = listFrn.iterator();
    		while(il.hasNext()){
    			Fornitori frn = il.next();
    			if(frn.getId() == idFornitore.intValue())
    				listRefFrn.add(ref);
    		}
    	}
    	return listRefFrn;
    }
	
    public Referenti findReferenteByIdReferente(Integer idReferente){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_referente", idReferente);     
         return super.findOneResult(Referenti.FIND_REFERENTE_BY_ID, parameters);
    }
    
    public List<Referenti> getListaReferentiByIdCliente(Integer idCliente){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("id_cliente", idCliente);     
       //System.out.println("[ReferenteDAO][getListaReferentiByIdCliente] - parameters: " + parameters);
        return super.findAllReferencedById(Referenti.FIND_REFERENTE_BY_ID_CLIENTE, parameters);
    }
    
    public List<Referenti> getListaReferentiByIdLavoro(Integer idLavoro){
    	
    	List<Referenti> listRefLav = new ArrayList<Referenti>();
    	
    	List<Referenti> listaRef = super.findAll();
    	Iterator<Referenti> ir = listaRef.iterator();
    	while(ir.hasNext()){
    		Referenti ref = ir.next();
    		List<Lavori> listLav = ref.getLavoris();
    		Iterator<Lavori> il = listLav.iterator();
    		while(il.hasNext()){
    			Lavori lav = il.next();
    			if(lav.getId() == idLavoro.intValue())
    				listRefLav.add(ref);
    		}
    	}
    	return listRefLav;
    	
//        Map<String, Object> parameters = new HashMap<String, Object>();
//        parameters.put("id_lavoro", idLavoro);     
//        return super.findAllReferencedById(Referenti.FIND_REFERENTE_BY_ID_CLIENTE, parameters);
    }
    
    public List<Referenti> getListaSediOperative(){
        return super.findAll();
    }
    
    public Referenti create(Referenti referente){
    	return super.create(referente);
    }
    
    
}// end class