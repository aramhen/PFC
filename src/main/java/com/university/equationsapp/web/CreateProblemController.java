package com.university.equationsapp.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.university.equationsapp.domain.Method;
import com.university.equationsapp.service.MethodManager;
import com.university.equationsapp.service.ProblemManager;
import com.university.equationsapp.service.TeacherManager;
import com.university.equationsapp.web.constants.WebConstants;
import com.university.equationsapp.web.dto.CreateProblemDTO;

@Controller
@RequestMapping(value = "/createproblem.htm")
public class CreateProblemController {

	/*
	 * TODO ARH Hay que recoger el profesor actual de alguna forma porque será el que seteemos luego al guardar el
	 * problema. Por cookies o como sea
	 */

	private static final Logger logger = LoggerFactory.getLogger(CreateProblemController.class);

	@Autowired
	private ProblemManager problemManager;

	@Autowired
	private TeacherManager teacherManager;

	@Autowired
	private MethodManager methodManager;

	private ArrayList<Integer> numVariablesList = new ArrayList<Integer>();

	@RequestMapping(method = RequestMethod.GET)
	protected CreateProblemDTO formBackingObject(HttpServletRequest request, Model model) throws ServletException {
		CreateProblemDTO createProblemDTO = new CreateProblemDTO();

		if (logger.isDebugEnabled()) {
			logger.debug("A problem is going to be created");
		}

		return createProblemDTO;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@Valid CreateProblemDTO createProblemDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.error("There has been an error creating the problem");
			logger.error(result.getAllErrors().toString());
			return "createproblem";
		}

		problemManager.createProblem(createProblemDTO);

		System.out.println("-*-*-*-*-* Creating problem" + createProblemDTO.getEndDate());
		return "redirect:/problemcreated.htm";
	}

	/**
	 * Con esta funcion consigo insertar antes de cada llamada a la página (inicial o al recargar tras error) un objeto
	 * en el modelo para poder invocarlo en la jsp
	 */
	@ModelAttribute("methodList")
	public List<Method> populateMethods() {
		return this.methodManager.getMethodList();
	}

	@ModelAttribute("idTeacher")
	public int populateIdTeacher() {
		//TODO ARH IMPORTANTE ESTOY SETEANDO EL IDTEACHER A FUEGO, HAY QUE VER DE DONDE RECUPERARLO
		//We recover the teacher
		int idTeacher = 1;
		return idTeacher;
	}

	@ModelAttribute("numVariablesList")
	public List<Integer> populateVariables() {
		if (numVariablesList.size() == 0) {
			initVariables();
		}
		return numVariablesList;
	}

	public void setProblemManager(ProblemManager problemManager) {
		this.problemManager = problemManager;
	}

	public void setTeacherManager(TeacherManager teacherManager) {
		this.teacherManager = teacherManager;
	}

	/**
	 * Initialize the variables array use for the select in the jsp
	 */
	private void initVariables() {
		final int variablesSize = WebConstants.NUM_VARIABLES.length;
		for (int i = 0; i < variablesSize; i++) {
			numVariablesList.add(WebConstants.NUM_VARIABLES[i]);
		}
	}
}
