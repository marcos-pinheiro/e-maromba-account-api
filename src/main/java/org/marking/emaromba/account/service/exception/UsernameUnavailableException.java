package org.marking.emaromba.account.service.exception;

import org.marking.emaromba.account.exception.AccountAPIException;

/**
 * @author Marcos Pinheiro
 * @since 0.0.1
 *
 */
public class UsernameUnavailableException extends AccountAPIException {

	public UsernameUnavailableException() {
		super("Username unavailable");
	}

	private static final long serialVersionUID = 1L;
}
