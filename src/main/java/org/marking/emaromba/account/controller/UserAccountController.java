package org.marking.emaromba.account.controller;

import java.net.URI;

import org.marking.emaromba.account.domain.AuthenticatedUserAccount;
import org.marking.emaromba.account.domain.Role;
import org.marking.emaromba.account.domain.Roles;
import org.marking.emaromba.account.domain.User;
import org.marking.emaromba.account.domain.UserAccount;
import org.marking.emaromba.account.dto.AccountDTO;
import org.marking.emaromba.account.dto.CredentialsDTO;
import org.marking.emaromba.account.service.UserAccountService;
import org.marking.emaromba.account.service.exception.AccountNotFoundException;
import org.marking.emaromba.account.service.exception.InvalidCredentialsException;
import org.marking.emaromba.account.service.exception.UsernameUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	//private static final Logger LOGGER = LogManager.getLogger(UserAccountController.class);
	
	private UserAccountService userAccountService;
	
	
	UserAccountController() {
	}
	
	
	/**
	 * 
	 * @param accountDTO
	 * @param uriBuilder
	 * @return
	 * @throws UsernameUnavailableException
	 */
	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	protected ResponseEntity<UserAccount> create(@Validated @RequestBody AccountDTO accountDTO, UriComponentsBuilder uriBuilder) throws UsernameUnavailableException {
		
		final UserAccount account = UserAccount.of(new User(accountDTO.getEmail(), accountDTO.getPassword(), accountDTO.getName()), 
				Role.valueOf(accountDTO.getRole()));		
		
		userAccountService.register(account);
		
		final URI uri = uriBuilder.path("/accounts/{id}")
				.buildAndExpand(account.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(account);
	}
	
	
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws InvalidCredentialsException 
	 */
	@RequestMapping(value = "/accounts/sign", method = RequestMethod.POST)
	protected ResponseEntity<UserAccount> sign(@RequestBody CredentialsDTO credentials) throws InvalidCredentialsException {
		
		final AuthenticatedUserAccount authenticatedAccount = userAccountService.signByUserAndPassword(credentials);
		
		return ResponseEntity
				.ok()
				.header("Authorization", authenticatedAccount.getToken())
				.body(authenticatedAccount.getAccount());
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws AccountNotFoundException
	 */
	//@Cacheable("account") 
	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	protected ResponseEntity<UserAccount> getById(@PathVariable long id) throws AccountNotFoundException {
		
		final UserAccount account = userAccountService.getByUserId(id);
		
		return ResponseEntity.ok(account);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws AccountNotFoundException 
	 */
	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
	protected ResponseEntity<Void> inactivate(@PathVariable long id) throws AccountNotFoundException {
		
		userAccountService.inactivate(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	/**
	 * 
	 * @param id
	 * @param roles
	 * @return
	 * @throws AccountNotFoundException
	 */
	@RequestMapping(value = "/accounts/{id}/role", method = RequestMethod.PUT)
	protected ResponseEntity<UserAccount> modifyRoles(@PathVariable long id, @RequestBody Roles roles) throws AccountNotFoundException {
		
		final UserAccount account = userAccountService.changeRole(id, roles);
		
		return ResponseEntity.ok(account);
	}
	
	
	@Autowired
	void setUserAccountService(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}
}
