package org.marking.emaromba.account.service;

import org.marking.emaromba.account.entity.Roles;
import org.marking.emaromba.account.entity.UserAccount;
import org.marking.emaromba.account.service.exception.UsernameUnavailableException;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
public interface UserAccountService {
	
	void register(UserAccount account) throws UsernameUnavailableException;
	
	void changeRole(long id, Roles roles);
	
	void inactivate(long id);
	
}
