package it.mdnv.service;

import it.mdnv.domain.Referente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "referentiService")
@ApplicationScoped
public class ReferentiService implements Serializable {
	
	private static final long serialVersionUID = 5252494611550207410L;

	private final static String[] nomi;
	private final static String[] cognomi;
	
	private final static String[] incarichi;
	private final static String[] telefoni;
	private final static String[] cellulari;
	private final static String[] mails;

	static {
		nomi = new String[5];
		nomi[0] = "Antonio";
		nomi[1] = "Mario";
		nomi[2] = "Giuseppe";
		nomi[3] = "Paolo";
		nomi[4] = "Luca";


		cognomi = new String[5];
		cognomi[0] = "Bianchi";
		cognomi[1] = "Verdi";
		cognomi[2] = "Rossi";
		cognomi[3] = "Esposito";
		cognomi[4] = "Di Bari";

		incarichi = new String[5];
		incarichi[0] = "Responsabile";
		incarichi[1] = "Dirigente";
		incarichi[2] = "Tecnico";
		incarichi[3] = "Commerciale";
		incarichi[4] = "Manager";
		
		telefoni = new String[5];
		telefoni[0] = "02 845633";
		telefoni[1] = "080 15689415";
		telefoni[2] = "091 4583123";
		telefoni[3] = "06 915783520";
		telefoni[4] = "0773 4545463";
		
		cellulari = new String[5];
		cellulari[0] = "333 15489269";
		cellulari[1] = "348 51536625";
		cellulari[2] = "335 12485566";
		cellulari[3] = "329 51651654";
		cellulari[4] = "368 46489421";
		
		mails = new String[5];
		mails[0] = "mail1@domains.com";
		mails[1] = "mail2@domains.com";
		mails[2] = "mail3@domains.com";
		mails[3] = "mail4@domains.com";
		mails[4] = "mail5@domains.com";
	}

	public List<Referente> createReferente(int size) {
		List<Referente> list = new ArrayList<Referente>();
		for (int i = 0; i < size; i++) {
			list.add(new Referente(getRandomNomi(), getRandomCognomi(),
					getRandomIncarichi(), getRandomTelefoni(), getRandomCellulari(),
					getRandomMails()));
		}

		return list;
	}

	private String getRandomNomi() {
		return nomi[(int) (Math.random() * 5)];
	}

	private String getRandomCognomi() {
		return cognomi[(int) (Math.random() * 5)];
	}

	private String getRandomIncarichi() {
		return incarichi[(int) (Math.random() * 5)];
	}
	
	private String getRandomTelefoni() {
		return telefoni[(int) (Math.random() * 5)];
	}
	
	private String getRandomCellulari() {
		return cellulari[(int) (Math.random() * 5)];
	}
	
	private String getRandomMails() {
		return mails[(int) (Math.random() * 5)];
	}
	
	public List<String> getNomi() {
		return Arrays.asList(nomi);
	}

	public List<String> getCognomi() {
		return Arrays.asList(cognomi);
	}
	
	public List<String> getIncarichi() {
		return Arrays.asList(incarichi);
	}
	public List<String> getTelefoni() {
		return Arrays.asList(telefoni);
	}
	public List<String> getCellulari() {
		return Arrays.asList(cellulari);
	}
	public List<String> getMails() {
		return Arrays.asList(mails);
	}
}
