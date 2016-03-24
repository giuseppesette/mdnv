package it.mdnv;


import it.mdnv.domain.Fattura;
import it.mdnv.service.FattureService;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "fattureDTView")
@ViewScoped
public class FattureDTView implements Serializable {

	private static final long serialVersionUID = 2959296320745117732L;
	
	private List<Fattura> fatt1;
	private List<Fattura> fatt2;
	private List<Fattura> fatt3;
	private List<Fattura> fatt4;
	private List<Fattura> fatt5;

	private Fattura selectedFattura;
	private List<Fattura> selectedFatture;

	@ManagedProperty("#{fattureService}")
	private FattureService service;

	@PostConstruct	
	public void init() {
		System.out.println("[FattureDTView][init] ");
		fatt1 = service.createFattura(5);
		fatt2 = service.createFattura(5);
		fatt3 = service.createFattura(5);
		fatt4 = service.createFattura(5);
		fatt5 = service.createFattura(5);
	}

	public List<Fattura> getFatt1() {
		return fatt1;
	}

	public List<Fattura> getFatt2() {
		return fatt2;
	}

	public List<Fattura> getFatt3() {
		return fatt3;
	}

	public List<Fattura> getFatt4() {
		return fatt4;
	}

	public List<Fattura> getFatt5() {
		return fatt5;
	}

	public void setService(FattureService service) {
		this.service = service;
	}

	public Fattura getSelectedFattura() {
		return selectedFattura;
	}

	public void setSelectedFattura(Fattura selectedFattura) {
		this.selectedFattura = selectedFattura;
	}

	public List<Fattura> getSelectedFatture() {
		return selectedFatture;
	}

	public void setSelectedFatture(List<Fattura> selectedFatture) {
		this.selectedFatture = selectedFatture;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Fattura Selected",
				((Fattura) event.getObject()).getNumeroFattura());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Fattura Unselected",
				((Fattura) event.getObject()).getNumeroFattura());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
