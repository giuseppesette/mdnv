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
@Table(name = "SEDE_OPERATIVA")
@NamedQueries({
	@NamedQuery(name = "SEDE_OPERATIVA.findAllReferencedById", query = "SELECT so FROM SedeOperativa so WHERE so.idcliente = :id_cliente"),
	@NamedQuery(name = "SEDE_OPERATIVA.findSedeById", query = "SELECT so FROM SedeOperativa so WHERE so.id = :id_sedeoperativa")
})
public class SedeOperativa implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_ID_CLIENTE = "SEDE_OPERATIVA.findAllReferencedById";
	public static final String FIND_BY_ID_SEDEOP = "SEDE_OPERATIVA.findSedeById";
	
	//public static final String FIND_BY_ID_SEDEOP = "SELECT so FROM SEDE_OPERATIVA so WHERE so.id = :id_sedeoperativa";
	//public static final String FIND_MAX_ID_SEDEOP = "SELECT MAX(so.id)+1 FROM SEDE_OPERATIVA so";
	//public static final String COUNT_SEDEOP = "SELECT COUNT(so) FROM SEDE_OPERATIVA so";

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
	
	@Column(name = "prov", length=255)
	private String prov;
	
	@Column(name = "nazione", length=255)
	private String nazione;
	
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
		if (obj instanceof SedeOperativa) {
			SedeOperativa cli = (SedeOperativa) obj;
			return cli.getId() == id;
		}

		return false;
	}
}