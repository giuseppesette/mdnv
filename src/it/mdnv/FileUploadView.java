package it.mdnv;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;

@ManagedBean(name = "fileUploadView")
@ViewScoped
public class FileUploadView {

	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void upload() {
		System.out.println("[FileUploadView][upload] - called");
		if (file != null) {
			System.out.println("[FileUploadView][upload] - Nome File: " + file.getFileName());
			FacesMessage message = new FacesMessage("File: ",
					file.getFileName() + " caricato Correttamente.");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
}
