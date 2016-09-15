package org.marking.emaromba.account.controller.handler;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {
	
	private static final Logger LOGGER = LogManager.getLogger(RestExceptionHandler.class);
	
	@ExceptionHandler
	public ResponseEntity<ExceptionResponseMessage> handleException(Exception ex, HttpServletRequest httpRequest, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		final String uri = httpRequest.getRequestURI();
		
		ThreadContext.put("_http", "true");
		ThreadContext.put("_uri", uri);
		ThreadContext.put("_httpMethod", httpRequest.getMethod());
		ThreadContext.put("_httpStatusCode", String.valueOf(status.value()));
		
		LOGGER.error("HTTP Error in " + uri, ex);
		
		return ResponseEntity
				.status(status)
				.body(new ExceptionResponseMessage(ex.getMessage(), status.value()));
	}
		
	static class ExceptionResponseMessage {
		
		private String message;
		private int statusCode;
		
		public ExceptionResponseMessage(String message, int statusCode) {
			this.message = message;
			this.statusCode = statusCode;
		}
		
		public String getMessage() {
			return message;
		}
		
		public int getStatusCode() {
			return statusCode;
		}
	}
}
