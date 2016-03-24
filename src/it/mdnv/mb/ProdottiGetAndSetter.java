package it.mdnv.mb;

import it.mdnv.facade.FamiglieFacade;
import it.mdnv.facade.FornitoriFacade;
import it.mdnv.facade.TipologieFacade;
import it.mdnv.model.Famiglia;
import it.mdnv.model.Fornitori;
import it.mdnv.model.Tipologie;
import it.mdnv.utils.Utilities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProdottiGetAndSetter extends AbstractMB implements Serializable {

	private static final long serialVersionUID = -2440775114345686293L;

    public boolean readonly = false;
    
    public boolean disable = false;
    public boolean updateRendered = false;

    public boolean disableSaveSchedaMateriale = false;
    public boolean updateRenderedSchedaMateriale = false;
	
    public boolean disableAggiungiFornitoreMateriali = false;
    public boolean updateRenderedFornitoreMateriali = false;
    
    public int activeIndex = 0;
	
    public Map<String, String> famiglie;
    public Map<String, String> tipologie;
    public String famiglia;
    public String tipologia;

    public Map<String, String> fornitori;
	public String fornitore;

	/**
	 * popolaComboFamiglie
	 */
	protected void popolaComboFamiglie() {
		FamiglieFacade ff = new FamiglieFacade();
		List<Famiglia> lFam = ff.getListaFamiglie();
		if (lFam != null && lFam.size() > 0) {
			famiglie = new HashMap<String, String>();
			Iterator<Famiglia> ifam = lFam.iterator();
			while (ifam.hasNext()) {
				Famiglia f = ifam.next();
				famiglie.put(f.getFamiglia(), String.valueOf(f.getFamigliaId()));
			}
			famiglie = Utilities.sortByValues(famiglie);
		} // END IF
	} // END popolaComboFamiglie

	/**
	 * popolaComboTipologie
	 */
	protected void popolaComboTipologie() {
		TipologieFacade tf = new TipologieFacade();
		List<Tipologie> lTip = tf.getListaTipologie();
		if (lTip != null && lTip.size() > 0) {
			tipologie = new HashMap<String, String>();
			Iterator<Tipologie> itip = lTip.iterator();
			while (itip.hasNext()) {
				Tipologie t = itip.next();
				tipologie.put(t.getTipologia(), String.valueOf(t.getId()));
			}
			tipologie = Utilities.sortByValues(tipologie);
		} // END IF
	} // END popolaComboTipologie

	/**
	 * popolaComboFornitori
	 */
	protected void popolaComboFornitori() {
		FornitoriFacade ff = new FornitoriFacade();
		List<Fornitori> lFor = ff.getListaFornitori();
		if (lFor != null && lFor.size() > 0) {
			fornitori = new HashMap<String, String>();
			Iterator<Fornitori> ic = lFor.iterator();
			while (ic.hasNext()) {
				Fornitori f = ic.next();
				fornitori.put(f.getCodFornitore(), String.valueOf(f.getId()));
			}
		} // END IF
	} // END popolaComboFornitori
    
	/**********      GET AND SETTER         ****************/
	

	public String getFamiglia() {
		return famiglia;
	}

	public void setFamiglia(String famiglia) {
		this.famiglia = famiglia;
	}

	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public Map<String, String> getFamiglie() {
		return famiglie;
	}

	public Map<String, String> getTipologie() {
		return tipologie;
	}

	public String getFornitore() {
		return fornitore;
	}

	public void setFornitore(String fornitore) {
		this.fornitore = fornitore;
	}

	public Map<String, String> getFornitori() {
		return fornitori;
	}
	
	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
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
 	public boolean isDisableSaveSchedaMateriale() {
		return disableSaveSchedaMateriale;
	}
	public void setDisableSaveSchedaMateriale(boolean disableSaveSchedaMateriale) {
		this.disableSaveSchedaMateriale = disableSaveSchedaMateriale;
	}
	public boolean isUpdateRenderedSchedaMateriale() {
		return updateRenderedSchedaMateriale;
	}
	public void setUpdateRenderedSchedaMateriale(boolean updateRenderedSchedaMateriale) {
		this.updateRenderedSchedaMateriale = updateRenderedSchedaMateriale;
	}
	public boolean isDisableAggiungiFornitoreMateriali() {
		return disableAggiungiFornitoreMateriali;
	}

	public void setDisableAggiungiFornitoreMateriali(boolean disableAggiungiFornitoreMateriali) {
		this.disableAggiungiFornitoreMateriali = disableAggiungiFornitoreMateriali;
	}

	public boolean isUpdateRenderedFornitoreMateriali() {
		return updateRenderedFornitoreMateriali;
	}

	public void setUpdateRenderedFornitoreMateriali(boolean updateRenderedFornitoreMateriali) {
		this.updateRenderedFornitoreMateriali = updateRenderedFornitoreMateriali;
	}
	
	public int getActiveIndex() {
		return activeIndex;
	}
	public void setActiveIndex(int activeIndex) {
		this.activeIndex = activeIndex;
	}
	
    /**********      END GET AND SETTER         ****************/

	
}// end class
