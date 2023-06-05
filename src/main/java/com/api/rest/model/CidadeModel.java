package com.api.rest.model;

import java.util.List;

import com.api.rest.to.CidadeTO;

import lombok.Data;

@Data
public class CidadeModel
{
	
	List<CidadeTO> cidades;
}
