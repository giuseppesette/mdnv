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
@Table(name = "REFERENTI")
@NamedQueries({
	@NamedQuery(name = "REFERENTI.findAllReferencedByIdCliente", query = "SELECT r FROM Referente r WHERE r.idcliente = :id_cliente"),
	@NamedQuery(name = "REFERENTI.findReferenteById", query = "SELECT r FROM Referente r WHERE r.id = :id_referente")
})
public class Referenti implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_REFERENTE_BY_ID_CLIENTE = "REFERENTI.findAllReferencedByIdCliente";
	public static final String FIND_REFERENTE_BY_ID = "REFERENTI.findReferenteById";


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")	
	private int id;
	
	@Column(name = "nome", length=255)
	private String nome;
	
	@Column(name = "cognome", length=255)
	private String cognome;
	
	@Column(name = "incarico", length=255)
	private String incarico;
	
	@Column(name = "telefono", length=255)
	private String telefono;
	
	@Column(name = "cellulare", length=255)
	private String cellulare;
	
	@Column(name = "mail", length=255)
	private String mail;
		
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

	public String getIncarico() {
		return incarico;
	}

	public void setIncarico(String incarico) {
		this.incarico = incarico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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
		if (obj instanceof Referenti) {
			Referenti cli = (Referenti) obj;
			return cli.getId() == id;
		}

		return false;
	}
}