package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the contatti database table.
 * 
 */
@Entity
@Table(name = "CONTATTI")
@NamedQueries({
	//@NamedQuery(name = "CONTATTI.findClienteByCodCliente", query = "SELECT c FROM Clienti c WHERE c.codCliente = :cod_cliente"),
	@NamedQuery(name = "CONTATTI.findContattoByIdContatto", query = "SELECT c FROM Contatti c WHERE c.id = :id"),
	//@NamedQuery(name = "CONTATTI.count", query = "SELECT COUNT(c) FROM Clienti c"),
	@NamedQuery(name = "CONTATTI.maxId", query = "SELECT MAX(c.id) FROM Contatti c")
})
public class Contatti implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_MAX_ID_CONTATTI = "CONTATTI.maxId";
	public static final String FIND_BY_ID = "CONTATTI.findContattoByIdContatto";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String cap;

	private String cellulare;

	private String cf;

	private String comune;

	private String fax;

	private String indirizzo;

	private String mail1;

	private String mail2;

	private String nazione;

	@Column(name="piva_vat")
	private String pivaVat;

	private String prov;

	@Column(name="ragione_sociale")
	private String ragioneSociale;

	private String sito;

	private String telefono1;

	private String telefono2;

	public Contatti() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCellulare() {
		return this.cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getCf() {
		return this.cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getComune() {
		return this.comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getMail1() {
		return this.mail1;
	}

	public void setMail1(String mail1) {
		this.mail1 = mail1;
	}

	public String getMail2() {
		return this.mail2;
	}

	public void setMail2(String mail2) {
		this.mail2 = mail2;
	}

	public String getNazione() {
		return this.nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getPivaVat() {
		return this.pivaVat;
	}

	public void setPivaVat(String pivaVat) {
		this.pivaVat = pivaVat;
	}

	public String getProv() {
		return this.prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getRagioneSociale() {
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getSito() {
		return this.sito;
	}

	public void setSito(String sito) {
		this.sito = sito;
	}

	public String getTelefono1() {
		return this.telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return this.telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Contatti) {
			Contatti contatto = (Contatti) obj;
			return contatto.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString(){
		return "Contatto[ID="+this.getId()+"] - Rag_Soc: " + this.getRagioneSociale();
	}

} // end class