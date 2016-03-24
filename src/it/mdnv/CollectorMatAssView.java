package it.mdnv;

import it.mdnv.domain.Materiale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@ViewScoped
public class CollectorMatAssView implements Serializable {

	/*****	
	private Materiale materiale = new Materiale();
	private List<Materiale> materiali = new ArrayList<Materiale>();
	 *****/
	
	private Materiale materiale;
	private List<Materiale> materiali;
	
    @PostConstruct
    public void init() {
    	materiale = new Materiale();
    	materiali = new ArrayList<Materiale>();
    	materiali.add(new Materiale("MT1", "Materiale 1", "10"));
    	materiali.add(new Materiale("MT2", "Materiale 2", "3"));
    }

    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
    
    public void createNew() {
    	System.out.println("[CollectorMatAssView][createNew] - ");
        if(materiali.contains(materiale)) {
            FacesMessage msg = new FacesMessage("Dublicated", "Questo Materiale è stato già aggiunto");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } 
        else {
        	materiali.add(materiale);
        	materiale = new Materiale();
        }
    }
     
    public String reinit() {
    	System.out.println("[CollectorMatAssView][reinit] - ");
    	materiale = new Materiale();
        return null;
    }
 
    public Materiale getMateriale() {
        return materiale;
    }
 
    public List<Materiale> getMateriali() {
        return materiali;
    }
	
}// end class
