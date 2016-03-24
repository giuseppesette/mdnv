package it.mdnv.mb;

import it.mdnv.facade.DatiFatturazioneFacade;
import it.mdnv.facade.FornitoriFacade;
import it.mdnv.facade.MaterialiFacade;
import it.mdnv.facade.ReferentiFacade;
import it.mdnv.model.DatiFatturazione;
import it.mdnv.model.Fornitori;
import it.mdnv.model.Materiali;
import it.mdnv.model.Referenti;
import it.mdnv.model.Tag;
import it.mdnv.utils.Constants;
import it.mdnv.utils.Utilities;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

@ViewScoped
@ManagedBean(name = "fornitorebean")
public class FornitoriMB extends AbstractMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{fornitorebean}";
	
	private Fornitori frn;
	private boolean disable = false;
	private boolean updateRendered = false;
	
	private DatiFatturazione datifatt = new DatiFatturazione();
	private boolean disableDatiFatt = false;
	private boolean updateDatiFattRendered = false;
	
	private Referenti referente = new Referenti();
	private List<Referenti> referenteList;
	private Referenti selectedReferente;
	private boolean disableReferente = false;
	private boolean updateReferenteRendered = false;
	
	//private Lavori lavoro = new Lavori();
	//private List<Lavori> folderLavoriList;
	//private TreeNode lavoriRoot;
	//private DualListModel<Referenti> referentiPickList = new DualListModel<Referenti>();
	//private boolean disableLavoro = false;
	
	private int activeIndex = 0;

	/**
	 * COSTRUTTORE
	 */
	public FornitoriMB(){
		System.out.println("[FornitoriMB][COSTRUTTORE] - Fornitore: " + this.getFrn());
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		if(params != null && params.containsKey(Constants.IDFORNITORE)){
			
			// Valorizzo i bean per i dati singoli e specifici (no liste)
			FornitoriFacade fornitoriFacade = new FornitoriFacade();
			
			this.setFrn(fornitoriFacade.findFornitoreByIdFornitore(Integer.parseInt(params.get(Constants.IDFORNITORE))));
			
			// Valorizzo i dati di Fatturazione per il fornitore specificato nella selezione
			DatiFatturazioneFacade datiFattFacede = new DatiFatturazioneFacade();
			DatiFatturazione df = datiFattFacede.findDatiFatturazioneByIdFornitore(Integer.parseInt(params.get(Constants.IDFORNITORE)));
			System.out.println("[FornitoriMB][COSTRUTTORE] - DatiFatturazione: " + df);
			if(df != null) {
				this.setDatifatt(df);
				// Disabilito il Pulsante "Salva" dei Dati di Fatturazione
				this.setDisableDatiFatt(true);
				// Abilito il Pulsante "Aggiorna" dei Dati di Fatturazione
				this.setUpdateDatiFattRendered(true);
			} else {
				this.setDisableDatiFatt(false);
				this.setUpdateDatiFattRendered(false);
			}
			
			// Valorizzo le informazioni relative ai Materiali
			MaterialiFacade mf = new MaterialiFacade();
			Materiali m = mf.findMaterialiByIdFornitore(Integer.parseInt(params.get(Constants.IDFORNITORE)));
			if(m != null){
				this.setMateriali(m);
				
				populateTagLavList();
				
				// Disabilito il Pulsante "Salva" dei Materiali
				this.setDisableSaveMateriali(true);
				// Abilito il Pulsante "Aggiorna" dei Materiali
				this.setUpdateMaterialiRendered(true);
			} else {
				this.setDisableSaveMateriali(false);
				this.setUpdateMaterialiRendered(false);
			}
			
			// Abilito la visualizzazione dei pulsanti di Modifica per la Sede Legale
			
			// Disabilito il Pulsante "Salva" della Sede Legale
			this.setDisable(true);
			// Abilito il Pulsante "Aggiorna"
			this.setUpdateRendered(true);

			/**
			// Abilito la visualizzazione dei pulsanti di Modifica per i Dati di Fatturazione
			// Disabilito il Pulsante "Salva" dei Dati di Fatturazione
			this.setDisableDatiFatt(true);
			// Abilito il Pulsante "Aggiorna" dei Dati di Fatturazione
			this.setUpdateDatiFattRendered(true);
			**/
			
			// Abilito la visualizzazione dei pulsanti di Modifica per i Materiali
			
			// Disabilito il Pulsante "Salva" dei Materiali
			this.setDisableSaveMateriali(true);
			// Abilito il Pulsante "Aggiorna" dei Materiali
			this.setUpdateMaterialiRendered(true);
			
		}
		
		if(this.getFrn() != null && this.getFrn().getCodFornitore() != null && !this.getFrn().getCodFornitore().equalsIgnoreCase("")){
			System.out.println("[FornitoriMB][COSTRUTTORE] - Fornitore presente");
		} else {
			this.frn = new Fornitori();

			// Disabilito il Pulsante "Salva" dei Dati di Fatturazione
			this.setDisableDatiFatt(true);
			
			// Disabilito il Pulsante "Salva" dei Referenti
			this.setDisableReferente(true);

			// Disabilito il Pulsante "Salva" dei Lavori
			this.setDisableSaveMateriali(true);
			
			
			try {
				this.frn.setDataInserimento(Utilities.getSysdate_DDMMYYYY());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	
    @PostConstruct
    public void init() {
    	System.out.println("[FornitoriMB][init] - Fornitore: " + this.getFrn());

    	aggiornaListaReferenti();
    	
    	//this.referentiPickList = populateReferentiPickListCLienti(this.getFrn().getId());
        //aggiornaListaLavori();
    	System.out.println("[FornitoriMB][init] - ID Fornitore: " + this.getFrn().getId());
    }
    
    /*************   ACTION FORNITORE  *****************************/

    // @ TODO: SAVE FONRITORE
	public void save() {
		System.out.println("[FornitoriMB][save] - this.getFrn(): " + this.getFrn());
		if(this.getFrn().getRagioneSociale() != null && 
				!this.getFrn().getRagioneSociale().equalsIgnoreCase("")){
			FornitoriFacade fornitoriFacade = new FornitoriFacade();
	
			this.getFrn().setCodFornitore("");
			this.getFrn().setActive("1");
			
			Fornitori fornitore = fornitoriFacade.createFornitore(this.getFrn());
			System.out.println("[FornitoriMB][save] - fornitore: " + fornitore);
			System.out.println("[FornitoriMB][save] -id  fornitore: " + fornitore.getId());
			
			if(fornitore != null && fornitore.getId() != 0){
				
				// Effettuo l'UPDATE del Codice Fornitore
				fornitore.setCodFornitore(Utilities.getCodiceComposto(Constants.PREFIX_CODFRN ,fornitore.getId()));
				System.out.println("[FornitoriMB][save] - COD FRN: " + fornitore.getCodFornitore());
				
				
				fornitoriFacade.updateFornitore(fornitore);
				
				this.setFrn(fornitore);
				// Disabilito il Pulsante "Salva" della Sede Legale
				this.setDisable(true);
				// Abilito il Pulsante "Aggiorna"
				this.setUpdateRendered(true);
				// Imposto la visualizzazione del Panel relativo alla Sede Operativa
				this.setActiveIndex(1);
				
				// Riabilito i pulsanti di salavataggio delle altre schede

				// Abilito il Pulsante "Salva" dei Dati di Fatturazione
				this.setDisableDatiFatt(false);
				
				// Abilito il Pulsante "Salva" dei Referenti
				this.setDisableReferente(false);

				// Abilito il Pulsante "Salva" dei Materiali
				this.setDisableSaveMateriali(false);
				
				displayInfoMessageToUser("Inserimento Fornitore Avvenuto Correttamente. Codice Fornitore attribuito: '"+fornitore.getCodFornitore()+"'");
				
			} else 
				displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
		} else {
			displayErrorMessageToUser("Inserimento Fornitore: specificare la 'Ragione Sociale'.");
		}
	} // END save
	
    // @ TODO: UPDATE FONRITORE
	public void update() {
	//System.out.println("[FornitoriMB][update] - Fornitore Da Aggiornare: " + this.getFrn());
		FornitoriFacade fornitoriFacade = new FornitoriFacade();
		
		fornitoriFacade.updateFornitore(this.getFrn());
		
		init();
		displayInfoMessageToUser("Aggiornamento Fornitore '"+this.getFrn().getCodFornitore()+"' avvenuto Correttamente!");
	}// END update

    
	public void onRowFornitoreSelect(SelectEvent event) {
		int idFornitore = ((Fornitori) event.getObject()).getId();
		System.out.println("[SedeOperativaMB][onRowFornitoreSelect] - Fornitore Selezionato: " + idFornitore);
		
		// Disabilito il Pulsante "Salva" del Fornitore
		this.setDisable(true);
		// Abilito il Pulsante "Aggiorna" del Fornitore
		this.setUpdateRendered(true);
		
		// Recupero le informazioni del Fornitore selezionato
		FornitoriFacade fornitoriFacade = new FornitoriFacade();
		Fornitori fornitore = fornitoriFacade.findFornitoreByIdFornitore(idFornitore);
		
		this.setFrn(fornitore);

	}// END onRowFornitoreSelect
    
	//private Fornitori frn;
	public Fornitori getFrn() {
		return frn;
	}
	public void setFrn(Fornitori frn) {
		this.frn = frn;
	}

	public boolean isDisable() {
		return disable;
	}
	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	public int getActiveIndex() {
		return activeIndex;
	}
	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
	public boolean isUpdateRendered() {
		return updateRendered;
	}
	public void setUpdateRendered(boolean updateRendered) {
		this.updateRendered = updateRendered;
	}
	
    /*************   ACTION DATI FATTURAZIONE  *****************************/
    // @ TODO: SAVE DATI FATTURE
	public void saveDatiFatturazione() {
	//System.out.println("[FornitoriMB][saveDatiFatturazione]");
		if(this.getFrn().getId() != -1){
			if( this.getDatifatt().getRagioneSociale() != null && 
					!this.getDatifatt().getRagioneSociale().equalsIgnoreCase("")
				){
				DatiFatturazioneFacade datiFatturazioneFacade = new DatiFatturazioneFacade();
				
				this.getDatifatt().setActive("1");
				this.getDatifatt().setIdfornitore(this.getFrn().getId());
			//System.out.println("[FornitoriMB][saveDatiFatturazione] - this.getDatifatt(): " + this.getDatifatt());
				
				DatiFatturazione datiFatturazione = datiFatturazioneFacade.createDatiFatturazione(this.getDatifatt());
				//System.out.println("[FornitoriMB][saveDatiFatturazione] - datiFatturazione ID: " + datiFatturazione.getId());
				
				if(datiFatturazione != null && datiFatturazione.getId() != 0){
					this.setDatifatt(datiFatturazione);
					// Disabilito il Pulsante "Salva" dei Dati di Fatturazione
					this.setDisableDatiFatt(true);
					// Abilito il Pulsante "Aggiorna" dei Dati di Fatturazione
					this.setUpdateDatiFattRendered(true);
					
					displayInfoMessageToUser("Inserimento Dati Fatturazione Avvenuto Correttamente.");
				} else 
					displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
			} else {
				displayErrorMessageToUser("Inserimento Dati di Fatturazione: specificare la 'Ragione Sociale'.");
			}
		} else {
			displayErrorMessageToUser("Per inserire i 'Dati di Fatturazione' bisogna prima creare il Fornitore!");
		}
	} // END saveDatiFatturazione
	


    // @ TODO: UPDATE DATI FATTURA
	public void updateDatiFatturazione() {
	//System.out.println("[FornitoriMB][updateDatiFatturazione] - Dati di Fatturazione Da Aggiornare: " + this.getDatifatt());
		DatiFatturazioneFacade datiFatturazioneFacade = new DatiFatturazioneFacade();
		
		this.getDatifatt().setActive("1");
		this.getDatifatt().setIdfornitore(this.getFrn().getId());
		datiFatturazioneFacade.updateDatiFatturazione(this.getDatifatt());
		
		init();
		
		// Disabilito il Pulsante "Salva" dei Dati di Fatturazione
		this.setDisableDatiFatt(true);
		// Abilito il Pulsante "Aggiorna" dei Dati di Fatturazione
		this.setUpdateDatiFattRendered(true);
		
		displayInfoMessageToUser("Aggiornamento Dati di Fatturazione avvenuto Correttamente!");
		
	}// END updateDatiFatturazione

	public boolean isDisableDatiFatt() {
		return disableDatiFatt;
	}

	public void setDisableDatiFatt(boolean disableDatiFatt) {
		this.disableDatiFatt = disableDatiFatt;
	}

	public boolean isUpdateDatiFattRendered() {
		return updateDatiFattRendered;
	}

	public void setUpdateDatiFattRendered(boolean updateDatiFattRendered) {
		this.updateDatiFattRendered = updateDatiFattRendered;
	}

	public DatiFatturazione getDatifatt() {
		return datifatt;
	}

	public void setDatifatt(DatiFatturazione datifatt) {
		this.datifatt = datifatt;
	}
	
	
    /*************   ACTION REFERENTI  *****************************/

	public void saveReferente() {
	//System.out.println("[FornitoriMB][saveReferente]");
		if(this.getFrn().getId() != -1){
			if(this.getReferente().getNome() != null && 
					!this.getReferente().getNome().equalsIgnoreCase("")){
				ReferentiFacade referenteFacade = new ReferentiFacade();
				
				this.getReferente().setActive("1");
				this.getReferente().setIdfornitore(this.getFrn().getId());
			//System.out.println("[FornitoriMB][saveReferente] - this.getReferente(): " + this.getReferente());
				
				Referenti ref = referenteFacade.createReferente(this.getReferente());
			//System.out.println("[FornitoriMB][saveReferente] - Referente ID: " + ref.getId());
				
				if(ref != null && ref.getId() != 0){
					// Associo il Referente al Fornitore tramite la tabella di relazione
					FornitoriFacade fornitoriFacade = new FornitoriFacade();
					List<Referenti> referentis = new ArrayList<Referenti>();
					referentis.add(ref);
					this.getFrn().setReferentis(referentis);
					fornitoriFacade.updateFornitore(this.getFrn());
					
					displayInfoMessageToUser("Inserimento Referente '"+ this.getReferente().getNome() + " " + this.getReferente().getCognome() + "' Avvenuto Correttamente.");
				} else 
					displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
				
				//aggiornaListaReferenti();
				init();
				
				// Refresh dati
				//referente = new Referenti();
				this.setReferente(new Referenti());
			} else {
				displayErrorMessageToUser("Inserimento Referente: specificare'Nome'.");
			}
		} else {
			displayErrorMessageToUser("Per inserire un nuovo 'Referente' bisogna prima creare il Fornitore!");
		}
	} // END saveReferente

	
	public void onRowReferenteSelect(SelectEvent event) {
		int idReferente = ((Referenti) event.getObject()).getId();
	//System.out.println("[SedeOperativaMB][onRowReferenteSelect] - Referente Selezionato: " + idReferente);
		
		// Recupero le informazioni del Referente
		ReferentiFacade referenteFacade = new ReferentiFacade();
		Referenti referente = referenteFacade.findReferenteByIdReferente(idReferente);
		
	//System.out.println("[SedeOperativaMB][onRowReferenteSelect] - Referente Selezionato: " + idReferente);
		
		this.setReferente(referente);

	}// END onRowReferenteSelect
	
	
	public void editReferente() {

	//System.out.println("[SedeOperativaMB][editReferente] - Referente Selezionato: " + this.getReferente().getId());
		
		// Disabilito il Pulsante "Salva" del Referente
		this.setDisableReferente(true);
		// Abilito il Pulsante "Aggiorna" del Referente
		this.setUpdateReferenteRendered(true);
		
		// Imposto la visualizzazione del Panel relativo al Referente
		this.setActiveIndex(2);


	}// END editReferente
	
	public void updateReferente() {
	//System.out.println("[FornitoriMB][updateReferente] - Sede Operativa Da Aggiornare: " + this.getReferente());
		ReferentiFacade referenteFacade = new ReferentiFacade();
		
		referenteFacade.updateReferente(this.getReferente());
		
		displayInfoMessageToUser("Aggiornamento Referente '" + this.getReferente().getNome() + " " + this.getReferente().getCognome() + "' avvenuto Correttamente!");
		
		//aggiornaListaReferenti();
		init();
		
		// Abilito il Pulsante "Salva" della Sede Operativa
		this.setDisableReferente(false);
		// Disabilito il Pulsante "Aggiorna" della Sede Operativa
		this.setUpdateReferenteRendered(false);
		
		// Deselect Row
		selectedReferente = null;
		
		// Refresh dati
		referente = new Referenti();
		
	}// END updateReferente
	

	public Referenti getReferente() {
		return referente;
	}

	public List<Referenti> getReferenteList() {
		return referenteList;
	}
	public void setReferente(Referenti referente) {
		this.referente = referente;
	}
	public Referenti getSelectedReferente() {
		return selectedReferente;
	}

	public void setSelectedReferente(Referenti selectedReferentea) {
		this.selectedReferente = selectedReferentea;
	}
	
	public boolean isDisableReferente() {
		return disableReferente;
	}
	public void setDisableReferente(boolean disableReferente) {
		this.disableReferente = disableReferente;
	}
	public boolean isUpdateReferenteRendered() {
		return updateReferenteRendered;
	}
	public void setUpdateReferenteRendered(boolean updateReferenteRendered) {
		this.updateReferenteRendered = updateReferenteRendered;
	}

	
    /*************   ACTION LAVORI  *****************************/

	/***
	public void setReferentiPickList(DualListModel<Referenti> referentiPickList) {
		this.referentiPickList = referentiPickList;
	}
	public DualListModel<Referenti> getReferentiPickList() {
		return this.referentiPickList;
	}
	***/
	/*****
	public void creaFolderLavoro(){

	//System.out.println("[FornitoriMB][creaFolderLavoro] - this.getFrn().getId(): " + this.getFrn().getId());
		if(this.getFrn().getId() != -1) {
			if(this.getLavoro().getNomeLavoro() != null && 
					!this.getLavoro().getNomeLavoro().equalsIgnoreCase("")
				){
				
				FolderLavoriFacade folderLavoriFacade = new FolderLavoriFacade();
				
				this.getLavoro().setIdfornitore(this.getFrn().getId());
			//System.out.println("[FornitoriMB][creaFolderLavoro] - this.getLavoro().getNome_lavoro: " + this.getLavoro().getNomeLavoro());
				this.getLavoro().setActive("1");
				Lavori folderLavoro = folderLavoriFacade.createFolderLavori(this.getLavoro());
			//System.out.println("[FornitoriMB][creaFolderLavoro] - FolderLavori ID: " + folderLavoro.getId());
				
				if(folderLavoro != null && folderLavoro.getId() != 0){
					
					// [FornitoriMB][creaFolderLavoro] - referentiTarget: [2, 4]
					List lRefTrgt = this.getReferentiPickList().getTarget();
			        System.out.println("[FornitoriMB][creaFolderLavoro] - referentiTarget: " + lRefTrgt);
			        if(lRefTrgt != null && lRefTrgt.size() > 0){
			        	List<Referenti> lRefLav = new ArrayList<Referenti>();
			        	Iterator ir = lRefTrgt.iterator();
			        	while(ir.hasNext()){
			        		String idRef = (String)ir.next();
			        		ReferentiFacade referenteFacade = new ReferentiFacade();
			        		lRefLav.add(referenteFacade.findReferenteByIdReferente(Integer.parseInt(idRef)));
			        	}
			        	folderLavoro.setReferentis(lRefLav);
			        }
			        
					folderLavoro.setNomeLavoro(Utilities.getNomeLavoro(folderLavoro.getId(), folderLavoro.getNomeLavoro()));
					folderLavoriFacade.updateFolderLavori(folderLavoro);
					
					displayInfoMessageToUser("Creazione Cartella Lavoro '" + folderLavoro.getNomeLavoro() + "' Avvenuta Correttamente.");
				} else 
					displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
				
				aggiornaListaLavori();
				
				// Refresh dati
				lavoro = new Lavori();
			} else {
				displayErrorMessageToUser("Per creare una nuova 'Cartella Lavoro' bisogna specificare la descrizione!");
			}
		} else {
			displayErrorMessageToUser("Per creare una nuova 'Cartella Lavoro' bisogna prima creare il Fornitore!");
		}
		
	} // END creaFolderLavoro
	

	public Lavori getLavoro() {
		return lavoro;
	}
	public void setLavoro(Lavori lavoro) {
		this.lavoro = lavoro;
	}
	public boolean isDisableLavoro() {
		return disableLavoro;
	}
	public void setDisableLavoro(boolean disableLavoro) {
		this.disableLavoro = disableLavoro;
	}

	
    public TreeNode getLavoriRoot() {
        return lavoriRoot;
    }
	public List<Lavori> getFolderLavoriList() {
		return folderLavoriList;
	}
	****/	
	/***************************************************************************/
	/*********************      METODI PRIVATI     *****************************/


	
	private void aggiornaListaReferenti(){
		System.out.println("[FornitoriMB][aggiornaListaReferenti] - this.getFrn().getId(): " + this.getFrn().getId());
		if(this.getFrn().getId() > 0){
			ReferentiFacade referenteFacade = new ReferentiFacade();
			referenteList = referenteFacade.getListaReferentiByIdFornitore(this.getFrn().getId());
		}
		//this.referentiPickList = populateReferentiPickListCLienti(this.getFrn().getId());
		
	//System.out.println("[FornitoriMB][aggiornaListaReferenti] - referenteList: " + referenteList);
	} // END aggiornaListaReferenti
	
	/***
	private void aggiornaListaLavori(){
	//System.out.println("[FornitoriMB][aggiornaListaLavori] - this.getFrn().getId(): " + this.getFrn().getId());
		if(this.getFrn().getId() > 0){
			FolderLavoriFacade folderLavoriFacade = new FolderLavoriFacade();
			folderLavoriList = folderLavoriFacade.getListaFolderLavoriByIdFornitore(this.getFrn().getId());
	        
	        // Recuperiamo la lista dei Lavori per il Fornitore
			lavoriRoot = retrieveLavori();
			
		} else {
			lavoriRoot = new DefaultTreeNode();
		}

		//this.referentiPickList = populateReferentiPickListCLienti(this.getFrn().getId());
		
	//System.out.println("[FornitoriMB][aggiornaListaLavori] - folderLavoriList: " + folderLavoriList);
	} // END aggiornaListaLavori
	
	private TreeNode retrieveLavori() {
		TreeNode root = new DefaultTreeNode(new Document("Works", "", "Folder"), null);
		
		if(this.getFrn().getId() > 0){
			// ref: http://stackoverflow.com/questions/11189697/primefaces-tree-from-database
			// ref: http://stackoverflow.com/questions/12399772/building-primefaces-tree-from-database-using-recursion
			FolderLavoriFacade flf = new FolderLavoriFacade();
			List<Lavori> listLavori = flf.getListaFolderLavoriByIdFornitore(this.getFrn().getId());
			if(listLavori != null && listLavori.size() > 0){
				Iterator<Lavori> iFl = listLavori.iterator();
				while(iFl.hasNext()){
					Lavori fl = iFl.next();
					fl.getReferentis();
				}
			}
			
		} else {
			TreeNode work1 = new DefaultTreeNode(new Document("2014_001_NomeLavoro1", "Breve descrizione Lavoro1", "Folder"), root);
		    TreeNode ref11 = new DefaultTreeNode(new Document("Mario Rossi", "-", "Folder"), work1);
		    TreeNode ref12 = new DefaultTreeNode(new Document("Giuseppe Verdi", "-", "Folder"), work1);
			
			TreeNode work2 = new DefaultTreeNode(new Document("2014_002_NomeLavoro2", "Breve descrizione Lavoro2", "Folder"), root);
			TreeNode ref21 = new DefaultTreeNode(new Document("Andrea Bianchi", "-", "Folder"), work2);
			
			TreeNode work3 = new DefaultTreeNode(new Document("2014_003_NomeLavoro3", "Breve descrizione Lavoro3", "Folder"), root);
			TreeNode ref31 = new DefaultTreeNode(new Document("Mario Rossi", "-", "Folder"), work3);
			
			TreeNode work4 = new DefaultTreeNode(new Document("2014_004_NomeLavoro4", "Breve descrizione Lavoro4", "Folder"), root);
			TreeNode ref41 = new DefaultTreeNode(new Document("Giuseppe Verdi", "-", "Folder"), work4);
		}
		return root;
	}
	***/
	
	private Materiali materiali = new Materiali();
	private List<Tag> tagsList;
	private List<Tag> lavList;
	
	private boolean disableSaveMateriali = false;
	private boolean updateMaterialiRendered = false;

	// materiali
	public Materiali getMateriali() {
		return materiali;
	}
	public void setMateriali(Materiali materiali) {
		this.materiali = materiali;
	}
	// tagsList
	public List<Tag> getTagsList() {
		return tagsList;
	}
	// lavList
	public List<Tag> getLavList() {
		return lavList;
	}
	/**
	private String noteMateriali;
	//noteMateriali
	public String getNoteMateriali() {
		return noteMateriali;
	}
	public void setNoteMateriali(String noteMateriali) {
		this.noteMateriali = noteMateriali;
	}
	**/
	public boolean isDisableSaveMateriali() {
		return disableSaveMateriali;
	}
	public void setDisableSaveMateriali(boolean disableSaveMateriali) {
		this.disableSaveMateriali = disableSaveMateriali;
	}
	public boolean isUpdateMaterialiRendered() {
		return updateMaterialiRendered;
	}
	public void setUpdateMaterialiRendered(boolean updateMaterialiRendered) {
		this.updateMaterialiRendered = updateMaterialiRendered;
	}


	// @ TODO SAVE MATERIALI
	public void saveTagMaterialiLavorazioni() {
		
		System.out.println("[saveTagMaterialiLavorazioni] **************** ID Fornitore: " + this.getFrn().getId());
		System.out.println("[saveTagMaterialiLavorazioni] **************** Note Materiali: " + this.materiali.getNote());
		//**************** ID Fornitore: 0
		//**************** Note Materiali: ciao
		/**
		try{
			if(this.getFrn().getId() > 0){

				retrieveMaterialiFromView();
				
				MaterialiFacade mf = new MaterialiFacade();
				mf.createMateriali(this.materiali);
				
				displayInfoMessageToUser("Inserimento Materiali per Forntiore '" + this.getFrn().getCodFornitore() + "' Avvenuto Correttamente.");
			} else {
				displayInfoMessageToUser("Inserimento Materiali per Forntiore '" + this.getFrn().getCodFornitore() + "' Avvenuto Correttamente.");
			}
		}catch(Exception e){
			displayErrorMessageToUser("Attenzione! Errore di Sistema.");
		}
		**/
		
		retrieveMaterialiFromView();
		
	} // END saveTagMaterialiLavorazioni
	
	
	public void saveCommand() {
		System.out.println("[saveCommand] **************** ID Fornitore: " + this.getFrn().getId());
		try{
			if(this.getFrn().getId() > 0){

				MaterialiFacade mf = new MaterialiFacade();
				mf.createMateriali(this.materiali);

				// Disabilito il Pulsante "Salva" dei Materiali
				this.setDisableSaveMateriali(true);
				// Abilito il Pulsante "Aggiorna" dei Materiali
				this.setUpdateMaterialiRendered(true);
				
				populateTagLavList();
				
				displayInfoMessageToUser("Inserimento Materiali per Forntiore '" + this.getFrn().getCodFornitore() + "' Avvenuto Correttamente.");
			} else {
				displayInfoMessageToUser("Inserimento Materiali per Forntiore '" + this.getFrn().getCodFornitore() + "' Avvenuto Correttamente.");
			}
		}catch(Exception e){
			displayErrorMessageToUser("Attenzione! Errore di Sistema.");
		}

		
	} // END saveCommand
	
	// @ TODO UPDATE MATERIALI
	public void updateTagMaterialiLavorazioni(){
		System.out.println("[updateTagMaterialiLavorazioni] **************** ID Fornitore: " + this.getFrn().getId());
		System.out.println("[updateTagMaterialiLavorazioni] **************** Note Materiali: " + this.materiali.getNote());
		/**
		try{
			if(this.getFrn().getId() > 0){

				retrieveMaterialiFromView();
				
				MaterialiFacade mf = new MaterialiFacade();
				mf.updateMateriali(this.materiali);
				
				displayInfoMessageToUser("Aggiornamento Materiali per Forntiore '" + this.getFrn().getCodFornitore() + "' Avvenuto Correttamente.");
			} else {
				displayInfoMessageToUser("Aggiornamento Materiali per Forntiore '" + this.getFrn().getCodFornitore() + "' Avvenuto Correttamente.");
			}
		}catch(Exception e){
			displayErrorMessageToUser("Attenzione! Errore di Sistema.");
		}
		**/
		
		retrieveMaterialiFromView();
		
	} // END updateTagMaterialiLavorazioni
	
	public void updateCommand(){
		System.out.println("[updateCommand] **************** ID Fornitore: " + this.getFrn().getId());
		System.out.println("[updateCommand] **************** Note Materiali: " + this.materiali.getNote());

		try{
			if(this.getFrn().getId() > 0){

				MaterialiFacade mf = new MaterialiFacade();
				mf.updateMateriali(this.materiali);
				
				populateTagLavList();
				
				displayInfoMessageToUser("Aggiornamento Materiali per Forntiore '" + this.getFrn().getCodFornitore() + "' Avvenuto Correttamente.");
			} else {
				displayInfoMessageToUser("Aggiornamento Materiali per Forntiore '" + this.getFrn().getCodFornitore() + "' Avvenuto Correttamente.");
			}
		}catch(Exception e){
			displayErrorMessageToUser("Attenzione! Errore di Sistema.");
		}
		
	} // END updateCommand
	
	private void retrieveMaterialiFromView(){
		String valueTagMateriali = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tagMateriali");
		System.out.println("[retrieveMaterialiFromView] **************** The Javascirpt for 'materiali' is: " + valueTagMateriali);
		if(valueTagMateriali != null && !valueTagMateriali.equalsIgnoreCase("")){
			String[] listTag = valueTagMateriali.split(",");
			System.out.println("[retrieveMaterialiFromView] **************** listTag: " + listTag);
			for(int i=0; i < listTag.length; i++){
				System.out.println("Tag["+i+"]: " + listTag[i]);
			}
		}
		
		String valueLavMateriali = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("lavMateriali");
		System.out.println("[retrieveMaterialiFromView] **************** The Javascirpt for 'lavorazioni' is: " + valueLavMateriali);
		if(valueLavMateriali != null && !valueLavMateriali.equalsIgnoreCase("")){
			String[] listVal = valueLavMateriali.split(",");
			System.out.println("[retrieveMaterialiFromView] **************** listVal: " + listVal);
		}
		
		this.materiali.setIdfornitore(this.getFrn().getId());
		this.materiali.setMateriali(valueTagMateriali);
		this.materiali.setLavorazioni(valueLavMateriali);
		//this.materiali.setNote(this.noteMateriali);
	}
	
	private void populateTagLavList(){
		if(this.materiali != null ){
			if(this.materiali.getMateriali() != null 
				&& !this.materiali.getMateriali().equalsIgnoreCase("")){
				String[] tag = this.materiali.getMateriali().split(",");
				tagsList = new ArrayList<Tag>();
				for(int i=0; i < tag.length; i++){
					Tag t = new Tag();
					//t.setId(i);
					t.setValue(tag[i]);
					tagsList.add(t);
				}
			}
			
			if(this.materiali.getLavorazioni() != null 
					&& !this.materiali.getLavorazioni().equalsIgnoreCase("")){
					String[] tag = this.materiali.getLavorazioni().split(",");
					lavList = new ArrayList<Tag>();
					for(int i=0; i < tag.length; i++){
						Tag t = new Tag();
						t.setValue(tag[i]);
						lavList.add(t);
					}
				}
				
		}
	} // END populateTagLavList
	
}// end class