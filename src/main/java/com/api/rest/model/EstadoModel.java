package com.api.rest.model;

import java.util.List;

import com.api.rest.to.EstadoTO;

import lombok.Data;

@Data
public class EstadoModel
{
	
	List<EstadoTO> estados;
}
