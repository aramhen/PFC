package com.university.equationsapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.university.equationsapp.domain.Answer;

public class AnswerRepositoryImpl implements AnswerRepositoryCustom {
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
	public List<Answer> getAnswerList() {
		return em.createQuery("select a from Answer a order by a.idAnswers").getResultList();
	}

}
