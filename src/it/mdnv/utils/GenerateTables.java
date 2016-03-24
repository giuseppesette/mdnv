package it.mdnv.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class GenerateTables {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mdnv");

	}

}
