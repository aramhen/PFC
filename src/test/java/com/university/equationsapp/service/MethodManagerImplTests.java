package com.university.equationsapp.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.university.equationsapp.domain.Method;
import com.university.equationsapp.repository.InMemoryMethodRepository;
import com.university.equationsapp.repository.MethodRepository;

public class MethodManagerImplTests {

//	private ApplicationContext context;
	private MethodManagerImpl methodManager;
	private MethodRepository methodRepository;

//	private List<Method> methods;
	private static int METHODS_NUMBER = 2;
	private static String METHOD_GAUSS = "Gauss";
	private static String METHOD_ADDITION = "Addition";

//	@InjectMocks
//    final private MethodManagerImpl methodManagerk = new MethodManagerImpl();

	@Before
	public void setUp() throws Exception {
//		context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
//		methodRepository = (MethodRepository) context.getBean("methodRepository");
		methodManager = new MethodManagerImpl();

		//Set up data test
		List<Method> methods = new ArrayList<Method>();
		Method method = new Method();
		method.setName(METHOD_GAUSS);
		methods.add(method);
		method = new Method();
		method.setName(METHOD_ADDITION);
		methods.add(method);
		methodRepository = new InMemoryMethodRepository(methods);
		methodManager.setMethodRepository(methodRepository);

//		Mockito.when(methodManager.getMethods()).thenReturn(methods);
	}

	@Test
	public void testGetMethodsNull() {
		methodManager = new MethodManagerImpl();
		InMemoryMethodRepository tmpRep = new InMemoryMethodRepository(null);
		methodManager.setMethodRepository(tmpRep);
		assertNull(methodManager.getMethodList());
	}

	@Test
	public void testGetMethods() {
		List<Method> methods = methodManager.getMethodList();
		assertNotNull(methods);
		assertEquals(METHODS_NUMBER, methodManager.getMethodList().size());

		Method method = methods.get(0);
		assertEquals(METHOD_GAUSS, method.getName());

		method = methods.get(1);
		assertEquals(METHOD_ADDITION, method.getName());
	}

}
