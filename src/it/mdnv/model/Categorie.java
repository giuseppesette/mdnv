package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the categorie database table.
 * 
 */
@Entity
@Table(name = "CATEGORIE")
@NamedQueries({
	@NamedQuery(name = "CATEGORIE.findCategoriaByIdCategoria", query = "SELECT c FROM CATEGORIE c WHERE c.id = :id")
})
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID = "CATEGORIE.findCategoriaByIdCategoria";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private int id;

	private String categoria;

	public Categorie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Categorie) {
			Categorie categoria = (Categorie) obj;
			return categoria.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString(){
		return "Categoria [ID="+this.getId()+"] - Rag_Soc: " + this.getCategoria();
	}
}