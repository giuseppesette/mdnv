package it.mdnv.mb;

import it.mdnv.facade.FamiglieFacade;
import it.mdnv.facade.FornitoriFacade;
import it.mdnv.facade.ProdottiFacade;
import it.mdnv.facade.TipologieFacade;
import it.mdnv.model.Fornitori;
import it.mdnv.model.ProdFornitori;
import it.mdnv.model.Prodotti;
import it.mdnv.utils.Constants;
import it.mdnv.utils.Utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "prodottibean")
public class ProdottiMaterialiMB extends ProdottiGetAndSetter implements Serializable {

	private static final long serialVersionUID = -8186358638897022675L;

	public static final String INJECTION_NAME = "#{prodottibean}";
	

	private Prodotti prod;
	//private Prodotti prodToUpdated;

	private String viewComboBoxes = "Y";

	private ProdFornitori prodForn = new ProdFornitori();
	private List<ProdFornitori> prodFornitoreList;
	private ProdFornitori selectedProdFornitori;
	
	private boolean isModifica = false;
	
	/** ################################################################# **/
	/********** INIZIALIZZAZIONI ****************/
	/**
	 * Costruttore
	 */
	public ProdottiMaterialiMB() {
		System.out.println("[ProdottiMaterialiMB][COSTRUTTORE] - START");
		try {

			// CASO IN CUI PROVENGO DALLA LISTA (MODIFICA)
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

			if (params != null && params.containsKey(Constants.IDPRODOTTO)) {
				isModifica = true;

				// Valorizzo i bean per i dati singoli e specifici (no liste)
				ProdottiFacade pf = new ProdottiFacade();
				this.prod = pf.findProdottoByIdProdotto(Integer.parseInt(params.get(Constants.IDPRODOTTO)));

				abilitaAggiornaSuTutti();

			} // END

		} catch (Exception e) {
			e.printStackTrace();
			displayErrorMessageToUser("[COSTRUTTORE] - Si è verificato un errore: '" + e.getMessage() + "'");
		}

		if (this.getProd() != null && this.prod.getProdottiId() != 0
		// && !this.prod.getCodProdotto().equalsIgnoreCase("")
		) {

		} else {
			this.prod = new Prodotti();
		}

	} // END COSTRUTTORE

	/**
	 * INIT
	 */
	@PostConstruct
	public void init() {
		System.out.println("[ProdottiMaterialiMB][init] - @PostConstruct ");
		if (isModifica) {

			this.famiglia = String.valueOf(this.prod.getFamiglia().getFamigliaId());
			this.tipologia = String.valueOf(this.prod.getTipologie().getId());

			// Aggiornamento lista Prodotti per Fornitore
			aggiornaListaProdottiFornitori();
			
		} else {
			// Imposto la chiusura di tutti gli Accordion Panel
			//this.setActiveIndex(0);

			this.setReadonly(false);

			// Disabilito il Pulsante "Salva Scheda Materiale" della Scheda Materiale
			this.setDisableSaveSchedaMateriale(true);

			// Disabilito il Pulsante "Salva" dell'Elenco Fornitori
			this.setDisableAggiungiFornitoreMateriali(true);
		}

		// Popolo la lista delle Famiglie
		popolaComboFamiglie();
		// Popolo la lista delle Tipologie
		popolaComboTipologie();
		// Popolo la lista dei Fornitori
		popolaComboFornitori();

	} // END init


	/********** END INIZIALIZZAZIONI ****************/
	/** ################################################################# **/

	/** ################################################################# **/
	/********** ACTION SCHEDE ****************/

