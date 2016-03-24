package it.mdnv.service;


import it.mdnv.domain.Fattura;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "fattureService")
@ApplicationScoped
public class FattureService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final static String[] numeroFatture;
	private final static String[] dataFatture;

	static {
		numeroFatture = new String[5];
		numeroFatture[0] = "FTR_001_2014";
		numeroFatture[1] = "FTR_002_2014";
		numeroFatture[2] = "FTR_003_2014";
		numeroFatture[3] = "FTR_004_2014";
		numeroFatture[4] = "FTR_005_2014";


		dataFatture = new String[5];
		dataFatture[0] = "02/12/2014";
		dataFatture[1] = "15/06/2014";
		dataFatture[2] = "25/01/2014";
		dataFatture[3] = "16/03/2014";
		dataFatture[4] = "15/08/2014";


	}

	public List<Fattura> createFattura(int size) {
		List<Fattura> list = new ArrayList<Fattura>();
		for (int i = 0; i < size; i++) {
			list.add(new Fattura(getRandomNumeroFattura(), getRandomDataFattura()));
		}

		return list;
	}

	private String getRandomNumeroFattura() {
		return numeroFatture[(int) (Math.random() * 5)];
	}

	private String getRandomDataFattura() {
		return dataFatture[(int) (Math.random() * 5)];
	}

	
	public List<String> getNumeroFatture() {
		return Arrays.asList(numeroFatture);
	}

	public List<String> getDataFatture() {
		return Arrays.asList(dataFatture);
	}
	
}
