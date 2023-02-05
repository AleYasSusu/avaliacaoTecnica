package com.aledev.avaliacaotecnica.v1.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@Builder
@EqualsAndHashCode
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = -4834704685736900213L;

    private String errorMessage;

}
