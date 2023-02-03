package com.aledev.avaliacaotecnica.exception;

import org.springframework.http.HttpStatus;

public class CpfUnableException extends BusinessException {

	private static final long serialVersionUID = 5553707156721755355L;

	public CpfUnableException() {
		super("validacao-cpf-unable", HttpStatus.UNAUTHORIZED);
	}
}
