package it.mdnv.modelbkp;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "DATI_FATTURAZIONE")
@NamedQueries({
	@NamedQuery(name = "DATI_FATTURAZIONE.findAllReferencedById", query = "SELECT so FROM DatiFatturazione so WHERE so.idcliente = :id_cliente"),
	@NamedQuery(name = "DATI_FATTURAZIONE.findDatiFatturazioneById", query = "SELECT so FROM DatiFatturazione so WHERE so.id = :id_datiFatturazione")
})
public class DatiFatturazione implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_DATIFATT_BY_ID_CLIENTE = "DATI_FATTURAZIONE.findAllReferencedById";
	public static final String FIND_BY_DATIFATT = "DATI_FATTURAZIONE.findDatiFatturazioneById";


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")	
	private int id;
	
	@Column(name = "ragione_sociale", length=255, nullable = false)
	private String ragione_sociale;
	
	@Column(name = "nome", length=255)
	private String nome;
	
	@Column(name = "cognome", length=255)
	private String cognome;
	
	@Column(name = "indirizzo", length=255)
	private String indirizzo;
	
	@Column(name = "comune", length=255)
	private String comune;
	
	@Column(name = "cap", length=10)
	private String cap;
	
	@Column(name = "prov", length=2)
	private String prov;
	
	@Column(name = "nazione", length=255)
	private String nazione;
	
	@Column(name = "piva_vat", length=20)
	private String piva_vat;
	
	@Column(name = "iban", length=27)
	private String iban;
	
	@Column(name = "swift", length=20)
	private String swift;

	@Column(name = "cond_pag", length=150)
	private String cond_pag;
	
	@Column(name = "perc_ris")
	private String perc_ris = "0";
	
	@Column(name = "active")
	private String active = "1";

	@Column(name = "idcliente")	
	private int idcliente = -1;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProv() {
		return prov;
	}

	public void setProv(String prov) {
		this.prov = prov;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	
	

	public String getPiva_vat() {
		return piva_vat;
	}

	public void setPiva_vat(String piva_vat) {
		this.piva_vat = piva_vat;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	public String getCond_pag() {
		return cond_pag;
	}

	public void setCond_pag(String cond_pag) {
		this.cond_pag = cond_pag;
	}

	public String getPerc_ris() {
		return perc_ris;
	}

	public void setPerc_ris(String perc_ris) {
		this.perc_ris = perc_ris;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	public int getIdcliente() {
		return idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof DatiFatturazione) {
			DatiFatturazione cli = (DatiFatturazione) obj;
			return cli.getId() == id;
		}

		return false;
	}
}