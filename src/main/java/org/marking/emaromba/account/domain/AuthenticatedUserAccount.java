package org.marking.emaromba.account.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode @ToString
public final class AuthenticatedUserAccount {

	private final UserAccount account;
	private final String token;
	
	private AuthenticatedUserAccount(UserAccount account, String token) {
		this.account = account;
		this.token = token;
	}
	
	public static final AuthenticatedUserAccount of(UserAccount account, String token) {
		return new AuthenticatedUserAccount(account, token);
	}
	
	
	public UserAccount getAccount() {
		return account;
	}
	
	public String getToken() {
		return token;
	}
}
