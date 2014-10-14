package com.university.equationsapp.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class AnswerTests {
	
	 private Answer answer;

	    @Before
	    public void setUp() throws Exception {
	        answer = new Answer();
	    }

	    @Test
	    public void testSetAndGetAnswerDate() {
	    	Date testAnswerDate = new Date();
	        assertNull(answer.getAnswerDate());
	        answer.setAnswerDate(testAnswerDate);
	        assertEquals(testAnswerDate, answer.getAnswerDate());
	    }
	    
	    public void testSetAndGetSolution() {
	    	String testSolution = "stringTestSolution";
	        assertNull(answer.getSolution());
	        answer.setSolution(testSolution);
	        assertEquals(testSolution, answer.getSolution());
	    }
	    
	    public void testSetAndGetSteps() {
	    	String testSteps = "stringTestSteps";
	        assertNull(answer.getSteps());
	        answer.setSteps(testSteps);
	        assertEquals(testSteps, answer.getSteps());
	    }
	    
	    public void testSetAndGetProblemRef() {
	    	Problem testProblemRef = new Problem();
	        assertNull(answer.getProblemRef());
	        answer.setProblemRef(testProblemRef);
	        assertEquals(testProblemRef, answer.getProblemRef());
	    }
	    
	    public void testSetAndGetStudentRef() {
	    	Student testStudentRef = new Student();
	        assertNull(answer.getStudentRef());
	        answer.setStudentRef(testStudentRef);
	        assertEquals(testStudentRef, answer.getStudentRef());
	    }
}
