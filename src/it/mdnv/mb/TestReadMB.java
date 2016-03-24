package it.mdnv.mb;

import it.mdnv.facade.ParametriFacade;
import it.mdnv.model.Folder;
import it.mdnv.model.Parametri;
import it.mdnv.utils.Constants;

import java.io.File;
import java.net.MalformedURLException;
import java.net.UnknownHostException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@RequestScoped
@ManagedBean(name = "trbean")
public class TestReadMB extends AbstractMB implements Constants  {

	/** ****************************************************************************************** **/
	/** ****************************************************************************************** **/
	protected static Parametri pRadice = initializeRadice();
	protected static Parametri pAuth = initializeAuth();

	private static boolean isLocalhost() {
		boolean isLocalhost = false;
		try{
			HttpServletRequest origRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
			String serverName = origRequest.getServerName();
			System.out.println("[AbstractMB][isLocalhost] - serverName: " + serverName);
			if(serverName != null && !serverName.equalsIgnoreCase(""))
				if(serverName.contains("local"))
					isLocalhost = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return isLocalhost;
	} // END isLocalhost

	private static Parametri initializeRadice() {
		Parametri pr = null;
		try{
			ParametriFacade pf = new ParametriFacade();
			if(isLocalhost())
				pr = pf.findValoreByParametro(SQL_LOC_PAR_PATHCARTELLELAVORO);
			else
				pr = pf.findValoreByParametro(SQL_PAR_PATHCARTELLELAVORO);
			System.out.println("[AbstractMB][initializeRadice] - pr: " + pr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pr;
	} // END initializeRadice
	
	private static Parametri initializeAuth() {
		Parametri pa = null;
		try{
			ParametriFacade pf = new ParametriFacade();
			if(isLocalhost())
				pa = pf.findValoreByParametro(SQL_LOC_PAR_AUTHCARTELLELAVORO);
			else
				pa = pf.findValoreByParametro(SQL_PAR_AUTHCARTELLELAVORO);
			System.out.println("[AbstractMB][initializeAuth] - pa: " + pa);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pa;
	} // END initializeAuth
	
	/** ****************************************************************************************** **/
	/** ****************************************************************************************** **/
	/** SHOWCASE: http://www.primefaces.org/showcase/ui/data/tree/contextMenu.xhtml **/
	private TreeNode root;
    public TreeNode getRoot() {
        return root;
    }
    
	private TreeNode rootSmb = null;
    public TreeNode getRootSmb() {
        return rootSmb;
    }
    
    private TreeNode selectedNode;
    public TreeNode getSelectedNode() {
        return selectedNode;
    }
    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }
    public void displaySelectedSingle() {
        if(selectedNode != null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", ((Folder)selectedNode.getData()).getNomeFolder());
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public void deleteNode() { 
        selectedNode.getChildren().clear();
        selectedNode.getParent().getChildren().remove(selectedNode);
        selectedNode.setParent(null);
         
        selectedNode = null;
    }
    
    /** END SHOWCASE        */
	/** *** *** *** *** *** */

    //private String userSmb = "modonovoserver:modonovo2014";
    //private String defaultRadice = "Z:\\5_LAVORI";
	//public String rootFolder;

    private String userSmb = null;
    private String defaultRadice = null;
	/**
	public TestReadMB(){
		System.out.println("");
		System.out.println("[TestReadMB][constructor]");
		System.out.println("");
	}
	**/
	
	@PostConstruct
	public void init() {
		System.out.println("");
		System.out.println("[TestReadMB][init] - @PostConstruct ");
		
		String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		System.out.println("[TestReadMB][init] - @PostConstruct viewId..........: " + viewId);
		
		defaultRadice = "L:\\LAVORO\\70_MD\\5_LAVORI\\LAVORI 2015";
		System.out.println("[TestReadMB][init] - @PostConstruct defaultRadice...: " + defaultRadice);
		
		if(viewId != null && !viewId.equalsIgnoreCase("") && !viewId.contains("Smb"))
			root = retrieveFolders();
		else
			try {
				
				// Ambiente ITR
				//defaultRadice = "smb://10.10.10.244/SCBatch/";
				//userSmb = "Administrator:NormTest1";
				
				// Ambiente MDNV
				//defaultRadice = "smb://192.168.200.7/sp_1/dati/5_LAVORI/";
				//userSmb = "modonovoserver:modonovo2014";
				
				defaultRadice = pRadice.getValore();
				userSmb = pAuth.getValore();
				System.out.println("[TestReadMB][init] - @PostConstruct defaultRadice...: " + defaultRadice);
				System.out.println("[TestReadMB][init] - @PostConstruct userSmb.........: " + userSmb);
				
				rootSmb = retrieveSmbFolders();
				System.out.println("[TestReadMB][init] - @PostConstruct rootSmb.........: " + rootSmb);
				
			} catch (SmbException e) {
				System.out.println("[TestReadMB][init] - @PostConstruct SmbException: " + e.getMessage());
				e.printStackTrace();
			} catch (MalformedURLException e) {
				System.out.println("[TestReadMB][init] - @PostConstruct MalformedURLException: " + e.getMessage());
				e.printStackTrace();
			} catch (UnknownHostException e) {
				System.out.println("[TestReadMB][init] - @PostConstruct UnknownHostException: " + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("[TestReadMB][init] - @PostConstruct Exception: " + e.getMessage());
				e.printStackTrace();
			}

			//rootSmb = null;

		System.out.println("");
	} // END init

	
	private TreeNode retrieveFolders() {
		// http://www.mkyong.com/java/how-to-traverse-a-directory-structure-in-java/
		File rootFolder = new File(defaultRadice);
		Folder f = new Folder();
		f.setNomeFolder(rootFolder.getName());
		
		TreeNode root = new DefaultTreeNode(f, null);
		displayIt(root, rootFolder);
		return root;
	}
	
	public static void displayIt(TreeNode root, File node){
		 
		Folder fnode = new Folder();
		fnode.setNomeFolder(node.getName());
		
		System.out.println("[TestReadMB][displayIt] -fnode.........: " + fnode.getNomeFolder());
		if(!fnode.getNomeFolder().startsWith("._") && !fnode.getNomeFolder().startsWith(".DS")){
			
			//Set icon
			if(!node.isDirectory()){
				fnode.setType(ATTR_ICON_DOCUMENT);
			} else 
				fnode.setType(ATTR_ICON_FOLDER);
	
			TreeNode work1 = new DefaultTreeNode(fnode, root);		
			//System.out.println(fnode);
			
			//System.out.println(node.getAbsoluteFile());
			if(node.isDirectory()){
				String[] subNote = node.list();
				for(String filename : subNote){
					displayIt(work1, new File(node, filename));
				}
			}
		}
	} // END displayIt
	
	private TreeNode retrieveSmbFolders() throws SmbException, MalformedURLException, UnknownHostException {
		// http://www.mkyong.com/java/how-to-traverse-a-directory-structure-in-java/

		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(userSmb);
		
		SmbFile rootFolder = new SmbFile(defaultRadice, auth);
		Folder f = new Folder();
		TreeNode root = null;
		if(rootFolder != null){
			/**
			try {
				System.out.println("Permission Actions...: " + rootFolder.getPermission().getActions());
				System.out.println("ContentType..........: " + rootFolder.getContentType());
				System.out.println("DfsPath..............: " + rootFolder.getDfsPath());
				System.out.println("UncPath..............: " + rootFolder.getUncPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			**/
			
			f.setNomeFolder(rootFolder.getName());
			
			root = new DefaultTreeNode(f, null);
			displayItSmb(root, rootFolder);
		}
		
		//System.out.println("[retrieveSmbFolders] - fine ciclo ricorsivo: " + root.getRowKey());
		
		return root;
	} // END retrieveSmbFolders


	public static void displayItSmb(TreeNode root, SmbFile node) throws SmbException, MalformedURLException, UnknownHostException{
		
		//System.out.println("[displayIt(SmbFile)] - node name: " + node.getName());
		
		Folder fnode = new Folder();
		fnode.setNomeFolder(node.getName());
		
		//Set icon
		if(!node.isDirectory()){
			fnode.setType(ATTR_ICON_DOCUMENT);
		} else 
			fnode.setType(ATTR_ICON_FOLDER);

		TreeNode work1 = new DefaultTreeNode(fnode, root);
		
		if(node.isDirectory()){
			//System.out.println("dir: " + node.getName());
			SmbFile[] dirs = node.listFiles();
			for(SmbFile sf : dirs){
				displayItSmb(work1, sf);
			}
		}
	} // END displayItSmb
	
	/**
	private String leggiParametri(){
		System.out.println("");
		System.out.println("[TestReadMB][leggiParametri] - ");
		//String radice = null;
		try {
			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			String param = params.get("radice");
			System.out.println("[TestReadMB][leggiParametri] - param[radice]: " + param);
			if(param != null && !param.equalsIgnoreCase(""))
				rootFolder = param;
			else
				rootFolder = defaultRadice;
			
			System.out.println("[TestReadMB][leggiParametri] - rootFolder: " +rootFolder);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("");
		return rootFolder;
	} // END leggiParametri
	

	private void leggiContenutoFolder(String radice){
		System.out.println("");
		System.out.println("[TestReadMB][leggiContenutoFolder] - radice: " + radice);
		File listaFileFolder = new File(radice);
		if(listaFileFolder.isDirectory()){
			if(listaFileFolder.listFiles() != null && listaFileFolder.listFiles().length > 0){
				listaFolder = new ArrayList<Folder>();
				for(int i=0; i < listaFileFolder.listFiles().length; i++){
					Folder f = new Folder();
					try{
						File lF = listaFileFolder.listFiles()[i];
						if(lF != null && lF.isDirectory()){
							System.out.println("[TestReadMB][leggiContenutoFolder] - folder: " + lF.getName());
							f.setNomeFolder(lF.getName());
							listaFolder.add(f);
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				} // END FOR
			} // END IF
		} // END IF
		System.out.println("");
	} // END leggiContenutoFolder

	private List<Folder> listaFolder;
	private Folder selectedFolder;
	
	public void onRowFolderSelect(SelectEvent event) {
		System.out.println("");
		System.out.println("[TestReadMB][onRowFolderSelect] - selectedFolder: " + selectedFolder.getNomeFolder());
		try{
			String nomeFolder = ((Folder) event.getObject()).getNomeFolder();
			System.out.println("[TestReadMB][onRowFolderSelect] - Folder Selezionato: " + nomeFolder);
	
			// Leggo la Root dei Folder Lavoro
			if(listaFolder != null && listaFolder.size() > 0){
				Iterator<Folder> iF = listaFolder.iterator();
				while(iF.hasNext()){
					Folder iFolder = iF.next();
					if(iFolder.getNomeFolder().equalsIgnoreCase(nomeFolder)){
						this.setSelectedFolder(iFolder);
					}
				}
			}
			System.out.println("[TestReadMB][onRowFolderSelect] - selectedFolder: " + selectedFolder);
			System.out.println("");
		}catch(Exception e){
			displayErrorMessageToUser("Errore: "+e.getMessage());
		}
	}// END onRowFolderSelect
	
	public void visualizzaCartella() {
		System.out.println("");
		System.out.println("[TestReadMB][visualizzaCartella] - this.getSelectedFolder(): " + this.getSelectedFolder().getNomeFolder());
		try {
			//Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
			//String radice = params.get("radice");
			String radice = root + System.getProperty(SYSPATH) + this.getSelectedFolder().getNomeFolder();
			System.out.println("[TestReadMB][visualizzaCartella] - radice: " + radice);
			
			// Ricarica la lista col contenuto della cartella selezionata
			leggiContenutoFolder(radice);
			
			FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("radice", radice);
			
			//FacesContext.getCurrentInstance().getExternalContext().redirect("test.xhtml?idLavoro=" + this.getSelectedFolder().getNomeFolder());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("");
	} // END visualizzaCartella
	
	public List<Folder> getListaFolder() {
		return listaFolder;
	}

	public Folder getSelectedFolder() {
		return selectedFolder;
	}

	public void setSelectedFolder(Folder selectedFolder) {
		this.selectedFolder = selectedFolder;
	}
	
	**/
	
} // end class
