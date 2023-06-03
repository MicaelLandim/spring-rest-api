package com.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.rest.exception.CidadeNaoEncontradaException;
import com.api.rest.model.CidadeModel;
import com.api.rest.repository.CidadeRepository;

@Service
public class CidadeService
{
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public CidadeModel salvar(CidadeModel cidade)
	{
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId)
	{
		try
		{
			cidadeRepository.deleteById(cidadeId);
		}
		catch (EmptyResultDataAccessException e)
		{
			throw new CidadeNaoEncontradaException(cidadeId);
		}
	}
	
	public CidadeModel buscarOuFalhar(Long cidadeId)
	{
		return cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
	}
	
}
