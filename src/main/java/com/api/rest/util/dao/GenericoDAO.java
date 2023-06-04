package com.api.rest.util.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericoDAO<Entity> extends JpaRepository<Entity, Long>
{
	
}
