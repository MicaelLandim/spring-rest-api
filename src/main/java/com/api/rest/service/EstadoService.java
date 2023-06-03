package com.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.api.rest.exception.EstadoNaoEncontradoException;
import com.api.rest.model.EstadoModel;
import com.api.rest.repository.EstadoRepository;

@Service
public class EstadoService
{
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public EstadoModel salvar(EstadoModel estado)
	{
		return estadoRepository.save(estado);
	}
	
	public void excluir(Long estadoId)
	{
		try
		{
			estadoRepository.deleteById(estadoId);
			
		}
		catch (EmptyResultDataAccessException e)
		{
			throw new EstadoNaoEncontradoException(estadoId);
		}
	}
	
	public EstadoModel buscarOuFalhar(Long estadoId)
	{
		return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
	}
	
}
