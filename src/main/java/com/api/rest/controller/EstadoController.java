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

import com.api.rest.model.EstadoModel;
import com.api.rest.repository.EstadoRepository;
import com.api.rest.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController
{
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoService cadastroEstado;
	
	@GetMapping("/listar")
	public List<EstadoModel> listar()
	{
		return estadoRepository.findAll();
	}
	
	@GetMapping("/listar/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId)
	{
		return cadastroEstado.buscarOuFalhar(estadoId);
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody EstadoModel estado)
	{
		return cadastroEstado.salvar(estado);
	}
	
	@PutMapping("/atualizar/{idEstado}")
	public EstadoModel atualizar(@PathVariable Long idEstado, @RequestBody EstadoModel estadoModel)
	{
		EstadoModel estadoAtual = cadastroEstado.buscarOuFalhar(idEstado);
		
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
