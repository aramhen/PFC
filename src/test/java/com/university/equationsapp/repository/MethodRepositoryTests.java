package com.university.equationsapp.repository;

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

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class })
@Transactional
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class MethodRepositoryTests {

	@Autowired
	private MethodRepository methodRepository;

	private final static String METHOD_NAME = "Gauss";

	//ARH TODO limpiar system.outs
	@Test
	public void testFindByIdMethods() {
		System.out.println("testFindByIdMethods");
		Method method = methodRepository.findByIdMethods(1);
		Assert.assertNotNull(method);
		System.out.println("First Method: " + method.getMethodName());
	}

	@Test
	public void testfindByMethodName() {
		System.out.println("testfindByMethodName");
		Method method = methodRepository.findByMethodName(METHOD_NAME);
		Assert.assertNotNull(method);
		System.out.println("Gauss Method Id: " + method.getIdMethods());
		Assert.assertEquals(METHOD_NAME, method.getMethodName());
	}

}
