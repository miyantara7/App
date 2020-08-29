package com.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class BaseDao<T> {

	public Class<T> clazz;
	
	@PersistenceContext
	protected EntityManager em;

	public final void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}
	
	public void add(T entity) {
		em.persist(entity);
	}
	
	public void edit(T entity) {
		em.merge(entity);
	}
	
	public void delete(T entity) {
		em.remove(entity);
	}

}
