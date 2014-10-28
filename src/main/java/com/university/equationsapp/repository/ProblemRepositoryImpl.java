package com.university.equationsapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.university.equationsapp.domain.Problem;

public class ProblemRepositoryImpl implements ProblemRepositoryCustom {

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
	public List<Problem> getProblemList() {
		return em.createQuery("select p from Problem p order by p.idProblems").getResultList();
	}

}
