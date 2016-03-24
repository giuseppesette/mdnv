package it.mdnv.mb;

import it.mdnv.domain.Materiale;
import it.mdnv.facade.AssemblatiFacade;
import it.mdnv.facade.FamiglieFacade;
import it.mdnv.facade.FornitoriFacade;
import it.mdnv.facade.TipologieFacade;
import it.mdnv.model.Assemblati;
import it.mdnv.model.Fornitori;
import it.mdnv.model.ProdFornitori;
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

@ViewScoped
@ManagedBean(name = "prodassbean")
public class ProdottiAssemblatiMB extends ProdottiGetAndSetter implements Serializable {

	private static final long serialVersionUID = -4920483344437207917L;
	public static final String INJECTION_NAME = "#{prodassbean}";

	private Assemblati asmbl;
	
	//private final String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
	//private boolean isModificaAssemblati = false;
	//private boolean isAssemblati = false;
	private String titleSchedaMaterialeAssemblati;
	private String assemblati;
	

	private ProdFornitori prodForn = new ProdFornitori();
	private List<ProdFornitori> prodFornitoreList;
	private ProdFornitori selectedProdFornitori;
	
	private Materiale materiale;
	private List<Materiale> materiali;
	
	private boolean isModifica = false;
	
	/** ################################################################# **/
	/********** INIZIALIZZAZIONI ****************/
	/**
	 * Costruttore
	 */
	public ProdottiAssemblatiMB() {
		System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - START");
		try {
			
			// Identificazione della pagina
			//retrievePageMaterialiAssemblati();
			
			// CASO IN CUI PROVENGO DALLA LISTA (MODIFICA)
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			//System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - getRequestParameterMap...: " + params);
			//System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - isModifica: " + isModifica);
			if (params != null && params.containsKey(Constants.IDASSEMBLATO)) {
				isModifica = true;
				System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - idAssemblato...........: " + params.get(Constants.IDASSEMBLATO));
				//System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - isModifica: " + isModifica);

				// Valorizzo i bean per i dati singoli e specifici (no liste)
				AssemblatiFacade af = new AssemblatiFacade();
				this.asmbl = af.findAssemblatoByIdAssemblato(Integer.parseInt(params.get(Constants.IDASSEMBLATO)));

				System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - this.asmbl............: " + this.asmbl);
				//System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				//System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - this.prod.getProdFornitoris...: " + this.prod.getProdFornitoris());
				//System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				
				//System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - Disabilito i pulsanti 'Salva' ed abilito i pulsanti 'Aggiorna'");
				abilitaAggiornaSuTutti();

			} // END
		} catch (Exception e) {
			e.printStackTrace();
			displayErrorMessageToUser("[COSTRUTTORE] - Si è verificato un errore: '" + e.getMessage() + "'");
		}

		//System.out.println("[ProdottiAssemblatiMB][COSTRUTTORE] - Prodotto: " + this.getProd());
		if (this.getAsmbl() != null && this.asmbl.getId() != 0) {

		} else {
			this.asmbl = new Assemblati();
		}
	} // END COSTRUTTORE

