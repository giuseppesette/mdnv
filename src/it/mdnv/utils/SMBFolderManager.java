package it.mdnv.utils;

import it.mdnv.facade.ParametriFacade;
import it.mdnv.model.Folder;
import it.mdnv.model.Parametri;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@SuppressWarnings("all")
public class SMBFolderManager implements Constants {
	
	private static SMBFolderManager sMBFolderManager;
	
	private static Parametri pRadice = initializeRadice();
	private static Parametri pAuth = initializeAuth();


	private static Parametri initializeRadice() {
		Parametri pr = null;
		try{
			ParametriFacade pf = new ParametriFacade();
			pr = pf.findValoreByParametro(SQL_PAR_PATHCARTELLELAVORO);
			System.out.println("[SMBFolderManager][initializeRadice] - pr: " + pr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pr;
	} // END initializeRadice
	
	private static Parametri initializeAuth() {
		Parametri pa = null;
		try{
			ParametriFacade pf = new ParametriFacade();
			pa = pf.findValoreByParametro(SQL_PAR_AUTHCARTELLELAVORO);
			System.out.println("[SMBFolderManager][initializeAuth] - pa: " + pa);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pa;
	} // END initializeAuth
	
	/**
	public static SMBFolderManager getInstance() throws Exception {
		if(sMBFolderManager == null)
			sMBFolderManager = new SMBFolderManager();
		return sMBFolderManager;
	}
	**/
	
	public static int nextSequenceInFolder(){
		int sequence = -1;
		try {
			System.out.println("[SMBFolderManager][nextSequenceInFolder] - pRadice: " + pRadice.getValore());
			System.out.println("[SMBFolderManager][nextSequenceInFolder] - pAuth: " + pAuth.getValore());
			
			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(pAuth.getValore());
			SmbFile rootFolder = new SmbFile(pRadice.getValore(), auth);
			Folder f = new Folder();
			TreeNode root = null;
			if(rootFolder != null){
				f.setNomeFolder(rootFolder.getName());
				f.setCounter(retrieveFolderCounter(rootFolder.getName()));
				root = new DefaultTreeNode(f, null);
				displayItSmb(root, rootFolder);
			}
			
			System.out.println("[SMBFolderManager][nextSequenceInFolder] - root.getChildCount: " + root.getChildCount());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sequence;
	} // END nextSequenceInFolder
	
	private static void displayItSmb(TreeNode root, SmbFile node) throws SmbException, MalformedURLException, UnknownHostException{
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
	
	private static String retrieveFolderCounter(String nomeFolder){
		System.out.println("\n[SMBFolderManager][retrieveFolderCounter] - nomeFolder: " + nomeFolder);
		String counter = "000";
		try{
			if(nomeFolder != null && !nomeFolder.equalsIgnoreCase("")){
				
			}
		}catch(Exception e){
			
		}
		System.out.println("\n[SMBFolderManager][retrieveFolderCounter] - counter: " + counter);
		return counter;
	} // END retrieveFolderCounter
	
	public static String creaCartellaLavoro(String cartellaLavoro){
		String pathCartellaLavoro = null;
		try {

			InputStream inputStream = SMBFolderManager.class.getResourceAsStream(XML_FILE);
			Reader reader = new InputStreamReader(inputStream, UTF8);
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(reader);

			Element root = document.getRootElement();
			pathCartellaLavoro = pRadice.getValore()
					//+ File.getpathSeparator + cartellaLavoro + File.pathSeparator;
					//+ Constants.NETWORK_SYSPATH 
					+ cartellaLavoro + Constants.NETWORK_SYSPATH;
			System.out.println("[SMBFolderManager][creaCartellaLavoro] - cartellaLavoro...: " + cartellaLavoro);
			System.out.println("[SMBFolderManager][creaCartellaLavoro] - pathCartellaLavoro...: " + pathCartellaLavoro);
			
			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication( pAuth.getValore());
			
			// Estraggo i figli dalla radice
			List children = root.getChildren();
			Iterator iterator = children.iterator();

			SmbFile newdir;// = new SmbFile(dirpath, auth);
			//SmbFile newdir = new SmbFile(pathCartellaLavoro, auth);
			//newdir.mkdirs();
			//pathCartellaLavoro += Constants.NETWORK_SYSPATH;
			while (iterator.hasNext()) {
				Element subdirs = (Element) iterator.next();
				String folder = subdirs.getAttributeValue(XML_ATT_ID);

				String newFolder =  pathCartellaLavoro + folder;
				System.out.println("1 >> FOLDER >> " + newFolder);

				newdir = new SmbFile(newFolder, auth);
				newdir.mkdirs();

				List subdirs2 = subdirs.getChildren(XML_ITEM_NAME);
				if (subdirs2 != null && subdirs2.size() > 0) {
					Iterator iSubdirs2 = subdirs2.iterator();
					while (iSubdirs2.hasNext()) {
						Element subdirs2Item = (Element) iSubdirs2.next();
						String subdir = subdirs2Item.getAttributeValue(XML_ATT_ID);

						String newSubDir = pathCartellaLavoro + folder + Constants.NETWORK_SYSPATH + subdir;
						System.out.println(" >> >> SUBDIR >> " + newSubDir);

						newdir = new SmbFile(newSubDir, auth);
						newdir.mkdirs();

						List subdirs3 = subdirs2Item.getChildren(XML_SUBITEM_NAME);
						if (subdirs3 != null && subdirs3.size() > 0) {
							Iterator iSubdirs3 = subdirs3.iterator();
							while (iSubdirs3.hasNext()) {
								Element subdirs3Item = (Element) iSubdirs3.next();
								String subdir2 = subdirs3Item.getAttributeValue(XML_ATT_ID);

								String newSubDirS = pathCartellaLavoro + folder + Constants.NETWORK_SYSPATH + subdir +  Constants.NETWORK_SYSPATH + subdir2;

								newdir = new SmbFile(newSubDirS, auth);
								newdir.mkdirs();

							} // END WHILE SUBDIR 2
						} // IF SUBDIR 2 EXIST

					} // END WHILE SUBDIR
				} // IF SUBDIR EXIST

			} // END WHILE XML

		} catch (Exception e) {
			e.printStackTrace();
			pathCartellaLavoro = null;
		}
		return pathCartellaLavoro;
	} // END creaCartellaLavoro

} // end class