	/**
	 * saveProdotto
	 */
	public void saveProdotto() {
		//System.out.println("[ProdottiMaterialiMB][saveProdotto] - ");

		// Verifica dell'inserimento di tutti i campi obbligatori
		String nomeCampoDaInserire = verificaCampiObbligatori();

		// Verifica inserimento Campi Obbligatori
		if (nomeCampoDaInserire == null) {

			try {
				ProdottiFacade pf = new ProdottiFacade();
				//this.prod = pf.createProdotto(this.prod);
				Prodotti prodotto = pf.createProdotto(this.prod);
				if(prodotto != null && prodotto.getProdottiId() != 0){
				
					// Effettuo l'UPDATE del Codice Prodotto generato automaticamente
					prodotto.setCodProdotto(Utilities.getCodiceComposto(Constants.PREFIX_CODPROD, prodotto.getProdottiId()));
					pf.updateProdotto(prodotto);
					
					this.setProd(prodotto);

					// Disabilito il Pulsante "Salva"
					this.setDisable(true);
					// Abilito il Pulsante "Aggiorna"
					this.setUpdateRendered(true);
	
					// Disabilito il Pulsante "Salva" della Scheda Materiale
					this.setDisableSaveSchedaMateriale(true);
					// Abilito il Pulsante "Aggiorna" della Scheda Materiale
					this.setUpdateRenderedSchedaMateriale(true);
	
					// Abilito il Pulsante "Salva" dell'Elenco Fornitori
					this.setDisableAggiungiFornitoreMateriali(false);

					displayInfoMessageToUser("Inserimento Prodotto '" + this.prod.getProdotto() + "' con Codice '" + this.prod.getCodProdotto() + "' avvenuto con successo!");
					
				} else 
					displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
				
			} catch (Exception e) {
				e.printStackTrace();
				displayErrorMessageToUser("[saveProdotto] - Si è verificato un errore: '" + e.getMessage() + "'");
			}
		} else {
			displayWarningMessageToUser("Specificare il valore del campo: '" + nomeCampoDaInserire + "'");
		}

	} // END saveProdotto

	/**
	 * updateProdotto
	 */
	public void updateProdotto() {
		// Verifica dell'inserimento di tutti i campi obbligatori
		String nomeCampoDaInserire = verificaCampiObbligatori();

		// Verifica inserimento Campi Obbligatori
		if (nomeCampoDaInserire == null) {

			try {
				ProdottiFacade pf = new ProdottiFacade();
				this.prod = pf.updateProdotto(this.prod);

				this.setReadonly(true);

				displayInfoMessageToUser("Aggiornamento Prodotto '" + this.prod.getProdotto() + "' avvenuto con successo.");

				// Disabilito il Pulsante "Salva" della Scheda Materiale
				this.setDisableSaveSchedaMateriale(true);
				// Abilito il Pulsante "Aggiorna" della Scheda Materiale
				this.setUpdateRenderedSchedaMateriale(true);

			} catch (Exception e) {
				displayErrorMessageToUser("[updateProdotto] - Si è verificato un errore: '" + e.getMessage() + "'");
			}

		} else {
			displayWarningMessageToUser("Specificare il valore del campo: '" + nomeCampoDaInserire + "'");
		}
	} // END updateProdotto

	/**
	 * saveSchedaMateriale --> Probabilmente non verrà mai utilizzato
	 */
	public void saveSchedaMateriale() {
		//System.out.println("[ProdottiMaterialiMB][saveSchedaMateriale] - ");

		// Disabilito il Pulsante "Salva" della Scheda Materiale
		this.setDisableSaveSchedaMateriale(true);
		// Abilito il Pulsante "Aggiorna" della Scheda Materiale
		this.setUpdateRenderedSchedaMateriale(true);

		displayInfoMessageToUser("Inserimento Dettagli per il Prodotto '" + this.prod.getProdotto() + "' con Codice '" + this.prod.getCodProdotto() + "' avvenuto con successo!");
	} // END saveSchedaMateriale

	/**
	 * updateSchedaMateriale
	 */
	public void updateSchedaMateriale() {
		try {
			ProdottiFacade pf = new ProdottiFacade();
			this.prod = pf.updateProdotto(this.prod);

			displayInfoMessageToUser("Aggiornamento Scheda Materiali del Prodotto '" + this.prod.getProdotto() + "' avvenuto con successo.");

			// Disabilito il Pulsante "Salva" della Scheda Materiale
			this.setDisableSaveSchedaMateriale(true);
			// Abilito il Pulsante "Aggiorna" della Scheda Materiale
			this.setUpdateRenderedSchedaMateriale(true);

		} catch (Exception e) {
			e.printStackTrace();
			displayErrorMessageToUser("[updateSchedaMateriale] - Si è verificato un errore: '" + e.getMessage() + "'");
		}

	} // END updateSchedaMateriale

