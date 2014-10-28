package com.university.equationsapp.web;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.university.equationsapp.service.ProblemManager;
import com.university.equationsapp.service.TeacherManager;

@Controller
public class CreateProblemsController {

	private static final Logger logger = LoggerFactory.getLogger(CreateProblemsController.class);

	@Autowired
	private ProblemManager problemManager;

	@Autowired
	private TeacherManager teacherManager;

	@RequestMapping(value = "/createproblem.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String now = (new Date()).toString();
		logger.info("Returning hello view with " + now);
		logger.error("error");
		if (logger.isDebugEnabled()) {
			System.out.println("SI");
		}
		logger.debug("debug");

		Map<String, Object> myModel = new HashMap<String, Object>();
		myModel.put("now", now);
		myModel.put("problems", this.problemManager.getProblemList());
		myModel.put("teachers", this.teacherManager.getTeacherList());

		return new ModelAndView("createproblem", "model", myModel);
	}

	public void setProblemManager(ProblemManager problemManager) {
		this.problemManager = problemManager;
	}

	public void setTeacherManager(TeacherManager teacherManager) {
		this.teacherManager = teacherManager;
	}

}
