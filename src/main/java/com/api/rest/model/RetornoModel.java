package com.api.rest.model;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.api.rest.tipodado.RespostaRest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public class RetornoModel<T>
{
	
	private Integer nrStatus;
	
	private String txStatus;
	
	private Date dhRetorno;
	
	private Integer nrRetorno;
	
	private String txRetorno;
	
	private String txToken;
	
	private T payload;
	
	public RetornoModel()
	{
		nrStatus = HttpStatus.OK.value();
		txStatus = HttpStatus.OK.getReasonPhrase();
		nrRetorno = RespostaRest.TRANSACAO_OK.getValor();
		txRetorno = RespostaRest.TRANSACAO_OK.toString();
		dhRetorno = new Date();
	}
}
