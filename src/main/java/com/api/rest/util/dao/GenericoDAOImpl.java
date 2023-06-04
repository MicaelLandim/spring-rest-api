package com.api.rest.util.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.Assert;

import jakarta.persistence.EntityManager;

@NoRepositoryBean
public class GenericoDAOImpl<Entity> extends SimpleJpaRepository<Entity, Long> implements GenericoDAO<Entity>
{
	
	private final EntityManager genericoEntityManager;
	
	public GenericoDAOImpl(JpaEntityInformation<Entity, ?> entityInformation, EntityManager entityManager)
	{
		super(entityInformation, entityManager);
		this.genericoEntityManager = entityManager;
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
	
	// public List<Entity> find(Entity filter) throws Exception
	// {
	// return this.find(filter, null, null, new Object[][] {});
	// }
	//
	// public List<Entity> find(Entity filter, Integer qtMaxRegistros) throws Exception
	// {
	// return this.find(filter, qtMaxRegistros, new Object[][] {});
	// }
	//
	// public List<Entity> find(Entity filter, Object[][] juncoes) throws Exception
	// {
	// return this.find(filter, null, null, juncoes);
	// }
	//
	// public List<Entity> find(Entity filter, Integer qtMaxRegistros, Object[][] juncoes) throws Exception
	// {
	// return find(filter, qtMaxRegistros, null, juncoes);
	// }
	//
	// public List<Entity> find(Entity filter, Order order) throws Exception
	// {
	// return find(filter, null, order);
	// }
	//
	// public List<Entity> find(Entity filter, Order order, Object[][] juncoes) throws Exception
	// {
	// return find(filter, null, order, juncoes);
	// }
	//
	// public List<Entity> find(Entity filter, Integer qtMaxRegistros, Order order) throws Exception
	// {
	// return find(filter, qtMaxRegistros, order, new Object[][] {});
	// }
	//
	// public List<Entity> find(Entity filter, Integer qtMaxRegistros, Order order, Object[][] juncoes) throws Exception
	// {
	// CriteriaBuilder criteriaBuilder = genericoEntityManager.getCriteriaBuilder();
	// CriteriaQuery<Entity> query = (CriteriaQuery<Entity>) criteriaBuilder.createQuery(filter.getClass());
	//
	// Session sessao = getSession();
	// Criteria criteria = sessao.createCriteria(filter.getClass());
	//
	// return find(criteria, filter, qtMaxRegistros, null, order, juncoes);
	// }
	//
	// public List<Entity> find(Entity filter, Integer qtMaxRegistros, Integer nrPagina, Order order, Object[][] juncoes) throws Exception
	// {
	// Session sessao = getSession();
	// Criteria criteria = sessao.createCriteria(filter.getClass());
	//
	// return find(criteria, filter, qtMaxRegistros, nrPagina, order, juncoes);
	// }
	
}
