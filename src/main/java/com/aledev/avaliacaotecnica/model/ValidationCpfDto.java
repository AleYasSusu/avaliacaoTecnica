package com.aledev.avaliacaotecnica.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationCpfDto implements Serializable {
	private static final long serialVersionUID = 633031857370234293L;
	
	private String status;
}
