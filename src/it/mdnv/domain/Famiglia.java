package it.mdnv.domain;

import java.io.Serializable;

public class Famiglia implements Serializable {
	private static final long serialVersionUID = 1L;
	public String codice;
	public String nome;


	public Famiglia(String codice, String nome) {
		this.codice = codice;
		this.nome = nome;
	}

	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Famiglia{" + "codice=" + codice + ", nome=" + nome + "}";
	}
}