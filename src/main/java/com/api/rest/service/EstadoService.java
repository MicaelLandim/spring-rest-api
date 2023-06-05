package com.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.rest.dao.EstadoDAO;
import com.api.rest.exception.APIException;
import com.api.rest.to.EstadoTO;

import jakarta.persistence.criteria.JoinType;

@Service
public class EstadoService
{
	
	@Autowired
	private EstadoDAO estadoDAO;
	
	public List<EstadoTO> pesquisar()
	{
		EstadoTO estadoTO = new EstadoTO();
		List<EstadoTO> estados = estadoDAO.findWithoutCircularReferences(estadoTO, new Object[][] { { "cidades", JoinType.LEFT } });
		return estados;
	}
	
	public EstadoTO visualizar(Long idEstado)
	{
		return estadoDAO.findById(idEstado).orElseThrow(() -> new APIException("A cidade informada não existe."));
	}
	
	public EstadoTO salvar(EstadoTO estadoTO)
	{
		return estadoDAO.save(estadoTO);
	}
	
	public void excluir(Long idEstado)
	{
		try
		{
			estadoDAO.deleteById(idEstado);
		}
		catch (EmptyResultDataAccessException e)
		{
			throw new APIException("O estado informado não existe.");
		}
		catch (DataIntegrityViolationException e)
		{
			throw new APIException("Remova todas as cidades referentes ao estado informado.");
		}
	}
	
	public EstadoTO buscarEstado(Long estadoId)
	{
		return estadoDAO.findById(estadoId).orElseThrow(() -> new APIException("O estado informado não existe."));
	}
	
}
