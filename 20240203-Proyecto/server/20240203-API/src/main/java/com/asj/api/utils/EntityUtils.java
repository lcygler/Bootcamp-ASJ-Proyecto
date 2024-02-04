package com.asj.api.utils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class EntityUtils {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public <T> T refresh(T entity) {
		entityManager.refresh(entity);
		return entity;
	}

	@Transactional
	public <T> T flushAndRefresh(T entity) {
		entityManager.flush();
		entityManager.refresh(entity);
		return entity;
	}

}

//Instrucciones
//1. Importar:
//import com.asj.api.utils.EntityUtils;
//2. Inyectar dependencias:
//@Autowired
//private EntityUtils entityUtils;
