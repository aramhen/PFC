package com.university.equationsapp.web;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.university.equationsapp.service.MethodManager;
import com.university.equationsapp.service.ProblemManager;
import com.university.equationsapp.service.TeacherManager;

@Controller
@RequestMapping(value = "/listanswer.htm")
public class ListAnswerController {

	@Autowired
	private ProblemManager problemManager;

	@Autowired
	private TeacherManager teacherManager;

	@Autowired
	private MethodManager methodManager;

	private ArrayList<Integer> numVariablesList = new ArrayList<Integer>();

	private static final Logger logger = LoggerFactory.getLogger(ListAnswerController.class);

	@RequestMapping(method = RequestMethod.GET)
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

		return new ModelAndView("listanswer", "model", myModel);
	}

}
