package com.university.equationsapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.university.equationsapp.domain.Method;

public interface MethodRepository  extends CrudRepository<Method, Integer>, MethodRepositoryCustom {
	public Method findByIdMethods(int idMethods);
	public Method findByMethodName(String methodName);

}
