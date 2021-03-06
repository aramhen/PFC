package com.university.equationsapp.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.university.equationsapp.domain.Answer;
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
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws SolveProblemException, ParseException {

		//TODO You have to implement here the recover of the student when this app is installed in the server
		//We recover the student answers and store it in session to recover it later
		int idStudent = 7;
		request.getSession().setAttribute("idStudent", idStudent);

		//We recover the problem the student is going to solve and check for errors
		//When the users reload the page the ModelAttribute idProblem is missing, so we have to check if we have stored it in session
		//We also check if the problem is uniqueAnswer and the student has already solved it or if the problem can be answered on this date
		Problem problem = new Problem();
		try {
			int problemId = (Integer) idProblem;
		} catch (ClassCastException ccex) {
			if (request.getSession().getAttribute("idProblem") != null) {
				idProblem = request.getSession().getAttribute("idProblem");
				if (logger.isDebugEnabled()) {
					logger.debug("We recover the idProblem, the user have reloaded the page");
				}
			} else {
				logger.error("There has been a casting error recovering the idProblem", ccex);
				throw new SolveProblemException(CommonConstants.RB_ERROR_IDPROBLEM_CAST);
			}
		}

		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Loading data of problem " + idProblem);
			}
			problem = problemManager.findByIdProblems((Integer) idProblem);
			//We store the idProblem key in the session in case the user reload the page
			request.getSession().setAttribute("idProblem", idProblem);
		} catch (Exception ex) {
			logger.error("There has been an error recovering the idProblem", ex);
			throw new SolveProblemException(CommonConstants.RB_ERROR_IDPROBLEM_CAST);
		}
		if (problem == null) {
			logger.error("The problem " + idProblem + " has not been found on the DB");
			throw new SolveProblemException(CommonConstants.RB_ERROR_IDPROBLEM_CAST);
		}
		if (problem.isUniqueAnswer()) {
			List<Answer> res = answerManager.findByProblemRefAndStudentRef((Integer) idProblem, idStudent);
			if (res.size() > 0) {
				logger.error("The problem " + idProblem + " has already been answered by the student " + idStudent);
				throw new SolveProblemException(CommonConstants.RB_ERROR_IDPROBLEM_ALREADY_SOLVED);
			}
		}
		SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
		Date actualDate = sdf.parse(sdf.format(new Date()));
		Date initDate = sdf.parse(sdf.format(problem.getInitDate()));
		Date endDate = sdf.parse(sdf.format(problem.getEndDate()));
		if ((actualDate.before(initDate)) || (actualDate.after(endDate))){
			logger.error("The problem " + idProblem + " is trying to be answered out of date by idStudent " + idStudent);
			throw new SolveProblemException(CommonConstants.RB_ERROR_IDPROBLEM_OUT_DATE);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Recovered problem " + problem.getTitle());
		}
		model.addAttribute("Problem", problem);
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
	public String onSubmit(@Valid StudentSolveProblemDTO studentSolveProblemDTO, BindingResult result, Model model,
			HttpServletRequest request) {
		if (result.hasErrors()) {
			logger.error("Error processing the result" + result.toString());
			return "studentsolveproblem";
		}

		int idProblem = (Integer) request.getSession().getAttribute("idProblem");
		int idStudent = (Integer) request.getSession().getAttribute("idStudent");

		//If the user has answered the problem, we can clear the idProblem and idStudent session objects
		request.getSession().removeAttribute("idProblem");
		request.getSession().removeAttribute("idStudent");

		if (logger.isDebugEnabled()) {
			logger.debug("Creating solution for problem " + idProblem + " by student " + idStudent);
		}
		answerManager.createAnswer(studentSolveProblemDTO, idProblem, idStudent);
		return "redirect:/studentproblemsolved.htm";
	}

	/**
	 * 
	 * We handle the SolveProblemException we have thrown if anything has gone wrong
	 */
	@ExceptionHandler(SolveProblemException.class)
	public ModelAndView handleSolveProblemExceptions(SolveProblemException ex) {
		logger.debug("Handling Exception " + ex.getMessage());

		ModelAndView model = new ModelAndView("error/errorstudent");
		model.addObject("exception", ex);
		return model;
	}
}
