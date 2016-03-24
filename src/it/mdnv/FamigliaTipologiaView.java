package it.mdnv;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

 
@ManagedBean
@ViewScoped
public class FamigliaTipologiaView implements Serializable {

	private static final long serialVersionUID = -6912865290000770788L;
	
	private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
	private Map<String,String> famiglie;
    private Map<String,String> tipologie;
    private String famiglia; 
    private String tipologia;  
    
    @PostConstruct
    public void init() {

    	famiglie  = new HashMap<String, String>();
    	famiglie.put("Famiglia_1", "F1");
    	famiglie.put("Famiglia_2", "F2");
    	famiglie.put("Famiglia_3", "F3");

    	
        Map<String,String> map = new HashMap<String, String>();
        map.put("Tipo 1 Fam 1", "T11");
        map.put("Tipo 2 Fam 1", "T12");
        map.put("Tipo 3 Fam 1", "T13");
        data.put("F1", map);
         
        map = new HashMap<String, String>();
        map.put("Tipo 1 Fam 2", "T21");
        map.put("Tipo 2 Fam 2", "T22");
        map.put("Tipo 3 Fam 2", "T23");
        data.put("F2", map);
         
        map = new HashMap<String, String>();
        map.put("Tipo 1 Fam 3", "T31");
        map.put("Tipo 2 Fam 3", "T32");
        map.put("Tipo 3 Fam 3", "T33");
        data.put("F3", map);
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }
 	
    public String getFamiglia() {
        return famiglia;
    }
 
    public void setFamiglia(String famiglia) {
        this.famiglia = famiglia;
    }
 
    public String getTipologia() {
        return tipologia;
    }
 
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public Map<String, String> getFamiglie() {
        return famiglie;
    }
     public Map<String, String> getTipologie() {
        return tipologie;
    }
    
    public void onFamigliaChange() {
    	System.out.println("[FamigliaTipologiaView][FamigliaTipologiaView] - famiglia: " + famiglia);
        if(famiglia !=null && !famiglia.equals(""))
        	tipologie = data.get(famiglia);
        else
        	tipologie = new HashMap<String, String>();
    }
    
    public void displayLocation() {
        FacesMessage msg;
        if(tipologia != null && famiglia != null)
            msg = new FacesMessage("Selected", tipologia + " of " + famiglia);
        else
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid", "City is not selected."); 
             
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
	
    
    
}
