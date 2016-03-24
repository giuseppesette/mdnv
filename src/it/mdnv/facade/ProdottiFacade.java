package it.mdnv.facade;

import it.mdnv.dao.ProdFornitoriDAO;
import it.mdnv.dao.ProdottiDAO;
import it.mdnv.model.ProdFornitori;
import it.mdnv.model.Prodotti;

import java.util.Iterator;
import java.util.List;

public class ProdottiFacade {
	
	private ProdottiDAO prodottiDAO = new ProdottiDAO();
	private ProdFornitoriDAO prodFornitoriDAO = new ProdFornitoriDAO();

	public List<Prodotti> getListaProdotti() {
		prodottiDAO.beginTransaction();
		List<Prodotti> lista = prodottiDAO.findAll();
		//System.out.println("[ProdottiFacade][getListaProdotti] lista: " + lista);
		return lista;
	} // END getListaProdotti
	
	public Prodotti getProdottoFromNomeProdotto(String nomeProdotto){
		prodottiDAO.beginTransaction();
		return prodottiDAO.getProdottoFromNomeProdotto(nomeProdotto);
	} // END getProdottoFromNomeProdotto
	
	public Prodotti getProdottoById(int idProdotto){
		prodottiDAO.beginTransaction();
		return prodottiDAO.find(idProdotto);
	} // END getProdottoFromNomeProdotto
	
	public Prodotti updateProdotto(Prodotti prodotto){
		prodottiDAO.beginTransaction();
		Prodotti prodottoUpdated = prodottiDAO.update(prodotto);
		prodottiDAO.commitAndCloseTransaction();
		return prodottoUpdated;
	}// END updateProdotto
	
	public Prodotti createProdotto(Prodotti prodotto){
		//System.out.println("[ProdottiFacade][createProdotto] START");
		prodottiDAO.beginTransaction();
		Prodotti newprd = prodottiDAO.create(prodotto);
		//System.out.println("[ProdottiFacade][createProdotto] newprd: " + newprd);
		if(newprd != null && newprd.getProdottiId() != 0)
			prodottiDAO.commitAndCloseTransaction();
		//System.out.println("[ProdottiFacade][createProdotto] END");
		return newprd;
	}// END createProdotto
	
	public ProdFornitori aggiungiFornitoreMateriali(ProdFornitori prodFornitori){
		//System.out.println("[ProdottiFacade][aggiungiFornitoreMateriali] START");
		prodFornitoriDAO.beginTransaction();
		ProdFornitori newprdfor = prodFornitoriDAO.create(prodFornitori);
		//System.out.println("[ProdottiFacade][aggiungiFornitoreMateriali] newprdfor: " + newprdfor);
		if(newprdfor != null && newprdfor.getId() != 0)
			prodFornitoriDAO.commitAndCloseTransaction();
		//System.out.println("[ProdottiFacade][aggiungiFornitoreMateriali] END");
		return newprdfor;
	}// END aggiungiFornitoreMateriali
	
	public ProdFornitori findProdottoFornitoreByIdProdotto(Prodotti prodotto){
		//System.out.println("[ProdottiFacade][findProdottoFornitoreByIdProdotto] START");
		ProdFornitori pf = null;
		List<ProdFornitori> l = prodotto.getProdFornitoris();
		if(l != null && l.size() > 0){
			boolean continua = true;
			Iterator<ProdFornitori> ipf = l.iterator();
			do{
				pf = ipf.next();
				List<Prodotti> lp = pf.getProdottis();
				if(lp != null && lp.size() > 0){
					Iterator<Prodotti> ip = lp.iterator();
					do{
						Prodotti p = ip.next();
						if(p.getProdottiId() == prodotto.getProdottiId())
							continua = false;
					}while(continua);
				} //
				//if(pf.getProdotti().getProdottiId() == prodotto.getProdottiId())
				//	continua = false;
			}while(continua);
		}
		//System.out.println("[ProdottiFacade][findProdottoFornitoreByIdProdotto] END");
		return pf;
	}// END findProdottoFornitoreByIdProdotto
	
	public ProdFornitori findProdottoFornitoreById(int idProdFornitori){
		//System.out.println("[ProdottiFacade][findProdottoFornitoreById] START");
		//System.out.println("[ProdottiFacade][findProdottoFornitoreById] - idProdFornitori: " + idProdFornitori);
		
		prodFornitoriDAO.beginTransaction();
		ProdFornitori prodFornitori = prodFornitoriDAO.findReferenceOnly(idProdFornitori);

		if (prodFornitori == null || prodFornitori.getId() != idProdFornitori) {
			return null;
		}

		//System.out.println("[ProdottiFacade][findProdottoFornitoreById] - ProdFornitori: " + prodFornitori);
		//System.out.println("[ProdottiFacade][findProdottoFornitoreById] END");
		
		return prodFornitori;
	}// END findProdottoFornitoreById
	
	
	public ProdFornitori updateFornitoreMateriali(ProdFornitori prodFornitori){
		//System.out.println("[ProdottiFacade][updateFornitoreMateriali] START");
		//System.out.println("[ProdottiFacade][updateFornitoreMateriali] >>>>> prodFornitori..........: " + prodFornitori);
		prodFornitoriDAO.beginTransaction();
		ProdFornitori prodFornitoriUpdated = prodFornitoriDAO.update(prodFornitori);
		
		//System.out.println("[ProdottiFacade][updateFornitoreMateriali] >>>>> prodFornitoriUpdated...: " + prodFornitoriUpdated);
		prodFornitoriDAO.commitAndCloseTransaction();
		//System.out.println("[ProdottiFacade][updateFornitoreMateriali] END");
		return prodFornitoriUpdated;
	}// END updateFornitoreMateriali
	

	public Prodotti findProdottoByIdProdotto(int idProdotto) {
		prodottiDAO.beginTransaction();
		Prodotti prodotto = prodottiDAO.findProdottoByIdProdotto(idProdotto);
		//System.out.println("[ProdottiFacade][findProdottoByIdProdotto] findProdottoByIdProdotto["+idProdotto+"]: " + prodotto);
		if (prodotto == null || prodotto.getProdottiId() != idProdotto) {
			return null;
		}

		return prodotto;
	} // END findProdottoByIdProdotto
	
} // end class