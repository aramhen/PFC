package com.university.equationsapp.web;

import java.util.ResourceBundle;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.university.equationsapp.common.constants.CommonConstants;
import com.university.equationsapp.common.exceptions.SolveProblemException;
import com.university.equationsapp.domain.Problem;
import com.university.equationsapp.service.AnswerManager;
import com.university.equationsapp.service.ProblemManager;
import com.university.equationsapp.service.TeacherManager;
import com.university.equationsapp.web.dto.StudentSolveProblemDTO;

@Controller
@RequestMapping(value = "/studentsolveproblem.htm")
public class StudentSolveProblemController {

	@Autowired
	private ProblemManager problemManager;

	@Autowired
	private TeacherManager teacherManager;

	@Autowired
	private AnswerManager answerManager;

	private static final Logger logger = LoggerFactory.getLogger(StudentSolveProblemController.class);

	@RequestMapping(method = RequestMethod.GET)
	public StudentSolveProblemDTO printWelcome(@ModelAttribute("idProblem") Object idProblem, BindingResult result,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws SolveProblemException {

		//TODO ARH IMPORTANTE ESTOY SETEANDO EL IDSTUDENT A FUEGO, HAY QUE VER DE DONDE RECUPERARLO
		//We recover the student answers
		int idStudent = 7;

		//We recover the problem the student is going to solve and check for errors
		Problem problem = new Problem();
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Loading data of problem " + idProblem);
			}
			problem = problemManager.findByIdProblems((Integer) idProblem);
		} catch (ClassCastException ccex) {
			ResourceBundle rb =  ResourceBundle.getBundle(CommonConstants.RESOURCE_BUNDLE);
			logger.error("There has been a casting error recovering the idProblem", ccex);
			throw new SolveProblemException(rb.getString(CommonConstants.RB_ERROR_IDPROBLEM_CAST));
		} catch (Exception ex) {
			ResourceBundle rb =  ResourceBundle.getBundle(CommonConstants.RESOURCE_BUNDLE);
			logger.error("There has been an error recovering the idProblem", ex);
			throw new SolveProblemException(rb.getString(CommonConstants.RB_ERROR_IDPROBLEM_CAST));
		} 
		if(problem == null){
			ResourceBundle rb =  ResourceBundle.getBundle(CommonConstants.RESOURCE_BUNDLE);
			logger.error("The problem " + idProblem + " has not been found on the DB");
			throw new SolveProblemException(rb.getString(CommonConstants.RB_ERROR_IDPROBLEM_CAST));
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Recovered problem " + problem.getTitle());
		}
		model.addAttribute("Problem", problem);
		model.addAttribute("idStudent", idStudent);
		//Here we split the equations to show them separated
		StringTokenizer st = new StringTokenizer(problem.getEquations(), CommonConstants.SEPARATOR);
		int i = 1;
		while (st.hasMoreElements()) {
			model.addAttribute("Equation" + i, st.nextElement());
			i++;
		}
		StudentSolveProblemDTO studentSolveProblemDTO = new StudentSolveProblemDTO();
		return studentSolveProblemDTO;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@Valid StudentSolveProblemDTO studentSolveProblemDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.error("Error processing the result" + result.toString());
			return "studentsolveproblem";
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Creating solution for problem " + studentSolveProblemDTO.getIdProblem());
		}
		answerManager.createAnswer(studentSolveProblemDTO);
		return "redirect:/studentproblemsolved.htm";
	}

	@ExceptionHandler(SolveProblemException.class)
	public ModelAndView handleSolveProblemException(SolveProblemException ex) {
		System.out.println("Handling exception");
		ModelAndView model = new ModelAndView("studenterror");
		model.addObject("exception", ex);
		return model;

	}
}
