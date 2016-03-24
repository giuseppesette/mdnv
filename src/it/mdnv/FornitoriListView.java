package it.mdnv;

import it.mdnv.domain.Fornitore;
import it.mdnv.service.SedeOperativaService;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
 
@ManagedBean
@ViewScoped
public class FornitoriListView implements Serializable {

	private static final long serialVersionUID = -1844336549607781737L;

	private List<Fornitore> frn1;
    
    private List<Fornitore> frn2;
     
    private List<Fornitore> frn3;
     
    private Fornitore selectedFornitore;
     
    @ManagedProperty("#{fornitoriService}")
    private SedeOperativaService service;
     
    @PostConstruct
    public void init() {
        frn1 = service.createFornitori(10);
        frn2 = service.createFornitori(5);
        frn3 = service.createFornitori(50);
    }
 
    public List<Fornitore> getFrn1() {
        return frn1;
    }
 
    public List<Fornitore> getFrn2() {
        return frn2;
    }
 
    public List<Fornitore> getFrn3() {
        return frn3;
    }    
 
    public void setService(SedeOperativaService service) {
        this.service = service;
    }
 
    public Fornitore getSelectedFornitore() {
        return selectedFornitore;
    }
 
    public void setSelectedFornitore(Fornitore selectedFornitore) {
        this.selectedFornitore = selectedFornitore;
    }
}
