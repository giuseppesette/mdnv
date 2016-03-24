package it.mdnv.utils;

/**
 * http://sanjaal.com/java/875/java-utilities/java-tutorial-using-jcifs-to-copy-files-to-shared-network-drive-using-username-and-password/
 * http://jcifs.samba.org/
 */

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class NetworkShareFileCopy {
	static final String USER_NAME = "Administrator";
	static final String PASSWORD = "NormTest1";
	// e.g. Assuming your network folder is:
	// \\my.myserver.net\shared\public\photos\
	static final String NETWORK_FOLDER = "smb://10.10.10.244/SCBatch/";

	public static void main(String args[]) {
		String fileContent = "This is a test file";

		new NetworkShareFileCopy().copyFiles(fileContent, "testFile.text");
	}

	public boolean copyFiles(String fileContent, String fileName) {
		boolean successful = false;
		try {
			String user = USER_NAME + ":" + PASSWORD;
			System.out.println("User: " + user);

			NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(user);
			String path = NETWORK_FOLDER + fileName;
			
			System.out.println("Path: " + path);

			String dirpath = NETWORK_FOLDER + "MYDIR/";
			
			System.out.println("dirpath" + dirpath);
			SmbFile sDir = new SmbFile(dirpath, auth);
			sDir.mkdirs();
			path = dirpath + fileName;
				SmbFile sFile = new SmbFile(path, auth);
				SmbFileOutputStream sfos = new SmbFileOutputStream(sFile);
				sfos.write(fileContent.getBytes());
	
				successful = true;
				System.out.println("Successful" + successful);
			
		} catch (Exception e) {
			successful = false;
			e.printStackTrace();
		}
		return successful;
	}
}