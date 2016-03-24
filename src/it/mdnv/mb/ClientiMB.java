package it.mdnv.mb;

import it.mdnv.domain.LavoriReferenti;
import it.mdnv.facade.ClientiFacade;
import it.mdnv.facade.DatiFatturazioneFacade;
import it.mdnv.facade.FolderLavoriFacade;
import it.mdnv.facade.ReferentiFacade;
import it.mdnv.facade.SedeOperativaFacade;
import it.mdnv.model.Clienti;
import it.mdnv.model.DatiFatturazione;
import it.mdnv.model.Lavori;
import it.mdnv.model.Referenti;
import it.mdnv.model.SedeOperativa;
import it.mdnv.utils.Constants;
import it.mdnv.utils.SMBFolderManager;
import it.mdnv.utils.Utilities;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

@ViewScoped
@ManagedBean(name = "clientebean")
public class ClientiMB extends AbstractMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{clientebean}";
	
	private Clienti cli;
	private boolean disable = false;
	private boolean updateRendered = false;
	
	private SedeOperativa sedeop = new SedeOperativa();
	private boolean disableSo = false;
	private boolean updateSoRendered = false;
	private List<SedeOperativa> sd;
	private SedeOperativa selectedSedeOperativa;
	
	private DatiFatturazione datifatt = new DatiFatturazione();
	private boolean disableDatiFatt = false;
	private boolean updateDatiFattRendered = false;
	
	private Referenti referente = new Referenti();
	private List<Referenti> referenteList;
	private Referenti selectedReferente;
	private boolean disableReferente = false;
	private boolean updateReferenteRendered = false;
	
	private Lavori lavoro = new Lavori();
	private List<Lavori> folderLavoriList;
	private TreeNode lavoriRoot;

	private DualListModel<Referenti> referentiPickList = new DualListModel<Referenti>();
	
	private boolean disableLavoro = false;
	
	
	private int activeIndex = 0;
	


	/**
	 * COSTRUTTORE
	 */
	public ClientiMB(){
	System.out.println("[ClientiMB][COSTRUTTORE] - Cliente: " + this.getCli());
		
		//System.out.println("[ClientiMB][COSTRUTTORE] - Cliente from MBLClienti: " + mBLClienti.getSelectedCliente());
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	//System.out.println("[ClientiMB][COSTRUTTORE] - getRequestParameterMap: " + params);
		
		if(params != null && params.containsKey(Constants.IDCLIENTE)){
			
			// Valorizzo i bean per i dati singoli e specifici (no liste)
			ClientiFacade clienteFacade = new ClientiFacade();
			this.setCli(clienteFacade.findClienteByIdCliente(Integer.parseInt(params.get(Constants.IDCLIENTE))));
			
			DatiFatturazioneFacade datiFattFacede = new DatiFatturazioneFacade();
			DatiFatturazione df = datiFattFacede.findDatiFatturazioneByIdCliente(Integer.parseInt(params.get(Constants.IDCLIENTE)));
		System.out.println("[ClientiMB][COSTRUTTORE] - DatiFatturazione: " + df);
			if(df != null){
				this.setDatifatt(df);
				
				// Disabilito il Pulsante "Salva" dei Dati di Fatturazione
				this.setDisableDatiFatt(true);
				// Abilito il Pulsante "Aggiorna" dei Dati di Fatturazione
				this.setUpdateDatiFattRendered(true);
				
			} else {
				// Disabilito il Pulsante "Salva" dei Dati di Fatturazione
				this.setDisableDatiFatt(false);
				// Abilito il Pulsante "Aggiorna" dei Dati di Fatturazione
				this.setUpdateDatiFattRendered(false);
			}
			
			// Abilito la visualizzazione dei pulsanti di Modifica per la Sede Legale e per i Dati di Fatturazione
			
			// Disabilito il Pulsante "Salva" della Sede Legale
			this.setDisable(true);
			// Abilito il Pulsante "Aggiorna"
			this.setUpdateRendered(true);

			
		}
		
		if(this.getCli() != null && this.cli.getCodCliente() != null && !this.cli.getCodCliente().equalsIgnoreCase("")){
		//System.out.println("[ClientiMB][COSTRUTTORE] - Cliente presente");
		} else {
			this.cli = new Clienti();
		//System.out.println("[ClientiMB][COSTRUTTORE] - Nuovo Cliente");
			// Disabilito tutti i pulsanti di Salva
			
			// Disabilito il Pulsante "Salva" delle Sedi Operative
			this.setDisableSo(true);
			
			// Disabilito il Pulsante "Salva" dei Dati di Fatturazione
			this.setDisableDatiFatt(true);
			
			// Disabilito il Pulsante "Salva" dei Referenti
			this.setDisableReferente(true);

			// Disabilito il Pulsante "Salva" dei Lavori
			this.setDisableLavoro(true);

			
			try {
				this.cli.setDataInserimento(Utilities.getSysdate_DDMMYYYY());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	
	
    @PostConstruct
    public void init() {
    	System.out.println("[ClientiMB][@PostConstruct] - aggiornaListaSediOperative");
    	aggiornaListaSediOperative();
    	
    	System.out.println("[ClientiMB][@PostConstruct] - aggiornaListaReferenti");
    	aggiornaListaReferenti();

    	this.referentiPickList = populateReferentiPickListCLienti(this.getCli().getId());
        
    	System.out.println("[ClientiMB][@PostConstruct] - aggiornaListaLavori");
        aggiornaListaLavori();

    }
    
    /*************   ACTION CLIENTE  *****************************/

	public void save() {
	//System.out.println("[ClientiMB][save]");
		if(this.getCli().getRagioneSociale() != null && 
				!this.getCli().getRagioneSociale().equalsIgnoreCase("")){
			ClientiFacade clienteFacade = new ClientiFacade();
	
			this.getCli().setCodCliente("");
			this.getCli().setActive("1");
			
			Clienti cliente = clienteFacade.createCliente(this.getCli());
			
			if(cliente != null && cliente.getId() != 0){
				
				// Effettuo l'UPDATE del Codice CLiente
				cliente.setCodCliente(Utilities.getCodiceComposto(Constants.PREFIX_CODCLI ,cliente.getId()));
				clienteFacade.updateCliente(cliente);
				
				this.setCli(cliente);
				// Disabilito il Pulsante "Salva" della Sede Legale
				this.setDisable(true);
				// Abilito il Pulsante "Aggiorna"
				this.setUpdateRendered(true);
				// Imposto la visualizzazione del Panel relativo alla Sede Operativa
				this.setActiveIndex(1);
				
				// Riabilito i pulsanti di salavataggio delle altre schede
				// Abilito il Pulsante "Salva" delle Sedi Operative
				this.setDisableSo(false);
				
				// Abilito il Pulsante "Salva" dei Dati di Fatturazione
				this.setDisableDatiFatt(false);
				
				// Abilito il Pulsante "Salva" dei Referenti
				this.setDisableReferente(false);

				// Abilito il Pulsante "Salva" dei Lavori
				this.setDisableLavoro(false);
				
				displayInfoMessageToUser("Inserimento Cliente Avvenuto Correttamente. Codice Cliente attribuito: '"+cliente.getCodCliente()+"'");
				
			} else 
				displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
		} else {
			displayErrorMessageToUser("Inserimento Cliente: specificare la 'Ragione Sociale'.");
		}
	} // END save
	
	public void update() {
	//System.out.println("[ClientiMB][update] - Cliente Da Aggiornare: " + this.getCli());
		ClientiFacade clienteFacade = new ClientiFacade();
		
		clienteFacade.updateCliente(this.getCli());
		
		init();
		
		displayInfoMessageToUser("Aggiornamento Cliente '"+this.getCli().getCodCliente()+"' avvenuto Correttamente!");
	}// END update

	
	/** onrate **/
    public void onrate(RateEvent rateEvent) {
    //System.out.println("[ClientiMB][onrate]");
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        //FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    /** oncancel **/
    public void oncancel() {
    //System.out.println("[ClientiMB][oncancel]");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
	public void onRowClienteSelect(SelectEvent event) {
		int idCliente = ((Clienti) event.getObject()).getId();
	//System.out.println("[SedeOperativaMB][onRowClienteSelect] - Cliente Selezionato: " + idCliente);
		
		// Disabilito il Pulsante "Salva" del Cliente
		this.setDisable(true);
		// Abilito il Pulsante "Aggiorna" del Cliente
		this.setUpdateRendered(true);
		
		// Recupero le informazioni del Cliente selezionato
		ClientiFacade clienteFacade = new ClientiFacade();
		Clienti cliente = clienteFacade.findClienteByIdCliente(idCliente);
		
		this.setCli(cliente);

	}// END onRowClienteSelect
    
	public Clienti getCli() {
		return cli;
	}
	public void setCli(Clienti cli) {
		this.cli = cli;
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
	
    /*************   ACTION SEDI OPERATIVE  *****************************/
	
	public void saveSedeOperativa() {
	//System.out.println("[ClientiMB][saveSedeOperativa]");
		if(this.getCli().getId() != -1){
			if(this.getSedeop().getRagioneSociale() != null && 
					!this.getSedeop().getRagioneSociale().equalsIgnoreCase("")
					){
				SedeOperativaFacade sedeOperativaFacade = new SedeOperativaFacade();
				
				this.getSedeop().setActive("1");
				this.getSedeop().setIdcliente(this.getCli().getId());
			//System.out.println("[ClientiMB][saveSedeOperativa] - this.getSedeop(): " + this.getSedeop());
				
				SedeOperativa sedeOperativa = sedeOperativaFacade.createSedeOperativa(this.getSedeop());
			//System.out.println("[ClientiMB][saveSedeOperativa] - sedeOperativa ID: " + sedeOperativa.getId());
				
				if(sedeOperativa != null && sedeOperativa.getId() != 0){
					// Imposto la visualizzazione del Panel relativo ai Dati di Fatturazione
					//this.setActiveIndex(2);
					
					// Disabilito il Pulsante "Salva" della Sede Legale
					//this.setDisable(true);
					// Abilito il Pulsante "Aggiorna"
					//this.setUpdateRendered(true);
					
					displayInfoMessageToUser("Inserimento Sede Operativa Avvenuto Correttamente.");
				} else 
					displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
				
				init();
				//aggiornaListaSediOperative();
				
				// Refresh dati
				sedeop = new SedeOperativa();
			} else {
				displayErrorMessageToUser("Inserimento Sede Operativa: specificare la 'Ragione Sociale'!");
			}
		} else {
			displayErrorMessageToUser("Per inserire i 'Dati di Fatturazione' bisogna prima creare il Cliente!");
		}
	} // END saveSedeOperativa

	public void onRowSedeOperativaSelect(SelectEvent event) {
		int idSedeOp = ((SedeOperativa) event.getObject()).getId();
	//System.out.println("[SedeOperativaMB][onRowSedeOperativaSelect] - Sede Operativa Selezionata: " + idSedeOp);
		
		// Recupero le informazioni della Sede Operativa selezionata
		SedeOperativaFacade sedeOperativaFacade = new SedeOperativaFacade();
		SedeOperativa sedeOperativa = sedeOperativaFacade.findSedeOperativaByIdSedeOperativa(idSedeOp);
		
		this.setSedeop(sedeOperativa);

	}// END onRowSedeOperativaSelect
	
	
	public void editSedeOperativa() {

	//System.out.println("[SedeOperativaMB][editSedeOperativa] - Sede Operativa Selezionata: " + this.getSedeop().getId());
		
		// Disabilito il Pulsante "Salva" della Sede Operativa
		this.setDisableSo(true);
		// Abilito il Pulsante "Aggiorna" della Sede Operativa
		this.setUpdateSoRendered(true);
		
		// Imposto la visualizzazione del Panel relativo alla Sede Operativa
		this.setActiveIndex(1);


	}// END editSedeOperativa
	
	
	public void updateSedeOperativa() {
	//System.out.println("[ClientiMB][updateSedeOperativa] - Sede Operativa Da Aggiornare: " + this.getSedeop());
		SedeOperativaFacade sedeOperativaFacade = new SedeOperativaFacade();
		
		sedeOperativaFacade.updateSedeOperativa(this.getSedeop());
		
		displayInfoMessageToUser("Aggiornamento Sede Operativa '"+this.getSedeop().getRagioneSociale()+"' avvenuto Correttamente!");
		
		//aggiornaListaSediOperative();
		init();
		
		// Abilito il Pulsante "Salva" della Sede Operativa
		this.setDisableSo(false);
		// Disabilito il Pulsante "Aggiorna" della Sede Operativa
		this.setUpdateSoRendered(false);
		
		// Deselect Row
		selectedSedeOperativa = null;
		
		// Refresh dati
		sedeop = new SedeOperativa();
		
	}// END updateSedeOperativa
	

	public SedeOperativa getSedeop() {
		return sedeop;
	}

	public List<SedeOperativa> getSd() {
		return sd;
	}
	public void setSedeop(SedeOperativa sedeop) {
		this.sedeop = sedeop;
	}
	public SedeOperativa getSelectedSedeOperativa() {
		return selectedSedeOperativa;
	}

	public void setSelectedSedeOperativa(SedeOperativa selectedSedeOperativa) {
		this.selectedSedeOperativa = selectedSedeOperativa;
	}
	
	public boolean isDisableSo() {
		return disableSo;
	}
	public void setDisableSo(boolean disableSo) {
		this.disableSo = disableSo;
	}
	public boolean isUpdateSoRendered() {
		return updateSoRendered;
	}
	public void setUpdateSoRendered(boolean updateSoRendered) {
		this.updateSoRendered = updateSoRendered;
	}
	
	/*********** REF PICK LIST *****/
	/**
	public void setReferentiPickList(DualListModel<String> referentiPickList) {
		this.referentiPickList = referentiPickList;
	}
	public DualListModel<String> getReferentiPickList() {
		return this.referentiPickList;
	}
	**/
	public void setReferentiPickList(DualListModel<Referenti> referentiPickList) {
		this.referentiPickList = referentiPickList;
	}
	public DualListModel<Referenti> getReferentiPickList() {
		return this.referentiPickList;
	}
	
	
    /*************   ACTION DATI FATTURAZIONE  *****************************/
	
	public void saveDatiFatturazione() {
	//System.out.println("[ClientiMB][saveDatiFatturazione]");
		if(this.getCli().getId() != -1){
			if( this.getDatifatt().getRagioneSociale() != null && 
					!this.getDatifatt().getRagioneSociale().equalsIgnoreCase("")
				){
				DatiFatturazioneFacade datiFatturazioneFacade = new DatiFatturazioneFacade();
				
				this.getDatifatt().setActive("1");
				this.getDatifatt().setIdcliente(this.getCli().getId());
			//System.out.println("[ClientiMB][saveDatiFatturazione] - this.getDatifatt(): " + this.getDatifatt());
				
				DatiFatturazione datiFatturazione = datiFatturazioneFacade.createDatiFatturazione(this.getDatifatt());
			//System.out.println("[ClientiMB][saveDatiFatturazione] - datiFatturazione ID: " + datiFatturazione.getId());
				
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
			displayErrorMessageToUser("Per inserire i 'Dati di Fatturazione' bisogna prima creare il Cliente!");
		}
	} // END saveDatiFatturazione
	



	public void updateDatiFatturazione() {
		System.out.println("[ClientiMB][updateDatiFatturazione] - Dati di Fatturazione Da Aggiornare: " + this.getDatifatt());
		DatiFatturazioneFacade datiFatturazioneFacade = new DatiFatturazioneFacade();
		
		this.getDatifatt().setActive("1");
		this.getDatifatt().setIdcliente(this.getCli().getId());
		datiFatturazioneFacade.updateDatiFatturazione(this.getDatifatt());
		
		init();
		
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
	//System.out.println("[ClientiMB][saveReferente]");
		if(this.getCli().getId() != -1){
			if(this.getReferente().getNome() != null && 
					!this.getReferente().getNome().equalsIgnoreCase("")){
				ReferentiFacade referenteFacade = new ReferentiFacade();
				
				this.getReferente().setActive("1");
				this.getReferente().setIdcliente(this.getCli().getId());
			//System.out.println("[ClientiMB][saveReferente] - this.getReferente(): " + this.getReferente());
				
				Referenti ref = referenteFacade.createReferente(this.getReferente());
			//System.out.println("[ClientiMB][saveReferente] - Referente ID: " + ref.getId());
				
				if(ref != null && ref.getId() != 0){
					// Associo il Referente al Cliente tramite la tabella di relazione
					ClientiFacade clienteFacade = new ClientiFacade();
					List<Referenti> referentis = new ArrayList<Referenti>();
					referentis.add(ref);
					this.getCli().setReferentis(referentis);
					clienteFacade.updateCliente(this.getCli());
					
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
			displayErrorMessageToUser("Per inserire un nuovo 'Referente' bisogna prima creare il Cliente!");
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
		this.setActiveIndex(3);


	}// END editReferente
	
	public void updateReferente() {
	//System.out.println("[ClientiMB][updateReferente] - Sede Operativa Da Aggiornare: " + this.getReferente());
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
	@SuppressWarnings("rawtypes")
	public void creaFolderLavoro(){

		//System.out.println("[ClientiMB][creaFolderLavoro] - this.getCli().getId(): " + this.getCli().getId());
		if(this.getCli().getId() != -1) {
			if(this.getLavoro().getNomeLavoro() != null && 
					!this.getLavoro().getNomeLavoro().equalsIgnoreCase("")
				){
				
				FolderLavoriFacade folderLavoriFacade = new FolderLavoriFacade();
				
				this.getLavoro().setIdcliente(this.getCli().getId());
				//System.out.println("[ClientiMB][creaFolderLavoro] - this.getLavoro().getNome_lavoro: " + this.getLavoro().getNomeLavoro());
				this.getLavoro().setActive("1");
				try{
					System.out.println("[ClientiMB][creaFolderLavoro] - this.getLavoro(): " + this.getLavoro());
					Lavori folderLavoro = folderLavoriFacade.createFolderLavori(this.getLavoro());
					//System.out.println("[ClientiMB][creaFolderLavoro] - FolderLavori ID: " + folderLavoro.getId());
					
					if(folderLavoro != null && folderLavoro.getId() != 0){
						
						// [ClientiMB][creaFolderLavoro] - referentiTarget: [2, 4]
						List lRefTrgt = this.getReferentiPickList().getTarget();
				        System.out.println("[ClientiMB][creaFolderLavoro] - referentiTarget: " + lRefTrgt);
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
				        
				        // Recuperiamo il massimo numero di sequenza presente nella cartella 5_LAVORI
				        int sequence = -1;
				        try{
				        	sequence = SMBFolderManager.nextSequenceInFolder();
				        } catch(Exception e){
				        	
				        }
				        
				        if(sequence != -1)
				        	folderLavoro.setNomeLavoro(Utilities.getNomeLavoro(sequence, folderLavoro.getNomeLavoro()));
				        else
				        	folderLavoro.setNomeLavoro(Utilities.getNomeLavoro(folderLavoro.getId(), folderLavoro.getNomeLavoro()));
				        
						// Crea Folder Lavoro su File System
						String pathLavoro = SMBFolderManager.creaCartellaLavoro(folderLavoro.getNomeLavoro());
						System.out.println("[ClientiMB][creaFolderLavoro] - CreaCartelleLavoro.crea: " + pathLavoro);
						 
						if(pathLavoro == null)
							throw new Exception("Non e' stato possibile creare la Cartella Lavoro nel server condiviso");
							
						folderLavoro.setPathLavoro(pathLavoro);
						folderLavoriFacade.updateFolderLavori(folderLavoro);
	 
						displayInfoMessageToUser("Creazione Cartella Lavoro '" + folderLavoro.getNomeLavoro() + "' Avvenuta Correttamente. \n");
					} else {
						System.err.println("[ClientiMB][creaFolderLavoro] - E' stato impossibile salvare '"+this.getLavoro().getNomeLavoro()+"' nel Database");
						throw new Exception("E' stato impossibile salvare '"+this.getLavoro().getNomeLavoro()+"' nel Database");
					}
				}catch(Exception e){
					e.printStackTrace();
					displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione! \n" + e.getMessage());
				}
				aggiornaListaLavori();
				
				// Refresh dati
				lavoro = new Lavori();
			} else {
				displayErrorMessageToUser("Per creare una nuova 'Cartella Lavoro' bisogna specificare la descrizione!");
			}
		} else {
			displayErrorMessageToUser("Per creare una nuova 'Cartella Lavoro' bisogna prima creare il Cliente!");
		}
		
	} // END creaFolderLavoro
	
    public TreeNode getLavoriRoot() {
        return lavoriRoot;
    }
	public Lavori getLavoro() {
		return lavoro;
	}
	public void setLavoro(Lavori lavoro) {
		this.lavoro = lavoro;
	}
	public List<Lavori> getFolderLavoriList() {
		return folderLavoriList;
	}
	
	
	public boolean isDisableLavoro() {
		return disableLavoro;
	}
	public void setDisableLavoro(boolean disableLavoro) {
		this.disableLavoro = disableLavoro;
	}
	/***************************************************************************/
	/*********************      METODI PRIVATI     *****************************/



	private void aggiornaListaSediOperative(){
		
	//System.out.println("[ClientiMB][aggiornaListaSediOperative] - this.getCli().getId(): " + this.getCli().getId());
		if(this.getCli().getId() > 0){
			SedeOperativaFacade sedeOperativaFacade = new SedeOperativaFacade();
			sd = sedeOperativaFacade.getListaSediOperativeByIdCliente(this.getCli().getId());
		}
	//System.out.println("[ClientiMB][aggiornaListaSediOperative] - sd: " + sd);
		
	} // END aggiornaListaSediOperative
	
	
	private void aggiornaListaReferenti(){
	//System.out.println("[ClientiMB][aggiornaListaReferenti] - this.getCli().getId(): " + this.getCli().getId());
		if(this.getCli().getId() > 0){
			ReferentiFacade referenteFacade = new ReferentiFacade();
			referenteList = referenteFacade.getListaReferentiByIdCliente(this.getCli().getId());
		}
		this.referentiPickList = populateReferentiPickListCLienti(this.getCli().getId());
		
	//System.out.println("[ClientiMB][aggiornaListaReferenti] - referenteList: " + referenteList);
	} // END aggiornaListaReferenti
	
	
	private void aggiornaListaLavori(){
	//System.out.println("[ClientiMB][aggiornaListaLavori] - this.getCli().getId(): " + this.getCli().getId());
		if(this.getCli().getId() > 0){
			FolderLavoriFacade folderLavoriFacade = new FolderLavoriFacade();
			folderLavoriList = folderLavoriFacade.getListaFolderLavoriByIdCliente(this.getCli().getId());
	        
	        // Recuperiamo la lista dei Lavori per il Cliente
			lavoriRoot = retrieveLavori();
			
		} else {
			lavoriRoot = new DefaultTreeNode();
		}

		this.referentiPickList = populateReferentiPickListCLienti(this.getCli().getId());
		
	//System.out.println("[ClientiMB][aggiornaListaLavori] - folderLavoriList: " + folderLavoriList);
	} // END aggiornaListaLavori
	
	private TreeNode retrieveLavori() {
		TreeNode root = new DefaultTreeNode(new LavoriReferenti("Works", "", "Folder"), null);
		
		if(this.getCli().getId() > 0){
			// ref: http://stackoverflow.com/questions/11189697/primefaces-tree-from-database
			// ref: http://stackoverflow.com/questions/12399772/building-primefaces-tree-from-database-using-recursion
			FolderLavoriFacade flf = new FolderLavoriFacade();
			List<Lavori> listLavori = flf.getListaFolderLavoriByIdCliente(this.getCli().getId());
			if(listLavori != null && listLavori.size() > 0){
				Iterator<Lavori> iFl = listLavori.iterator();
				while(iFl.hasNext()){
					Lavori fl = iFl.next();
					TreeNode work1 = new DefaultTreeNode(new LavoriReferenti(fl.getNomeLavoro(), fl.getStato(), "Folder"), root);
					List<Referenti> listRef = fl.getReferentis();
					Iterator<Referenti> iRe = listRef.iterator();
					while(iRe.hasNext()){
						Referenti r = iRe.next();
						new DefaultTreeNode(new LavoriReferenti(r.getCognome() + " " + r.getNome(), r.getIncarico(), "Folder"), work1);
					}
				}
			}
			
		} else {
			/***
			// FAKE LIST
			TreeNode work1 = new DefaultTreeNode(new LavoriReferenti("2014_001_NomeLavoro1", "Breve descrizione Lavoro1", "Folder"), root);
		    TreeNode ref11 = new DefaultTreeNode(new LavoriReferenti("Mario Rossi", "-", "Folder"), work1);
		    TreeNode ref12 = new DefaultTreeNode(new LavoriReferenti("Giuseppe Verdi", "-", "Folder"), work1);
			
			TreeNode work2 = new DefaultTreeNode(new LavoriReferenti("2014_002_NomeLavoro2", "Breve descrizione Lavoro2", "Folder"), root);
			TreeNode ref21 = new DefaultTreeNode(new LavoriReferenti("Andrea Bianchi", "-", "Folder"), work2);
			
			TreeNode work3 = new DefaultTreeNode(new LavoriReferenti("2014_003_NomeLavoro3", "Breve descrizione Lavoro3", "Folder"), root);
			TreeNode ref31 = new DefaultTreeNode(new LavoriReferenti("Mario Rossi", "-", "Folder"), work3);
			
			TreeNode work4 = new DefaultTreeNode(new LavoriReferenti("2014_004_NomeLavoro4", "Breve descrizione Lavoro4", "Folder"), root);
			TreeNode ref41 = new DefaultTreeNode(new LavoriReferenti("Giuseppe Verdi", "-", "Folder"), work4);
			***/
		}
		return root;
	}
	
}// end class