package org.marking.emaromba.account.repository;

import java.util.Optional;

import org.marking.emaromba.account.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	
	//Revisar JPQL
	@Query("SELECT account, user, roles FROM UserAccount account JOIN account.user user JOIN account.roles roles WHERE user.email = ?1 AND account.inactive = 0")
	Optional<UserAccount> findByEmail(String email);
	
	@Query("SELECT account, user, roles FROM UserAccount account JOIN account.user user JOIN account.roles roles WHERE user.id = ?1 AND account.inactive = 0")
	UserAccount findActivesById(Long id);
	
}
