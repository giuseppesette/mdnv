package it.mdnv;


import it.mdnv.domain.Referente;
import it.mdnv.service.ReferentiService;

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

@ManagedBean(name = "referentiDTView")
@ViewScoped
public class ReferentiDTView implements Serializable {

	private static final long serialVersionUID = 2959296320745117732L;
	
	private List<Referente> ref1;
	private List<Referente> ref2;
	private List<Referente> ref3;
	private List<Referente> ref4;
	private List<Referente> ref5;

	private Referente selectedReferente;
	private List<Referente> selectedReferenti;

	@ManagedProperty("#{referentiService}")
	private ReferentiService service;

	@PostConstruct	
	public void init() {
		//System.out.println("[ReferentiDTView][init] ");
		ref1 = service.createReferente(5);
		ref2 = service.createReferente(5);
		ref3 = service.createReferente(5);
		ref4 = service.createReferente(5);
		ref5 = service.createReferente(5);
	}

	public List<Referente> getRef1() {
		return ref1;
	}

	public List<Referente> getRef2() {
		return ref2;
	}

	public List<Referente> getRef3() {
		return ref3;
	}

	public List<Referente> getRef4() {
		return ref4;
	}

	public List<Referente> getRef5() {
		return ref5;
	}

	public void setService(ReferentiService service) {
		this.service = service;
	}

	public Referente getSelectedReferente() {
		return selectedReferente;
	}

	public void setSelectedReferente(Referente selectedReferente) {
		this.selectedReferente = selectedReferente;
	}

	public List<Referente> getSelectedReferenti() {
		return selectedReferenti;
	}

	public void setSelectedReferenti(List<Referente> selectedReferenti) {
		this.selectedReferenti = selectedReferenti;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Referente Selected",
				((Referente) event.getObject()).getCognome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Referente Unselected",
				((Referente) event.getObject()).getCognome());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
