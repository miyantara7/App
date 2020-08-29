package com.app.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseService {

	public <T> T readValue(String content, Class<T> kelas)
			throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(content, kelas);
	}
	
	public <T> T setCreatedAtAndUpdateAtBySystem(Class<T> clazz)
			throws  InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		T newClass = clazz.newInstance();
		Method[] methods = clazz.getDeclaredMethods();
		List<Method> listMethod = new ArrayList<>(Arrays.asList(methods));
		getSuperMethod(clazz, listMethod);

		for (int j = 0; j < listMethod.size(); j++) {
			Method m = listMethod.get(j);

			if (m.getName().startsWith("setCreatedAt")) {
				m.invoke(newClass, new Timestamp(System.currentTimeMillis()));
			}else if(m.getName().startsWith("setUpdatedAt")) {
				m.invoke(newClass, new Timestamp(System.currentTimeMillis()));
			}else if(m.getName().startsWith("setUpdatedBy")) {
				m.invoke(newClass, "system");
			}else if(m.getName().startsWith("setCreatedBy")) {
				m.invoke(newClass, "system");
			}

		}
		return newClass;
	}
	
	public <T> T setCreatedAtAndUpdateAtByUser(T entity)
			throws  InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException {
		
		Method[] methods = entity.getClass().getDeclaredMethods();
		List<Method> listMethod = new ArrayList<>(Arrays.asList(methods));
		getSuperMethod(entity.getClass(), listMethod);

		for (int j = 0; j < listMethod.size(); j++) {
			Method m = listMethod.get(j);
			if (m.getName().startsWith("setCreatedAt")) {
				m.invoke(entity, new Timestamp(System.currentTimeMillis()));
			}else if(m.getName().startsWith("setUpdatedAt")) {
				m.invoke(entity, new Timestamp(System.currentTimeMillis()));
			}else if(m.getName().startsWith("setUpdatedBy")) {
				m.invoke(entity, "system");
			}else if(m.getName().startsWith("setCreatedBy")) {
				m.invoke(entity, "system");
			}

		}
		return entity;
	}
	
	private static <T> void getSuperMethod(Class<T> clazz, List<Method> listMethod) {
		Class<? super T> superClazz = clazz.getSuperclass();
		while (superClazz != null) {
			Method[] methodSuper = superClazz.getDeclaredMethods();
			listMethod.addAll(Arrays.asList(methodSuper));
			superClazz = superClazz.getSuperclass();
		}
	}
	
}
