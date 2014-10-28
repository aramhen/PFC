package com.university.equationsapp.repository;

import java.util.List;

import com.university.equationsapp.domain.Method;

public class InMemoryMethodRepository implements MethodRepository {

	private List<Method> methodList;

	public InMemoryMethodRepository(List<Method> methodList) {
		this.methodList = methodList;
	}

	public List<Method> getMethodList() {
		return this.methodList;
	}

	public <S extends Method> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public <S extends Method> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	public Method findOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean exists(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<Method> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Method> findAll(Iterable<Integer> ids) {
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

	public void delete(Method entity) {
		// TODO Auto-generated method stub

	}

	public void delete(Iterable<? extends Method> entities) {
		// TODO Auto-generated method stub

	}

	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	public Method findByIdMethods(int idMethods) {
		// TODO Auto-generated method stub
		return null;
	}

	public Method findByMethodName(String methodName) {
		// TODO Auto-generated method stub
		return null;
	}

}
