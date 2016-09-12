package org.marking.emaromba.account.repository;

import java.util.Optional;

import org.marking.emaromba.account.domain.User;
import org.marking.emaromba.account.util.monitor.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@Repository @Monitor
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select user from User user WHERE user.email = ?#{[0]}")
	Optional<User> findByEmail(String email);
	
}