	/**
	 * saveProdottoFornitore
	 * 
	 */
	public void saveProdottoFornitore() {
		//System.out.println("[ProdottiMaterialiMB][saveProdottoFornitore] - ");
		try{
			if(this.getProd().getProdottiId() != -1){
				if(this.getFornitore() != null &&
						Integer.parseInt(this.getFornitore()) > 0){
					
					if(this.getProdForn().getCodArticolo() != null && 
							!this.getProdForn().getCodArticolo().equalsIgnoreCase("")){

						List<Prodotti> prodottis = new ArrayList<Prodotti>();
						prodottis.add(this.getProd());
						//System.out.println("[ProdottiMaterialiMB][saveProdottoFornitore] - List<Prodotti>: " + prodottis);
						
						ProdottiFacade pf = new ProdottiFacade();
						
						this.getProdForn().setProdottis(prodottis);
						this.getProdForn().setIdFornitore(Integer.parseInt(this.getFornitore()));
						
						ProdFornitori prodFornitori = pf.aggiungiFornitoreMateriali(this.getProdForn());
						//System.out.println("[ProdottiMaterialiMB][saveProdottoFornitore] - ProdFornitori ID: " + prodFornitori.getId());
						
						if(prodFornitori != null && prodFornitori.getId() != 0){
							// Associo il ProdottoFornitore al Prodotto tramite la tabella di relazione
							List<ProdFornitori> prodFornitoriS = new ArrayList<ProdFornitori>();
							prodFornitoriS.add(prodFornitori);
							this.getProd().setProdFornitoris(prodFornitoriS);
							pf.updateProdotto(this.getProd());
							
							displayInfoMessageToUser("Inseriemnto ProdottoFornitore 'Articolo: " + this.getProdForn().getCodArticolo() + " Fornitore: " + this.getProdForn().getCodFornitore() + "' avvenuto Correttamente!");
						} else 
							displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
						
						aggiornaListaProdottiFornitori();
						
						this.setProdForn(new ProdFornitori());
						
						this.fornitore = "";
						
					} else {
						displayWarningMessageToUser("Inserire il Codice Articolo.");
					}
				} else {
					displayWarningMessageToUser("Selezionare il Fornitore.");
				}
			} else {
				displayWarningMessageToUser("Per inserire un nuovo 'Prodotto' per Fornitore specificato, bisogna prima creare il Prodotto!");
			}
		}catch(Exception e){
			e.printStackTrace();
			displayErrorMessageToUser("Si è verificato un errore: " + e.getMessage());
		}
	} // END saveProdottoFornitore
	
	/**
	 * updateProdottoFornitore
	 */
	public void updateProdottoFornitore() {
		//System.out.println("[ProdottiMaterialiMB][updateProdottoFornitore] - INIZIO");
		try{
			ProdottiFacade pf = new ProdottiFacade();
			pf.updateFornitoreMateriali(this.getProdForn());
			
			displayInfoMessageToUser("Aggiornamento ProdottoFornitore 'Articolo: " + this.getProdForn().getCodArticolo() + " Fornitore: " + this.getProdForn().getCodFornitore() + "' avvenuto Correttamente!");
			
			aggiornaListaProdottiFornitori();
			
			// Abilito il Pulsante "Salva" dell'Elenco Fornitori
			this.setDisableAggiungiFornitoreMateriali(false);
			// Disabilito il Pulsante "Aggiorna" dell'Elenco Fornitori
			this.setUpdateRenderedFornitoreMateriali(false);
						
			// Deselect Row
			selectedProdFornitori = null;
			
			// Refresh dati
			prodForn = new ProdFornitori();

			fornitore = "0";
			
		}catch(Exception e){
			e.printStackTrace();
			displayErrorMessageToUser("[updateProdottoFornitore] - Si è verificato un errore: '" + e.getMessage() + "'");
		}
	} // END updateProdottoFornitore
	
	
	/********** END ACTION SCHEDE ****************/
	/** ################################################################# **/

	/** ################################################################# **/

