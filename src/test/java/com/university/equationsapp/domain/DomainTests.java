package com.university.equationsapp.domain;

import java.beans.IntrospectionException;

import org.junit.Test;

import com.university.equationsapp.util.JavaBeanTester;

public class DomainTests {

	@Test
	public void AnswerTest() throws IntrospectionException {
		JavaBeanTester.test(Answer.class);
	}

	@Test
	public void MethodTest() throws IntrospectionException {
		JavaBeanTester.test(Method.class);
	}

	@Test
	public void ProblemTest() throws IntrospectionException {
		JavaBeanTester.test(Problem.class);
	}

	@Test
	public void StudentTest() throws IntrospectionException {
		JavaBeanTester.test(Student.class);
	}

	@Test
	public void TeacherTest() throws IntrospectionException {
		JavaBeanTester.test(Teacher.class);
	}

}
