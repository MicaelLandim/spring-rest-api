package com.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.rest.dao.CidadeDAO;
import com.api.rest.exception.APIException;
import com.api.rest.to.CidadeTO;

@Service
public class CidadeService
{
	
	@Autowired
	private CidadeDAO cidadeRepository;
	
	public CidadeTO salvar(CidadeTO cidadeTO)
	{
		try
		{
			return cidadeRepository.saveAndFlush(cidadeTO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void excluir(Long idCidade)
	{
		try
		{
			cidadeRepository.deleteById(idCidade);
		}
		catch (EmptyResultDataAccessException e)
		{
			throw new APIException("A cidade informada não existe.");
		}
	}
	
	public CidadeTO buscarCidade(Long idCidade)
	{
		return cidadeRepository.findById(idCidade).orElseThrow(() -> new APIException("A cidade informada não existe."));
	}
	
}
