package org.marking.emaromba.account.service.exception;

import org.marking.emaromba.account.exception.AccountAPIException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public final class InvalidCredentialsException extends AccountAPIException {

	public InvalidCredentialsException() {
		super("Invalid Credentials");
	}

	private static final long serialVersionUID = 1L;
}
