package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipologie database table.
 * 
 */
@Entity
public class Tipologie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String tipologia;

	//bi-directional many-to-one association to Prodotti
	@OneToMany(mappedBy="tipologie")
	private List<Prodotti> prodottis;

	//bi-directional many-to-one association to Famiglia
	@ManyToOne
	@JoinColumn(name="idFamiglia")
	private Famiglia famiglia;

	public Tipologie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipologia() {
		return this.tipologia;
	}

	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}

	public List<Prodotti> getProdottis() {
		return this.prodottis;
	}

	public void setProdottis(List<Prodotti> prodottis) {
		this.prodottis = prodottis;
	}

	public Prodotti addProdotti(Prodotti prodotti) {
		getProdottis().add(prodotti);
		prodotti.setTipologie(this);

		return prodotti;
	}

	public Prodotti removeProdotti(Prodotti prodotti) {
		getProdottis().remove(prodotti);
		prodotti.setTipologie(null);

		return prodotti;
	}

	public Famiglia getFamiglia() {
		return this.famiglia;
	}

	public void setFamiglia(Famiglia famiglia) {
		this.famiglia = famiglia;
	}

	
	public String toString(){
		return "> ID="+this.id+" - " + this.tipologia;
	}

}