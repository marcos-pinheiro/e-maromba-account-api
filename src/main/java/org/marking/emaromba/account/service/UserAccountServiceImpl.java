package org.marking.emaromba.account.service;

import java.util.Optional;

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
@Service("userAccountService")
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

	private UserAccountRepository accountRepository;
	private UserRepository userRepository;
	
	UserAccountServiceImpl() {
		super();
	}
	
	@Autowired
	public UserAccountServiceImpl(UserAccountRepository accountRepository, UserRepository userRepository) {
		this.accountRepository = accountRepository;
		this.userRepository = userRepository;
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
	public UserAccount signByUserAndPassword(CredentialsDTO credentials) throws InvalidCredentialsException {
		
		final Optional<UserAccount> optional = accountRepository.findByEmail(credentials.getEmail());		
		final UserAccount account = optional.orElseThrow(InvalidCredentialsException::new);
		
		if(! account.getUser().passwordIsEquals(credentials.getPassword())) {
			throw new InvalidCredentialsException();
		}
		
		return account;
	}

}
