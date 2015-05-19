package com.university.equationsapp.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.university.equationsapp.domain.Method;

/**
 * Test of MethodRepository using spring-test
 * 
 * @author Burt
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class MethodRepositoryTestsSpring {

	@Autowired
	private MethodRepository methodRepository;

	/**
	 * Data to test
	 */
	private final static int GAUSS_METHOD_ID = 4;
	private final static String GAUSS_METHOD_NAME = "Gauss";
	private final static int NUMBER_OF_METHODS = 5;

	//ARH TODO limpiar system.outs
	/**
	 * 
	 */
	@Test
	public void testFindByIdMethods() {
		System.out.println("testFindByIdMethods");
		Method method = methodRepository.findByIdMethods(GAUSS_METHOD_ID);
		Assert.assertNotNull(method);
		Assert.assertEquals(GAUSS_METHOD_NAME, method.getName());
		System.out.println("First Method: " + method.getName());
	}

	/**
	 * 
	 */
	@Test
	public void testfindByName() {
		System.out.println("testfindByName");
		Method method = methodRepository.findByName(GAUSS_METHOD_NAME);
		Assert.assertNotNull(method);
		System.out.println("Gauss Method Id: " + method.getIdMethods());
		Assert.assertEquals(GAUSS_METHOD_ID, method.getIdMethods());
	}

	/**
	 * 
	 */
	@Test
	public void testgetMethodList() {
		System.out.println("testgetMethodList");
		List<Method> methodList = methodRepository.getMethodList();
		Assert.assertNotNull(methodList);
		System.out.println("Gauss Method Id: " + methodList);
		Assert.assertEquals(NUMBER_OF_METHODS, methodList.size());
	}

}
