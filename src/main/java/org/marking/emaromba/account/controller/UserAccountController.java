package org.marking.emaromba.account.controller;

import java.net.URI;

import org.marking.emaromba.account.controller.dto.UserDTO;
import org.marking.emaromba.account.entity.Role;
import org.marking.emaromba.account.entity.User;
import org.marking.emaromba.account.entity.UserAccount;
import org.marking.emaromba.account.service.UserAccountService;
import org.marking.emaromba.account.service.exception.UsernameUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@RestController
public class UserAccountController {
	
	@Autowired
	private UserAccountService userAccountService;
	
	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	ResponseEntity<UserAccount> create(@Validated @RequestBody UserDTO userDTO, UriComponentsBuilder uriBuilder) {
		
		final UserAccount account = UserAccount.of(new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName()), 
				Role.valueOf(userDTO.getRole()));
		
		try {
			userAccountService.register(account);
		} 
		catch (UsernameUnavailableException e) {
			//throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		
		final URI uri = uriBuilder.path("/accounts/{id}").buildAndExpand(account.getId()).toUri();
		
		return ResponseEntity.created(uri).body(account);
	}
	
	
	void setUserAccountService(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}
}
