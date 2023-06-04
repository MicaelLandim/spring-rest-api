package com.api.rest.tipodado;

public enum RespostaRest {

	TRANSACAO_OK(0, "Transacao OK"),

	ERRO_INESPERADO(1, "O sistema apresentou uma inconsistência. Contate o administrador do sistema."),
	
	RECURSO_NAO_ENCONTRADO(2, "O recurso não foi encontrado, por favor tente novamente."),
	
	FORMATO_INVALIDO(3, "Erro ao processar requisição. Verifique erro de sintaxe."),
	
	PARAMETRO_INVALIDO(4, "O parâmetro de URL recebeu o valor de um tipo inválido."),

	OUTROS_ERROS(9999, null)

	;

	private Integer valor;

	private String descricao;

	private RespostaRest(Integer valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public Integer getValor() {
		return valor;
	}

	public String toString() {
		return this.descricao;
	}

}
