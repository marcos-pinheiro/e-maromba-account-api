package org.marking.emaromba.account.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.marking.util.types.Password;

import lombok.Data;

@Data
public final class AccountDTO implements Serializable {
	
	@NotEmpty @Length(min = 2, max = 60)
	private String name;
	
	@NotEmpty @Email
	private String email;
	
	@NotNull
	private Password password;
	
	@NotEmpty
	private String role;
	
	private static final long serialVersionUID = 1L;
}
