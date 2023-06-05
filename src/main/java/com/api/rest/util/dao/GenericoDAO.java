package com.api.rest.util.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericoDAO<Entity> extends JpaRepository<Entity, Long>
{
	
	<S extends Entity> List<S> find(S filter, Object[][] joinsMatrix);
	
	<S extends Entity> List<S> findWithoutCircularReferences(S filter, Object[][] joinsMatrix);
}
