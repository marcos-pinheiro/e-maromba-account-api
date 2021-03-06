package org.marking.emaromba.account.service.exception;

import org.marking.emaromba.account.exception.AccountAPIException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
@ResponseStatus(HttpStatus.CONFLICT)
public final class UsernameUnavailableException extends AccountAPIException {

	public UsernameUnavailableException() {
		super("Username unavailable");
	}

	private static final long serialVersionUID = 1L;
}
