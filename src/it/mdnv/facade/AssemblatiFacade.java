package it.mdnv.facade;

import it.mdnv.dao.AssemblatiDAO;
import it.mdnv.model.Assemblati;

import java.util.List;

public class AssemblatiFacade {
	
	private AssemblatiDAO assemblatiDAO = new AssemblatiDAO();
	//private ProdFornitoriDAO prodFornitoriDAO = new ProdFornitoriDAO();

	public List<Assemblati> getListaAssemblati() {
		assemblatiDAO.beginTransaction();
		List<Assemblati> lista = assemblatiDAO.findAll();
		//System.out.println("[AssemblatiFacade][getListaAssemblati] lista: " + lista);
		return lista;
	} // END getListaAssemblati
	
	public Assemblati getAssemblatoFromNomeAssemblato(String nomeAssemblato){
		assemblatiDAO.beginTransaction();
		return assemblatiDAO.getAssemblatoFromNomeAssemblato(nomeAssemblato);
	} // END getAssemblatoFromNomeAssemblato

	public Assemblati getAssemblatoById(int idAssemblato){
		assemblatiDAO.beginTransaction();
		return assemblatiDAO.find(idAssemblato);
	} // END getAssemblatoById
	
	public Assemblati updateAssemblato(Assemblati assemblato){
		assemblatiDAO.beginTransaction();
		Assemblati assemblatoUpdated = assemblatiDAO.update(assemblato);
		assemblatiDAO.commitAndCloseTransaction();
		return assemblatoUpdated;
	}// END updateAssemblato
	
	public Assemblati createAssemblato(Assemblati assemblato){
		//System.out.println("[AssemblatiFacade][createAssemblato] START");
		assemblatiDAO.beginTransaction();
		Assemblati newasmb = assemblatiDAO.create(assemblato);
		//System.out.println("[AssemblatiFacade][createAssemblato] newasmb: " + newasmb);
		if(newasmb != null && newasmb.getId() != 0)
			assemblatiDAO.commitAndCloseTransaction();
		//System.out.println("[AssemblatiFacade][createAssemblato] END");
		return newasmb;
	}// END createAssemblato
	
	/**
	public ProdFornitori aggiungiFornitoreMateriali(ProdFornitori prodFornitori){
		//System.out.println("[AssemblatiFacade][aggiungiFornitoreMateriali] START");
		prodFornitoriDAO.beginTransaction();
		ProdFornitori newprdfor = prodFornitoriDAO.create(prodFornitori);
		//System.out.println("[AssemblatiFacade][aggiungiFornitoreMateriali] newprdfor: " + newprdfor);
		if(newprdfor != null && newprdfor.getId() != 0)
			prodFornitoriDAO.commitAndCloseTransaction();
		//System.out.println("[AssemblatiFacade][aggiungiFornitoreMateriali] END");
		return newprdfor;
	}// END aggiungiFornitoreMateriali
	
	public ProdFornitori findProdottoFornitoreByIdProdotto(Prodotti prodotto){
		//System.out.println("[AssemblatiFacade][findProdottoFornitoreByIdProdotto] START");
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
		//System.out.println("[AssemblatiFacade][findProdottoFornitoreByIdProdotto] END");
		return pf;
	}// END findProdottoFornitoreByIdProdotto
	
	public ProdFornitori findProdottoFornitoreById(int idProdFornitori){
		//System.out.println("[AssemblatiFacade][findProdottoFornitoreById] START");
		//System.out.println("[AssemblatiFacade][findProdottoFornitoreById] - idProdFornitori: " + idProdFornitori);
		
		prodFornitoriDAO.beginTransaction();
		ProdFornitori prodFornitori = prodFornitoriDAO.findReferenceOnly(idProdFornitori);

		if (prodFornitori == null || prodFornitori.getId() != idProdFornitori) {
			return null;
		}

		//System.out.println("[AssemblatiFacade][findProdottoFornitoreById] - ProdFornitori: " + prodFornitori);
		//System.out.println("[AssemblatiFacade][findProdottoFornitoreById] END");
		
		return prodFornitori;
	}// END findProdottoFornitoreById
	
	
	public ProdFornitori updateFornitoreMateriali(ProdFornitori prodFornitori){
		//System.out.println("[AssemblatiFacade][updateFornitoreMateriali] START");
		//System.out.println("[AssemblatiFacade][updateFornitoreMateriali] >>>>> prodFornitori..........: " + prodFornitori);
		prodFornitoriDAO.beginTransaction();
		ProdFornitori prodFornitoriUpdated = prodFornitoriDAO.update(prodFornitori);
		
		//System.out.println("[AssemblatiFacade][updateFornitoreMateriali] >>>>> prodFornitoriUpdated...: " + prodFornitoriUpdated);
		prodFornitoriDAO.commitAndCloseTransaction();
		//System.out.println("[AssemblatiFacade][updateFornitoreMateriali] END");
		return prodFornitoriUpdated;
	}// END updateFornitoreMateriali
	***/

	public Assemblati findAssemblatoByIdAssemblato(int idAssemblato) {
		assemblatiDAO.beginTransaction();
		Assemblati assemblato = assemblatiDAO.findAssemblatoByIdAssemblato(idAssemblato);
		//System.out.println("[AssemblatiFacade][findAssemblatoByIdAssemblato] - ["+idAssemblato+"]: " + idAssemblato);
		if (assemblato == null || assemblato.getId() != idAssemblato) {
			return null;
		}
		return assemblato;
	} // END findAssemblatoByIdAssemblato
	
} // end class