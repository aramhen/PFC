package com.university.equationsapp.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProblemCreatedController {

	private static final Logger logger = LoggerFactory.getLogger(ProblemCreatedController.class);

	@RequestMapping(value = "/problemcreated.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("The problem has been created");
		}

		Map<String, Object> myModel = new HashMap<String, Object>();
		return new ModelAndView("problemcreated", "model", myModel);
	}

}
