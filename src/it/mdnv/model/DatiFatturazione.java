package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the dati_fatturazione database table.
 * 
 */
@Entity
@Table(name="dati_fatturazione")
@NamedQueries({
	@NamedQuery(name = "DATI_FATTURAZIONE.findAllReferencedByIdFornitore", query = "SELECT so FROM DatiFatturazione so WHERE so.idfornitore = :id_fornitore"),
	@NamedQuery(name = "DATI_FATTURAZIONE.findAllReferencedByIdCliente", query = "SELECT so FROM DatiFatturazione so WHERE so.idcliente = :id_cliente"),
	@NamedQuery(name = "DATI_FATTURAZIONE.findDatiFatturazioneById", query = "SELECT so FROM DatiFatturazione so WHERE so.id = :id_datiFatturazione")
})
public class DatiFatturazione implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_DATIFATT_BY_ID_CLIENTE = "DATI_FATTURAZIONE.findAllReferencedByIdCliente";
	public static final String FIND_DATIFATT_BY_ID_FORNITORE = "DATI_FATTURAZIONE.findAllReferencedByIdFornitore";
	public static final String FIND_BY_DATIFATT = "DATI_FATTURAZIONE.findDatiFatturazioneById";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")	
	private int id;

	private String active;

	private String cap;

	private String cognome;

	private String comune;

	@Column(name="cond_pag")
	private String condPag;

	private String iban;

	private int idcliente;

	private int idfornitore;

	private String indirizzo;

	private String nazione;

	private String nome;

	@Column(name="perc_ris")
	private int percRis;

	@Column(name="piva_vat")
	private String pivaVat;

	private String prov;

	@Column(name="ragione_sociale")
	private String ragioneSociale;

	private String swift;

	public DatiFatturazione() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getComune() {
		return this.comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getCondPag() {
		return this.condPag;
	}

	public void setCondPag(String condPag) {
		this.condPag = condPag;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public int getIdfornitore() {
		return this.idfornitore;
	}

	public void setIdfornitore(int idfornitore) {
		this.idfornitore = idfornitore;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNazione() {
		return this.nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPercRis() {
		return this.percRis;
	}

	public void setPercRis(int percRis) {
		this.percRis = percRis;
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

	public String getSwift() {
		return this.swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
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