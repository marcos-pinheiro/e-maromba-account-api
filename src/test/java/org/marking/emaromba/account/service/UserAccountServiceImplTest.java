package org.marking.emaromba.account.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.marking.emaromba.account.domain.Role;
import org.marking.emaromba.account.domain.User;
import org.marking.emaromba.account.domain.UserAccount;
import org.marking.emaromba.account.repository.UserAccountRepository;
import org.marking.emaromba.account.repository.UserRepository;
import org.marking.emaromba.account.service.exception.AccountNotFoundException;
import org.marking.util.types.Password;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserAccountServiceImplTest {

	private UserAccountServiceImpl userAccountService;
	
	@MockBean UserRepository userRepository;
	@MockBean UserAccountRepository accountRepository;

	
	@Before
	public void setup() {
		this.userAccountService = new UserAccountServiceImpl(accountRepository, userRepository, null);
	}
	

	@Test
	public void testAccountInactivation() throws AccountNotFoundException {
		final long id = 666L;
		final UserAccount account = UserAccount.of(new User("test@test.com", Password.of("secret"), "Teste da silva"), Role.ADMIN);
		
		
		when(accountRepository.findActivesById(id))
			.thenReturn(account);

		when(accountRepository.save(account))
			.thenReturn(account);
	
		
		userAccountService.inactivate(id);
		
		assertEquals(true, account.isInactive());
		verify(accountRepository).save(account);
	}
	
	
	@Test(expected = AccountNotFoundException.class)
	public void testAccountInactivationButAccountNotFound() throws AccountNotFoundException {
		final long id = 666L;
		
		when(accountRepository.findActivesById(id))
			.thenReturn(null);
		
		userAccountService.inactivate(id);
	}

}
