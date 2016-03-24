package it.mdnv;

import it.mdnv.domain.Fornitore;
import it.mdnv.service.FornitoriService;

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

@ManagedBean(name = "fornitoriDTView")
@ViewScoped
public class FornitoriDTView implements Serializable {

	private static final long serialVersionUID = 2959296320745117732L;
	
	private List<Fornitore> frn1;
	private List<Fornitore> frn2;
	private List<Fornitore> frn3;
	private List<Fornitore> frn4;
	private List<Fornitore> frn5;
	private List<Fornitore> frn6;
	
	private Fornitore selectedFornitore;
	private List<Fornitore> selectedFornitores;

	@ManagedProperty("#{fornitoriService}")
	private FornitoriService service;

	@PostConstruct
	public void init() {
		frn1 = service.createFornitori(10);
		frn2 = service.createFornitori(10);
		frn3 = service.createFornitori(10);
		frn4 = service.createFornitori(10);
		frn5 = service.createFornitori(10);
		frn6 = service.createFornitori(10);
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

	public List<Fornitore> getFrn4() {
		return frn4;
	}

	public List<Fornitore> getFrn5() {
		return frn5;
	}

	public List<Fornitore> getFrn6() {
		return frn6;
	}

	public void setService(FornitoriService service) {
		this.service = service;
	}

	public Fornitore getSelectedFornitore() {
		return selectedFornitore;
	}

	public void setSelectedFornitore(Fornitore selectedFornitore) {
		this.selectedFornitore = selectedFornitore;
	}

	public List<Fornitore> getSelectedFornitores() {
		return selectedFornitores;
	}

	public void setSelectedFornitores(List<Fornitore> selectedFornitores) {
		this.selectedFornitores = selectedFornitores;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Fornitore Selected",
				((Fornitore) event.getObject()).getModel());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Fornitore Unselected",
				((Fornitore) event.getObject()).getModel());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
