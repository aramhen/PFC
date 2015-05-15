package com.university.equationsapp.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.university.equationsapp.common.constants.CommonConstants;
import com.university.equationsapp.domain.Problem;
import com.university.equationsapp.service.AnswerManager;
import com.university.equationsapp.service.ProblemManager;
import com.university.equationsapp.service.TeacherManager;
import com.university.equationsapp.web.json.DTOToJsonObject;
import com.university.equationsapp.web.json.ListProblemJsonDTO;
import com.university.equationsapp.web.utils.WebUtils;

@Controller
public class ListProblemController {

	private static final Logger logger = LoggerFactory.getLogger(ListProblemController.class);

	@Autowired
	private ProblemManager problemManager;

	@Autowired
	private TeacherManager teacherManager;

	@Autowired
	private AnswerManager answerManager;

	@RequestMapping(value = "/listproblem.htm", method = RequestMethod.GET)
	public String printWelcome(@ModelAttribute("Problem") Problem problem, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		return "listproblem";

	}

	@RequestMapping(value = "/listproblem.htm", method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("Problem") Problem problem, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.error("Error processing the result");
			return "listproblem";
		}

		int idProblem = problem.getIdProblems();
		if (logger.isDebugEnabled()) {
			logger.debug("Deleting problem " + idProblem);
		}
		answerManager.deleteByProblemRef(idProblem);
		problemManager.deleteProblem(idProblem);
		return "redirect:/listproblem.htm";
	}

	@RequestMapping(value = "/listproblempagination.htm", method = RequestMethod.GET, produces = "application/json", headers = "Accept=*/*")
	public @ResponseBody String springPaginationDataTables(HttpServletRequest request, HttpServletResponse response) {
		//Need to fix special ascii characters showed in datatable
		response.setContentType("application/json; charset=iso-8859-1");

		//Create page list data
		List<ListProblemJsonDTO> problemsList = getListProblemDTO(problemManager.getProblemList());

		int problemSize = problemsList.size();
		DTOToJsonObject<ListProblemJsonDTO> problemJsonObject = new DTOToJsonObject<ListProblemJsonDTO>();
		//Set Total display record
		problemJsonObject.setiTotalDisplayRecords(problemSize);
		//Set Total record
		problemJsonObject.setiTotalRecords(problemSize);
		problemJsonObject.setAaData(problemsList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(problemJsonObject);

		return json2;
	}

	private List<ListProblemJsonDTO> getListProblemDTO(List<Problem> problemList) {
		ListProblemJsonDTO tmp = new ListProblemJsonDTO();
		Problem node;
		List<ListProblemJsonDTO> tmpList = new ArrayList<ListProblemJsonDTO>();

		SimpleDateFormat format = new SimpleDateFormat(CommonConstants.DATE_FORMAT, new Locale(
				CommonConstants.LOCALE_ES));
		Iterator<Problem> itProblem = problemList.iterator();

		while (itProblem.hasNext()) {
			node = itProblem.next();
			tmp = new ListProblemJsonDTO();

			tmp.setIdProblem(String.valueOf(node.getIdProblems()));
			tmp.setProblemTitle(node.getTitle());
			tmp.setTeacherName(node.getTeacherRef().getName());
			tmp.setMethodName(node.getMethodRef().getName());
			tmp.setNumVariables(String.valueOf(node.getNumVariables()));
			tmp.setUniqueAnswer(String.valueOf(node.isUniqueAnswer()));
			tmp.setInitDate(format.format(node.getInitDate()));
			tmp.setEndDate(format.format(node.getEndDate()));
			tmp.setEquations(WebUtils.equationsAndSolutionToMathJax(node.getEquations()));
			if (node.getSolution() != null) {
				tmp.setSolution(WebUtils.equationsAndSolutionToMathJax(node.getSolution()));
			}
			tmpList.add(tmp);
		}
		return tmpList;
	}
}
