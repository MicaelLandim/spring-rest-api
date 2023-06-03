package com.api.rest.exceptionhandler;

import com.api.rest.exception.NegocioException;

public abstract class EntidadeNaoEncontradaException extends NegocioException
{
	
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String mensagem)
	{
		super(mensagem);
	}
	
}
