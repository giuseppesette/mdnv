package it.mdnv.mb;

import it.mdnv.facade.ProdottiFacade;
import it.mdnv.model.Prodotti;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@RequestScoped
@ManagedBean(name = "lprodottibean")
public class MBLProdotti extends AbstractMB implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{lprodottibean}";

	private List<Prodotti> prodottiLista;
	private Prodotti selectedProdotto;

	@PostConstruct
	public void init() {
		//System.out.println("[MBLProdotti][init] - @PostConstruct ");
		ProdottiFacade prodottiFacade = new ProdottiFacade();
		prodottiLista = prodottiFacade.getListaProdotti();
	}

	public void editProdotto() {
		//System.out.println("[MBLProdotti][editProdotto] - this.getSelectedProdotto(): " + this.getSelectedProdotto());
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("materiali-ins.xhtml?idProdotto=" + this.getSelectedProdotto().getProdottiId());
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // END editProdotto

	public void onRowProdottoSelect(SelectEvent event) {
		int idProdotto = ((Prodotti) event.getObject()).getProdottiId();
		//System.out.println("[MBLProdotti][onRowProdottoSelect] - Fornitore Selezionato: " + idProdotto);

		// Recupero le informazioni del Prodotto selezionato
		ProdottiFacade prodottiFacade = new ProdottiFacade();
		Prodotti prodotto = prodottiFacade.findProdottoByIdProdotto(idProdotto);

		this.setSelectedProdotto(prodotto);

	}// END onRowProdottoSelect

	public void cancellaProdotto(){
		//System.out.println("[MBLProdotti][cancellaProdotto] - this.getSelectedProdotto(): " + this.getSelectedProdotto());
		/***		
		ProdottiFacade prodottiFacade = new ProdottiFacade();
		boolean isCancelled = prodottiFacade.cancellaProdotto(this.getSelectedProdotto());
		if(isCancelled){
			prodottiLista = prodottiFacade.getListaProdotti();
			displayInfoMessageToUser("Cancellazione Prodotto '"+this.getSelectedProdotto().getCodProdotto()+"' avvenuta Correttamente!");
		}
		***/
	} // END cancellaProdotto

	public List<Prodotti> getProdottiLista() {
		return prodottiLista;
	}

	public Prodotti getSelectedProdotto() {
		return selectedProdotto;
	}

	public void setSelectedProdotto(Prodotti selectedProdotto) {
		this.selectedProdotto = selectedProdotto;
	}

}// end class