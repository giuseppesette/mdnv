package it.mdnv;

import it.mdnv.domain.Famiglia;
import it.mdnv.domain.Tipologia;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
 
@ManagedBean
@ViewScoped
public class FamigliaTipologiaView2 implements Serializable {

	private static final long serialVersionUID = -6912865290000770788L;
	
	private Map<String,Map<String, Object>> data = new HashMap<String, Map<String, Object>>();
	private Map<String, Object> famiglie;
    private Map<String, Object> tipologie;
	
    //private Object famiglia;
    private String famiglia; 
    private Object tipologia;  
     
    @PostConstruct
    public void init() {
    	
    	famiglie  = new HashMap<String, Object>();
    	famiglie.put("FM1", new Famiglia("FM1", "Famiglia_1"));
    	famiglie.put("FM2", new Famiglia("FM2", "Famiglia_2"));
    	famiglie.put("FM3", new Famiglia("FM3", "Famiglia_3"));
    	
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("TP1", new Tipologia("TP1", "Tipo 1 Fam 1"));
        map.put("TP2", new Tipologia("TP2", "Tipo 2 Fam 1"));
        map.put("TP3", new Tipologia("TP3", "Tipo 3 Fam 1"));
        data.put("FM1", map);
    	
    }

    public Map<String, Map<String, Object>> getData() {
        return data;
    }
    /*
    public Object getFamiglia() {
        return famiglia;
    }
     public void setFamiglia(Object famiglia) {
        this.famiglia = famiglia;
    }
     */
     public String getFamiglia() {
         return famiglia;
     }
     public void setFamiglia(String famiglia) {
         this.famiglia = famiglia;
     }
 
    public Object getTipologia() {
        return tipologia;
    }
 
    public void setTipologia(Object tipologia) {
        this.tipologia = tipologia;
    }
    
    public Map<String, Object> getFamiglie() {
        return famiglie;
    }
     public Map<String, Object> getTipologie() {
        return tipologie;
    }
    
    public void onFamigliaChange() {
    	System.out.println("[FamigliaTipologiaView][FamigliaTipologiaView] - famiglia: " + famiglia);
        if(famiglia !=null && !famiglia.equals(""))
        	tipologie = data.get(famiglia);
        else
        	tipologie = new HashMap<String, Object>();
    }
    
    
    
    /*
    public void displayLocation() {
        FacesMessage msg;
        if(tipologia != null && famiglia != null)
            msg = new FacesMessage("Selected", tipologia + " of " + famiglia);
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
    */
   
}
