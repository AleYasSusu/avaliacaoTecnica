package com.aledev.avaliacaotecnica.v1.exception;

import org.springframework.http.HttpStatus;

public class SessionTimeOutException extends BusinessException {

	private static final long serialVersionUID = 5553707156721755355L;

	public SessionTimeOutException() {
		super("session-7", HttpStatus.REQUEST_TIMEOUT);
	}
}
