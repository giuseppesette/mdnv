package it.mdnv.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the sede_operativa database table.
 * 
 */
@Entity
@Table(name="sede_operativa")
@NamedQueries({
	@NamedQuery(name = "SEDE_OPERATIVA.findAllReferencedById", query = "SELECT so FROM SedeOperativa so WHERE so.idcliente = :id_cliente"),
	@NamedQuery(name = "SEDE_OPERATIVA.findSedeById", query = "SELECT so FROM SedeOperativa so WHERE so.id = :id_sedeoperativa")
})
public class SedeOperativa implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_BY_ID_CLIENTE = "SEDE_OPERATIVA.findAllReferencedById";
	public static final String FIND_BY_ID_SEDEOP = "SEDE_OPERATIVA.findSedeById";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String active;

	private String cap;

	private String cognome;

	private String comune;

	private int idcliente;

	private String indirizzo;

	private String nazione;

	private String nome;

	private String prov;

	@Column(name="ragione_sociale")
	private String ragioneSociale;

	public SedeOperativa() {
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

	public int getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
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

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SedeOperativa) {
			SedeOperativa sp = (SedeOperativa) obj;
			return sp.getId() == id;
		}

		return false;
	}
}