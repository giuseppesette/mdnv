package it.mdnv;
import it.mdnv.domain.LavoriReferenti;
import it.mdnv.service.DocumentService;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.TreeNode;

 
@ManagedBean(name="ttListaLavoriView") // tt --> tree table
@ViewScoped
public class ListaLavoriView implements Serializable {

	private static final long serialVersionUID = 769400030903238113L;

	private TreeNode root;
    
    private LavoriReferenti selectedDocument;
         
    @ManagedProperty("#{documentService}")
    private DocumentService service;
     
    @PostConstruct
    public void init() {
        //root = service.createDocuments();
        root = service.retrieveLavori();
    }
 
    public TreeNode getRoot() {
        return root;
    }
 
    public void setService(DocumentService service) {
        this.service = service;
    }
 
    public LavoriReferenti getSelectedDocument() {
        return selectedDocument;
    }
 
    public void setSelectedDocument(LavoriReferenti selectedDocument) {
        this.selectedDocument = selectedDocument;
    }
}
