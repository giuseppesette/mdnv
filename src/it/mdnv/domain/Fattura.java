package it.mdnv.domain;

import java.io.Serializable;

public class Fattura implements Serializable {
	private static final long serialVersionUID = 1L;
	public String numeroFattura;
	public String dataFattura;


	public Fattura(String numeroFattura, String dataFattura) {
		this.numeroFattura = numeroFattura;
		this.dataFattura = dataFattura;
	}


	public String getNumeroFattura() {
		return numeroFattura;
	}


	public void setNumeroFattura(String numeroFattura) {
		this.numeroFattura = numeroFattura;
	}


	public String getDataFattura() {
		return dataFattura;
	}


	public void setDataFattura(String dataFattura) {
		this.dataFattura = dataFattura;
	}


	@Override
	public String toString() {
		return "Fattura{" + "numeroFattura=" + numeroFattura + ", dataFattura=" + dataFattura + "}";
	}
}