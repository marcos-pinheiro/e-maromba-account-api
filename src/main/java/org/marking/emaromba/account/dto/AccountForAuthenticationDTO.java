package org.marking.emaromba.account.dto;

import java.util.Set;

import org.marking.emaromba.account.domain.Permission;
import org.marking.emaromba.account.domain.UserAccount;

import lombok.Getter;
import lombok.Value;

@Value(staticConstructor = "of") @Getter
public final class AccountForAuthenticationDTO {
	
	private long id;
	private String email;
	private String name;
	private String role;
	private Set<Permission> permissions;
	
	public static AccountForAuthenticationDTO of(UserAccount userAccount) {		
		return new AccountForAuthenticationDTO(userAccount.getId(), 
				userAccount.getUser().getEmail(), 
				userAccount.getUser().getName(), 
				userAccount.getRoles().getRole().name(),  
				userAccount.getRoles().listPermissions());
	}
}
