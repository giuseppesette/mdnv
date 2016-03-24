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
	@Column(name = "id")	
	private int id;

	@Column(name = "nome_lavoro", length=255)
	private String nome_lavoro;
	
	@Column(name = "path_lavoro", length=1000)
	private String path_lavoro;
	
	@Column(name = "idcliente")	
	private int idcliente = -1;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome_lavoro() {
		return nome_lavoro;
	}
	public void setNome_lavoro(String nome_lavoro) {
		this.nome_lavoro = nome_lavoro;
	}
	public String getPath_lavoro() {
		return path_lavoro;
	}
	public void setPath_lavoro(String path_lavoro) {
		this.path_lavoro = path_lavoro;
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
		if (obj instanceof Lavori) {
			Lavori user = (Lavori) obj;
			return user.getId() == id;
		}

		return false;
	}
}