	/**
	 * INIT
	 */
	@PostConstruct
	public void init() {
		System.out.println("[ProdottiAssemblatiMB][INIT] - @PostConstruct ");
		if (isModifica) {

			this.famiglia = String.valueOf(this.asmbl.getFamiglia().getFamigliaId());
			this.tipologia = String.valueOf(this.asmbl.getTipologie().getId());

			// @FIXME
			// Aggiornamento lista Prodotti per Fornitore
			//aggiornaListaProdottiFornitori();
			
		} else {
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
		
		aggiornaListaMateriali();

	} // END init
	

	/**
	 * retrievePageMaterialiAssemblati
	 * 
	 * @throws Exception
	 *
	private void retrievePageMaterialiAssemblati() throws Exception {

		System.out.println("[ProdottiMaterialiAssemblatiMB][retrievePageMaterialiAssemblati] - viewId......: " + viewId);
		
		if(viewId != null && !viewId.equalsIgnoreCase("") && viewId.contains("/assemblati")){
			this.titleSchedaMaterialeAssemblati = Constants.TITLE_SCHEDA_ASSEMBLATI;
			this.assemblati = "Y";
			this.isAssemblati = true;
		} else {
			this.titleSchedaMaterialeAssemblati = Constants.TITLE_SCHEDA_MATERIALI;
			this.assemblati = "N";
			this.isAssemblati = false;
		}
		System.out.println("[ProdottiMaterialiAssemblatiMB][retrievePageMaterialiAssemblati] - assemblati..: " + assemblati);
	} // END retrievePageMaterialiAssemblati
	*/
	
	/**
	 * aggiornaListaMateriali
	 * 
	 */
	private void aggiornaListaMateriali(){
		System.out.println("[ProdottiMaterialiAssemblatiMB][aggiornaListaMateriali] - INIT");
		materiale = new Materiale();
		
		//@ FIXME: inserire logica da db
    	materiali = new ArrayList<Materiale>();
    	materiali.add(new Materiale("MT1", "Materiale 1", "10"));
    	materiali.add(new Materiale("MT2", "Materiale 2", "3"));
		
    	
    	System.out.println("[ProdottiMaterialiAssemblatiMB][aggiornaListaMateriali] - END");
	} // END aggiornaListaMateriali
	
	/********** END INIZIALIZZAZIONI ****************/
	/** ################################################################# **/

	/** ################################################################# **/
	/********** ACTION SCHEDE ****************/

	/**
	 * saveAssemblato
	 */
	public void saveAssemblato() {
		System.out.println("[ProdottiAssemblatiMB][saveAssemblato] - ");

		// Verifica dell'inserimento di tutti i campi obbligatori
		String nomeCampoDaInserire = verificaCampiObbligatori();

		// Verifica inserimento Campi Obbligatori
		if (nomeCampoDaInserire == null) {

			//System.out.println("[ProdottiAssemblatiMB][saveAssemblato] - idProdotto     ...: " + this.prod.getProdottiId());
			//System.out.println("[ProdottiAssemblatiMB][saveAssemblato] - idFamiglia     ...: " + this.prod.getFamiglia().getFamigliaId());
			//System.out.println("[ProdottiAssemblatiMB][saveAssemblato] - idTipologia    ...: " + this.prod.getTipologie().getId());
			//System.out.println("[ProdottiAssemblatiMB][saveAssemblato] - codiceProdotto ...: " + this.prod.getCodProdotto());

			try {
				AssemblatiFacade af = new AssemblatiFacade();
				Assemblati assemblato = af.createAssemblato(this.asmbl);
				if(assemblato != null && assemblato.getId() != 0){
					
					// Effettuo l'UPDATE del Codice Assemblato generato automaticamente
					assemblato.setCodAssemblato(Utilities.getCodiceComposto(Constants.PREFIX_CODASMB, assemblato.getId()));
					af.updateAssemblato(assemblato);
					
					this.setAsmbl(assemblato);
	
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
	
					// Imposto la visualizzazione del Panel relativo alla Scheda
					// Materiali
					// this.setActiveIndex(1);
	
					displayInfoMessageToUser("Inserimento Assemblato '" + this.asmbl.getAssemblato() + "' con Codice '" + this.asmbl.getCodAssemblato() + "' avvenuto con successo!");
						
				} else 
					displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
				
			} catch (Exception e) {
				e.printStackTrace();
				displayErrorMessageToUser("[saveAssemblato] - Si è verificato un errore: '" + e.getMessage() + "'");
			}
		} else {
			displayWarningMessageToUser("Specificare il valore del campo: '" + nomeCampoDaInserire + "'");
		}

	} // END saveAssemblato

	/**
	 * updateAssemblato
	 */
	public void updateAssemblato() {
		System.out.println("[ProdottiAssemblatiMB][updateAssemblato] - ");

		// Verifica dell'inserimento di tutti i campi obbligatori
		String nomeCampoDaInserire = verificaCampiObbligatori();

		// Verifica inserimento Campi Obbligatori
		if (nomeCampoDaInserire == null) {

			try {
				AssemblatiFacade af = new AssemblatiFacade();
				this.asmbl = af.updateAssemblato(this.asmbl);

				this.setReadonly(true);

				displayInfoMessageToUser("Aggiornamento Assemblato '" + this.asmbl.getCodAssemblato() + "' avvenuto con successo.");

				// Disabilito il Pulsante "Salva" della Scheda Materiale
				this.setDisableSaveSchedaMateriale(true);
				// Abilito il Pulsante "Aggiorna" della Scheda Materiale
				this.setUpdateRenderedSchedaMateriale(true);

			} catch (Exception e) {
				displayErrorMessageToUser("[updateAssemblato] - Si è verificato un errore: '" + e.getMessage() + "'");
			}

		} else {
			displayWarningMessageToUser("Specificare il valore del campo: '" + nomeCampoDaInserire + "'");
		}

	} // END updateAssemblato

	/**
	 * saveSchedaMaterialeAssemblato
	 */
	public void saveSchedaMaterialeAssemblato() {
		System.out.println("[ProdottiAssemblatiMB][saveSchedaMaterialeAssemblato] - ");
/***
		// Disabilito il Pulsante "Salva" della Scheda Materiale
		this.setDisableSaveSchedaMateriale(true);
		// Abilito il Pulsante "Aggiorna" della Scheda Materiale
		this.setUpdateRenderedSchedaMateriale(true);

		displayInfoMessageToUser("Inserimento Dettagli per il Prodotto '" + this.prod.getProdotto() + "' con Codice '" + this.prod.getCodProdotto() + "' avvenuto con successo!");
***/
	} // END saveSchedaMaterialeAssemblato

	/**
	 * updateSchedaMaterialeAssemblato
	 */
	public void updateSchedaMaterialeAssemblato() {
		System.out.println("[ProdottiAssemblatiMB][updateSchedaMaterialeAssemblato] - ");
/***
		//System.out.println("[ProdottiAssemblatiMB][updateSchedaMateriale] - Prodotto.........: " + this.getProd());
		
		//System.out.println("[ProdottiAssemblatiMB][updateSchedaMateriale] - idProdotto.......: " + this.prod.getProdottiId());
		//System.out.println("[ProdottiAssemblatiMB][updateSchedaMateriale] - descrizione......: " + this.prod.getDescrizione());
		//System.out.println("[ProdottiAssemblatiMB][updateSchedaMateriale] - materiale........: " + this.prod.getMateriale());
		//System.out.println("[ProdottiAssemblatiMB][updateSchedaMateriale] - unità di misura..: " + this.prod.getUm());

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
***/
	} // END updateSchedaMaterialeAssemblato

	/**
	 * saveProdottoFornitoreAssemblato
	 * 
	 */
	public void saveProdottoFornitoreAssemblato() {
		System.out.println("[ProdottiAssemblatiMB][saveProdottoFornitoreAssemblato] - ");
/***
		try{
			if(this.getProd().getProdottiId() != -1){
				if(this.getFornitore() != null &&
						Integer.parseInt(this.getFornitore()) > 0){
					
					if(this.getProdForn().getCodArticolo() != null && 
							!this.getProdForn().getCodArticolo().equalsIgnoreCase("")){

						List<Prodotti> prodottis = new ArrayList<Prodotti>();
						prodottis.add(this.getProd());
						//System.out.println("[ProdottiAssemblatiMB][saveProdottoFornitore] - List<Prodotti>: " + prodottis);
						
						ProdottiFacade pf = new ProdottiFacade();
						
						this.getProdForn().setProdottis(prodottis);
						this.getProdForn().setIdFornitore(Integer.parseInt(this.getFornitore()));
						
						ProdFornitori prodFornitori = pf.aggiungiFornitoreMateriali(this.getProdForn());
						//System.out.println("[ProdottiAssemblatiMB][saveProdottoFornitore] - ProdFornitori ID: " + prodFornitori.getId());
						
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
***/
	} // END saveProdottoFornitoreAssemblato
	
	/**
	 * updateProdottoFornitoreAssemblato
	 */
	public void updateProdottoFornitoreAssemblato() {
		System.out.println("[ProdottiAssemblatiMB][updateProdottoFornitoreAssemblato] - INIZIO");
/***
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
		
			//System.out.println("[ProdottiAssemblatiMB][updateProdottoFornitore] - fornitore: " + fornitore);
			
			fornitore = "0";
			
		}catch(Exception e){
			e.printStackTrace();
			displayErrorMessageToUser("[updateProdottoFornitore] - Si è verificato un errore: '" + e.getMessage() + "'");
		}
***/
	} // END updateProdottoFornitoreAssemblato
	
	
	/********** END ACTION SCHEDE ****************/
	/** ################################################################# **/


	/** ################################################################# **/

	/********** EVENT METHODS ****************/
	
	/**
	 * completeNomeAssemblato
	 * 
	 * @param String
	 *            name
	 * @return List<String>
	 */
	public List<String> completeNomeAssemblato(String name) {
		//System.out.println("[ProdottiMaterialiAssemblatiMB][completeNomeAssemblato] - name: " + name);
		List<String> results = new ArrayList<String>();

		AssemblatiFacade af = new AssemblatiFacade();
		List<Assemblati> listaAssemblati = af.getListaAssemblati();
		for (Assemblati assemblato : listaAssemblati) {
			if (assemblato.getAssemblato().toLowerCase().startsWith(name.toLowerCase())) {
				results.add(assemblato.getAssemblato());
				this.asmbl.setCodAssemblato(assemblato.getCodAssemblato());
			}
		}

		return results;
	} // END completeNomeAssemblato
	

	/**
	 * popolaCodiceAssemblato
	 */
	public void popolaCodiceAssemblato() {
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

			//System.out.println("[ProdottiMaterialiAssemblatiMB][popolaCodiceAssemblato] - assemblato........: " + this.asmbl);

			AssemblatiFacade af = new AssemblatiFacade();
			Assemblati assemblatoToUpdated = af.getAssemblatoFromNomeAssemblato(this.asmbl.getAssemblato());

			//System.out.println("[ProdottiMaterialiAssemblatiMB][popolaCodiceAssemblato] - assemblatoToUpdated...: " + assemblatoToUpdated);

			this.asmbl.setCodAssemblato(assemblatoToUpdated.getCodAssemblato());

			/*********************************************************/

		} else {
			displayWarningMessageToUser("Selezionare la " + nomeCampoDaInserire + " a cui associare l'Assemblato che si vuol inserire.");
			this.asmbl.setAssemblato("");
		}
	} // END popolaCodiceAssemblato
	
