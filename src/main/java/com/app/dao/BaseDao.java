package com.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.app.helper.Builder;

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
	
	public void createUUID() {
		em.createNativeQuery(Builder.build("create extension \"uuid-ossp\""));
	}
}
