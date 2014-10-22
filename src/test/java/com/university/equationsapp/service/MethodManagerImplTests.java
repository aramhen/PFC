package com.university.equationsapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.university.equationsapp.domain.Method;

public class MethodManagerImplTests {

	private MethodManagerImpl methodManager;

	private List<Method> methods;
	private static int METHODS_NUMBER = 2;
	private static String METHOD_GAUSS = "Gauss";
	private static String METHOD_ADDITION = "Addition";

	@Before
	public void setUp() throws Exception {
		methodManager = new MethodManagerImpl();

		//Set up data test
		methods = new ArrayList<Method>();
		Method method = new Method();
		method.setMethodName(METHOD_GAUSS);
		methods.add(method);
		method = new Method();
		method.setMethodName(METHOD_ADDITION);
		methods.add(method);
		methodManager.setMethods(methods);
	}

	@Test
	public void testGetProductsWithNoProducts() {
		methodManager = new MethodManagerImpl();
		assertNull(methodManager.getMethods());
	}

	@Test
	public void testGetMethods() {
		List<Method> methods = methodManager.getMethods();
		assertNotNull(methods);
		assertEquals(METHODS_NUMBER, methodManager.getMethods().size());

		Method method = methods.get(0);
		assertEquals(METHOD_GAUSS, method.getMethodName());

		method = methods.get(1);
		assertEquals(METHOD_ADDITION, method.getMethodName());
	}

}
