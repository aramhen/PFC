package com.university.equationsapp.service;

import java.util.List;

import com.university.equationsapp.domain.Method;

public class MethodManagerImpl implements MethodManager {

	private static final long serialVersionUID = 1L;
	
	private List<Method> methods;

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

}
