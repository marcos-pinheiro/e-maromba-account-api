package org.marking.emaromba.account.service;

import org.marking.emaromba.account.domain.AuthenticatedUserAccount;
import org.marking.emaromba.account.domain.Roles;
import org.marking.emaromba.account.domain.UserAccount;
import org.marking.emaromba.account.dto.CredentialsDTO;
import org.marking.emaromba.account.service.exception.AccountNotFoundException;
import org.marking.emaromba.account.service.exception.InvalidCredentialsException;
import org.marking.emaromba.account.service.exception.UsernameUnavailableException;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
public interface UserAccountService {
	
	void register(UserAccount account) throws UsernameUnavailableException;
	
	UserAccount changeRole(long id, Roles roles) throws AccountNotFoundException;
	
	AuthenticatedUserAccount signByUserAndPassword(CredentialsDTO user) throws InvalidCredentialsException;
	
	UserAccount getByUserId(long id) throws AccountNotFoundException;
	
	void inactivate(long id) throws AccountNotFoundException;
	
}
