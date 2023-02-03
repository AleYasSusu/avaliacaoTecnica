package com.aledev.avaliacaotecnica.exception;

import org.springframework.http.HttpStatus;

public class VoteAlreadyExistsException extends BusinessException {

	private static final long serialVersionUID = 5553707156721755355L;

	public VoteAlreadyExistsException() {
		super("voto-7", HttpStatus.ALREADY_REPORTED);
	}
}
