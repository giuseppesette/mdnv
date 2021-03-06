package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the prod_fornitori database table.
 * 
 */
@Entity
@Table(name="prod_fornitori")
@NamedQueries({
    @NamedQuery(name=ProdFornitori.FIND_PRODFOR_BY_ID, query=ProdFornitori.FIND_PRODFOR_BY_ID_QUERY)
})
public class ProdFornitori implements Serializable {
	
    static final String FIND_PRODFOR_BY_ID_QUERY = "from ProdFornitori pf where pf.id = :id";
    public static final String FIND_PRODFOR_BY_ID = "FIND_PRODFOR_BY_ID";

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String codArticolo;

	private String codFornitore;

	private String costo1;

	private String costo2;

	private String costo3;

	private String costo4;

	private String costo5;

	private String costoInterno;

	private String dimensione;

	private int idFornitore;

	private int minimoOrdine;

	private String modPagamento;

	private String note;

	private String peso;

	private int rating;

	private String unitaDiMisura;

	//bi-directional many-to-many association to Prodotti
	@ManyToMany
	@JoinTable(
		name="rel_prodotti_fornitori"
		, joinColumns={
			@JoinColumn(name="IDProdottiFornitori")
			}
		, inverseJoinColumns={
			@JoinColumn(name="IDProdotti")
			}
		)
	private List<Prodotti> prodottis;

	public ProdFornitori() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodArticolo() {
		return this.codArticolo;
	}

	public void setCodArticolo(String codArticolo) {
		this.codArticolo = codArticolo;
	}

	public String getCodFornitore() {
		return this.codFornitore;
	}

	public void setCodFornitore(String codFornitore) {
		this.codFornitore = codFornitore;
	}

	public String getCosto1() {
		return this.costo1;
	}

	public void setCosto1(String costo1) {
		this.costo1 = costo1;
	}

	public String getCosto2() {
		return this.costo2;
	}

	public void setCosto2(String costo2) {
		this.costo2 = costo2;
	}

	public String getCosto3() {
		return this.costo3;
	}

	public void setCosto3(String costo3) {
		this.costo3 = costo3;
	}

	public String getCosto4() {
		return this.costo4;
	}

	public void setCosto4(String costo4) {
		this.costo4 = costo4;
	}

	public String getCosto5() {
		return this.costo5;
	}

	public void setCosto5(String costo5) {
		this.costo5 = costo5;
	}

	public String getCostoInterno() {
		return this.costoInterno;
	}

	public void setCostoInterno(String costoInterno) {
		this.costoInterno = costoInterno;
	}

	public String getDimensione() {
		return this.dimensione;
	}

	public void setDimensione(String dimensione) {
		this.dimensione = dimensione;
	}

	public int getIdFornitore() {
		return this.idFornitore;
	}

	public void setIdFornitore(int idFornitore) {
		this.idFornitore = idFornitore;
	}

	public int getMinimoOrdine() {
		return this.minimoOrdine;
	}

	public void setMinimoOrdine(int minimoOrdine) {
		this.minimoOrdine = minimoOrdine;
	}

	public String getModPagamento() {
		return this.modPagamento;
	}

	public void setModPagamento(String modPagamento) {
		this.modPagamento = modPagamento;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPeso() {
		return this.peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getUnitaDiMisura() {
		return this.unitaDiMisura;
	}

	public void setUnitaDiMisura(String unitaDiMisura) {
		this.unitaDiMisura = unitaDiMisura;
	}

	public List<Prodotti> getProdottis() {
		return this.prodottis;
	}

	public void setProdottis(List<Prodotti> prodottis) {
		this.prodottis = prodottis;
	}
	
	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProdFornitori) {
			ProdFornitori pf = (ProdFornitori) obj;
			return pf.getId() == id;
		}

		return false;
	}
	
	public String toString(){
		return "\n>>>>>>>>>>> ProdFornitori[id=" + id + " - idFornitore=" + idFornitore +
				"\n>>>>>>>>>>> codFornitore=" + codFornitore + " - codArticolo=" + codArticolo +
				"\n>>>>>>>>>>> rating=" + rating + " - dimensione=" + dimensione + " - UM=" + unitaDiMisura + 
				"\n>>>>>>>>>>> peso=" + peso + " - note=" + note + "]\n";
	}

}// end class