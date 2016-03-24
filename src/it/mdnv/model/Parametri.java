package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the parametri database table.
 * 
 */
@Entity
@Table(name = "PARAMETRI")
@NamedQueries({
	@NamedQuery(name = "PARAMETRI.findValoreByParametro", query = "SELECT p FROM Parametri p WHERE p.parametro = :parametro")
})
public class Parametri implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_VALORE_BY_PARAMETRO = "PARAMETRI.findValoreByParametro";
	
	@Id
	private String parametro;

	private String valore;

	public Parametri() {
	}

	public String getParametro() {
		return this.parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getValore() {
		return this.valore;
	}

	public void setValore(String valore) {
		this.valore = valore;
	}
	
	public String toString(){
		return "[param="+parametro+" - valore="+valore+"]";
	}

}