	/********** EVENT METHODS ****************/
	/**
	 * onFamigliaChange
	 */
	public void onFamigliaChange() {
		//System.out.println("[ProdottiMaterialiMB][onFamigliaChange] - famiglia: " + famiglia);
		if (famiglia != null && !famiglia.equalsIgnoreCase("")) {
			FamiglieFacade ff = new FamiglieFacade();
			this.prod.setFamiglia(ff.getFamigliaFromId(Integer.parseInt(famiglia)));
		}
		//System.out.println("[ProdottiMaterialiMB][onFamigliaChange] - idFamiglia......: " + this.prod.getFamiglia().getFamigliaId());
	} // END onFamigliaChange

	/**
	 * onTipologiaChange
	 */
	public void onTipologiaChange() {
		//System.out.println("[ProdottiMaterialiMB][onTipologiaChange] - tipologia: " + tipologia);
		if (tipologia != null && !tipologia.equalsIgnoreCase("")) {
			TipologieFacade tf = new TipologieFacade();
			this.prod.setTipologie(tf.getTipologiaFromId(Integer.parseInt(tipologia)));
		}
		//System.out.println("[ProdottiMaterialiMB][onTipologiaChange] - idTipologia......: " + this.prod.getTipologie().getId());
	} // END onTipologiaChange

