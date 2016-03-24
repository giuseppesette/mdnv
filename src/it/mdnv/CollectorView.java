package it.mdnv;

import it.mdnv.domain.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class CollectorView implements Serializable {

	private Book book;

	private List<Book> books;

	@PostConstruct
	public void init() {
		book = new Book();
		books = new ArrayList<Book>();

	}

	public void createNew() {
		System.out.println("[CollectorView][createNew] - ");
		if (books.contains(book)) {
			FacesMessage msg = new FacesMessage("Dublicated",
					"This book has already been added");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			books.add(book);
			book = new Book();
		}
	}

	public String reinit() {
		System.out.println("[CollectorView][reinit] - ");
		book = new Book();

		return null;
	}

	public Book getBook() {
		return book;
	}

	public List<Book> getBooks() {
		return books;
	}
}