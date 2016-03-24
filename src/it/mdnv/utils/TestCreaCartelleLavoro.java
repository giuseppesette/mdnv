package it.mdnv.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

@SuppressWarnings("all")
public class TestCreaCartelleLavoro implements Constants {

	private static String cartellaLavoro;
	
	public TestCreaCartelleLavoro(){
		try{
			System.out.println("[TestCreaCartelleLavoro] - ");
			initCartellaLavoro();
		}catch(Exception e){
			cartellaLavoro = "FAKE_FOLDER";
		}
	}
	
	private void initCartellaLavoro(){
		System.out.println("[initCartellaLavoro] - ");
		Random rand = new Random();
		int n1 = rand.nextInt(50) + 1;
		int n2 = rand.nextInt(50) + 1;
		String code = String.format("%03d", n1);
		
		cartellaLavoro = "LAVORO_" + code + "_Mio Lavoro_" + n2;
	}
	
	/**
	 * 
	 * @param String cartellaLavoro
	 * @return String pathFinale
	 */
	public String creaFromXml(){
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
			System.out.println("[creaFromXml] - radiceCartellaLavoro: " + radiceCartellaLavoro);
			
			
			//String command = "MOVE \"C:\\Source\" \"C:\\Destination\"";
			String command = "cmd /c xcopy \"" + radiceCartellaLavoro + "\" \"" + root.getAttributeValue("destinazione") +"\"";
			System.out.println("command >> " + command);
			
			// Estraggo i figli dalla radice
			List children = root.getChildren();
			Iterator iterator = children.iterator();
			File newdir;
			while (iterator.hasNext()) {
				Element subdirs = (Element) iterator.next();
				String folder = subdirs.getAttributeValue(XML_ATT_ID);

				String newFolder =  radiceCartellaLavoro + System.getProperty(SYSPATH) + folder;
				//System.out.println("1 >> FOLDER >> " + newFolder);

				//URL rootUrl = new URL(newFolder);
				//newdir = new File(rootUrl.getFile());
				newdir = new File(newFolder);
				if(newdir.mkdirs()){

					List subdirs2 = subdirs.getChildren(XML_ITEM_NAME);
					if (subdirs2 != null && subdirs2.size() > 0) {
						Iterator iSubdirs2 = subdirs2.iterator();
						while (iSubdirs2.hasNext()) {
							Element subdirs2Item = (Element) iSubdirs2.next();
							String subdir = subdirs2Item.getAttributeValue(XML_ATT_ID);
	
							String newSubDir = radiceCartellaLavoro + System.getProperty(SYSPATH) + folder + System.getProperty(SYSPATH) + subdir;
							//System.out.println(" >> >> SUBDIR >> " + newSubDir);
	
							newdir = new File(newSubDir);
							newdir.mkdirs();
	
							List subdirs3 = subdirs2Item.getChildren(XML_SUBITEM_NAME);
							if (subdirs3 != null && subdirs3.size() > 0) {
								Iterator iSubdirs3 = subdirs3.iterator();
								while (iSubdirs3.hasNext()) {
									Element subdirs3Item = (Element) iSubdirs3.next();
									String subdir2 = subdirs3Item.getAttributeValue(XML_ATT_ID);
	
									String newSubDirS = radiceCartellaLavoro + folder + System.getProperty(SYSPATH) + subdir + System.getProperty(SYSPATH) + subdir2;
									//System.out.println(" >> >> >> >> SUBDIR 2 >> " + newSubDirS);
	
									//URL newSubDirUrl = new URL(newSubDirS);
									//newdir = new File(newSubDirUrl.getPath());
									newdir = new File(newSubDirS);
									newdir.mkdirs();
								} // END WHILE SUBDIR 2
							} // IF SUBDIR 2 EXIST
	
						} // END WHILE SUBDIR
					} // IF SUBDIR EXIST
				} else {
					System.out.println(" Si è verificato un errore nella creazione delle directory '" + newFolder + "'");
				}
			} // END WHILE XML

			pathFinale = " '" + radiceCartellaLavoro + "' "; 
			pathFinale += " === \n === " + executeCommand(command);
			System.out.println("pathFinale: " + pathFinale);
			
		} catch (Exception e) {
			e.printStackTrace();
			pathFinale = null;
		}
		return pathFinale;
	} // END creaCartelleLavoro

	/**
	 * 
	 * @param targetPath
	 * @return
	 */
	public String creaFolderInTarget(String targetPath){
		String pathFinale = null;
		try {
			InputStream inputStream = TestCreaCartelleLavoro.class.getResourceAsStream(XML_FILE);
			Reader reader = new InputStreamReader(inputStream, UTF8);

			// Creo un SAXBuilder e con esco costruisco un document
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(reader);

			// Prendo la radice
			Element root = document.getRootElement();
			String radiceCartellaLavoro = targetPath + System.getProperty(SYSPATH);
			System.out.println("[creaFolderInTarget] - radiceCartellaLavoro: " + radiceCartellaLavoro);
			
			// Estraggo i figli dalla radice
			List children = root.getChildren();
			Iterator iterator = children.iterator();
			File newdir;
			while (iterator.hasNext()) {
				Element subdirs = (Element) iterator.next();
				String folder = subdirs.getAttributeValue(XML_ATT_ID);

				String newFolder =  radiceCartellaLavoro + folder;
				//System.out.println("1 >> FOLDER >> " + newFolder);

				newdir = new File(newFolder);
				if(newdir.mkdirs()){

					List subdirs2 = subdirs.getChildren(XML_ITEM_NAME);
					if (subdirs2 != null && subdirs2.size() > 0) {
						Iterator iSubdirs2 = subdirs2.iterator();
						while (iSubdirs2.hasNext()) {
							Element subdirs2Item = (Element) iSubdirs2.next();
							String subdir = subdirs2Item.getAttributeValue(XML_ATT_ID);
	
							String newSubDir = radiceCartellaLavoro + folder + System.getProperty(SYSPATH) + subdir;
							//System.out.println(" >> >> SUBDIR >> " + newSubDir);
	
							newdir = new File(newSubDir);
							newdir.mkdirs();
	
							List subdirs3 = subdirs2Item.getChildren(XML_SUBITEM_NAME);
							if (subdirs3 != null && subdirs3.size() > 0) {
								Iterator iSubdirs3 = subdirs3.iterator();
								while (iSubdirs3.hasNext()) {
									Element subdirs3Item = (Element) iSubdirs3.next();
									String subdir2 = subdirs3Item.getAttributeValue(XML_ATT_ID);
	
									String newSubDirS = radiceCartellaLavoro + folder + System.getProperty(SYSPATH) + subdir + System.getProperty(SYSPATH) + subdir2;
									//System.out.println(" >> >> >> >> SUBDIR 2 >> " + newSubDirS);
	
									newdir = new File(newSubDirS);
									newdir.mkdirs();
								} // END WHILE SUBDIR 2
							} // IF SUBDIR 2 EXIST
	
						} // END WHILE SUBDIR
					} // IF SUBDIR EXIST
				} else {
					System.out.println(" Si è verificato un errore nella creazione delle directory '" + newFolder + "'");
				}
			} // END WHILE XML

			pathFinale = " '" + radiceCartellaLavoro + "' "; 
			System.out.println("pathFinale: " + pathFinale);
			
		} catch (Exception e) {
			e.printStackTrace();
			pathFinale = null;
		}
		return pathFinale;
	} // END creaFolderWithSourceAndTarget
	
	/**
	 * 
	 * @param targetPath
	 * @return
	 */
	public String creaFolderInTargetUseURL(String targetPath){
		String pathFinale = null;
		try {
			InputStream inputStream = TestCreaCartelleLavoro.class.getResourceAsStream(XML_FILE);
			Reader reader = new InputStreamReader(inputStream, UTF8);

			// Creo un SAXBuilder e con esco costruisco un document
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(reader);

			// Prendo la radice
			Element root = document.getRootElement();
			String radiceCartellaLavoro = targetPath + System.getProperty(SYSPATH);
			System.out.println("[creaFolderInTargetUseURL] - radiceCartellaLavoro: " + radiceCartellaLavoro);
			
			// Estraggo i figli dalla radice
			List children = root.getChildren();
			Iterator iterator = children.iterator();
			File newdir;
			while (iterator.hasNext()) {
				Element subdirs = (Element) iterator.next();
				String folder = subdirs.getAttributeValue(XML_ATT_ID);

				String newFolder =  radiceCartellaLavoro + folder;
				//System.out.println("1 >> FOLDER >> " + newFolder);

				URL rootUrl = new URL(newFolder);
				newdir = new File(rootUrl.getPath());
				//newdir = new File(newFolder);
				if(newdir.mkdirs()){

					List subdirs2 = subdirs.getChildren(XML_ITEM_NAME);
					if (subdirs2 != null && subdirs2.size() > 0) {
						Iterator iSubdirs2 = subdirs2.iterator();
						while (iSubdirs2.hasNext()) {
							Element subdirs2Item = (Element) iSubdirs2.next();
							String subdir = subdirs2Item.getAttributeValue(XML_ATT_ID);
	
							String newSubDir = radiceCartellaLavoro + folder + System.getProperty(SYSPATH) + subdir;
							//System.out.println(" >> >> SUBDIR >> " + newSubDir);
							
							URL newSubDirUrl = new URL(newSubDir);
							newdir = new File(newSubDirUrl.getPath());
							newdir.mkdirs();
	
							List subdirs3 = subdirs2Item.getChildren(XML_SUBITEM_NAME);
							if (subdirs3 != null && subdirs3.size() > 0) {
								Iterator iSubdirs3 = subdirs3.iterator();
								while (iSubdirs3.hasNext()) {
									Element subdirs3Item = (Element) iSubdirs3.next();
									String subdir2 = subdirs3Item.getAttributeValue(XML_ATT_ID);
	
									String newSubDirS = radiceCartellaLavoro + folder + System.getProperty(SYSPATH) + subdir + System.getProperty(SYSPATH) + subdir2;
									//System.out.println(" >> >> >> >> SUBDIR 2 >> " + newSubDirS);
									URL newSubDir2Url = new URL(newSubDirS);
									newdir = new File(newSubDir2Url.getPath());
									newdir.mkdirs();
								} // END WHILE SUBDIR 2
							} // IF SUBDIR 2 EXIST
	
						} // END WHILE SUBDIR
					} // IF SUBDIR EXIST
				} else {
					System.out.println(" Si è verificato un errore nella creazione delle directory '" + newFolder + "'");
				}
			} // END WHILE XML

			pathFinale = " '" + radiceCartellaLavoro + "' "; 
			System.out.println("pathFinale: " + pathFinale);
			
		} catch (Exception e) {
			e.printStackTrace();
			pathFinale = null;
		}
		return pathFinale;
	} // END creaFolderInTargetUseURL


	/**
	 * 
	 * @param String cartellaLavoro
	 * @return String pathFinale
	 */
	public String creaFolderInTargetUseSambaLib(String targetPath, String username, String password){
		String pathFinale = null;
		try {
			InputStream inputStream = CreaCartelleLavoro.class.getResourceAsStream(XML_FILE);
			Reader reader = new InputStreamReader(inputStream, UTF8);

			// Creo un SAXBuilder e con esco costruisco un document
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(reader);

			// Prendo la radice
			Element root = document.getRootElement();
			String radiceCartellaLavoro = targetPath + Constants.NETWORK_SYSPATH;
			System.out.println("[creaFolderInTargetUseSambaLib] - radiceCartellaLavoro: " + radiceCartellaLavoro);
			
			String user = username + ":" + password;
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

			pathFinale = " '" + radiceCartellaLavoro + "' "; 
			System.out.println("pathFinale: " + pathFinale);
		} catch (Exception e) {
			e.printStackTrace();
			pathFinale = null;
		}
		return pathFinale;
	} // END creaCartelleLavoro
	
	/**
	 * 
	 * 
	 * 
	 * @param sourcePath
	 * @param targetPath
	 * @param dosCommand for example: 'move' or 'copy' or 'xcopy' or 'cmd /c xcopy'
	 * @return
	 */
	public String creaFolderUseCommandDos(String sourcePath, String targetPath, String dosCommand){
		String pathFinale = null;
		try {
			InputStream inputStream = TestCreaCartelleLavoro.class.getResourceAsStream(XML_FILE);
			Reader reader = new InputStreamReader(inputStream, UTF8);

			// Creo un SAXBuilder e con esco costruisco un document
			SAXBuilder builder = new SAXBuilder();
			Document document = builder.build(reader);

			// Prendo la radice
			Element root = document.getRootElement();
			String radiceCartellaLavoro = sourcePath;
			System.out.println("[creaFolderUseCommandDos] - radiceCartellaLavoro: " + radiceCartellaLavoro);
			
			//command = "MOVE \"C:\\Source\" \"C:\\Destination\"";
			//command = "cmd /c xcopy \"" + radiceCartellaLavoro + "\" \"" + root.getAttributeValue("destinazione") +"\"";
			dosCommand += " '" +sourcePath + "' '" + targetPath + "'";
			System.out.println("command >> " + dosCommand);
			
			// Estraggo i figli dalla radice
			List children = root.getChildren();
			Iterator iterator = children.iterator();
			File newdir;
			while (iterator.hasNext()) {
				Element subdirs = (Element) iterator.next();
				String folder = subdirs.getAttributeValue(XML_ATT_ID);

				String newFolder =  radiceCartellaLavoro + System.getProperty(SYSPATH) + folder;
				//System.out.println("1 >> FOLDER >> " + newFolder);

				//URL rootUrl = new URL(newFolder);
				//newdir = new File(rootUrl.getFile());
				newdir = new File(newFolder);
				if(newdir.mkdirs()){

					List subdirs2 = subdirs.getChildren(XML_ITEM_NAME);
					if (subdirs2 != null && subdirs2.size() > 0) {
						Iterator iSubdirs2 = subdirs2.iterator();
						while (iSubdirs2.hasNext()) {
							Element subdirs2Item = (Element) iSubdirs2.next();
							String subdir = subdirs2Item.getAttributeValue(XML_ATT_ID);
	
							String newSubDir = radiceCartellaLavoro + System.getProperty(SYSPATH) + folder + System.getProperty(SYSPATH) + subdir;
							//System.out.println(" >> >> SUBDIR >> " + newSubDir);
	
							newdir = new File(newSubDir);
							newdir.mkdirs();
	
							List subdirs3 = subdirs2Item.getChildren(XML_SUBITEM_NAME);
							if (subdirs3 != null && subdirs3.size() > 0) {
								Iterator iSubdirs3 = subdirs3.iterator();
								while (iSubdirs3.hasNext()) {
									Element subdirs3Item = (Element) iSubdirs3.next();
									String subdir2 = subdirs3Item.getAttributeValue(XML_ATT_ID);
	
									String newSubDirS = radiceCartellaLavoro + folder + System.getProperty(SYSPATH) + subdir + System.getProperty(SYSPATH) + subdir2;
									//System.out.println(" >> >> >> >> SUBDIR 2 >> " + newSubDirS);
	
									//URL newSubDirUrl = new URL(newSubDirS);
									//newdir = new File(newSubDirUrl.getPath());
									newdir = new File(newSubDirS);
									newdir.mkdirs();
								} // END WHILE SUBDIR 2
							} // IF SUBDIR 2 EXIST
	
						} // END WHILE SUBDIR
					} // IF SUBDIR EXIST
				} else {
					System.out.println(" Si è verificato un errore nella creazione delle directory '" + newFolder + "'");
				}
			} // END WHILE XML

			pathFinale = " '" + radiceCartellaLavoro + "' "; 
			if(dosCommand != null && !dosCommand.equalsIgnoreCase(""))
				pathFinale += " === \n === " + executeCommand(dosCommand);
			System.out.println("pathFinale: " + pathFinale);
			
		} catch (Exception e) {
			e.printStackTrace();
			pathFinale = null;
		}
		return pathFinale;
	} // END creaFolderWithSourceTargetAndCommandDos
	
	/**
	 * 
	 * @param String dosCommand
	 * @return String esito esecuzione cmd
	 */
	private String executeCommand(String dosCommand) {

		StringBuffer output = new StringBuffer();

		Process p;
		try {
			//Process auth = Runtime.getRuntime().exec("net use \\\\\\10.10.10.244\\d$\\App NormTest1 /user:Administrator");
			p = Runtime.getRuntime().exec(dosCommand);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output.toString();

	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Random rand = new Random();
		int n1 = rand.nextInt(50) + 1;
		int n2 = rand.nextInt(50) + 1;
		String code = String.format("%03d", n1);
		TestCreaCartelleLavoro tcc = new TestCreaCartelleLavoro();
		
		/*
		//String cartellaLavoro = CreaCartelleLavoroLocal.crea("LAVORO_" + code + "_Mio Lavoro_" + n2);
		String cartellaLavoro = tcc.creaFromXml();
		//cartellaLavoro = cartellaLavoro.replace(oldChar, newChar)
		System.out.println(cartellaLavoro);
		*/
		String targetFolder = "\\\\10.10.10.244\\SCBatch";
		tcc.leggiTargetFolder(targetFolder);
		
		tcc.creaFolderInTarget(targetFolder);
		
	}
	
	public void leggiTargetFolder(String targetFolder) {
		System.out.println("####################################################à");
		File file = new File(targetFolder);
		if (file.isDirectory()) {
			File[] filesInDir = file.listFiles();
			Arrays.sort(filesInDir);
			for (File f : filesInDir) {
				String prefix = "";

				if (f.isFile())
					prefix = "[f] ";
				else if (f.isDirectory())
					prefix = "[d] ";

				System.out.println(prefix + f.toString());
			}
		}
		System.out.println("####################################################à");
	} // END leggiTargetFolder
	
} // end class
