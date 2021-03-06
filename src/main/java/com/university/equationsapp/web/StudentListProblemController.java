package com.university.equationsapp.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.university.equationsapp.common.constants.CommonConstants;
import com.university.equationsapp.common.utils.WebUtils;
import com.university.equationsapp.domain.Problem;
import com.university.equationsapp.service.AnswerManager;
import com.university.equationsapp.service.ProblemManager;
import com.university.equationsapp.web.json.DTOToJsonObject;
import com.university.equationsapp.web.json.StudentListProblemJsonDTO;

@Controller
public class StudentListProblemController {

	private static final Logger logger = LoggerFactory.getLogger(StudentListProblemController.class);

	@Autowired
	private ProblemManager problemManager;

	@Autowired
	private AnswerManager answerManager;

	@RequestMapping(value = "/studentlistproblem.htm", method = RequestMethod.GET)
	public String printWelcome(@ModelAttribute("Problem") Problem problem, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		return "studentlistproblem";

	}

	@RequestMapping(value = "/studentlistproblem.htm", method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("Problem") Problem problem, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Error processing the result");
			return "studentlistproblem";
		}

		int idProblem = problem.getIdProblems();
		if (logger.isDebugEnabled()) {
			logger.debug("Solving problem " + idProblem);
		}

		//We send the idProblem to the studentsolveproblem
		redirectAttributes.addFlashAttribute("idProblem", idProblem);
		return "redirect:/studentsolveproblem.htm";
	}

	/**
	 * Returns to the datatable the problem list
	 */
	@RequestMapping(value = "/studentlistproblempagination.htm", method = RequestMethod.GET, produces = "application/json", headers = "Accept=*/*")
	public @ResponseBody String springPaginationDataTables(HttpServletRequest request, HttpServletResponse response) {
		//Need to fix special ascii characters showed in datatable
		response.setContentType("application/json; charset=iso-8859-1");

		//Create page list data
		List<StudentListProblemJsonDTO> problemsList = new ArrayList<StudentListProblemJsonDTO>();
		try {
			problemsList = getListProblemDTO(problemManager.getProblemList());
		} catch (Exception ex) {
			logger.error("There has been an error recovering the problems", ex);
		}

		int problemSize = problemsList.size();
		DTOToJsonObject<StudentListProblemJsonDTO> problemJsonObject = new DTOToJsonObject<StudentListProblemJsonDTO>();
		//Set Total display record
		problemJsonObject.setiTotalDisplayRecords(problemSize);
		//Set Total record
		problemJsonObject.setiTotalRecords(problemSize);
		problemJsonObject.setAaData(problemsList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(problemJsonObject);

		return json2;
	}

	/**
	 * Parse a list of Problem into a list of StudentListProblemJsonDTO to use it in the Datatable
	 */
	private List<StudentListProblemJsonDTO> getListProblemDTO(List<Problem> problemList) throws ParseException {
		StudentListProblemJsonDTO tmp = new StudentListProblemJsonDTO();
		Problem node;
		List<StudentListProblemJsonDTO> tmpList = new ArrayList<StudentListProblemJsonDTO>();
		boolean inDate;
		boolean alreadeAnswered;

		//TODO You have to implement here the recover of the student when this app is installed in the server
		//We recover the student
		int idStudent = 7;

		SimpleDateFormat format = new SimpleDateFormat(CommonConstants.DATE_FORMAT, new Locale(
				CommonConstants.LOCALE_ES));
		Iterator<Problem> itProblem = problemList.iterator();
		SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
		Date actualDate = sdf.parse(sdf.format(new Date()));
		Date initDate;
		Date endDate;

		while (itProblem.hasNext()) {
			node = itProblem.next();
			tmp = new StudentListProblemJsonDTO();
			inDate = true;
			alreadeAnswered = false;
			initDate = sdf.parse(sdf.format(node.getInitDate()));
			endDate = sdf.parse(sdf.format(node.getEndDate()));

			//We check if the student can solve the problem or he is out of date or he already answered it and it's uniqueAnswer
			if (actualDate.before(initDate) || actualDate.after(endDate)) {
				inDate = false;
			}
			if (node.isUniqueAnswer()
					&& (answerManager.findByProblemRefAndStudentRef(node.getIdProblems(), idStudent).size() > 0)) {
				alreadeAnswered = true;
			}

			if (inDate && !alreadeAnswered) {
				tmp.setIdProblem(String.valueOf(node.getIdProblems()));
				tmp.setProblemTitle(node.getTitle());
				tmp.setTeacherName(node.getTeacherRef().getName());
				tmp.setMethodName(node.getMethodRef().getName());
				tmp.setNumVariables(String.valueOf(node.getNumVariables()));
				tmp.setUniqueAnswer(String.valueOf(node.isUniqueAnswer()));
				tmp.setInitDate(format.format(node.getInitDate()));
				tmp.setEndDate(format.format(node.getEndDate()));
				tmp.setEquations(WebUtils.equationsAndSolutionToMathJax(node.getEquations()));

				tmpList.add(tmp);
			}
		}
		return tmpList;
	}
}
