package com.api.rest.exception;

import com.api.rest.tipodado.RespostaRest;

public class APIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public APIException(RespostaRest mensagem) {
		this(mensagem.toString());
	}
	
	public APIException(String mensagem) {
		super(mensagem);
	}
	
	public APIException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
