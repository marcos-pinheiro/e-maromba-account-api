package org.marking.emaromba.account.repository;

import org.marking.emaromba.account.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	
}
