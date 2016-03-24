package it.mdnv.entities;

import it.mdnv.model.Role;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the utenti database table.
 * 
 */
@Entity
@Table(name = "UTENTI")
@NamedQuery(name = "User.findUserByEmail", query = "select u from User u where u.email = :email")
public class Utenti implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String email;

	private String name;

	private String password;

	private String role;

	public Utenti() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isAdmin() {
		return Role.ADMIN.equals(role);
	}

	public boolean isUser() {
		return Role.USER.equals(role);
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Utenti) {
			Utenti user = (Utenti) obj;
			return user.getId() == id;
		}

		return false;
	}
}