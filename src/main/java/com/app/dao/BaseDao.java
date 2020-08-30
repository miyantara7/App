package com.app.dao;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public void deleteByid(String id) throws Exception {
		T entity = getById(id);
		delete(entity);
	}
	
	public T getById(String id) throws Exception{
		return em.find(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> getAll() throws Exception{
		List<T> list = em.createQuery("FROM "+clazz.getName())
				.getResultList();

		return !list.isEmpty() ? list: null;
	}
	
	public static <T> List<T> bMapperList(List<Object[]> listMapping, Class<T> clazz, String... sql)
			throws Exception {

		List<T> listResult = new ArrayList<>();

		listMapping.forEach(valDb -> {
			try {
				T newClass = clazz.newInstance();
				Method[] methods = clazz.getDeclaredMethods();
				List<Method> listMethod = new ArrayList<>(Arrays.asList(methods));
				getSuperMethod(clazz, listMethod);

				for (int i = 0; i < valDb.length; i++) {
					Object value = valDb[i];
					String mapping = sql[i];

					invokeMethod(newClass, listMethod, value, mapping);

				}

				listResult.add(newClass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return listResult;
	}
	private static <T> void getSuperMethod(Class<T> clazz, List<Method> listMethod) {
		Class<? super T> superClazz = clazz.getSuperclass();
		while (superClazz != null) {
			Method[] methodSuper = superClazz.getDeclaredMethods();
			listMethod.addAll(Arrays.asList(methodSuper));
			superClazz = superClazz.getSuperclass();
		}
	}

	private static <T> void invokeMethod(T newClass, List<Method> listMethod, Object value, String mapping)
			throws Exception {
		for (int j = 0; j < listMethod.size(); j++) {
			boolean isFound = false;
			Method m = listMethod.get(j);

			if (m.getName().startsWith("set")) {

				for (Parameter p : m.getParameters()) {

					if (p.getName().equalsIgnoreCase(mapping)) {

						isFound = true;
						m.invoke(newClass, value);
						break;
					}

					break;
				}
			}

			if (isFound)
				break;
		}
	}
}
