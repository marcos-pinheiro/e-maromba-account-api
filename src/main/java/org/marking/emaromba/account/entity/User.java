package org.marking.emaromba.account.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.marking.emaromba.types.Password;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@Entity @Table(name = "user")
public final class User implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Transient
	private Password password;
	
	private String name;
	private String email;
	private String secret;

	User() {
	}
	
	public User(String email) {
		this.email = email;
	}

	public User(String email, Password password) {
		this.email = email;
		this.password = password;
		this.secret = this.password.toString();
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = Password.of(password);
		this.secret = this.password.toString();
	}
	
	public User(String email, String password, String name) {
		this.email = email;
		this.password = Password.of(password);
		this.secret = this.password.toString();
		this.name  = name;
	}
	

	public long getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPassword(Password password) {
		this.password = password;
		this.secret = this.password.toString();
	}
	
	public void setPassword(String secret) {
		password = Password.of(secret);
		this.secret = this.password.toString();
	}
	
	public void setHasedPassword(String hash) {
		password = Password.ofHashed(hash);
		this.secret = this.password.toString();
	}
	
	public String getHasedPassword() {
		return secret;
	}
	
	public boolean passwordIsEquals(String otherPassword) {
		return password.thisIsTheSameSecret(otherPassword);
	}	
	
	private static final long serialVersionUID = 1L;
}