	/**
	 * onFornitoreChange
	 */
	public void onFornitoreChange() throws Exception {
		//System.out.println("[ProdottiMaterialiMB][onFornitoreChange] - fornitore: " + fornitore);
		try{
			if (fornitore != null && !fornitore.equalsIgnoreCase("")) {
				FornitoriFacade ff = new FornitoriFacade();
				Fornitori f = ff.getFornitoreFromId(Integer.parseInt(fornitore));
				//System.out.println("[ProdottiMaterialiMB][onFornitoreChange] - Dati Fornitore: " + f);
				if(f != null && f.getCodFornitore() != null && f.getId() > 0){
					this.prodForn.setCodFornitore(f.getCodFornitore());
					this.prodForn.setIdFornitore(f.getId());
				} else {
					displayWarningMessageToUser("[onFornitoreChange] - Fornitore ["+fornitore+"] non trovato in archivio!!!");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			displayErrorMessageToUser("[onFornitoreChange] - Errore: " + e.getMessage());
			throw e;
		}
	} // END onFornitoreChange
	
	
	/**
	 * completeNomeProdotto
	 * 
	 * @param String
	 *            name
	 * @return List<String>
	 */
	public List<String> completeNomeProdotto(String name) {
		//System.out.println("[ProdottiMaterialiMB][completeNomeProdotto] - name: " + name);
		List<String> results = new ArrayList<String>();

		ProdottiFacade pf = new ProdottiFacade();
		List<Prodotti> listaProdotti = pf.getListaProdotti();
		for (Prodotti prodotto : listaProdotti) {
			if (prodotto.getProdotto().toLowerCase().startsWith(name.toLowerCase())) {
				results.add(prodotto.getProdotto());
				this.prod.setCodProdotto(prodotto.getCodProdotto());
			}
		}

		return results;
	} // END completeNomeProdotto

	/**
	 * popolaCodiceProdotto
	 */
	public void popolaCodiceProdotto() {
		// Evento invocato se e solo se viene selezionato un Prodotto esistente:
		// cambio il pulsante da "Salva" ad "Aggiorna"

		// Verifica dell'inserimento di tutti i campi obbligatori
		String nomeCampoDaInserire = checkFamigliaTipologia();
		if (nomeCampoDaInserire == null) {
			// abilitaAggiornaSuTutti();

			// Disabilito il Pulsante "Salva"
			this.setDisable(true);
			// Abilito il Pulsante "Aggiorna"
			this.setUpdateRendered(true);

			// Disabilito il Pulsante "Salva" dell'Elenco Fornitori
			this.setDisableAggiungiFornitoreMateriali(true);
			// Abilito il Pulsante "Aggiorna" dell'Elenco Fornitori
			this.setUpdateRenderedFornitoreMateriali(true);

			//System.out.println("[ProdottiMaterialiMB][popolaCodiceProdotto] - prodotto........: " + this.prod);

			ProdottiFacade pf = new ProdottiFacade();
			Prodotti prodToUpdated = pf.getProdottoFromNomeProdotto(this.prod.getProdotto());

			//System.out.println("[ProdottiMaterialiMB][popolaCodiceProdotto] - prodToUpdated...: " + prodToUpdated);

			this.prod.setCodProdotto(prodToUpdated.getCodProdotto());

			/*********************************************************/

		} else {
			displayWarningMessageToUser("Selezionare la " + nomeCampoDaInserire + " a cui associare il Materiale che si vuol inserire.");
			this.prod.setProdotto("");
		}
	} // END popolaCodiceProdotto

	/***
	 * public void changeCodiceProdotto(){ System.out.println(
	 * "[ProdottiMaterialiMB][changeCodiceProdotto] - nomeProdotto: " +
	 * this.nomeProdotto);
	 * 
	 * // Abilito il Pulsante "Salva" 
	 * this.setDisable(false); 
	 * // Disabilito il Pulsante "Aggiorna" 
	 * this.setUpdateRendered(false);
	 * 
	 * } // END changeCodiceProdotto
	 **/

	/**
	 * clickCodiceProdotto
	 * 
	 * Appena si clicca sul campo 'Nome Prodotto' vengono ripuliti i campi e si
	 * riabilitano tutti i pulsanti "Salva"
	 */
	public void clickCodiceProdotto() {
		//System.out.println("[ProdottiMaterialiMB][clickCodiceProdotto] - nomeProdotto: " + this.prod.getProdotto());
		//System.out.println("[ProdottiMaterialiMB][clickCodiceProdotto] - isModifica: " + isModifica);
		if (!isModifica) {
			
			this.prod.setProdotto("");
			this.prod.setCodProdotto("");
	
			abilitaSalvaSuTutti();
	
			this.setUpdateRendered(false);
		}
	} // END onKeyUp

	
	/***
	 * 
	 * @param event
	 */
	public void onRowProdFornitoreSelect(SelectEvent event) {
		int idProdFornitori = ((ProdFornitori) event.getObject()).getId();
		//System.out.println("[ProdottiMaterialiMB][onRowProdFornitoreSelect] - Prodotto Fornitore Selezionato [idProdFornitori]: " + idProdFornitori);
		
		// Disabilito il Pulsante "Salva Scheda Materiale" della Scheda Materiale
		this.setDisableSaveSchedaMateriale(true);
		// Abilito il Pulsante "Aggiorna Scheda Materiale" della Scheda Materiale
		this.setUpdateRenderedSchedaMateriale(true);
		
		// Recupero le informazioni del Prodotto associato al Fornitore selezionato
		ProdottiFacade pf = new ProdottiFacade();
		//ProdFornitori prodfor = pf.findProdottoFornitoreById(idProdFornitori, this.prod);
		ProdFornitori prodfor = pf.findProdottoFornitoreById(idProdFornitori);
		
		this.setFornitore(String.valueOf(prodfor.getIdFornitore()));
		
		this.setProdForn(prodfor);

	}// END onRowProdFornitoreSelect
	
	
	public void editProdottoFornitore() {
		//System.out.println("[ProdottiMaterialiMB][editProdottoFornitore] - INVOKED");
		
		// Disabilito il Pulsante "Salva" dell'Elenco Fornitori
		this.setDisableAggiungiFornitoreMateriali(true);
		// Abilito il Pulsante "Aggiorna" dell'Elenco Fornitori
		this.setUpdateRenderedFornitoreMateriali(true);
		
		// Imposto la visualizzazione del Panel relativo all'Elenco Fornitori
		//this.setActiveIndex(3);
	}// END editProdottoFornitore
	
	/********** END EVENT METHODS ****************/
	/** ################################################################# **/

	/** ################################################################# **/
	/********** METODI PRIVATI E/O DI UTILITA' ****************/

	/**
	 * aggiornaListaProdottiFornitori
	 * 
	 */
	private void aggiornaListaProdottiFornitori(){
		if(this.getProd().getProdottiId() > 0){
			ProdottiFacade pf = new ProdottiFacade();
			prodFornitoreList = pf.findProdottoByIdProdotto(this.getProd().getProdottiId()).getProdFornitoris();
		}
	} // END aggiornaListaProdottiFornitori
	
	/**
	 * verificaCampiObbligatori
	 * 
	 * @return String
	 */
	private String verificaCampiObbligatori() {
		String nomeCampoDaInserire = null;

		/*********************************************************/
		/* 09/04/2015 */
		nomeCampoDaInserire = checkFamigliaTipologia();

		//System.out.println("[ProdottiMaterialiMB][verificaCampiObbligatori] - Nome Prodotto.....: " + this.prod.getProdotto());
		//System.out.println("[ProdottiMaterialiMB][verificaCampiObbligatori] - Codice Prodotto...: " + this.prod.getCodProdotto());
		if (nomeCampoDaInserire == null)
			if (this.prod.getProdotto() == null || (this.prod.getProdotto() != null && this.prod.getProdotto().equalsIgnoreCase("")))
				nomeCampoDaInserire = "Nome Prodotto";

		return nomeCampoDaInserire;
	} // END verificaCampiObbligatori

	/**
	 * checkFamigliaTipologia
	 * 
	 * @return String
	 */
	private String checkFamigliaTipologia() {
		String nomeCampoDaInserire = null;

		if (famiglia == null || (famiglia != null && famiglia.equalsIgnoreCase("")))
			nomeCampoDaInserire = "Famiglia";
		else if (tipologia == null || (tipologia != null && tipologia.equalsIgnoreCase("")))
			nomeCampoDaInserire = "Tipologia";
		return nomeCampoDaInserire;
	} // END checkFamigliaTipologia

	/**
	 * abilitaAggiornaSuTutti
	 */
	private void abilitaAggiornaSuTutti() {

		// Disabilito il Pulsante "Salva"
		this.setDisable(true);
		// Abilito il Pulsante "Aggiorna"
		this.setUpdateRendered(true);

		// Disabilito il Pulsante "Salva Scheda Materiale" della Scheda
		// Materiale
		this.setDisableSaveSchedaMateriale(true);
		// Abilito il Pulsante "Aggiorna Scheda Materiale" della Scheda
		// Materiale
		this.setUpdateRenderedSchedaMateriale(true);

	} // END abilitaAggiornaSuTutti

	/********************************************
	 * abilitaSalvaSuTutti
	 **/
	private void abilitaSalvaSuTutti() {

		// Disabilito il Pulsante "Salva"
		this.setDisable(false);
		// Abilito il Pulsante "Aggiorna"
		this.setUpdateRendered(false);

		// Disabilito il Pulsante "Salva Scheda Materiale" della Scheda Materiale
		// this.setDisableSaveSchedaMateriale(false);
		// Abilito il Pulsante "Aggiorna Scheda Materiale" della Scheda Materiale
		// this.setUpdateRenderedSchedaMateriale(false);

		// Abilito il Pulsante "Salva" dell'Elenco Fornitori
		this.setDisableAggiungiFornitoreMateriali(false);
		// Disabilito il Pulsante "Aggiorna" dell'Elenco Fornitori
		this.setUpdateRenderedFornitoreMateriali(false);

	} // END abilitaSalvaSuTutti


	/********** END METODI PRIVATI E/O DI UTILITA' ****************/
	/** ################################################################# **/

	/********** GET AND SETTER ****************/

	public Prodotti getProd() {
		return prod;
	}

	public void setProd(Prodotti prod) {
		this.prod = prod;
	}

	public String getViewComboBoxes() {
		return viewComboBoxes;
	}

	public ProdFornitori getProdForn() {
		return prodForn;
	}

	public void setProdForn(ProdFornitori prodForn) {
		this.prodForn = prodForn;
	}

	public List<ProdFornitori> getProdFornitoreList() {
		return prodFornitoreList;
	}

	public void setProdFornitoreList(List<ProdFornitori> prodFornitoreList) {
		this.prodFornitoreList = prodFornitoreList;
	}

	public ProdFornitori getSelectedProdFornitori() {
		return selectedProdFornitori;
	}

	public void setSelectedProdFornitori(ProdFornitori selectedProdFornitori) {
		this.selectedProdFornitori = selectedProdFornitori;
	}

	/********** END GET AND SETTER ****************/

}// end class