package com.api.rest.tipodado;

public enum RespostaRest
{
	
	TRANSACAO_OK(0, "Transacao OK"),
	
	OUTROS_ERROS(9999, "{0}")
	
	;
	
	private Integer valor;
	
	private String descricao;
	
	private RespostaRest(Integer valor, String descricao)
	{
		this.valor = valor;
		this.descricao = descricao;
	}
	
	public Integer getValor()
	{
		return valor;
	}
	
	public String toString()
	{
		return this.descricao;
	}
	
}
