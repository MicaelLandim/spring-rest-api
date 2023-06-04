package com.api.rest.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.dao.EstadoDAO;
import com.api.rest.service.EstadoService;
import com.api.rest.to.EstadoTO;

@RestController
@RequestMapping("/estados")
public class EstadoController
{
	
	@Autowired
	private EstadoDAO estadoDAO;
	
	@Autowired
	private EstadoService cadastroEstado;
	
	@GetMapping("/listar")
	public List<EstadoTO> listar()
	{
		return estadoDAO.findAll();
	}
	
	@GetMapping("/listar/{estadoId}")
	public EstadoTO buscar(@PathVariable Long estadoId)
	{
		return cadastroEstado.buscarEstado(estadoId);
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoTO adicionar(@RequestBody EstadoTO estado)
	{
		return cadastroEstado.salvar(estado);
	}
	
	@PutMapping("/atualizar/{idEstado}")
	public EstadoTO atualizar(@PathVariable Long idEstado, @RequestBody EstadoTO estadoModel)
	{
		EstadoTO estadoAtual = cadastroEstado.buscarEstado(idEstado);
		
		BeanUtils.copyProperties(estadoModel, estadoAtual, "id");
		
		return cadastroEstado.salvar(estadoAtual);
	}
	
	@DeleteMapping("/remover/{idEstado}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long idEstado)
	{
		cadastroEstado.excluir(idEstado);
	}
	
}
