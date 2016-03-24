package it.mdnv.mb;

import it.mdnv.facade.FornitoriFacade;
import it.mdnv.model.Fornitori;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@RequestScoped
@ManagedBean(name = "lfornitorebean")
public class MBLFornitori extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{lfornitorebean}";

	private List<Fornitori> fornitoriLista;
	private Fornitori selectedFornitore;

	@PostConstruct
	public void init() {
		System.out.println("[MBLFornitori][init] - @PostConstruct ");
		FornitoriFacade fornitoriFacade = new FornitoriFacade();
		fornitoriLista = fornitoriFacade.getListaFornitori();
	}

	public void editFornitore() {
		System.out.println("[MBLFornitori][editFornitore] - this.getSelectedFornitore(): " + this.getSelectedFornitore());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("fornitori-ins.xhtml?idFornitore=" + this.getSelectedFornitore().getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // END editFornitore

	public void onRowFornitoreSelect(SelectEvent event) {
		int idFornitore = ((Fornitori) event.getObject()).getId();
		System.out.println("[MBLFornitori][onRowFornitoreSelect] - Fornitore Selezionato: " + idFornitore);

		// Recupero le informazioni del Fornitore selezionato
		FornitoriFacade fornitoriFacade = new FornitoriFacade();
		Fornitori fornitore = fornitoriFacade.findFornitoreByIdFornitore(idFornitore);

		this.setSelectedFornitore(fornitore);

	}// END onRowFornitoreSelect

	public void cancellaFornitore(){
		System.out.println("[MBLFornitori][cancellaFornitore] - this.getSelectedFornitore(): " + this.getSelectedFornitore());
		
		FornitoriFacade fornitoriFacade = new FornitoriFacade();
		boolean isCancelled = fornitoriFacade.cancellaFornitore(this.getSelectedFornitore());
		if(isCancelled){
			fornitoriLista = fornitoriFacade.getListaFornitori();
			displayInfoMessageToUser("Cancellazione Fornitore '"+this.getSelectedFornitore().getCodFornitore()+"' avvenuta Correttamente!");
		}
			
		
	} // END cancellaFornitore
	
	public List<Fornitori> getFornitoriLista() {
		return fornitoriLista;
	}

	public Fornitori getSelectedFornitore() {
		return selectedFornitore;
	}

	public void setSelectedFornitore(Fornitori selectedFornitore) {
		this.selectedFornitore = selectedFornitore;
	}

}// end class