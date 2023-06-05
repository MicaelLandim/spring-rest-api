package com.api.rest.util.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.Assert;

import com.api.rest.util.Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

@NoRepositoryBean
public class GenericoDAOImpl<Entity> extends SimpleJpaRepository<Entity, Long> implements GenericoDAO<Entity>
{
	
	private final EntityManager genericEntityManager;
	
	public GenericoDAOImpl(JpaEntityInformation<Entity, ?> entityInformation, EntityManager entityManager)
	{
		super(entityInformation, entityManager);
		this.genericEntityManager = entityManager;
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}
	
	@Override
	public void delete(Entity entity)
	{
		// beforeDelete(sessao, entity); //TODO
		super.delete(entity);
	}
	
	@Override
	public <S extends Entity> S save(S entity)
	{
		Assert.notNull(entity, "Objeto não pode ser nulo ao salvar");
		
		// beforeSave(sessao, entity); //TODO
		// HibernateUtil.removeNotInsertableUpdatableEntity(entity); //TODO
		super.save(entity);
		// afterSave(sessao, entity, ifNew); //TODO
		
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <S extends Entity> List<S> find(S filter, Object[][] joinsMatrix)
	{
		CriteriaBuilder criteriaBuilder = genericEntityManager.getCriteriaBuilder();
		CriteriaQuery<Entity> criteriaQuery = criteriaBuilder.createQuery(getDomainClass());
		Root<Entity> root = criteriaQuery.from(getDomainClass());
		
		for (Object[] joinVector : joinsMatrix)
		{
			String joinPath = (String) joinVector[0];
			JoinType joinType = (JoinType) joinVector[1];
			
			root.join(joinPath, joinType);
		}
		
		criteriaQuery.select(root).distinct(true);
		
		List<S> list = (List<S>) genericEntityManager.createQuery(criteriaQuery).getResultList();
		
		return list;
	}
	
	@Override
	public <S extends Entity> List<S> findWithoutCircularReferences(S filter, Object[][] joinsMatrix)
	{
		List<S> list = this.find(filter, joinsMatrix);
		
		try
		{
			for (Object object : list)
				Util.retirarReferenciasCiclicasJavassist(object, false);
		}
		catch (Exception e)
		{
		}
		
		return list;
	}
	
}
