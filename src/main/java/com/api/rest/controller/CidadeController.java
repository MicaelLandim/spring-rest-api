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

import com.api.rest.exception.EstadoNaoEncontradoException;
import com.api.rest.exception.NegocioException;
import com.api.rest.model.CidadeModel;
import com.api.rest.repository.CidadeRepository;
import com.api.rest.service.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController
{
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cadastroCidadeService;
	
	@GetMapping("/listar")
	public List<CidadeModel> listar()
	{
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/listar/{idCidade}")
	public CidadeModel buscar(@PathVariable Long idCidade)
	{
		return cadastroCidadeService.buscarOuFalhar(idCidade);
	}
	
	@PostMapping("/adicionar")
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody CidadeModel cidadeModel)
	{
		try
		{
			return cadastroCidadeService.salvar(cidadeModel);
		}
		catch (EstadoNaoEncontradoException e)
		{
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/atualizar/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody CidadeModel cidade)
	{
		try
		{
			CidadeModel cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
			
			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			
			return cadastroCidadeService.salvar(cidadeAtual);
		}
		catch (EstadoNaoEncontradoException e)
		{
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@DeleteMapping("/remover/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId)
	{
		cadastroCidadeService.excluir(cidadeId);
	}
	
}
