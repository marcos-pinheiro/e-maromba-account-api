package org.marking.emaromba.account.service;

import org.marking.emaromba.account.entity.Roles;
import org.marking.emaromba.account.entity.UserAccount;
import org.marking.emaromba.account.repository.UserAccountRepository;
import org.marking.emaromba.account.repository.UserRepository;
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
	
	
	UserAccountServiceImpl() { }
	
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
		
		accountRepository.saveAndFlush(account);
	}

	@Override
	public void changeRole(long id, Roles roles) {		
	}

	@Override
	public void inactivate(long id) {
		
	}

}
