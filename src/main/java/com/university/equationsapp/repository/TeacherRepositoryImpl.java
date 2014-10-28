package com.university.equationsapp.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.university.equationsapp.domain.Teacher;

public class TeacherRepositoryImpl implements TeacherRepositoryCustom {
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
	public List<Teacher> getTeacherList() {
		return em.createQuery("select t from Teacher t order by t.idTeachers").getResultList();
	}

}
