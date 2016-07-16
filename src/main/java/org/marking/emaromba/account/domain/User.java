package org.marking.emaromba.account.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.marking.emaromba.types.Password;
import org.marking.emaromba.types.convertes.jpa.PasswordConverter;

import lombok.EqualsAndHashCode;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@Entity @Table(name = "user") @EqualsAndHashCode
public final class User implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String email;
	
	@Column	@Convert(converter = PasswordConverter.class) //JPA
	private Password password;

	User() {
	}
	
	public User(String email) {
		this.email = email;
	}

	public User(String email, Password password) {
		this(email);
		this.password = password;
	}
	
	public User(String email, Password password, String name) {
		this(email, password);
		this.name = name;
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
	}
	
	public void setPassword(String secret) {
		password = Password.of(secret);
	}
	
	public boolean passwordIsEquals(String otherSecret) {
		return password.isEquals(otherSecret);
	}
	
	private static final long serialVersionUID = 1L;
}
