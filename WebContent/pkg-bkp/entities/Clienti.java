package it.mdnv.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the clienti database table.
 * 
 */
@Entity
@Table(name = "CLIENTI")

@NamedQueries({
	@NamedQuery(name = "CLIENTI.findClienteByCodCliente", query = "SELECT c FROM Cliente c WHERE c.cod_cliente = :cod_cliente"),
	@NamedQuery(name = "CLIENTI.findClienteByIdCliente", query = "SELECT c FROM Cliente c WHERE c.id = :id_cliente"),
	@NamedQuery(name = "CLIENTI.count", query = "SELECT COUNT(c) FROM Cliente c"),
	//@NamedQuery(name = "CLIENTI.maxId", query = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'mdnv' AND TABLE_NAME = 'clienti'")
	@NamedQuery(name = "CLIENTI.maxId", query = "SELECT MAX(c.id) FROM Cliente c")
})
public class Clienti implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_MAX_ID_CLIENTE = "CLIENTI.maxId";
	public static final String FIND_BY_ID_CLIENTE = "CLIENTI.findClienteByIdCliente";
	public static final String FIND_BY_COD_CLIENTE = "SELECT c FROM CLIENTI c WHERE c.cod_cliente = :cod_cliente";
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String active;

	private String canale;

	private String cap;

	private String cellulare;

	private String cf;

	@Column(name="cod_cliente")
	private String codCliente;

	private String comune;

	@Temporal(TemporalType.DATE)
	@Column(name="data_inserimento")
	private Date dataInserimento;

	private String fax;

	private String indirizzo;

	private String mail1;

	private String mail2;

	private String nazione;

	private String note;

	@Column(name="piva_vat")
	private String pivaVat;

	private String prov;

	@Column(name="ragione_sociale")
	private String ragioneSociale;

	private int rating;

	private String sito;

	private String telefono1;

	private String telefono2;

	//bi-directional many-to-many association to Referenti
	@ManyToMany
	@JoinTable(
		name="rel_referenti_clienti"
		, joinColumns={
			@JoinColumn(name="IDClienti")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDReferenti")
			}
		)
	private List<Referenti> referentis;

	public Clienti() {
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

	public String getCanale() {
		return this.canale;
	}

	public void setCanale(String canale) {
		this.canale = canale;
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

	public String getCodCliente() {
		return this.codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getComune() {
		return this.comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public Date getDataInserimento() {
		return this.dataInserimento;
	}

	public void setDataInserimento(Date dataInserimento) {
		this.dataInserimento = dataInserimento;
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

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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

	public List<Referenti> getReferentis() {
		return this.referentis;
	}

	public void setReferentis(List<Referenti> referentis) {
		this.referentis = referentis;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Clienti) {
			Clienti cli = (Clienti) obj;
			return cli.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString(){
		return "Cliente[ID="+this.getId()+"] - COD_CLI: " + this.getCodCliente() + 
				" - Rag_Soc: " + this.getRagioneSociale() + " - Canale: " + this.getCanale() + 
				" - Rating: " + this.getRating();
	}
}