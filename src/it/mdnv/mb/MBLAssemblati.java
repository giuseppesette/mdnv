package it.mdnv.mb;

import it.mdnv.facade.AssemblatiFacade;
import it.mdnv.model.Assemblati;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@RequestScoped
@ManagedBean(name = "lassemblatibean")
public class MBLAssemblati extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{lassemblatibean}";

	private List<Assemblati> assemblatiLista;
	private Assemblati selectedAssemblato;

	@PostConstruct
	public void init() {
		//System.out.println("[MBLAssemblati][init] - @PostConstruct ");
		AssemblatiFacade af = new AssemblatiFacade();
		assemblatiLista = af.getListaAssemblati();
	}

	public void editAssemblato() {
		//System.out.println("[MBLAssemblati][editAssemblato] - this.getSelectedProdotto(): " + this.getSelectedProdotto());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("assemblati-ins.xhtml?idAssemblato=" + this.getSelectedAssemblato().getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // END editAssemblato

	public void onRowAssemblatoSelect(SelectEvent event) {
		int idAssemblato = ((Assemblati) event.getObject()).getId();
		//System.out.println("[MBLAssemblati][onRowAssemblatoSelect] - Assemblato Selezionato: " + idAssemblato);

		// Recupero le informazioni dell'Assemblato selezionato
		AssemblatiFacade af = new AssemblatiFacade();
		Assemblati assemblato = af.findAssemblatoByIdAssemblato(idAssemblato);

		this.setSelectedAssemblato(assemblato);

	}// END onRowAssemblatoSelect

	public void cancellaAssemblato(){
		//System.out.println("[MBLAssemblati][cancellaAssemblato] - this.getSelectedAssemblato(): " + this.getSelectedAssemblato());
		/***		
		AssemblatiFacade af = new AssemblatiFacade();
		boolean isCancelled = af.cancellaAssemblato(this.getSelectedAssemblato());
		if(isCancelled){
			assemblatiLista = af.getListaAssemblati();
			displayInfoMessageToUser("Cancellazione Assemblato '"+this.getSelectedAssemblato().getCodAssemblato()+"' avvenuta Correttamente!");
		}
		***/
	} // END cancellaAssemblato

	public List<Assemblati> getAssemblatiLista() {
		return assemblatiLista;
	}

	public Assemblati getSelectedAssemblato() {
		return selectedAssemblato;
	}

	public void setSelectedAssemblato(Assemblati selectedAssemblato) {
		this.selectedAssemblato = selectedAssemblato;
	}

}// end class