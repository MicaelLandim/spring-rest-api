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

import com.api.rest.dao.CidadeDAO;
import com.api.rest.model.RetornoModel;
import com.api.rest.service.CidadeService;
import com.api.rest.to.CidadeTO;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController
{
	
	@Autowired
	private CidadeDAO cidadeDAO;
	
	@Autowired
	private CidadeService cadastroCidadeService;
	
	@GetMapping("/listar")
	public List<CidadeTO> listar()
	{
		return cidadeDAO.findAll();
	}
	
	@GetMapping("/listar/{idCidade}")
	@ResponseStatus(HttpStatus.OK)
	public RetornoModel<CidadeTO> buscar(@PathVariable Long idCidade)
	{
		RetornoModel<CidadeTO> retornoModel = new RetornoModel<CidadeTO>();
		
		CidadeTO cidadeTO = cadastroCidadeService.buscarCidade(idCidade);
		
		retornoModel.setPayload(cidadeTO);
		
		return retornoModel;
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.OK)
	public RetornoModel<Object> adicionar(@RequestBody CidadeTO cidadeModel)
	{
		RetornoModel<Object> retornoModel = new RetornoModel<Object>();
		
		cadastroCidadeService.salvar(cidadeModel);
		
		retornoModel.setTxRetorno("A cidade foi criada com sucesso.");
		
		return retornoModel;
	}
	
	@PutMapping("/atualizar/{cidadeId}")
	@ResponseStatus(HttpStatus.OK)
	public CidadeTO atualizar(@PathVariable Long cidadeId, @RequestBody CidadeTO cidade)
	{
		CidadeTO cidadeAtual = cadastroCidadeService.buscarCidade(cidadeId);
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		return cadastroCidadeService.salvar(cidadeAtual);
	}
	
	@DeleteMapping("/remover/{cidadeId}")
	@ResponseStatus(HttpStatus.OK)
	public void remover(@PathVariable Long cidadeId)
	{
		cadastroCidadeService.excluir(cidadeId);
	}
	
}
