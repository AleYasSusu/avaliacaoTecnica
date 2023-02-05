package com.aledev.avaliacaotecnica.v1.exception;

import org.springframework.http.HttpStatus;

public class StaffNotFoundException extends BusinessException {

	private static final long serialVersionUID = 5553707156721755355L;

	public StaffNotFoundException() {
		super("pauta-6", HttpStatus.NOT_FOUND);
	}
}
