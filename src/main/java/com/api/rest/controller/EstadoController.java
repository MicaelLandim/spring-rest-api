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
import com.api.rest.model.RetornoModel;
import com.api.rest.service.EstadoService;
import com.api.rest.to.EstadoTO;

@RestController
@RequestMapping("/estados")
public class EstadoController
{
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping("/pesquisar")
	@ResponseStatus(HttpStatus.OK)
	public RetornoModel<EstadoModel> pesquisar()
	{
		RetornoModel<EstadoModel> retornoModel = new RetornoModel<EstadoModel>();
		EstadoModel estadoModel = new EstadoModel();
		
		List<EstadoTO> estados = estadoService.pesquisar();
		
		estadoModel.setEstados(estados);
		
		retornoModel.setPayload(estadoModel);
		
		return retornoModel;
		
	}
	
	@GetMapping("/visualizar/{idEstado}")
	@ResponseStatus(HttpStatus.OK)
	public RetornoModel<EstadoTO> visualizar(@PathVariable Long idEstado)
	{
		RetornoModel<EstadoTO> retornoModel = new RetornoModel<EstadoTO>();
		
		EstadoTO estadoTO = estadoService.visualizar(idEstado);
		
		retornoModel.setPayload(estadoTO);
		
		return retornoModel;
		
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.OK)
	public RetornoModel<Object> adicionar(@RequestBody EstadoTO estadoTO)
	{
		RetornoModel<Object> retornoModel = new RetornoModel<Object>();
		
		estadoService.salvar(estadoTO);
		
		retornoModel.setTxRetorno("O estado foi adicionado com sucesso.");
		
		return retornoModel;
	}
	
	// TODO testar o atualizar
	@PutMapping("/atualizar/{idEstado}")
	@ResponseStatus(HttpStatus.OK)
	public EstadoTO atualizar(@PathVariable Long idEstado, @RequestBody EstadoTO estadoTO)
	{
		EstadoTO estadoAtual = estadoService.buscarEstado(idEstado);
		
		BeanUtils.copyProperties(estadoTO, estadoAtual, "id");
		
		return estadoService.salvar(estadoAtual);
	}
	
	@DeleteMapping("/remover/{idEstado}")
	@ResponseStatus(HttpStatus.OK)
	public RetornoModel<Object> remover(@PathVariable Long idEstado)
	{
		RetornoModel<Object> retornoModel = new RetornoModel<Object>();
		
		estadoService.excluir(idEstado);
		
		retornoModel.setTxRetorno("O estado foi removido com sucesso.");
		
		return retornoModel;
	}
	
}
