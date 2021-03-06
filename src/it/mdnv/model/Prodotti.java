package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the prodotti database table.
 * 
 */
@Entity
@Table(name = "PRODOTTI")
@NamedQueries({
        @NamedQuery(name=Prodotti.FIND_BY_ID_PRODOTTO, query=Prodotti.FIND_BY_ID_PRODOTTO_QUERY),
        @NamedQuery(name=Prodotti.FIND_BY_NOME_PRODOTTO, query=Prodotti.FIND_BY_NOME_PRODOTTO_QUERY)
 })
public class Prodotti implements Serializable {
	private static final long serialVersionUID = 1L;

    static final String FIND_BY_NOME_PRODOTTO_QUERY = "from Prodotti p where p.prodotto = :prodotto";
    public static final String FIND_BY_NOME_PRODOTTO = "FIND_BY_NOME_PRODOTTO";
	
    static final String FIND_BY_ID_PRODOTTO_QUERY = "from Prodotti p where p.prodottiId = :id_prodotto";
    public static final String FIND_BY_ID_PRODOTTO = "FIND_BY_ID_PRODOTTO";

	@Id
	@Column(name="prodotti_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int prodottiId;

	private String codProdotto;

	private String descrizione;

	private String dwnDWGLibreria;

	private String dwnDWGProduzione;

	private String dwnl2d;

	private String dwnl3d;

	private String dwnlPdf;

	private String dwnlSchedaTecnica;

	private String materiale;

	private String pathImmagine;

	private String pathVrml;

	private String prodotto;

	private String um;
	//bi-directional many-to-one association to ProdFornitori	//@OneToMany(mappedBy="prodotti")	//private List<ProdFornitori> prodFornitoris;
	//bi-directional many-to-many association to ProdFornitori
	@ManyToMany(mappedBy="prodottis")
	private List<ProdFornitori> prodFornitoris;

	//bi-directional many-to-one association to Famiglia
	@ManyToOne
	@JoinColumn(name="id_famiglia")
	private Famiglia famiglia;

	//bi-directional many-to-one association to Tipologie
	@ManyToOne
	@JoinColumn(name="id_tipologia")
	private Tipologie tipologie;

	public Prodotti() {
	}

	public int getProdottiId() {
		return this.prodottiId;
	}

	public void setProdottiId(int prodottiId) {
		this.prodottiId = prodottiId;
	}

	public String getCodProdotto() {
		return this.codProdotto;
	}

	public void setCodProdotto(String codProdotto) {
		this.codProdotto = codProdotto;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getDwnDWGLibreria() {
		return this.dwnDWGLibreria;
	}

	public void setDwnDWGLibreria(String dwnDWGLibreria) {
		this.dwnDWGLibreria = dwnDWGLibreria;
	}

	public String getDwnDWGProduzione() {
		return this.dwnDWGProduzione;
	}

	public void setDwnDWGProduzione(String dwnDWGProduzione) {
		this.dwnDWGProduzione = dwnDWGProduzione;
	}

	public String getDwnl2d() {
		return this.dwnl2d;
	}

	public void setDwnl2d(String dwnl2d) {
		this.dwnl2d = dwnl2d;
	}

	public String getDwnl3d() {
		return this.dwnl3d;
	}

	public void setDwnl3d(String dwnl3d) {
		this.dwnl3d = dwnl3d;
	}

	public String getDwnlPdf() {
		return this.dwnlPdf;
	}

	public void setDwnlPdf(String dwnlPdf) {
		this.dwnlPdf = dwnlPdf;
	}

	public String getDwnlSchedaTecnica() {
		return this.dwnlSchedaTecnica;
	}

	public void setDwnlSchedaTecnica(String dwnlSchedaTecnica) {
		this.dwnlSchedaTecnica = dwnlSchedaTecnica;
	}

	public String getMateriale() {
		return this.materiale;
	}

	public void setMateriale(String materiale) {
		this.materiale = materiale;
	}

	public String getPathImmagine() {
		return this.pathImmagine;
	}

	public void setPathImmagine(String pathImmagine) {
		this.pathImmagine = pathImmagine;
	}

	public String getPathVrml() {
		return this.pathVrml;
	}

	public void setPathVrml(String pathVrml) {
		this.pathVrml = pathVrml;
	}

	public String getProdotto() {
		return this.prodotto;
	}

	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}

	public String getUm() {
		return this.um;
	}

	public void setUm(String um) {
		this.um = um;
	}

	public Famiglia getFamiglia() {
		return this.famiglia;
	}

	public void setFamiglia(Famiglia famiglia) {
		this.famiglia = famiglia;
	}

	public Tipologie getTipologie() {
		return this.tipologie;
	}

	public void setTipologie(Tipologie tipologie) {
		this.tipologie = tipologie;
	}
	
	public List<ProdFornitori> getProdFornitoris() {
		return this.prodFornitoris;
	}

	public void setProdFornitoris(List<ProdFornitori> prodFornitoris) {
		this.prodFornitoris = prodFornitoris;
	}
/***
	public ProdFornitori addProdFornitori(ProdFornitori prodFornitori) {
		getProdFornitoris().add(prodFornitori);
		prodFornitori.setProdotti(this);

		return prodFornitori;
	}

	public ProdFornitori removeProdFornitori(ProdFornitori prodFornitori) {
		getProdFornitoris().remove(prodFornitori);
		prodFornitori.setProdotti(null);

		return prodFornitori;
	}

***/
	
	
	@Override
	public int hashCode() {
		return getProdottiId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Prodotti) {
			Prodotti p = (Prodotti) obj;
			return p.getProdottiId() == prodottiId;
		}

		return false;
	}
	
	@Override
	public String toString(){
		return "\n>>>>>>>>>>> PRODOTTO [Nome Prodotto: " + prodotto + " id: " + prodottiId + " codProdotto: " + codProdotto + 
			    "\n>>>>>>>>>>> Famiglia{"+famiglia+"} " + 
				"\n>>>>>>>>>>> Tipologia{"+tipologie+"} " + 
			    "\n>>>>>>>>>>> Lista ProdFornitori{"+prodFornitoris+"}]\n";
	}

}