package it.mdnv.mb;

import it.mdnv.facade.ContattiFacade;
import it.mdnv.model.Clienti;
import it.mdnv.model.Contatti;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@RequestScoped
@ManagedBean(name = "lcontattobean")
public class MBLContatti extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{lcontattobean}";

	private List<Contatti> contattiLista;
	private Contatti selectedContatto;

	@PostConstruct
	public void init() {
		//System.out.println("[MBLContatti][init] - @PostConstruct ");
		ContattiFacade contattiFacade = new ContattiFacade();
		contattiLista = contattiFacade.getListaContatti();
	}
	
	public void nuovoContatto() {
		try {
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String daDoveViene = params.get("here");
			if(daDoveViene != null && !daDoveViene.equalsIgnoreCase(""))
				FacesContext.getCurrentInstance().getExternalContext().redirect("pages/contatti-ins.xhtml");
			else
				FacesContext.getCurrentInstance().getExternalContext().redirect("contatti-ins.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // END nuovoContatto

	public void editContatto() {
		//System.out.println("[MBLContatti][editContatto] - this.getSelectedContatto(): " + this.getSelectedContatto());
		
		try {
			if(this.getSelectedContatto() != null){
				Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				String daDoveViene = params.get("here");
				if(daDoveViene != null && !daDoveViene.equalsIgnoreCase(""))
					FacesContext.getCurrentInstance().getExternalContext().redirect("pages/contatti-ins.xhtml?ID=" + this.getSelectedContatto().getId());
				else
					FacesContext.getCurrentInstance().getExternalContext().redirect("contatti-ins.xhtml?ID=" + this.getSelectedContatto().getId());
			} else 
				displayInfoMessageToUser("Selezionare un Contatto per modificarne le informazioni");
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // END editContatto

	public void onRowContattoSelect(SelectEvent event) {
		int idContatto = ((Contatti) event.getObject()).getId();
		//System.out.println("[MBLContatti][onRowContattoSelect] - ID Contatto Selezionato: " + idContatto);

		// Recupero le informazioni del Cliente selezionato
		ContattiFacade contattiFacade = new ContattiFacade();
		Contatti contatto = contattiFacade.findContattoByIdContatto(idContatto);

		this.setSelectedContatto(contatto);

	}// END onRowContattoSelect

	public void cancellaContatto(){
		//System.out.println("[MBLContatti][cancellaContatto] - this.getSelectedContatto(): " + this.getSelectedContatto());
		/****
		ContattiFacade contattiFacade = new ContattiFacade();
		boolean isCancelled = contattiFacade.cancellaContatto(this.getSelectedContatto());
		if(isCancelled){
			contattiLista = contattiFacade.getListaContatti();
			displayInfoMessageToUser("Cancellazione Contatto '"+this.getSelectedContatto().getRagioneSociale()+"' avvenuta Correttamente!");
		}
		****/	
		displayErrorMessageToUser("Cancellazione Contatto non abilitata!");
		
	} // END cancellaContatto
	
	public List<Contatti> getContattiLista() {
		return contattiLista;
	}

	public Contatti getSelectedContatto() {
		return selectedContatto;
	}

	public void setSelectedContatto(Contatti selectedContatto) {
		this.selectedContatto = selectedContatto;
	}

}// end class