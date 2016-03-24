package it.mdnv.mb;

import it.mdnv.facade.ReferentiFacade;
import it.mdnv.model.Referenti;
import it.mdnv.utils.JSFMessageUtil;
import it.mdnv.utils.SessionUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;


@ManagedBean(name = "absbean")
public class AbstractMB {
	private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";

	public AbstractMB() {
		super();
	}
	
	/*
	 * HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
	 */


	protected DualListModel<Referenti> populateReferentiPickListLavori(int idLavoro, int idCliente){
		// Differenza tra lista referenti clienti e lista referenti associati al lavoro
        List<Referenti> referentiSourceClienti = getReferentiPickListReferentiCLienti(idCliente);
        
        List<Referenti> referentiSource = new ArrayList<Referenti>();
        List<Referenti> referentiTarget = getReferentiPickListReferentiLavoro(idLavoro);
        
        // Effettuo la differenza tra target e source per determinare il reale delta del source
        // Scorro la lista Target
        Iterator<Referenti> irSrc = referentiSourceClienti.iterator();
        while(irSrc.hasNext()){
        	Referenti r = irSrc.next();
        	if(!referentiTarget.contains(r))
        		referentiSource.add(r);
        		
        }
        
//        System.out.println("[AbstractMB][populateReferentiPickListTargetLavori] - referentiSource: " + referentiSource);
//        System.out.println("[AbstractMB][populateReferentiPickListTargetLavori] - referentiTarget: " + referentiTarget);
        
        return new DualListModel<Referenti>(referentiSource, referentiTarget);
	} // END populateReferentiPickListTargetLavori
	
	private List<Referenti> getReferentiPickListReferentiLavoro(int idLavoro){
		List<Referenti> refPickList = new ArrayList<Referenti>();
//		System.out.println("[AbstractMB][getReferentiPickListReferentiLavoro] - idLavoro: " + idLavoro);
		if(idLavoro > 0){
			ReferentiFacade referenteFacade = new ReferentiFacade();
			refPickList = referenteFacade.getListaReferentiByIdLavoro(idLavoro);
		}
//		System.out.println("[AbstractMB][getReferentiPickListReferentiLavoro] - refPickList: " + refPickList);
		return refPickList;
	} // END getReferentiPickListReferentiLavoro
	

	protected DualListModel<Referenti> populateReferentiPickListCLienti(int idCliente){
        List<Referenti> referentiSource = getReferentiPickListReferentiCLienti(idCliente);
        List<Referenti> referentiTarget = new ArrayList<Referenti>();
        
//        System.out.println("[AbstractMB][populateReferentiPickListCLienti] - referentiSource: " + referentiSource);
//        System.out.println("[AbstractMB][populateReferentiPickListCLienti] - referentiTarget: " + referentiTarget);
        
        return new DualListModel<Referenti>(referentiSource, referentiTarget);
	} // END populateReferentiPickListCLienti
	
	protected DualListModel<Referenti> populateReferentiPickListTargetCLienti(int idCliente){
        List<Referenti> referentiSource = new ArrayList<Referenti>();
        List<Referenti> referentiTarget = getReferentiPickListReferentiCLienti(idCliente);
        
//        System.out.println("[AbstractMB][populateReferentiPickListTargetCLienti] - referentiSource: " + referentiSource);
//        System.out.println("[AbstractMB][populateReferentiPickListTargetCLienti] - referentiTarget: " + referentiTarget);
        
        return new DualListModel<Referenti>(referentiSource, referentiTarget);
	} // END populateReferentiPickListTargetCLienti
	
	private List<Referenti> getReferentiPickListReferentiCLienti(int idCliente){
		List<Referenti> refPickList = new ArrayList<Referenti>();
//		System.out.println("[AbstractMB][getReferentiPickListReferentiCLienti] - idCliente: " + idCliente);
		if(idCliente > 0){
			ReferentiFacade referenteFacade = new ReferentiFacade();
			refPickList = referenteFacade.getListaReferentiByIdCliente(idCliente);
		}
//		System.out.println("[AbstractMB][getReferentiPickListReferentiCLienti] - refPickList: " + refPickList);
		return refPickList;
	} // END getReferentiPickListReferentiCLienti

	protected void displayErrorMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendErrorMessageToUser(message);
	}
	
	protected void displayInfoMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendInfoMessageToUser(message);
	}
	
	protected void displayWarningMessageToUser(String message) {
		JSFMessageUtil messageUtil = new JSFMessageUtil();
		messageUtil.sendWarningMessageToUser(message);
	}
	
	protected void closeDialog(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
	}
	
	protected void keepDialogOpen(){
		getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
	}
	
	protected RequestContext getRequestContext(){
		return RequestContext.getCurrentInstance();
	}
	
	public String logout() {
    	System.out.println("[AbstractMB][logout]");
        HttpSession session = SessionUtil.getSession();
        session.invalidate();
        return "/login.xhtml";
     }
}