package it.mdnv;

import it.mdnv.domain.Materiale;
import it.mdnv.service.MaterialiService;

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

@ManagedBean(name = "materialiDTView")
@ViewScoped
public class MaterialiDTView implements Serializable {

	private static final long serialVersionUID = -8011123264957172829L;
	private List<Materiale> mat1;
	private List<Materiale> mat2;
	private List<Materiale> mat3;
	private List<Materiale> mat4;
	private List<Materiale> mat5;

	
	private Materiale selectedMateriale;
	private List<Materiale> selectedMateriali;

	@ManagedProperty("#{materialiService}")
	private MaterialiService service;

	@PostConstruct
	public void init() {
		mat1 = service.createMateriali(5);
		mat2 = service.createMateriali(5);
		mat3 = service.createMateriali(5);
		mat4 = service.createMateriali(5);
		mat5 = service.createMateriali(5);

	}

	public List<Materiale> getMat1() {
		return mat1;
	}

	public List<Materiale> getMat2() {
		return mat2;
	}

	public List<Materiale> getMat3() {
		return mat3;
	}

	public List<Materiale> getMat4() {
		return mat4;
	}

	public List<Materiale> getMat5() {
		return mat5;
	}


	public void setService(MaterialiService service) {
		this.service = service;
	}

	public Materiale getSelectedMateriale() {
		return selectedMateriale;
	}

	public void setSelectedMateriale(Materiale selectedMateriale) {
		this.selectedMateriale = selectedMateriale;
	}

	public List<Materiale> getSelectedMateriali() {
		return selectedMateriali;
	}

	public void setSelectedMateriali(List<Materiale> selectedMateriali) {
		this.selectedMateriali = selectedMateriali;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Materiale Selected",
				((Materiale) event.getObject()).getCodProdotto());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Materiale Unselected",
				((Materiale) event.getObject()).getCodProdotto());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
