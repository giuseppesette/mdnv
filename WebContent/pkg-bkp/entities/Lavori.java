package it.mdnv.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lavori database table.
 * 
 */
@Entity
@Table(name = "LAVORI")
@NamedQueries({
	@NamedQuery(name = "LAVORI.findFolderLavoroByIdCliente", query = "select fl from FolderLavori fl where fl.idcliente = :id_cliente"),
	@NamedQuery(name = "LAVORI.findFolderLavoroById", query = "select fl from FolderLavori fl where fl.id = :id_lavoro"),
	@NamedQuery(name = "LAVORI.maxId", query = "SELECT MAX(fl.id)+1 FROM FolderLavori fl")
})
public class Lavori implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_MAX_ID_LAVORO = "LAVORI.maxId";
	public static final String FIND_LAVORO_BY_ID_CLIENTE = "LAVORI.findFolderLavoroByIdCliente";
	public static final String FIND_BY_ID_LAVORO = "LAVORI.findFolderLavoroById";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String active;

	private int idcliente;

	@Column(name="nome_lavoro")
	private String nomeLavoro;

	@Column(name="path_lavoro")
	private String pathLavoro;

	private String stato;

	//bi-directional many-to-many association to Referenti
	@ManyToMany
	@JoinTable(
		name="rel_referenti_lavori"
		, joinColumns={
			@JoinColumn(name="IDLavori")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDReferenti")
			}
		)
	private List<Referenti> referentis;

	public Lavori() {
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

	public int getIdcliente() {
		return this.idcliente;
	}

	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}

	public String getNomeLavoro() {
		return this.nomeLavoro;
	}

	public void setNomeLavoro(String nomeLavoro) {
		this.nomeLavoro = nomeLavoro;
	}

	public String getPathLavoro() {
		return this.pathLavoro;
	}

	public void setPathLavoro(String pathLavoro) {
		this.pathLavoro = pathLavoro;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
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
		if (obj instanceof Lavori) {
			Lavori user = (Lavori) obj;
			return user.getId() == id;
		}

		return false;
	}

}