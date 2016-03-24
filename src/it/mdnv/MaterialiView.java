package it.mdnv;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class MaterialiView {

	private String nomeMateriale;

    public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
	
	public String getNomeMateriale() {
		return nomeMateriale;
	}

	public void setNomeMateriale(String nomeMateriale) {
		this.nomeMateriale = nomeMateriale;
	}
	
	
	
} // end class
