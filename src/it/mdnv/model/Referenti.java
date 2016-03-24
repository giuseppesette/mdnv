package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the referenti database table.
 * 
 */
@Entity
@Table(name = "REFERENTI")
@NamedQueries({
	@NamedQuery(name = "REFERENTI.findAllReferencedByIdCliente", query = "SELECT r FROM Referenti r WHERE r.idcliente = :id_cliente"),
	@NamedQuery(name = "REFERENTI.findReferenteById", query = "SELECT r FROM Referenti r WHERE r.id = :id_referente")
})
public class Referenti implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String FIND_REFERENTE_BY_ID_CLIENTE = "REFERENTI.findAllReferencedByIdCliente";
	public static final String FIND_REFERENTE_BY_ID = "REFERENTI.findReferenteById";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String active;

	private String cellulare;

	private String cognome;

	private int idcliente;

	private int idfornitore;

	private String incarico;

	private String mail;

	private String nome;

	private String telefono;

	//bi-directional many-to-many association to Clienti
	@ManyToMany(mappedBy="referentis")
	private List<Clienti> clientis;

	//bi-directional many-to-many association to Lavori
	@ManyToMany(mappedBy="referentis")
	private List<Lavori> lavoris;

	//bi-directional many-to-many association to Fornitori
	@ManyToMany(mappedBy="referentis")
	private List<Fornitori> fornitoris;

	public Referenti() {
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

	public String getCellulare() {
		return this.cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
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

	public String getIncarico() {
		return this.incarico;
	}

	public void setIncarico(String incarico) {
		this.incarico = incarico;
	}

	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<Clienti> getClientis() {
		return this.clientis;
	}

	public void setClientis(List<Clienti> clientis) {
		this.clientis = clientis;
	}

	public List<Lavori> getLavoris() {
		return this.lavoris;
	}

	public void setLavoris(List<Lavori> lavoris) {
		this.lavoris = lavoris;
	}
	
	public List<Fornitori> getFornitoris() {
		return this.fornitoris;
	}

	public void setFornitoris(List<Fornitori> fornitoris) {
		this.fornitoris = fornitoris;
	}
	
	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Referenti) {
			Referenti cli = (Referenti) obj;
			return cli.getId() == id;
		}

		return false;
	}

}