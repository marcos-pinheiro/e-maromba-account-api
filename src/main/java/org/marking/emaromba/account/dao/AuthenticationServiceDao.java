package org.marking.emaromba.account.dao;

import java.net.URI;
import java.util.List;

import org.marking.emaromba.account.domain.UserAccount;
import org.marking.emaromba.account.dto.AccountForAuthenticationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component("authenticationService")
public final class AuthenticationServiceDao {
	
	private static final String AUTH_LOCATION = "http://localhost:8081/auth";
	
	public String authenticateUserAccount(UserAccount account) throws HttpClientErrorException {
		
		final RestTemplate restTemplate = new RestTemplate();
		
		final AccountForAuthenticationDTO requestObject = AccountForAuthenticationDTO.of(account);
		final ResponseEntity<Void> responseEntity = restTemplate.postForEntity(URI.create(AUTH_LOCATION), 
				requestObject, 
				Void.class);
		

		final List<String> authorizationHeader = responseEntity.getHeaders().get("Authorization");
		if(authorizationHeader.isEmpty()) {
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Server not authenticate account");
		}
		
		return authorizationHeader.get(0);
	}

}
