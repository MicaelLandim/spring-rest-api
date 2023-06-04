package com.api.rest.exception;

import java.util.Date;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.rest.model.RetornoModel;
import com.api.rest.tipodado.RespostaRest;
import com.api.rest.util.Util;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler
{
	
	private RespostaRest criarRespostaRest(Exception ex)
	{
		RespostaRest respostaRest = RespostaRest.ERRO_INESPERADO;
		
		try
		{
			if (ex instanceof APIException)
				respostaRest = (RespostaRest) Util.getEnumValorDescricao(RespostaRest.class, ex.getMessage());
			
			if (respostaRest == null)
				respostaRest = RespostaRest.OUTROS_ERROS;
		}
		catch (Exception e)
		{
		}
		
		return respostaRest;
	}
	
	private RetornoModel<Object> criarRetornoBuilder(HttpStatus httpStatus, RespostaRest respostaRest)
	{
		return criarRetornoBuilder(httpStatus, respostaRest, null);
	}
	
	private RetornoModel<Object> criarRetornoBuilder(HttpStatus httpStatus, RespostaRest respostaRest, String txRetorno)
	{
		RetornoModel<Object> retornoModel = new RetornoModel<Object>();
		retornoModel.setNrStatus(httpStatus.value());
		retornoModel.setTxStatus(httpStatus.getReasonPhrase());
		retornoModel.setDhRetorno(new Date());
		retornoModel.setNrRetorno(respostaRest.getValor());
		if (txRetorno != null)
			retornoModel.setTxRetorno(txRetorno);
		else if (respostaRest.toString() != null)
			retornoModel.setTxRetorno(respostaRest.toString());
		else
			retornoModel.setTxRetorno(RespostaRest.ERRO_INESPERADO.toString());
		
		return retornoModel;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request)
	{
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		RespostaRest respostaRest = RespostaRest.ERRO_INESPERADO;
		RetornoModel<Object> retorno = criarRetornoBuilder(status, respostaRest);
		
		return handleExceptionInternal(ex, retorno, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(APIException.class)
	public ResponseEntity<?> handleNegocio(APIException ex, WebRequest request)
	{
		HttpStatus status = HttpStatus.BAD_REQUEST;
		RespostaRest respostaRest = criarRespostaRest(ex);
		RetornoModel<Object> retorno = criarRetornoBuilder(status, respostaRest);
		
		if (respostaRest.equals(RespostaRest.OUTROS_ERROS))
			retorno = criarRetornoBuilder(status, respostaRest, ex.getMessage());
		
		return handleExceptionInternal(ex, retorno, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		if (body == null)
		{
			RespostaRest respostaRest = RespostaRest.ERRO_INESPERADO;
			body = criarRetornoBuilder(HttpStatus.valueOf(status.value()), respostaRest);
		}
		else if (body instanceof String)
		{
			RespostaRest respostaRest = RespostaRest.OUTROS_ERROS;
			body = criarRetornoBuilder(HttpStatus.valueOf(status.value()), respostaRest, (String) body);
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException)
		{
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, HttpStatus.valueOf(status.value()), request);
		}
		else if (rootCause instanceof PropertyBindingException)
		{
			return handlePropertyBinding((PropertyBindingException) rootCause, headers, HttpStatus.valueOf(status.value()), request);
		}
		
		RespostaRest respostaRest = RespostaRest.FORMATO_INVALIDO;
		RetornoModel<Object> retorno = criarRetornoBuilder(HttpStatus.valueOf(status.value()), respostaRest);
		
		return handleExceptionInternal(ex, retorno, headers, status, request);
		
	}
	
	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
		
		RespostaRest respostaRest = RespostaRest.FORMATO_INVALIDO;
		String txRetorno = String.format("A propriedade '%s' recebeu o valor '%s', " + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.", path, ex.getValue(), ex.getTargetType().getSimpleName());
		RetornoModel<Object> retorno = criarRetornoBuilder(status, respostaRest, txRetorno);
		
		return handleExceptionInternal(ex, retorno, headers, status, request);
	}
	
	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		String path = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
		
		RespostaRest respostaRest = RespostaRest.FORMATO_INVALIDO;
		String txRetorno = String.format("A propriedade '%s' não existe. " + "Corrija ou remova essa propriedade e tente novamente.", path);
		RetornoModel<Object> retorno = criarRetornoBuilder(status, respostaRest, txRetorno);
		
		return handleExceptionInternal(ex, retorno, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		if (ex instanceof MethodArgumentTypeMismatchException)
		{
			RespostaRest respostaRest = RespostaRest.PARAMETRO_INVALIDO;
			String txRetorno = String.format("O parâmetro de URL '%s' recebeu o valor '%s', " + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.", ((MethodArgumentTypeMismatchException) ex).getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
			RetornoModel<Object> retorno = criarRetornoBuilder(HttpStatus.valueOf(status.value()), respostaRest, txRetorno);
			
			return handleExceptionInternal(ex, retorno, headers, status, request);
		}
		
		return super.handleTypeMismatch(ex, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		RespostaRest respostaRest = RespostaRest.RECURSO_NAO_ENCONTRADO;
		String txRetorno = String.format("O recurso %s, que você tentou acessar, não existe.", ex.getRequestURL());
		RetornoModel<Object> retorno = criarRetornoBuilder(HttpStatus.valueOf(status.value()), respostaRest, txRetorno);
		
		return handleExceptionInternal(ex, retorno, headers, status, request);
	}
	
}
