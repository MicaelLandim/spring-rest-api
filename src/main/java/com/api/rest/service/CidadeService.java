package com.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.rest.dao.CidadeDAO;
import com.api.rest.exception.APIException;
import com.api.rest.to.CidadeTO;

import jakarta.persistence.criteria.JoinType;

@Service
public class CidadeService
{
	
	@Autowired
	private CidadeDAO cidadeDAO;
	
	public List<CidadeTO> pesquisar()
	{
		CidadeTO cidadeTO = new CidadeTO();
		List<CidadeTO> cidades = cidadeDAO.findWithoutCircularReferences(cidadeTO, new Object[][] { { "estadoTO", JoinType.LEFT } });
		return cidades;
	}
	
	public CidadeTO visualizar(Long idCidade)
	{
		return cidadeDAO.findById(idCidade).orElseThrow(() -> new APIException("A cidade informada não existe."));
	}
	
	public CidadeTO salvar(CidadeTO cidadeTO)
	{
		return cidadeDAO.saveAndFlush(cidadeTO);
	}
	
	public void excluir(Long idCidade)
	{
		try
		{
			cidadeDAO.deleteById(idCidade);
		}
		catch (EmptyResultDataAccessException e)
		{
			throw new APIException("A cidade informada não existe.");
		}
	}
	
}
