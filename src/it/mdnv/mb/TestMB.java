package it.mdnv.mb;

import it.mdnv.utils.Constants;
import it.mdnv.utils.TestCreaCartelleLavoro;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "tbean")
public class TestMB extends AbstractMB implements Constants {
	
	private TestCreaCartelleLavoro tcc = new TestCreaCartelleLavoro();

	
	
	// createFolderTreeInTargetFolder
	private String strTargetPath;
	
	// createFolderTreeUseSambaLib
	private String strTargetPath2;
	private String username;
	private String password;

	// createFolderTreeUseURL
	private String strTargetPath3;
	
	// createFolderTreeUseCommandDos
	private String strSourcePath;
	private String strTargetPath4;
	private String dosCommand;
	
	/**
	 * 
	 */
	public void createFolderTreeFromXML(){

		String ret = tcc.creaFromXml();
		
		displayInfoMessageToUser("Risultato: " + ret);

	} // END createFolderTreeFromXML

	/**
	 * 
	 */
	public void createFolderTreeInTargetFolder(){

		if(this.strTargetPath != null && !strTargetPath.equalsIgnoreCase("")){
			
			tcc.leggiTargetFolder(this.strTargetPath);
			
			String ret = tcc.creaFolderInTarget(this.strTargetPath);
			
			displayInfoMessageToUser("Risultato: " + ret);
			
		} else {
			displayErrorMessageToUser("Inserire la Path di Destinazione!");
		}
	} // END createFolderTreeInTargetFolder
	
	/**
	 * 
	 */
	public void createFolderTreeUseSambaLib(){
		if(this.strTargetPath2 != null && !strTargetPath2.equalsIgnoreCase("")){
			
			if(this.username != null && !username.equalsIgnoreCase("") && 
					this.password != null && !password.equalsIgnoreCase("")){
				
				String ret = tcc.creaFolderInTargetUseSambaLib(strTargetPath2, username, password);
				
				displayInfoMessageToUser("Risultato: " + ret);
				
			} else {
				displayErrorMessageToUser("Inserire Username e Password per accedere alla Cartella di Rete");
			}
			
		} else {
			displayErrorMessageToUser("Inserire la Path di Destinazione!");
		}
	} // END createFolderTreeUseSambaLib
	
	/**
	 * 
	 */
	public void createFolderTreeUseURL(){

			if(this.strTargetPath3 != null && !strTargetPath3.equalsIgnoreCase("")){
				
				String ret = tcc.creaFolderInTargetUseURL(strTargetPath3);
				
				displayInfoMessageToUser("Risultato: " + ret);
				
			} else {
				displayErrorMessageToUser("Inserire la Path di Destinazione!");
			}
	} // END createFolderTreeUseURL
	
	/**
	 * 
	 */
	public void createFolderTreeUseCommandDos(){
		if(this.strSourcePath != null && !strSourcePath.equalsIgnoreCase("")){
			
			if(this.strTargetPath4 != null && !strTargetPath4.equalsIgnoreCase("")){ 
				if(this.dosCommand != null && !dosCommand.equalsIgnoreCase("")){
				
					String ret = tcc.creaFolderUseCommandDos(strSourcePath, strTargetPath4, dosCommand);
					
					displayInfoMessageToUser("Risultato: " + ret);
					
				} else {
					displayErrorMessageToUser("Inserire il comando DOS");
				}
				
			} else {
				displayErrorMessageToUser("Inserire la Path di Destinazione!");
			}
			
		} else {
			displayErrorMessageToUser("Inserire la Path Sorgente!");
		}
	} // END createFolderTreeUseCommandDos
	
	
	/****************************************************************************************************************************/
	/***                GET E SET METHOD              ***/
	
	
	public String getStrSourcePath() {
		return strSourcePath;
	}
	public void setStrSourcePath(String strSourcePath) {
		this.strSourcePath = strSourcePath;
	}
	public String getStrTargetPath() {
		return strTargetPath;
	}
	public void setStrTargetPath(String strTargetPath) {
		this.strTargetPath = strTargetPath;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDosCommand() {
		return dosCommand;
	}
	public void setDosCommand(String dosCommand) {
		this.dosCommand = dosCommand;
	}
	public String getStrTargetPath2() {
		return strTargetPath2;
	}
	public void setStrTargetPath2(String strTargetPath2) {
		this.strTargetPath2 = strTargetPath2;
	}
	public String getStrTargetPath3() {
		return strTargetPath3;
	}
	public void setStrTargetPath3(String strTargetPath3) {
		this.strTargetPath3 = strTargetPath3;
	}
	public String getStrTargetPath4() {
		return strTargetPath4;
	}
	public void setStrTargetPath4(String strTargetPath4) {
		this.strTargetPath4 = strTargetPath4;
	}

	@Override
	public String toString(){
		return "SRC='" + strSourcePath + "' \n TRG='" + strTargetPath + "'";
	}

	/** ****************************************************************************************** **/

} // end class
