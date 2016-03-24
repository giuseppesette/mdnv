package it.mdnv.utils;

import it.mdnv.facade.ParametriFacade;
import it.mdnv.model.Parametri;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

@SuppressWarnings("all")
public class CreaCartelleLavoro implements Constants {

	/**
	 * 
	 * @param String cartellaLavoro
	 * @return String pathFinale
	 */
	public static String crea(String cartellaLavoro){
		String pathFinale = null;
		try {
			InputStream inputStream = CreaCartelleLavoro.class.getResourceAsStream(XML_FILE);
			Reader reader = new InputStreamReader(inputStream, UTF8);

			// Creo un SAXBuilder e con esco costruisco un document
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(reader);

			// Prendo la radice
			Element root = document.getRootElement();
			String radiceCartellaLavoro = root.getAttributeValue(XML_ATT_ROOT) + cartellaLavoro +  Constants.NETWORK_SYSPATH;

			String u = root.getAttributeValue("username");
			String p = root.getAttributeValue("password");
			System.out.println("Auth: " + u + " - " + p);
			
			String user = u + ":" + p;
			System.out.println("Auth User: " + user);
			
			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
			
			// Estraggo i figli dalla radice
			List children = root.getChildren();
			Iterator iterator = children.iterator();

			SmbFile newdir;// = new SmbFile(dirpath, auth);
			while (iterator.hasNext()) {
				Element subdirs = (Element) iterator.next();
				String folder = subdirs.getAttributeValue(XML_ATT_ID);

				String newFolder =  radiceCartellaLavoro + folder;
				System.out.println("1 >> FOLDER >> " + newFolder);

				newdir = new SmbFile(newFolder, auth);
				newdir.mkdirs();
				if(newdir.exists())
					System.out.println("----------- OK " + newFolder);
				else
					System.out.println("-----NOT EXIST " + newFolder);
				
					List subdirs2 = subdirs.getChildren(XML_ITEM_NAME);
					if (subdirs2 != null && subdirs2.size() > 0) {
						Iterator iSubdirs2 = subdirs2.iterator();
						while (iSubdirs2.hasNext()) {
							Element subdirs2Item = (Element) iSubdirs2.next();
							String subdir = subdirs2Item.getAttributeValue(XML_ATT_ID);
	
							String newSubDir = radiceCartellaLavoro + folder + Constants.NETWORK_SYSPATH + subdir;
							System.out.println(" >> >> SUBDIR >> " + newSubDir);
	
							newdir = new SmbFile(newSubDir, auth);
							newdir.mkdirs();
	
							if(newdir.exists())
								System.out.println("----------- OK " + newSubDir);
							else
								System.out.println("-----NOT EXIST " + newSubDir);
							
							List subdirs3 = subdirs2Item.getChildren(XML_SUBITEM_NAME);
							if (subdirs3 != null && subdirs3.size() > 0) {
								Iterator iSubdirs3 = subdirs3.iterator();
								while (iSubdirs3.hasNext()) {
									Element subdirs3Item = (Element) iSubdirs3.next();
									String subdir2 = subdirs3Item.getAttributeValue(XML_ATT_ID);
	
									String newSubDirS = radiceCartellaLavoro + folder + Constants.NETWORK_SYSPATH + subdir +  Constants.NETWORK_SYSPATH + subdir2;

									newdir = new SmbFile(newSubDirS, auth);
									newdir.mkdirs();
									
									if(newdir.exists())
										System.out.println("----------- OK " + newSubDirS);
									else
										System.out.println("-----NOT EXIST " + newSubDirS);
									
								} // END WHILE SUBDIR 2
							} // IF SUBDIR 2 EXIST
	
						} // END WHILE SUBDIR
					} // IF SUBDIR EXIST

			} // END WHILE XML

			pathFinale = radiceCartellaLavoro;
			
		} catch (Exception e) {
			e.printStackTrace();
			pathFinale = null;
		}
		return pathFinale;
	} // END creaCartelleLavoro

	public static String creaFromXml(String cartellaLavoro){
		String pathFinale = null;
		try {
			InputStream inputStream = TestCreaCartelleLavoro.class.getResourceAsStream(XML_FILE);
			Reader reader = new InputStreamReader(inputStream, UTF8);

			// Creo un SAXBuilder e con esco costruisco un document
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(reader);

			// Prendo la radice
			Element root = document.getRootElement();
			String radiceCartellaLavoro = root.getAttributeValue(XML_ATT_ROOT) + cartellaLavoro + System.getProperty(SYSPATH);
			System.out.println("[CreaCartelleLavoro][creaFromXml] - radiceCartellaLavoro: " + radiceCartellaLavoro);
			
			// Estraggo i figli dalla radice
			List children = root.getChildren();
			Iterator iterator = children.iterator();
			File newdir;
			while (iterator.hasNext()) {
				Element subdirs = (Element) iterator.next();
				String folder = subdirs.getAttributeValue(XML_ATT_ID);

				String newFolder =  radiceCartellaLavoro + System.getProperty(SYSPATH) + folder;

				newdir = new File(newFolder);
				if(newdir.mkdirs()){

					List subdirs2 = subdirs.getChildren(XML_ITEM_NAME);
					if (subdirs2 != null && subdirs2.size() > 0) {
						Iterator iSubdirs2 = subdirs2.iterator();
						while (iSubdirs2.hasNext()) {
							Element subdirs2Item = (Element) iSubdirs2.next();
							String subdir = subdirs2Item.getAttributeValue(XML_ATT_ID);
	
							String newSubDir = radiceCartellaLavoro + System.getProperty(SYSPATH) + folder + System.getProperty(SYSPATH) + subdir;
	
							newdir = new File(newSubDir);
							newdir.mkdirs();
	
							List subdirs3 = subdirs2Item.getChildren(XML_SUBITEM_NAME);
							if (subdirs3 != null && subdirs3.size() > 0) {
								Iterator iSubdirs3 = subdirs3.iterator();
								while (iSubdirs3.hasNext()) {
									Element subdirs3Item = (Element) iSubdirs3.next();
									String subdir2 = subdirs3Item.getAttributeValue(XML_ATT_ID);
	
									String newSubDirS = radiceCartellaLavoro + folder + System.getProperty(SYSPATH) + subdir + System.getProperty(SYSPATH) + subdir2;

									newdir = new File(newSubDirS);
									newdir.mkdirs();
								} // END WHILE SUBDIR 2
							} // IF SUBDIR 2 EXIST
	
						} // END WHILE SUBDIR
					} // IF SUBDIR EXIST
				} else {
					System.out.println("[CreaCartelleLavoro][creaFromXml] - Si è verificato un errore nella creazione delle directory '" + newFolder + "'");
				}
			} // END WHILE XML

			pathFinale = " '" + radiceCartellaLavoro + "' "; 

			System.out.println("[CreaCartelleLavoro][creaFromXml] - pathFinale: " + pathFinale);
			
		} catch (Exception e) {
			e.printStackTrace();
			pathFinale = null;
		}
		return pathFinale;
	} // END creaCartelleLavoro

	
	public static void main(String[] args) {
		Random rand = new Random();
		int n1 = rand.nextInt(50) + 1;
		int n2 = rand.nextInt(50) + 1;
		String code = String.format("%03d", n1);
		
		String cartellaLavoro = CreaCartelleLavoro.crea("LAVORO_" + code + "_Mio Lavoro_" + n2);
		//cartellaLavoro = cartellaLavoro.replace(oldChar, newChar)
		System.out.println(cartellaLavoro);
	}
	
} // end class
