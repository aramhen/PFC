package com.university.equationsapp.web;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.university.equationsapp.domain.Problem;
import com.university.equationsapp.domain.Teacher;
import com.university.equationsapp.repository.InMemoryProblemRepository;
import com.university.equationsapp.repository.InMemoryTeacherRepository;
import com.university.equationsapp.repository.ProblemRepository;
import com.university.equationsapp.repository.TeacherRepository;
import com.university.equationsapp.service.ProblemManagerImpl;
import com.university.equationsapp.service.TeacherManagerImpl;

public class CreateProblemsControllerTests {

	private final static String VIEW_NAME = "createproblem";

	@Test
	public void testHandleRequestView() throws Exception {
		//Esto lo mismo va en el setup
		ProblemRepository problemRepository = new InMemoryProblemRepository(new ArrayList<Problem>());
		TeacherRepository teacherRepository = new InMemoryTeacherRepository(new ArrayList<Teacher>());
		CreateProblemsController controller = new CreateProblemsController();
		ProblemManagerImpl problemManager = new ProblemManagerImpl();
		problemManager.setProblemRepository(problemRepository);
		TeacherManagerImpl teacherManager = new TeacherManagerImpl();
		teacherManager.setTeacherRepository(teacherRepository);
		controller.setProblemManager(problemManager);
		controller.setTeacherManager(teacherManager);
		ModelAndView modelAndView = controller.handleRequest(null, null);
		assertEquals(VIEW_NAME, modelAndView.getViewName());
	}

}
