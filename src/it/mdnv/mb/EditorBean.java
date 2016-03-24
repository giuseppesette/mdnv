package it.mdnv.mb;

import javax.faces.bean.ManagedBean;

/**
 * ref: http://www.mkyong.com/jsf2/primefaces/primefaces-hello-world-example/
 * @author giuseppe
 *
 */
@ManagedBean(name = "editor")
public class EditorBean {

	private String value = "This editor is provided by PrimeFaces";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}