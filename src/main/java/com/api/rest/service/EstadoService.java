package com.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.rest.dao.EstadoDAO;
import com.api.rest.exception.APIException;
import com.api.rest.to.EstadoTO;

@Service
public class EstadoService
{
	
	@Autowired
	private EstadoDAO estadoRepository;
	
	public EstadoTO salvar(EstadoTO estadoTO)
	{
		return estadoRepository.save(estadoTO);
	}
	
	public void excluir(Long idEstado)
	{
		try
		{
			estadoRepository.deleteById(idEstado);
		}
		catch (EmptyResultDataAccessException e)
		{
			throw new APIException("O estado informado não existe.");
		}
	}
	
	public EstadoTO buscarEstado(Long estadoId)
	{
		return estadoRepository.findById(estadoId).orElseThrow(() -> new APIException("O estado informado não existe."));
	}
	
}
