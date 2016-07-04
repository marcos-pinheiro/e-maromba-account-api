package org.marking.emaromba.account.controller.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public final class UserDTO implements Serializable {
	
	@NotEmpty @Email
	private String username;
	
	@NotEmpty @Length(min = 2, max = 60)
	private String name;
	
	@NotEmpty @Length(min = 6, max = 32)
	private String password;
	
	@NotEmpty
	private String role;
	
	private static final long serialVersionUID = 1L;
}
