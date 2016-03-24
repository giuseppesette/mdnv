package it.mdnv.domain;

import java.io.Serializable;

public class Materiale implements Serializable {

	
	public String codProdotto;
	public String prodotto;
	public String codArtFornitore;
	public String materiale;
	public String lavorazione;
	public String costoUnitario;
	public String qtaMinOrd;
	public String quantita;

	public Materiale(){}
	
	public Materiale(String codProdotto, String prodotto, String quantita){
		this.codProdotto = codProdotto;
		this.prodotto = prodotto;
		this.quantita = quantita;
	}
	
	public Materiale(String codProdotto, String prodotto, String codArtFornitore, String materiale, 
			String lavorazione, String costoUnitario, String qtaMinOrd) {
		this.codProdotto = codProdotto;
		this.prodotto = prodotto;
		this.codArtFornitore = codArtFornitore;
		this.materiale = materiale;
		this.lavorazione = lavorazione;
		this.costoUnitario = costoUnitario;
		this.qtaMinOrd = qtaMinOrd;
	}


	
	public String getCodProdotto() {
		return codProdotto;
	}



	public void setCodProdotto(String codProdotto) {
		this.codProdotto = codProdotto;
	}



	public String getProdotto() {
		return prodotto;
	}



	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}



	public String getCodArtFornitore() {
		return codArtFornitore;
	}



	public void setCodArtFornitore(String codArtFornitore) {
		this.codArtFornitore = codArtFornitore;
	}



	public String getMateriale() {
		return materiale;
	}



	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}



	public String getLavorazione() {
		return lavorazione;
	}



	public void setLavorazione(String lavorazione) {
		this.lavorazione = lavorazione;
	}



	public String getCostoUnitario() {
		return costoUnitario;
	}



	public void setCostoUnitario(String costoUnitario) {
		this.costoUnitario = costoUnitario;
	}



	public String getQtaMinOrd() {
		return qtaMinOrd;
	}



	public void setQtaMinOrd(String qtaMinOrd) {
		this.qtaMinOrd = qtaMinOrd;
	}



	public String getQuantita() {
		return quantita;
	}

	public void setQuantita(String quantita) {
		this.quantita = quantita;
	}


	public boolean equals(Object obj) {
		if (!(obj instanceof Materiale))
			return false;

		Materiale materiale = (Materiale) obj;

		return (materiale.getProdotto() != null && materiale.getProdotto().equals(prodotto))
				&& (materiale.getQuantita() != null && materiale.getQuantita().equals(quantita));
	}

	public int hashCode() {
		int hash = 1;
		if (prodotto != null)
			hash = hash * 31 + prodotto.hashCode();

		if (quantita != null)
			hash = hash * 29 + quantita.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "Materiale{" + "codProdotto=" + codProdotto + ", prodotto=" + prodotto + ", codArtFornitore="
				+ codArtFornitore + ", materiale=" + materiale + ", lavorazione=" + lavorazione + 
				", costoUnitario=" + costoUnitario + ", qtaMinOrd=" + qtaMinOrd + "}";
	}
}
