package it.mdnv.modelbkp;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
	//public static final String FIND_NEXT_AUTO_INCREMENT_ID_CLIENTE = "SELECT AUTO_INCREMENT FROM TABLES WHERE TABLE_SCHEMA = 'mdnv' AND TABLE_NAME = 'clienti'";
	public static final String FIND_BY_ID_CLIENTE = "CLIENTI.findClienteByIdCliente";
	
	public static final String FIND_BY_COD_CLIENTE = "SELECT c FROM CLIENTI c WHERE c.cod_cliente = :cod_cliente";
	//public static final String FIND_BY_ID_CLIENTE = "SELECT c FROM CLIENTI c WHERE c.id = :id_cliente";
	
	

	//public static final String COUNT_CLIENTI = "CLIENTI.count";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")	
	private int id;
	
	@Column(name = "cod_cliente", length=10)
	private String cod_cliente;
	
	@Column(name = "rating", columnDefinition="int(10) default 0")
	private int rating;
	
	@Column(name = "canale", length=255)
	private String canale;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inserimento", columnDefinition="date default NOW()")
	private Date data_inserimento;
	
	@Column(name = "ragione_sociale", length=255, nullable = false)
	private String ragione_sociale;
	
	@Column(name = "telefono1", length=20)
	private String telefono1;
	
	@Column(name = "telefono2", length=20)
	private String telefono2;
	
	@Column(name = "cellulare", length=20)
	private String cellulare;
	
	@Column(name = "fax", length=20)
	private String fax;
	
	@Column(name = "mail1", length=50)
	private String mail1;
	
	@Column(name = "mail2", length=50)
	private String mail2;
	
	@Column(name = "sito", length=150)
	private String sito;
	
	@Column(name = "indirizzo", length=255)
	private String indirizzo;
	
	@Column(name = "comune", length=255)
	private String comune;
	
	@Column(name = "cap", length=10)
	private String cap;
	
	@Column(name = "prov", length=255)
	private String prov;
	
	@Column(name = "nazione", length=255)
	private String nazione;
	
	@Column(name = "piva_vat", length=20)
	private String piva_vat;
	
	@Column(name = "cf", length=16)
	private String cf;
	
	@Column(name = "note", length=2000)
	private String note;
	
	@Column(name = "active")
	private String active = "1";
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(String cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCanale() {
		return canale;
	}

	public void setCanale(String canale) {
		this.canale = canale;
	}

	//@Date(fomat="dd/mm/yyyy");
	public Date getData_inserimento() {
		return data_inserimento;
	}

	public void setData_inserimento(Date data_inserimento) {
		this.data_inserimento = data_inserimento;
	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMail1() {
		return mail1;
	}

	public void setMail1(String mail1) {
		this.mail1 = mail1;
	}

	public String getMail2() {
		return mail2;
	}

	public void setMail2(String mail2) {
		this.mail2 = mail2;
	}

	public String getSito() {
		return sito;
	}

	public void setSito(String sito) {
		this.sito = sito;
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

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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
		return "Cliente[ID="+this.getId()+"] - COD_CLI: " + this.getCod_cliente() + 
				" - Rag_Soc: " + this.getRagione_sociale() + " - Canale: " + this.getCanale() + 
				" - Rating: " + this.getRating();
	}
}