package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the stato_lavori database table.
 * 
 */
@Entity
@Table(name="stato_lavori")
public class StatoLavori implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String descrizione;

	private String stato;

	public StatoLavori() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

}