	/**
	 * clickCodiceAssemblato
	 * 
	 * Appena si clicca sul campo 'Nome Prodotto' vengono ripuliti i campi e si
	 * riabilitano tutti i pulsanti "Salva"
	 */
	public void clickCodiceAssemblato() {
		//System.out.println("[ProdottiMaterialiAssemblatiMB][clickCodiceAssemblato] - nomeProdotto: " + this.prod.getProdotto());
		//System.out.println("[ProdottiMaterialiAssemblatiMB][clickCodiceAssemblato] - isModifica: " + isModifica);
		if (!isModifica) {
			
			this.asmbl.setAssemblato("");
			this.asmbl.setCodAssemblato("");
	
			abilitaSalvaSuTutti();
	
			this.setUpdateRendered(false);
		}
	} // END clickCodiceAssemblato
	
	/**
	 * onFamigliaChange
	 */
	public void onFamigliaChange() {
		//System.out.println("[ProdottiMaterialiAssemblatiMB][onFamigliaChange] - famiglia: " + famiglia);
		if (famiglia != null && !famiglia.equalsIgnoreCase("")) {
			FamiglieFacade ff = new FamiglieFacade();
			this.asmbl.setFamiglia(ff.getFamigliaFromId(Integer.parseInt(famiglia)));
		}
		//System.out.println("[ProdottiMaterialiAssemblatiMB][onFamigliaChange] - idFamiglia......: " + this.prod.getFamiglia().getFamigliaId());
	} // END onFamigliaChange

