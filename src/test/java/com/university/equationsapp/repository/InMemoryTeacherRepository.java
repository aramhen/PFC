package com.university.equationsapp.repository;

import java.util.List;

import com.university.equationsapp.domain.Teacher;

public class InMemoryTeacherRepository implements TeacherRepository {

	private List<Teacher> teacherList;

	public InMemoryTeacherRepository(List<Teacher> teacherList) {
		super();
		this.teacherList = teacherList;
	}

	public void setTeacherList(List<Teacher> teacherList) {
		this.teacherList = teacherList;
	}

	public List<Teacher> getTeacherList() {
		return teacherList;
	}

	public <S extends Teacher> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Teacher> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	public Teacher findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<Teacher> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Teacher> findAll(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	public void delete(Teacher entity) {
		// TODO Auto-generated method stub

	}

	public void delete(Iterable<? extends Teacher> entities) {
		// TODO Auto-generated method stub

	}

	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	public Teacher findByName(String teacherName) {
		// TODO Auto-generated method stub
		return null;
	}

}
