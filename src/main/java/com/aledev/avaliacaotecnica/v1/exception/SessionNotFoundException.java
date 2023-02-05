package com.aledev.avaliacaotecnica.v1.exception;

import org.springframework.http.HttpStatus;

public class SessionNotFoundException extends BusinessException {

	private static final long serialVersionUID = 5553707156721755355L;

	public SessionNotFoundException() {
		super("sessao-6", HttpStatus.NOT_FOUND);
	}
}
