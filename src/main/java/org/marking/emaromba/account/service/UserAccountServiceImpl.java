package org.marking.emaromba.account.service;

import java.util.Optional;

import org.marking.emaromba.account.dao.AuthenticationServiceDao;
import org.marking.emaromba.account.domain.AuthenticatedUserAccount;
import org.marking.emaromba.account.domain.Roles;
import org.marking.emaromba.account.domain.UserAccount;
import org.marking.emaromba.account.dto.CredentialsDTO;
import org.marking.emaromba.account.repository.UserAccountRepository;
import org.marking.emaromba.account.repository.UserRepository;
import org.marking.emaromba.account.service.exception.AccountNotFoundException;
import org.marking.emaromba.account.service.exception.InvalidCredentialsException;
import org.marking.emaromba.account.service.exception.UsernameUnavailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@Transactional @Service("userAccountService")
public class UserAccountServiceImpl implements UserAccountService {

	private UserAccountRepository accountRepository;
	private UserRepository userRepository;
	private AuthenticationServiceDao authenticationService;
	
	UserAccountServiceImpl() {
		super();
	}
	
	@Autowired
	public UserAccountServiceImpl(UserAccountRepository accountRepository, UserRepository userRepository, AuthenticationServiceDao authenticationService) {
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
		this.authenticationService = authenticationService;
	}
	
	
	@Override
	public void register(UserAccount account) throws UsernameUnavailableException {
		
		if(userRepository.findByEmail(account.getUser().getEmail()).isPresent()) {
			throw new UsernameUnavailableException();
		}
		
		accountRepository.save(account);
	}

	@Override
	public UserAccount changeRole(long id, Roles roles) throws AccountNotFoundException {
		
		final UserAccount account = this.getByUserId(id);
		
		account.changeRoles(roles);
		accountRepository.save(account);
		
		return account;
	}

	@Override
	public void inactivate(long id) throws AccountNotFoundException {
		
		final UserAccount account = this.getByUserId(id);		
		account.inactivate();
		
		accountRepository.save(account);
	}
	
	@Override
	public UserAccount getByUserId(long id) throws AccountNotFoundException {
		return Optional.ofNullable(accountRepository.findActivesById(id))
				.orElseThrow(AccountNotFoundException::new);
	}

	@Override
	public AuthenticatedUserAccount signByUserAndPassword(CredentialsDTO credentials) throws InvalidCredentialsException {
		
		//Find in database
		final Optional<UserAccount> optional = accountRepository.findByEmail(credentials.getEmail());		
		final UserAccount account = optional.orElseThrow(InvalidCredentialsException::new);
		
		if(! account.getUser().passwordIsEquals(credentials.getPassword())) {
			throw new InvalidCredentialsException();
		}
		
		//Register and rescue a valid token	from Auth API	
		final String token = authenticationService.authenticateUserAccount(account);
		
		return AuthenticatedUserAccount.of(account, token);
	}

}
