package it.mdnv.mb;

import it.mdnv.facade.ContattiFacade;
import it.mdnv.model.Contatti;
import it.mdnv.utils.Constants;
import it.mdnv.validator.EmailValidatorRegExp;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ViewScoped
@ManagedBean(name = "contattibean")
public class ContattiMB extends AbstractMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{contattibean}";
	
	private Contatti contatti;
	private boolean disable = false;
	private boolean updateRendered = false;

	private EmailValidatorRegExp emailValidator = new EmailValidatorRegExp();

	/**
	 * COSTRUTTORE
	 */
	public ContattiMB(){
		//System.out.println("[ContattiMB][COSTRUTTORE] - Contatto: " + this.getContatti());
		
		//System.out.println("[ContattiMB][COSTRUTTORE] - Contatto from MBLContatti: " + mBLClienti.getSelectedCliente());
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		//System.out.println("[ContattiMB][COSTRUTTORE] - getRequestParameterMap: " + params);
		
		if(params != null && params.containsKey(Constants.ID)){
			
			//System.out.println("[ContattiMB][COSTRUTTORE] - ID: " + params.get(Constants.ID));
			
			// Valorizzo i bean per i dati singoli e specifici (no liste)
			ContattiFacade contattiFacade = new ContattiFacade();
			this.setContatti(contattiFacade.findContattoByIdContatto(Integer.parseInt(params.get(Constants.ID))));
			
			// Disabilito il Pulsante "Salva"
			this.setDisable(true);
			// Abilito il Pulsante "Aggiorna"
			this.setUpdateRendered(true);

			
		}
		
		if(this.getContatti() != null && this.contatti.getId() != 0){
			//System.out.println("[ContattiMB][COSTRUTTORE] - Contatto presente");
		} else {
			this.contatti = new Contatti();
			
			/****
			//System.out.println("[ContattiMB][COSTRUTTORE] - Nuovo Cliente");
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
			****/
		}
		
	} // END COSTRUTTORE
	
	
    @PostConstruct
    public void init() {
    	/***
    	System.out.println("[ContattiMB][@PostConstruct] - aggiornaListaSediOperative");
    	aggiornaListaSediOperative();
    	
    	System.out.println("[ContattiMB][@PostConstruct] - aggiornaListaReferenti");
    	aggiornaListaReferenti();

    	this.referentiPickList = populateReferentiPickListCLienti(this.getCli().getId());
        
    	System.out.println("[ContattiMB][@PostConstruct] - aggiornaListaLavori");
        aggiornaListaLavori();
		**/
    }
    
    /*************   ACTION CONTATTI  *****************************/

	public void save() {
		//System.out.println("[ContattiMB][save]");
		if(this.getContatti().getRagioneSociale() != null && 
				!this.getContatti().getRagioneSociale().equalsIgnoreCase("")){
			
			if(this.getContatti().getMail1() != null && !this.getContatti().getMail1().equalsIgnoreCase("")){
				
				if(emailValidator.validate(this.getContatti().getMail1())){
				
					ContattiFacade contattiFacade = new ContattiFacade();
					//System.out.println("[ContattiMB][save] - this.getContatti(): " + this.getContatti());
					Contatti contatto = contattiFacade.createContatto(this.getContatti());
					//System.out.println("[ContattiMB][save] - contatto: " + contatto);
					//System.out.println("[ContattiMB][save] - contatto.getId(): " + contatto.getId());
					
					if(contatto != null && contatto.getId() != 0){
						
						this.setContatti(contatto);
						
						// Disabilito il Pulsante "Salva" 
						this.setDisable(true);
						// Abilito il Pulsante "Aggiorna"
						this.setUpdateRendered(true);
						
						displayInfoMessageToUser("Inserimento del Contatto Avvenuto Correttamente.");
						
					} else 
						displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
				} else {
					displayErrorMessageToUser("Inserimento Contatto: Indirizzo di Posta Elettronica non valido!!");
				}
			} else {
				displayErrorMessageToUser("Inserimento Contatto: specificare l'indirizzo di Posta Elettronica.");
			}
		} else {
			displayErrorMessageToUser("Inserimento Contatto: specificare la 'Ragione Sociale'.");
		}
	} // END save
	
	public void update() {
		//System.out.println("[ContattiMB][update] - Contatto Da Aggiornare: " + this.getContatti());
		if(this.getContatti().getRagioneSociale() != null && 
				!this.getContatti().getRagioneSociale().equalsIgnoreCase("")){
			
			if(this.getContatti().getMail1() != null && !this.getContatti().getMail1().equalsIgnoreCase("")){
				ContattiFacade contattiFacade = new ContattiFacade();
				
				contattiFacade.updateContatto(this.getContatti());
				
				displayInfoMessageToUser("Aggiornamento del Contatto '"+this.getContatti().getRagioneSociale()+"' avvenuto Correttamente!");
			} else {
				displayErrorMessageToUser("Aggiornamento Contatto: specificare l'indirizzo di Posta Elettronica.");
			}
		} else {
			displayErrorMessageToUser("Aggiornamento Contatto: specificare la 'Ragione Sociale'.");
		}
	}// END update

	/***
	// onrate
    public void onrate(RateEvent rateEvent) {
    //System.out.println("[ContattiMB][onrate]");
        //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        //FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    // oncancel 
    public void oncancel() {
    //System.out.println("[ContattiMB][oncancel]");
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
    **/
    
    
	public Contatti getContatti() {
		return contatti;
	}
	public void setContatti(Contatti contatti) {
		this.contatti = contatti;
	}
	

	public boolean isDisable() {
		return disable;
	}
	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public boolean isUpdateRendered() {
		return updateRendered;
	}
	public void setUpdateRendered(boolean updateRendered) {
		this.updateRendered = updateRendered;
	}
	
	/***************************************************************************/
	/*********************      METODI PRIVATI     *****************************/

	
}// end class