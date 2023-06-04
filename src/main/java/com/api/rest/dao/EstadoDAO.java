package com.api.rest.dao;

import org.springframework.stereotype.Repository;

import com.api.rest.to.EstadoTO;
import com.api.rest.util.dao.GenericoDAO;

@Repository
public interface EstadoDAO extends GenericoDAO<EstadoTO>
{
	
}
