package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the fornitori database table.
 * 
 */
@Entity
@Table(name = "FORNITORI")
@NamedQueries({
	@NamedQuery(name = "FORNITORI.findFornitoreByIdFornitore", query = "SELECT f FROM Fornitori f WHERE f.id = :id_fornitore")
})
public class Fornitori implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ID_FORNITORE = "FORNITORI.findFornitoreByIdFornitore";
	/**
	public static final String FIND_MAX_ID_CLIENTE = "CLIENTI.maxId";
	public static final String FIND_BY_ID_CLIENTE = "CLIENTI.findClienteByIdCliente";
	public static final String FIND_BY_COD_CLIENTE = "SELECT c FROM Clienti c WHERE c.cod_cliente = :cod_cliente";
	**/
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String active;

	private String cap;

	private String cellulare;

	private String cf;

	@Column(name="cod_fornitore")
	private String codFornitore;

	private String codEan;

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

	private String sito;

	private String telefono1;

	private String telefono2;

	//bi-directional many-to-many association to Referenti
	@ManyToMany
	@JoinTable(
		name="rel_referenti_fornitori"
		, joinColumns={
			@JoinColumn(name="IDFornitori")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDReferenti")
			}
		)
	private List<Referenti> referentis;

	public Fornitori() {
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

	public String getCodFornitore() {
		return this.codFornitore;
	}

	public void setCodFornitore(String codFornitore) {
		this.codFornitore = codFornitore;
	}

	public String getCodEan() {
		return this.codEan;
	}

	public void setCodEan(String codEan) {
		this.codEan = codEan;
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
			Fornitori frn = (Fornitori) obj;
			return frn.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString(){
		return "Fornitore[ID="+this.getId()+"] - COD_FRN: " + this.getCodFornitore() + 
				" - Rag_Soc: " + this.getRagioneSociale();
	}
}