package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "MATERIALI")
@NamedQueries({
	@NamedQuery(name = "MATERIALI.findAllReferencedByIdFornitore", query = "SELECT m FROM Materiali m WHERE m.idfornitore = :id_fornitore")
//	@NamedQuery(name = "DATI_FATTURAZIONE.findAllReferencedByIdCliente", query = "SELECT so FROM DatiFatturazione so WHERE so.idcliente = :id_cliente"),
//	@NamedQuery(name = "DATI_FATTURAZIONE.findDatiFatturazioneById", query = "SELECT so FROM DatiFatturazione so WHERE so.id = :id_datiFatturazione")
})
public class Materiali implements Serializable {
	private static final long serialVersionUID = 1L;

	
	public static final String FIND_MATERIALI_BY_ID_FORNITORE = "MATERIALI.findAllReferencedByIdFornitore";
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int idfornitore;

	private String lavorazioni;

	private String materiali;

	private String note;

	public Materiali() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdfornitore() {
		return this.idfornitore;
	}

	public void setIdfornitore(int idfornitore) {
		this.idfornitore = idfornitore;
	}

	public String getLavorazioni() {
		return this.lavorazioni;
	}

	public void setLavorazioni(String lavorazioni) {
		this.lavorazioni = lavorazioni;
	}

	public String getMateriali() {
		return this.materiali;
	}

	public void setMateriali(String materiali) {
		this.materiali = materiali;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}