package org.marking.emaromba.account.repository;

import java.util.Optional;

import org.marking.emaromba.account.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select user from User user WHERE user.email = ?#{[0]}")
	Optional<User> findByEmail(String email);
	
}
