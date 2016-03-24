package it.mdnv.mb;

import it.mdnv.facade.ClientiFacade;
import it.mdnv.model.Clienti;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@RequestScoped
@ManagedBean(name = "lclientebean")
public class MBLClienti extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{lclientebean}";

	// @ManagedProperty(value = ClientiMB.INJECTION_NAME)
	// private ClientiMB clientiMB;

	private List<Clienti> clientiLista;
	private Clienti selectedCliente;

	@PostConstruct
	public void init() {
		System.out.println("[MBLClienti][init] - @PostConstruct ");
		ClientiFacade clienteFacade = new ClientiFacade();
		clientiLista = clienteFacade.getListaClienti();
	}

	public void editCliente() {
		System.out.println("[MBLClienti][editCliente] - this.getSelectedCliente(): " + this.getSelectedCliente());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("clienti-ins.xhtml?idCliente=" + this.getSelectedCliente().getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // END editCliente

	public void onRowClienteSelect(SelectEvent event) {
		int idCliente = ((Clienti) event.getObject()).getId();
		System.out.println("[MBLClienti][onRowClienteSelect] - Cliente Selezionato: " + idCliente);

		// Recupero le informazioni del Cliente selezionato
		ClientiFacade clienteFacade = new ClientiFacade();
		Clienti cliente = clienteFacade.findClienteByIdCliente(idCliente);

		this.setSelectedCliente(cliente);

	}// END onRowClienteSelect

	public void cancellaCliente(){
		System.out.println("[MBLClienti][cancellaCliente] - this.getSelectedCliente(): " + this.getSelectedCliente());
		
		ClientiFacade clienteFacade = new ClientiFacade();
		boolean isCancelled = clienteFacade.cancellaCliente(this.getSelectedCliente());
		if(isCancelled){
			clientiLista = clienteFacade.getListaClienti();
			displayInfoMessageToUser("Cancellazione Cliente '"+this.getSelectedCliente().getCodCliente()+"' avvenuta Correttamente!");
		}
			
		
	} // END cancellaCliente
	
	public List<Clienti> getClientiLista() {
		return clientiLista;
	}

	public Clienti getSelectedCliente() {
		return selectedCliente;
	}

	public void setSelectedCliente(Clienti selectedCliente) {
		this.selectedCliente = selectedCliente;
	}
	/**
	 * public void setClientiMB(ClientiMB clientiMB) { this.clientiMB =
	 * clientiMB; } public ClientiMB getClientiMB() { return this.clientiMB; }
	 **/
}// end class