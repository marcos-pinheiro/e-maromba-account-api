package org.marking.emaromba.account.service.exception;

import org.marking.emaromba.account.exception.AccountAPIException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public final class AccountNotFoundException extends AccountAPIException {

	public AccountNotFoundException() {
		super("User account not found");
	}

	private static final long serialVersionUID = 1L;
}
