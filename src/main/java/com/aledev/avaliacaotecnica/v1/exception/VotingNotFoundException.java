package com.aledev.avaliacaotecnica.v1.exception;

import org.springframework.http.HttpStatus;

public class VotingNotFoundException extends BusinessException {

	private static final long serialVersionUID = 5553707156721755355L;

	public VotingNotFoundException() {
		super("votacao-1", HttpStatus.NOT_FOUND);
	}
}
