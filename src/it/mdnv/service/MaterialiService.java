package it.mdnv.service;

import it.mdnv.domain.Materiale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "materialiService")
@ApplicationScoped
public class MaterialiService implements Serializable {

	private static final long serialVersionUID = 2754189493517863095L;

	private final static String[] codProdotti;
	private final static String[] prodotti;
	private final static String[] codArtFornitore;
	private final static String[] materiale;
	private final static String[] lavorazione;
	private final static String[] costoUnitario;
	private final static String[] qtaMinOrd;
	

	static {
		codProdotti = new String[5];
		codProdotti[0] = "PRD_001";
		codProdotti[1] = "PRD_002";
		codProdotti[2] = "PRD_003";
		codProdotti[3] = "PRD_004";
		codProdotti[4] = "PRD_005";

		prodotti = new String[5];
		prodotti[0] = "PRODOTTO_1";
		prodotti[1] = "PRODOTTO_2";
		prodotti[2] = "PRODOTTO_3";
		prodotti[3] = "PRODOTTO_4";
		prodotti[4] = "PRODOTTO_5";

		codArtFornitore = new String[5];
		codArtFornitore[0] = "COD_ART_FRN_1";
		codArtFornitore[1] = "COD_ART_FRN_2";
		codArtFornitore[2] = "COD_ART_FRN_3";
		codArtFornitore[3] = "COD_ART_FRN_4";
		codArtFornitore[4] = "COD_ART_FRN_5";
		
		materiale = new String[5];
		materiale[0] = "MATERIALE_1";
		materiale[1] = "MATERIALE_2";
		materiale[2] = "MATERIALE_3";
		materiale[3] = "MATERIALE_4";
		materiale[4] = "MATERIALE_5";
		
		lavorazione = new String[5];
		lavorazione[0] = "LAVORAZIONE_1";
		lavorazione[1] = "LAVORAZIONE_2";
		lavorazione[2] = "LAVORAZIONE_3";
		lavorazione[3] = "LAVORAZIONE_4";
		lavorazione[4] = "LAVORAZIONE_5";
		
		costoUnitario = new String[5];
		costoUnitario[0] = "COSTO_1";
		costoUnitario[1] = "COSTO_2";
		costoUnitario[2] = "COSTO_3";
		costoUnitario[3] = "COSTO_4";
		costoUnitario[4] = "COSTO_5";
		
		qtaMinOrd = new String[5];
		qtaMinOrd[0] = "QTA_MIN_ORD_1";
		qtaMinOrd[1] = "QTA_MIN_ORD_2";
		qtaMinOrd[2] = "QTA_MIN_ORD_3";
		qtaMinOrd[3] = "QTA_MIN_ORD_4";
		qtaMinOrd[4] = "QTA_MIN_ORD_5";
	}

	public List<Materiale> createMateriali(int size) {
		List<Materiale> list = new ArrayList<Materiale>();
		for (int i = 0; i < size; i++) {
			list.add(new Materiale(getCodProdotto(), getRandomProdotto(),
					getRandomCodArtFornitore(), getRandomMateriale(), getRandomLavorazione(),
					getRandomCostoUnitario(), getRandomQtaMinOrd()));
		}

		return list;
	}
	
	private String getCodProdotto() {
		return codProdotti[(int) (Math.random() * 5)];
	}
	private String getRandomProdotto() {
		return prodotti[(int) (Math.random() * 5)];
	}
	private String getRandomCodArtFornitore() {
		return codArtFornitore[(int) (Math.random() * 5)];
	}
	private String getRandomMateriale() {
		return materiale[(int) (Math.random() * 5)];
	}
	private String getRandomLavorazione() {
		return lavorazione[(int) (Math.random() * 5)];
	}
	private String getRandomCostoUnitario() {
		return costoUnitario[(int) (Math.random() * 5)];
	}
	private String getRandomQtaMinOrd() {
		return qtaMinOrd[(int) (Math.random() * 5)];
	}
	
	public List<String> getCodProdotti() {
		return Arrays.asList(codProdotti);
	}

	public List<String> getProdotti() {
		return Arrays.asList(prodotti);
	}
	
	public List<String> getCodArtFornitori() {
		return Arrays.asList(codArtFornitore);
	}
	public List<String> getMateriali() {
		return Arrays.asList(materiale);
	}
	public List<String> getLavorazioni() {
		return Arrays.asList(lavorazione);
	}
	public List<String> getCostiUnitari() {
		return Arrays.asList(costoUnitario);
	}
	public List<String> getQtaMinOrds() {
		return Arrays.asList(qtaMinOrd);
	}
}
