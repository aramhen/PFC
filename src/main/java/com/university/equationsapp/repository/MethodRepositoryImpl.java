package com.university.equationsapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.university.equationsapp.domain.Method;

public class MethodRepositoryImpl implements MethodRepositoryCustom {

	private EntityManager em = null;

	/*
	 * Sets the entity manager.
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Method> getMethodList() {
		return em.createQuery("select m from Method m order by m.idMethods").getResultList();
	}

}
