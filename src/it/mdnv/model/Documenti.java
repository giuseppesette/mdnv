package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the documenti database table.
 * 
 */
@Entity
public class Documenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String cartella;

	private String documento;

	@Column(name="path_documento")
	private String pathDocumento;

	//bi-directional many-to-many association to Lavori
	@ManyToMany(mappedBy="documentis")
	private List<Lavori> lavoris;

	public Documenti() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCartella() {
		return this.cartella;
	}

	public void setCartella(String cartella) {
		this.cartella = cartella;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getPathDocumento() {
		return this.pathDocumento;
	}

	public void setPathDocumento(String pathDocumento) {
		this.pathDocumento = pathDocumento;
	}

	public List<Lavori> getLavoris() {
		return this.lavoris;
	}

	public void setLavoris(List<Lavori> lavoris) {
		this.lavoris = lavoris;
	}

}