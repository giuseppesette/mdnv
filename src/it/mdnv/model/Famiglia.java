package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the famiglia database table.
 * 
 */
@Entity
public class Famiglia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="famiglia_id")
	private int famigliaId;

	private String famiglia;

	//bi-directional many-to-one association to Prodotti
	@OneToMany(mappedBy="famiglia")
	private List<Prodotti> prodottis;

	//bi-directional many-to-one association to Tipologie
	@OneToMany(mappedBy="famiglia")
	private List<Tipologie> tipologies;

	public Famiglia() {
	}

	public int getFamigliaId() {
		return this.famigliaId;
	}

	public void setFamigliaId(int famigliaId) {
		this.famigliaId = famigliaId;
	}

	public String getFamiglia() {
		return this.famiglia;
	}

	public void setFamiglia(String famiglia) {
		this.famiglia = famiglia;
	}

	public List<Prodotti> getProdottis() {
		return this.prodottis;
	}

	public void setProdottis(List<Prodotti> prodottis) {
		this.prodottis = prodottis;
	}

	public Prodotti addProdotti(Prodotti prodotti) {
		getProdottis().add(prodotti);
		prodotti.setFamiglia(this);

		return prodotti;
	}

	public Prodotti removeProdotti(Prodotti prodotti) {
		getProdottis().remove(prodotti);
		prodotti.setFamiglia(null);

		return prodotti;
	}

	public List<Tipologie> getTipologies() {
		return this.tipologies;
	}

	public void setTipologies(List<Tipologie> tipologies) {
		this.tipologies = tipologies;
	}

	public Tipologie addTipology(Tipologie tipology) {
		getTipologies().add(tipology);
		tipology.setFamiglia(this);

		return tipology;
	}

	public Tipologie removeTipology(Tipologie tipology) {
		getTipologies().remove(tipology);
		tipology.setFamiglia(null);

		return tipology;
	}
	
	public String toString(){
		return "> ID="+this.famigliaId+" - " + this.famiglia;
	}

}