package org.marking.emaromba.account.exception;

public class AccountAPIException extends Exception {

	public AccountAPIException() {
		super();
	}

	public AccountAPIException(String message) {
		super(message);
	}
	
	public AccountAPIException(Throwable throwable) {
		super(throwable);
	}
	
	public AccountAPIException(String message, Throwable throwable) {
		super(message, throwable);
	}
	

	private static final long serialVersionUID = 1L;
}
