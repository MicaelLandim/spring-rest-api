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

import com.api.rest.model.CidadeModel;
import com.api.rest.model.RetornoModel;
import com.api.rest.service.CidadeService;
import com.api.rest.to.CidadeTO;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController
{
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping("/pesquisar")
	@ResponseStatus(HttpStatus.OK)
	public RetornoModel<CidadeModel> pesquisar() throws Exception
	{
		RetornoModel<CidadeModel> retornoModel = new RetornoModel<CidadeModel>();
		CidadeModel cidadeModel = new CidadeModel();
		
		List<CidadeTO> cidades = cidadeService.pesquisar();
		
		cidadeModel.setCidades(cidades);
		
		retornoModel.setPayload(cidadeModel);
		
		return retornoModel;
	}
	
	@GetMapping("/visualizar/{idCidade}")
	@ResponseStatus(HttpStatus.OK)
	public RetornoModel<CidadeTO> visualizar(@PathVariable Long idCidade)
	{
		RetornoModel<CidadeTO> retornoModel = new RetornoModel<CidadeTO>();
		
		CidadeTO cidadeTO = cidadeService.visualizar(idCidade);
		
		retornoModel.setPayload(cidadeTO);
		
		return retornoModel;
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.OK)
	public RetornoModel<Object> adicionar(@RequestBody CidadeTO cidadeModel)
	{
		RetornoModel<Object> retornoModel = new RetornoModel<Object>();
		
		cidadeService.salvar(cidadeModel);
		
		retornoModel.setTxRetorno("A cidade foi adicionada com sucesso.");
		
		return retornoModel;
	}
	
	// TODO testar o atualizar
	@PutMapping("/atualizar/{cidadeId}")
	@ResponseStatus(HttpStatus.OK)
	public CidadeTO atualizar(@PathVariable Long cidadeId, @RequestBody CidadeTO cidade)
	{
		CidadeTO cidadeAtual = cidadeService.visualizar(cidadeId);
		
		BeanUtils.copyProperties(cidade, cidadeAtual, "id");
		
		return cidadeService.salvar(cidadeAtual);
	}
	
	@DeleteMapping("/remover/{cidadeId}")
	@ResponseStatus(HttpStatus.OK)
	public RetornoModel<Object> remover(@PathVariable Long cidadeId)
	{
		RetornoModel<Object> retornoModel = new RetornoModel<Object>();
		
		cidadeService.excluir(cidadeId);
		
		retornoModel.setTxRetorno("A cidade foi removida com sucesso.");
		
		return retornoModel;
	}
	
}
