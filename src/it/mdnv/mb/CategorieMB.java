package it.mdnv.mb;

import it.mdnv.facade.CategorieFacade;
import it.mdnv.model.Categorie;
import it.mdnv.utils.Constants;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ViewScoped
@ManagedBean(name = "categoriebean")
public class CategorieMB extends AbstractMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String INJECTION_NAME = "#{categoriebean}";
	
	private Categorie categorie;
	private boolean disable = false;
	private boolean updateRendered = false;


	/**
	 * COSTRUTTORE
	 */
	public CategorieMB(){
		//System.out.println("[CategorieMB][COSTRUTTORE] - Categoria: " + this.getContatti());
		
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		//System.out.println("[CategorieMB][COSTRUTTORE] - getRequestParameterMap: " + params);
		
		if(params != null && params.containsKey(Constants.ID)){
			
			//System.out.println("[CategorieMB][COSTRUTTORE] - ID: " + params.get(Constants.ID));
			
			// Valorizzo i bean per i dati singoli e specifici (no liste)
			CategorieFacade categorieFacade = new CategorieFacade();
			this.setCategorie(categorieFacade.findCategoriaByIdCategoria(Integer.parseInt(params.get(Constants.ID))));
			
			// Disabilito il Pulsante "Salva"
			this.setDisable(true);
			// Abilito il Pulsante "Aggiorna"
			this.setUpdateRendered(true);

			
		}
		
		if(this.getCategorie() != null && this.categorie.getId() != 0){
			//System.out.println("[CategorieMB][COSTRUTTORE] - Categoria presente");
		} else {
			this.categorie = new Categorie();
			
			/****
			//System.out.println("[CategorieMB][COSTRUTTORE] - Nuova Categoria");
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
    public void init() { }
    
    /*************   ACTION CATEGORIE  *****************************/

	public void save() {
		//System.out.println("[CategorieMB][save]");
		if(this.getCategorie().getCategoria() != null && 
				!this.getCategorie().getCategoria().equalsIgnoreCase("")){
				
					CategorieFacade categorieFacade = new CategorieFacade();
					//System.out.println("[CategorieMB][save] - this.getCategorie(): " + this.getCategorie());
					Categorie categoria = categorieFacade.createCategorie(this.getCategorie());
					//System.out.println("[CategorieMB][save] - categoria: " + categoria);
					//System.out.println("[CategorieMB][save] - categoria.getId(): " + categoria.getId());
					
					if(categoria != null && categoria.getId() != 0){
						
						this.setCategorie(categoria);
						
						// Disabilito il Pulsante "Salva" 
						this.setDisable(true);
						// Abilito il Pulsante "Aggiorna"
						this.setUpdateRendered(true);
						
						displayInfoMessageToUser("Inserimento della Categoria Avvenuto Correttamente.");
						
					} else 
						displayErrorMessageToUser("Si e' verificato un problema durante l'elaborazione!");
		} else {
			displayErrorMessageToUser("Inserimento Categoria: specificare la 'Categoria'.");
		}
	} // END save
	
	public void update() {
		//System.out.println("[CategorieMB][update] - Categoria Da Aggiornare: " + this.getCategorie());
		if(this.getCategorie().getCategoria() != null && 
				!this.getCategorie().getCategoria().equalsIgnoreCase("")){
			
				CategorieFacade categorieFacade = new CategorieFacade();
				
				categorieFacade.updateCategorie(this.getCategorie());
				
				displayInfoMessageToUser("Aggiornamento della Categoria '"+this.getCategorie().getCategoria()+"' avvenuto Correttamente!");
		} else {
			displayErrorMessageToUser("Aggiornamento Categoria: specificare la Categoria'.");
		}
	}// END update

	public Categorie getCategorie() {
		return categorie;
	}
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
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