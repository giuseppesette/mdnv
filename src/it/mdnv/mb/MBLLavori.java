package it.mdnv.mb;

import it.mdnv.facade.FolderLavoriFacade;
import it.mdnv.model.Lavori;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@RequestScoped
@ManagedBean(name = "llavorobean")
public class MBLLavori extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{llavorobean}";

	private List<Lavori> lavoriLista;
	private Lavori selectedLavoro;

	@PostConstruct
	public void init() {
		System.out.println("[MBLLavori][init] - @PostConstruct ");
		FolderLavoriFacade flf = new FolderLavoriFacade();
		lavoriLista = flf.getListaLavori();
	}

	public void editLavoro() {
		System.out.println("[MBLLavori][editLavoro] - this.getSelectedLavoro(): " + this.getSelectedLavoro().getId());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("lavoro-ins.xhtml?idLavoro=" + this.getSelectedLavoro().getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // END editLavoro

	public void onRowLavoroSelect(SelectEvent event) {
		int idLavoro = ((Lavori) event.getObject()).getId();
		System.out.println("[MBLLavori][onRowLavoroSelect] - Lavoro Selezionato: " + idLavoro);

		// Recupero le informazioni del Lavoro selezionato
		FolderLavoriFacade flf = new FolderLavoriFacade();
		Lavori lavoro = flf.findFolderLavoriById(idLavoro);

		this.setSelectedLavoro(lavoro);

	}// END onRowLavoroSelect

	public void cancellaLavoro(){
		System.out.println("[MBLLavori][cancellaLavoro] - this.getSelectedLavoro(): " + this.getSelectedLavoro());
		
		FolderLavoriFacade flf = new FolderLavoriFacade();
		boolean isCancelled = flf.cancellaLavoro(this.getSelectedLavoro());
		if(isCancelled){
			lavoriLista = flf.getListaLavori();
			displayInfoMessageToUser("Cancellazione Lavoro '"+this.getSelectedLavoro().getNomeLavoro()+"' avvenuta Correttamente!");
		}
	} // END cancellaLavoro
	
	public List<Lavori> getLavoriLista() {
		return lavoriLista;
	}

	public Lavori getSelectedLavoro() {
		return selectedLavoro;
	}

	public void setSelectedLavoro(Lavori selectedLavoro) {
		this.selectedLavoro = selectedLavoro;
	}

}// end class