package it.mdnv.service;

import java.io.Serializable;

import it.mdnv.domain.LavoriReferenti;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;


@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService implements Serializable {
    
	private static final long serialVersionUID = 1262692929783322379L;

	public TreeNode retrieveLavori() {
		TreeNode root = new DefaultTreeNode(new LavoriReferenti("Works", "", "Folder"), null);
		
		TreeNode work1 = new DefaultTreeNode(new LavoriReferenti("2014_001_NomeLavoro1", "Breve descrizione Lavoro1", "Folder"), root);
	    TreeNode ref11 = new DefaultTreeNode(new LavoriReferenti("Mario Rossi", "-", "Folder"), work1);
	    TreeNode ref12 = new DefaultTreeNode(new LavoriReferenti("Giuseppe Verdi", "-", "Folder"), work1);
		
		TreeNode work2 = new DefaultTreeNode(new LavoriReferenti("2014_002_NomeLavoro2", "Breve descrizione Lavoro2", "Folder"), root);
		TreeNode ref21 = new DefaultTreeNode(new LavoriReferenti("Andrea Bianchi", "-", "Folder"), work2);
		
		TreeNode work3 = new DefaultTreeNode(new LavoriReferenti("2014_003_NomeLavoro3", "Breve descrizione Lavoro3", "Folder"), root);
		TreeNode ref31 = new DefaultTreeNode(new LavoriReferenti("Mario Rossi", "-", "Folder"), work3);
		
		TreeNode work4 = new DefaultTreeNode(new LavoriReferenti("2014_004_NomeLavoro4", "Breve descrizione Lavoro4", "Folder"), root);
		TreeNode ref41 = new DefaultTreeNode(new LavoriReferenti("Giuseppe Verdi", "-", "Folder"), work4);
		
		return root;
	}
	
	public TreeNode createDocuments() {
       TreeNode root = new DefaultTreeNode(new LavoriReferenti("Files", "-", "Folder"), null);
        
       //TreeNode documents = new DefaultTreeNode(new Document("Documents", "-", "Folder"), root);
       TreeNode pictures = new DefaultTreeNode(new LavoriReferenti("Pictures", "-", "Folder"), root);
       //TreeNode movies = new DefaultTreeNode(new Document("Movies", "-", "Folder"), root);
        
       //TreeNode work = new DefaultTreeNode(new Document("Work", "-", "Folder"), documents);
       //TreeNode primefaces = new DefaultTreeNode(new Document("PrimeFaces", "-", "Folder"), documents);
        
       //Documents
       //TreeNode expenses = new DefaultTreeNode("document", new Document("Expenses.doc", "30 KB", "Word Document"), work);
       //TreeNode resume = new DefaultTreeNode("document", new Document("Resume.doc", "10 KB", "Word Document"), work);
       //TreeNode refdoc = new DefaultTreeNode("document", new Document("RefDoc.pages", "40 KB", "Pages Document"), primefaces);
        
       //Pictures
       TreeNode barca = new DefaultTreeNode("picture", new LavoriReferenti("barcelona.jpg", "30 KB", "JPEG Image"), pictures);
       TreeNode primelogo = new DefaultTreeNode("picture", new LavoriReferenti("logo.jpg", "45 KB", "JPEG Image"), pictures);
       TreeNode optimus = new DefaultTreeNode("picture", new LavoriReferenti("optimusprime.png", "96 KB", "PNG Image"), pictures);
        
       //Movies
       //TreeNode pacino = new DefaultTreeNode(new Document("Al Pacino", "-", "Folder"), movies);
       //TreeNode deniro = new DefaultTreeNode(new Document("Robert De Niro", "-", "Folder"), movies);
        
       //TreeNode scarface = new DefaultTreeNode("mp3", new Document("Scarface", "15 GB", "Movie File"), pacino);
       //TreeNode carlitosWay = new DefaultTreeNode("mp3", new Document("Carlitos' Way", "24 GB", "Movie File"), pacino);
        
       //TreeNode goodfellas = new DefaultTreeNode("mp3", new Document("Goodfellas", "23 GB", "Movie File"), deniro);
       //TreeNode untouchables = new DefaultTreeNode("mp3", new Document("Untouchables", "17 GB", "Movie File"), deniro);
        
       return root;
   }
} // end class
