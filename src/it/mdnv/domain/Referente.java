package it.mdnv.domain;

import java.io.Serializable;

public class Referente implements Serializable {

	private static final long serialVersionUID = 6882786992617394553L;

	public String nome;
	public String cognome;
	public String incarico;
	public String telefono;
	public String cellulare;
	public String mail;

	public Referente(String nome, String cognome, String incarico, String telefono, String cellulare, String mail) {
		this.nome = nome;
		this.cognome = cognome;
		this.incarico = incarico;
		this.telefono = telefono;
		this.cellulare = cellulare;
		this.mail = mail;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIncarico() {
		return incarico;
	}

	public void setIncarico(String incarico) {
		this.incarico = incarico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
/*
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (!(obj instanceof Car))
			return false;

		Car compare = (Car) obj;

		return compare.model.equals(this.model);
	}

	@Override
	public int hashCode() {
		int hash = 1;

		return hash * 31 + model.hashCode();
	}
*/
	@Override
	public String toString() {
		return "Referente{" + "nome=" + nome + ", cognome=" + cognome + ", incarico="
				+ incarico + ", telefono=" + telefono + ", cellulare=" + cellulare + ", mail=" + mail + "}";
	}
}