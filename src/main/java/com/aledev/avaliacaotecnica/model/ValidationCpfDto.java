package com.aledev.avaliacaotecnica.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ValidationCpfDto implements Serializable {
	private static final long serialVersionUID = 633031857370234293L;
	
	private String status;
}
