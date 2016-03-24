package it.mdnv.mb;

import it.mdnv.facade.ClientiFacade;
import it.mdnv.facade.FolderLavoriFacade;
import it.mdnv.facade.ReferentiFacade;
import it.mdnv.model.Clienti;
import it.mdnv.model.Lavori;
import it.mdnv.model.Referenti;
import it.mdnv.utils.Constants;
import it.mdnv.utils.CreaCartelleLavoro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DualListModel;
import org.primefaces.model.TreeNode;

@ViewScoped
@ManagedBean(name = "lavorobean")
public class LavoriMB extends AbstractMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{lavorobean}";
	
	//private FolderLavori lavoro = new FolderLavori();
	private Lavori lavoro;
	private List<Lavori> folderLavoriList;
	
	private Map<String, String> clienti;
	private String cliente;
	
	private TreeNode lavoriRoot;
	private DualListModel<Referenti> referentiPickList = new DualListModel<Referenti>();
	
	private boolean disableLavoro = false;
	// Pulsante Aggiorna
	private boolean updateRendered = false;
	
	private int activeIndex = 0;
	
	//private String oggi;
	private Date sysdate;

	/**
	 * COSTRUTTORE
	 */
	public LavoriMB(){
		System.out.println("[LavoriMB][COSTRUTTORE] - FolderLavori: " + this.getLavoro());
		
		// CASO IN CUI PROVENGO DALLA LISTA (MODIFICA)
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		System.out.println("[LavoriMB][COSTRUTTORE] - getRequestParameterMap: " + params);
		
		if(params != null && params.containsKey(Constants.IDLAVORO)){
			
			// Valorizzo i bean per i dati singoli e specifici (no liste)
			FolderLavoriFacade folderLavoriFacade = new FolderLavoriFacade();
			this.setLavoro(folderLavoriFacade.findFolderLavoriById(Integer.parseInt(params.get("idLavoro"))));
			
			// Recupero le informazioni relative al Cliente ed alla lista di referenti associati
			System.out.println("[LavoriMB][COSTRUTTORE] - idCliente["+this.getLavoro().getNomeLavoro()+"]: " + this.getLavoro().getIdcliente());
			this.setCliente(String.valueOf(this.getLavoro().getIdcliente()));
			
           
            this.referentiPickList = populateReferentiPickListLavori(this.getLavoro().getId(), this.getLavoro().getIdcliente());
            
			// Disabilito il Pulsante "Crea"
			this.setDisableLavoro(true);
			
			// Abilito il Pulsante "Aggiorna"
			this.setUpdateRendered(true);
			
			/*
			// Disabilito il Pulsante "Salva" dei Dati di Fatturazione
			this.setDisableDatiFatt(true);
			// Abilito il Pulsante "Aggiorna" dei Dati di Fatturazione
			this.setUpdateDatiFattRendered(true);
			*/
		} else {
	        //Referenti PickList
	        referentiPickList = new DualListModel<Referenti>(new ArrayList<Referenti>(), new ArrayList<Referenti>());
		}
	}
	
	
    @PostConstruct
    public void init() {
    	System.out.println("[LavoriMB][init] - @PostConstruct ");
    	
    	ClientiFacade cf = new ClientiFacade();
    	List<Clienti> lCli = cf.getListaClienti();
    	if(lCli != null && lCli.size() > 0){
    		clienti  = new HashMap<String, String>();
    		Iterator<Clienti> ic = lCli.iterator();
    		while(ic.hasNext()){
    			Clienti c = ic.next();
    			//clienti.put("Famiglia_1", "F1");
    			clienti.put(c.getCodCliente(), String.valueOf(c.getId()));
    		}
    	} // END IF
    }
    
    public void onClientiChange() {
    	System.out.println("[LavoriMB][onClientiChange] - cliente: " + cliente);
    	System.out.println("[LavoriMB][onClientiChange] - this.getLavoro(): " + this.getLavoro());
        if(cliente !=null && !cliente.equals("")){
        	//this.setDisableLavoro(false);
        	//this.setIdCliente(Integer.parseInt(cliente));
        	//idCliente = Integer.parseInt(cliente);
        	if(this.getLavoro() == null)
        		lavoro = new Lavori();
        	this.getLavoro().setIdcliente(Integer.parseInt(cliente));
        	
            //Referenti PickList
//            List<String> referentiSource = getReferentiPickListString();
//            List<String> referentiTarget = new ArrayList<String>();
//            referentiPickList = new DualListModel<String>(referentiSource, referentiTarget);
        	this.referentiPickList = populateReferentiPickListCLienti(this.getLavoro().getIdcliente());
        }
        
    } // END onClientiChange
    

    public Map<String, String> getClienti() {
        return clienti;
    }
    public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Date getSysdate() {
		return sysdate;
	}
	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}
	
	public int getActiveIndex() {
		return activeIndex;
	}
	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
	
	public void setReferentiPickList(DualListModel<Referenti> referentiPickList) {
		this.referentiPickList = referentiPickList;
	}
	public DualListModel<Referenti> getReferentiPickList() {
		return this.referentiPickList;
	}
	
	
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

	public boolean isUpdateRendered() {
		return updateRendered;
	}
	public void setUpdateRendered(boolean updateRendered) {
		this.updateRendered = updateRendered;
	}

	/*************   ACTION LAVORI  *****************************/
	
	public void creaFolderLavoro(){
		System.out.println("[LavoriMB][creaFolderLavoro] - this.getLavoro().getIdcliente(): " + this.getLavoro().getIdcliente());
		if(this.getLavoro().getIdcliente() > 0) {
			if(this.getLavoro().getNomeLavoro() != null && 
					!this.getLavoro().getNomeLavoro().equalsIgnoreCase("")
				){
				
				System.out.println("[LavoriMB][onClientiChange] - referentiPickList.getTarget: " + referentiPickList.getTarget());
				
				this.getLavoro().setActive("1");
				
				FolderLavoriFacade folderLavoriFacade = new FolderLavoriFacade();
				System.out.println("[LavoriMB][creaFolderLavoro] - this.getLavoro().getNome_lavoro: " + this.getLavoro().getNomeLavoro());

				Lavori folderLavoro = folderLavoriFacade.createFolderLavori(this.getLavoro());
				System.out.println("[LavoriMB][creaFolderLavoro] - FolderLavori ID: " + folderLavoro.getId());
				
				if(folderLavoro != null && folderLavoro.getId() != 0){
					
					// Disabilito il campo della Descrizione Lavoro, il pulsante "Crea" e la Selezione Cliente
					this.setDisableLavoro(true);

					// Abilito il Pulsante "Aggiorna"
					this.setUpdateRendered(true);
					
					folderLavoro.setNomeLavoro(getNomeLavoro(folderLavoro.getId(), folderLavoro.getNomeLavoro()));
					
					String pathLavoro = CreaCartelleLavoro.crea(folderLavoro.getNomeLavoro());
					System.out.println("[LavoriMB][creaFolderLavoro] - CreaCartelleLavoro.crea: " + pathLavoro);
					folderLavoro.setPathLavoro(pathLavoro);
					
					folderLavoriFacade.updateFolderLavori(folderLavoro);
					
					displayInfoMessageToUser("Creazione Cartella Lavoro '" + folderLavoro.getNomeLavoro() + "' Avvenuta Correttamente.");
				} else 
					displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");

			} else {
				//referentiPickList = new DualListModel<String>(new ArrayList<String>(), new ArrayList<String>());
				 referentiPickList = new DualListModel<Referenti>(new ArrayList<Referenti>(), new ArrayList<Referenti>());
				displayErrorMessageToUser("Per creare una nuova 'Cartella Lavoro' bisogna specificare la descrizione!");
			}
		} else {
			//referentiPickList = new DualListModel<String>(new ArrayList<String>(), new ArrayList<String>());
			 referentiPickList = new DualListModel<Referenti>(new ArrayList<Referenti>(), new ArrayList<Referenti>());
			displayErrorMessageToUser("Per creare una nuova 'Cartella Lavoro' bisogna prima selezionare il Cliente!");
		}
		
	} // END creaFolderLavoro

	@SuppressWarnings("rawtypes")
	public void updateFolderLavoro(){
		System.out.println("[LavoriMB][updateFolderLavoro] - this.getLavoro().getId(): " + this.getLavoro().getId());
		//populateReferentiPickListTarget
		FolderLavoriFacade folderLavoriFacade = new FolderLavoriFacade();
		
		if(this.getReferentiPickList() != null && this.getReferentiPickList().getTarget() != null){
			List lRefTrgt = this.getReferentiPickList().getTarget();
	        System.out.println("[LavoriMB][updateFolderLavoro] - referentiTarget: " + lRefTrgt);
	        if(lRefTrgt != null && lRefTrgt.size() > 0){
	        	List<Referenti> lRefLav = new ArrayList<Referenti>();
	        	Iterator ir = lRefTrgt.iterator();
	        	while(ir.hasNext()){
	        		String idRef = (String)ir.next();
	        		ReferentiFacade referenteFacade = new ReferentiFacade();
	        		lRefLav.add(referenteFacade.findReferenteByIdReferente(Integer.parseInt(idRef)));
	        	}
	        	this.getLavoro().setReferentis(lRefLav);
	        } else {
				 System.out.println("[LavoriMB][updateFolderLavoro] - 1 referentiTarget Vuoto");
				 this.getLavoro().setReferentis(new ArrayList<Referenti>());
	        }
		} else {
			 System.out.println("[LavoriMB][updateFolderLavoro] - 2 referentiTarget Vuoto");
			 this.getLavoro().setReferentis(new ArrayList<Referenti>());
		}
        folderLavoriFacade.updateFolderLavori(this.getLavoro());
        
        this.referentiPickList = populateReferentiPickListLavori(this.getLavoro().getId(), this.getLavoro().getIdcliente());
        
        displayInfoMessageToUser("Aggiornamento Lavoro '"+this.getLavoro().getNomeLavoro()+"' avvenuto Correttamente!");
	} // END updateFolderLavoro
	
	
	/***************************************************************************/
	/*********************      METODI PRIVATI     *****************************/
	
	private String getNomeLavoro(int idFolderLavoro, String nomeLavoro){
		
		// Generazione del Codice Cliente
		String nextId = String.format("%03d", idFolderLavoro);
		System.out.println("[LavoriMB][getNomeLavoro] - nextId padded: " + nextId);
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		String yearInString = String.valueOf(year);
		
		// - Comporre il codice come: 2014_001_<Nome Lavoro>_R
		return yearInString + "_" + String.format("%03d", idFolderLavoro) + "_" + nomeLavoro + "_R";
		
	} // END getNomeLavoro

}// end class