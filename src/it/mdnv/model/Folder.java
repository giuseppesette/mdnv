package it.mdnv.model;

import java.io.Serializable;

public class Folder implements Serializable {

	private static final long serialVersionUID = 1775567859431050342L;
	
	public String type;
	public String nomeFolder;
	public String counter;

	public String getNomeFolder() {
		return nomeFolder;
	}

	public void setNomeFolder(String nomeFolder) {
		this.nomeFolder = nomeFolder;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCounter() {
		return counter;
	}

	public void setCounter(String counter) {
		this.counter = counter;
	}
	
	
	
	/*
	public String toString(){
		return "[" + nomeFolder + " --> type-icon: " + type;
	}
	*/
}
