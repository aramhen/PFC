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
		//This method is of spring framework and we don't need to test it
		return null;
	}

	public <S extends Method> Iterable<S> save(Iterable<S> entities) {
		//This method is of spring framework and we don't need to test it
		return null;
	}

	public Method findOne(Integer id) {
		//This method is of spring framework and we don't need to test it
		return null;
	}

	public boolean exists(Integer id) {
		//This method is of spring framework and we don't need to test it
		return false;
	}

	public Iterable<Method> findAll() {
		//This method is of spring framework and we don't need to test it
		return null;
	}

	public Iterable<Method> findAll(Iterable<Integer> ids) {
		//This method is of spring framework and we don't need to test it
		return null;
	}

	public long count() {
		//This method is of spring framework and we don't need to test it
		return 0;
	}

	public void delete(Integer id) {
		//This method is of spring framework and we don't need to test it

	}

	public void delete(Method entity) {
		//This method is of spring framework and we don't need to test it

	}

	public void delete(Iterable<? extends Method> entities) {
		//This method is of spring framework and we don't need to test it

	}

	public void deleteAll() {
		//This method is of spring framework and we don't need to test it

	}

	public Method findByIdMethods(int idMethods) {
		//This method is of spring framework and we don't need to test it
		return null;
	}

	public Method findByName(String name) {
		//This method is of spring framework and we don't need to test it
		return null;
	}

}