	/**
	 * onTipologiaChange
	 */
	public void onTipologiaChange() {
		//System.out.println("[ProdottiMaterialiAssemblatiMB][onTipologiaChange] - tipologia: " + tipologia);
		if (tipologia != null && !tipologia.equalsIgnoreCase("")) {
			TipologieFacade tf = new TipologieFacade();
			this.asmbl.setTipologie(tf.getTipologiaFromId(Integer.parseInt(tipologia)));
		}
		//System.out.println("[ProdottiMaterialiAssemblatiMB][onTipologiaChange] - idTipologia......: " + this.prod.getTipologie().getId());
	} // END onTipologiaChange

	/**
	 * onFornitoreChange
	 */
	public void onFornitoreChange() throws Exception {
		//System.out.println("[ProdottiMaterialiAssemblatiMB][onFornitoreChange] - fornitore: " + fornitore);
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
	
	
    public void createNew() {
    	System.out.println("[ProdottiMaterialiAssemblatiMB][createNew] - ");
        if(materiali.contains(materiale)) {
            //FacesMessage msg = new FacesMessage("Dublicated", "Questo Materiale è stato già aggiunto");
            //FacesContext.getCurrentInstance().addMessage(null, msg);
            
            displayWarningMessageToUser("Questo Materiale è stato già aggiunto");
        } 
        else {
        	System.out.println("[ProdottiMaterialiAssemblatiMB][createNew] - ");
        	
        	materiali.add(materiale);
        	materiale = new Materiale();
        }
    }
     
    public String reinit() {
    	System.out.println("[ProdottiMaterialiAssemblatiMB][reinit] - ");
    	materiale = new Materiale();
        return null;
    }
	
	/********** END EVENT METHODS ****************/
	/** ################################################################# **/

	/** ################################################################# **/
	/********** METODI PRIVATI E/O DI UTILITA' ****************/
	/**
	 * aggiornaListaProdottiFornitori
	 * 
	 * @FIXME
	private void aggiornaListaProdottiFornitori(){
		if(this.getAsmbl().getiId() > 0){
			AssemblatiFacade af = new AssemblatiFacade();
			prodFornitoreList = pf.findProdottoByIdProdotto(this.getProd().getProdottiId()).getProdFornitoris();
		}
	} // END aggiornaListaProdottiFornitori
    */
	/**
	 * verificaCampiObbligatori
	 * 
	 * @return String
	 */
	private String verificaCampiObbligatori() {
		String nomeCampoDaInserire = null;

		nomeCampoDaInserire = checkFamigliaTipologia();

		if (nomeCampoDaInserire == null)
			if (this.asmbl.getAssemblato() == null || (this.asmbl.getAssemblato() != null && this.asmbl.getAssemblato().equalsIgnoreCase("")))
				nomeCampoDaInserire = "Nome Assemblato";

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


	public Assemblati getAsmbl() {
		return asmbl;
	}

	public void setAsmbl(Assemblati asmbl) {
		this.asmbl = asmbl;
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
	
	
	
	
	
	
	public String getTitleSchedaMaterialeAssemblati() {
		return titleSchedaMaterialeAssemblati;
	}

	public void setTitleSchedaMaterialeAssemblati(String titleSchedaMaterialeAssemblati) {
		this.titleSchedaMaterialeAssemblati = titleSchedaMaterialeAssemblati;
	}

	public String getAssemblati() {
		return assemblati;
	}

	public void setAssemblati(String assemblati) {
		this.assemblati = assemblati;
	}
	
    public Materiale getMateriale() {
        return materiale;
    }
 
    public List<Materiale> getMateriali() {
        return materiali;
    }
	/********** END GET AND SETTER ****************/

}// end class