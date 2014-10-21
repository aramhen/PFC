package com.university.equationsapp.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.university.equationsapp.domain.Method;

/**
 * Test of MethodRepository using JUNIT
 * @author Burt
 *
 */
public class MethodRepositoryTests {

	private ApplicationContext context;
	@Autowired
	private MethodRepository methodRepository;

	/**
	 * Method name to test
	 */
	private final static String METHOD_NAME = "Gauss";

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		System.out.println("Entrando");
		context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
		methodRepository = (MethodRepository) context.getBean("methodRepository");
		String[] tmp = context.getBeanDefinitionNames();
		int sizea = tmp.length;
		System.out.println("Tamaño: " + sizea);
		for (int i = 0; i < sizea; i++) {
			System.out.println(tmp[i]);
		}
	}

	//ARH TODO limpiar system.outs
	/**
	 * 
	 */
	@Test
	public void testFindByIdMethods() {
		System.out.println("testFindByIdMethods");
		Method method = methodRepository.findByIdMethods(1);
		assertNotNull(method);
		System.out.println("First Method: " + method.getMethodName());
	}

	/**
	 * 
	 */
	@Test
	public void testfindByMethodName() {
		System.out.println("testfindByMethodName");
		Method method = methodRepository.findByMethodName(METHOD_NAME);
		assertNotNull(method);
		System.out.println("Gauss Method Id: " + method.getIdMethods());
		assertEquals(METHOD_NAME, method.getMethodName());
	}

}
