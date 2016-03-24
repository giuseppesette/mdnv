package it.mdnv.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the assemblati database table.
 * 
 */
@Entity
@Table(name = "ASSEMBLATI")
@NamedQueries({
        @NamedQuery(name=Assemblati.FIND_BY_ID_ASSEMBLATO, query=Assemblati.FIND_BY_ID_ASSEMBLATO_QUERY),
        @NamedQuery(name=Assemblati.FIND_BY_NOME_ASSEMBLATO, query=Assemblati.FIND_BY_NOME_ASSEMBLATO_QUERY)
 })
public class Assemblati implements Serializable {
	private static final long serialVersionUID = 1L;

    static final String FIND_BY_NOME_ASSEMBLATO_QUERY = "from Assemblati a where a.assemblato = :assemblato";
    public static final String FIND_BY_NOME_ASSEMBLATO = "FIND_BY_NOME_ASSEMBLATO";
	
    static final String FIND_BY_ID_ASSEMBLATO_QUERY = "from Assemblati a where a.id = :id_assemblato";
    public static final String FIND_BY_ID_ASSEMBLATO = "FIND_BY_ID_ASSEMBLATO";

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String assemblato;

	private String codAssemblato;

	private String descrizione;

	private String dwnDWGLibreria;

	private String dwnDWGProduzione;

	private String dwnl2d;

	private String dwnl3d;

	private String dwnlPdf;

	private String dwnlSchedaTecnica;

	private String lavorazione;

	private String pathImmagine;

	private String pathVrml;

	private String um;
	
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


	
	public Assemblati() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssemblato() {
		return this.assemblato;
	}

	public void setAssemblato(String assemblato) {
		this.assemblato = assemblato;
	}

	public String getCodAssemblato() {
		return this.codAssemblato;
	}

	public void setCodAssemblato(String codAssemblato) {
		this.codAssemblato = codAssemblato;
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

	public String getLavorazione() {
		return this.lavorazione;
	}

	public void setLavorazione(String lavorazione) {
		this.lavorazione = lavorazione;
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
	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Assemblati) {
			Assemblati a = (Assemblati) obj;
			return a.getId() == id;
		}

		return false;
	}
	
	@Override
	public String toString(){
		return "\n>>>>>>>>>>> ASSEMBLATO [Nome Assemblato: " + assemblato + " id: " + id + " codAssemblato: " + codAssemblato + 
			    "\n>>>>>>>>>>> Famiglia{"+famiglia+"} " + 
				"\n>>>>>>>>>>> Tipologia{"+tipologie+"} " + 
			    "\n>>>>>>>>>>> Lista ProdFornitori{"+prodFornitoris+"}]\n";
	}

}