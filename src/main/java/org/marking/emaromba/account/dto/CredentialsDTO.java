package org.marking.emaromba.account.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public final class CredentialsDTO implements Serializable {
	
	@NotEmpty @Email
	private String email;
	
	@NotEmpty @Length(min = 2, max = 32)
	private String password;
	
	CredentialsDTO() {
	}
	
	public CredentialsDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	private static final long serialVersionUID = 1L;
}
