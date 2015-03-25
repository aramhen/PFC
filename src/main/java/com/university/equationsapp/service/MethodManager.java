package com.university.equationsapp.service;

import java.io.Serializable;
import java.util.List;

import com.university.equationsapp.domain.Method;

public interface MethodManager extends Serializable {

	public List<Method> getMethodList();

	public Method getMethod(String name);

}
