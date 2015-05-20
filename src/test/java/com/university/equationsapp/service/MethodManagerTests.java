package com.university.equationsapp.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.university.equationsapp.domain.Method;
import com.university.equationsapp.web.HomeController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/test-context.xml" })
public class MethodManagerTests {

	//Test Data
	//Test Data
	private final static int GAUSS_METHOD_ID = 1;
	private final static String GAUSS_METHOD_NAME = "Gauss";
	private final static int ADDITION_METHOD_ID = 2;
	private final static String ADDITION_METHOD_NAME = "Addition";
	private final static int GAUSS_JORDAN_METHOD_ID = 3;
	private final static String GAUSS_JORDAN_METHOD_NAME = "Gauss-Jordan";
	private final static int NUMBER_OF_METHODS = 3;

	@Mock
	MethodManager methodManager;

	@InjectMocks
	HomeController homeController;

	Method m;
	List<Method> mList;

	@Before
	public void setup() {

		// this must be called for the @Mock annotations above to be processed
		// and for the mock service to be injected into the controller under
		// test.
		MockitoAnnotations.initMocks(this);

		mList = new ArrayList<Method>();
		m = new Method();
		m.setIdMethods(ADDITION_METHOD_ID);
		m.setName(ADDITION_METHOD_NAME);
		mList.add(m);
		m = new Method();
		m.setIdMethods(GAUSS_JORDAN_METHOD_ID);
		m.setName(GAUSS_JORDAN_METHOD_NAME);
		mList.add(m);
		m = new Method();
		m.setIdMethods(GAUSS_METHOD_ID);
		m.setName(GAUSS_METHOD_NAME);
		mList.add(m);
	}

	@Test
	public void testfindByName() {
		when(methodManager.findByName(GAUSS_METHOD_NAME)).thenReturn(m);
		Method method = methodManager.findByName(GAUSS_METHOD_NAME);
		Assert.assertNotNull(method);
		Assert.assertEquals(GAUSS_METHOD_ID, method.getIdMethods());
	}

	@Test
	public void testgetMethodList() {
		when(methodManager.getMethodList()).thenReturn(mList);
		List<Method> methodList = methodManager.getMethodList();
		Assert.assertNotNull(methodList);
		Assert.assertEquals(NUMBER_OF_METHODS, methodList.size());
		Assert.assertTrue(methodList.get(0).getIdMethods() == ADDITION_METHOD_ID);
	}

}
