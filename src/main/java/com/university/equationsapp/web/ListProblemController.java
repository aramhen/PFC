package com.university.equationsapp.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
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
	public String printWelcome(@ModelAttribute("problem") Problem problem, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		return "listproblem";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("Problem") Problem problem, BindingResult result, Model model) {
		if (result.hasErrors()) {
			logger.error("Error processing the result");
			return "listproblem";
		}
		
		int idProblem = problem.getIdProblems();
		if(logger.isDebugEnabled()){
			logger.debug("Deleting problem " + idProblem);
		}
		answerManager.deleteByProblemRef(idProblem);
		problemManager.deleteProblem(idProblem);
		return "redirect:/listproblem.htm";
	}

	//Used to store the submit problem to delete
	@ModelAttribute("Problem")
	public Problem idProblem() {
		return new Problem();
	}

	@RequestMapping(value = "/listproblempagination.htm", method = RequestMethod.GET, produces = "application/json", headers = "Accept=*/*")
	public @ResponseBody String springPaginationDataTables(HttpServletRequest request) throws IOException {

		//Fetch the page number from client
		Integer pageNumber = 0;
		if (null != request.getParameter("iDisplayStart"))
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;

		//Fetch search parameter
		String searchParameter = request.getParameter("sSearch");

		//Fetch Page display length
//		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		//Create page list data
		List<ListProblemJsonDTO> problemsList = getListProblemDTO(problemManager.getProblemList());

		//Here is server side pagination logic. Based on the page number you could make call 
		//to the data base create new list and send back to the client. For demo I am shuffling 
		//the same list to show data randomly
		if (pageNumber == 1) {

		} else if (pageNumber == 2) {
			Collections.shuffle(problemsList);
		} else {
			Collections.shuffle(problemsList);
		}

		//Search functionality: Returns filtered list based on search parameter
		problemsList = getListBasedOnSearchParameter(searchParameter, problemsList);

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

	private List<ListProblemJsonDTO> getListBasedOnSearchParameter(String searchParameter,
			List<ListProblemJsonDTO> problemsList) {

		if (null != searchParameter && !searchParameter.equals("")) {
			List<ListProblemJsonDTO> problemsListForSearch = new ArrayList<ListProblemJsonDTO>();
			searchParameter = searchParameter.toUpperCase();
			for (ListProblemJsonDTO problem : problemsList) {
				if (problem.getProblemTitle().toUpperCase().indexOf(searchParameter) != -1
						|| problem.getTeacherName().toUpperCase().indexOf(searchParameter) != -1
						|| problem.getMethodName().toUpperCase().indexOf(searchParameter) != -1
						|| problem.getNumVariables().toUpperCase().indexOf(searchParameter) != -1
						|| problem.getInitDate().toUpperCase().indexOf(searchParameter) != -1
						|| problem.getEndDate().toUpperCase().indexOf(searchParameter) != -1) {
					problemsListForSearch.add(problem);
				}
			}
			problemsList = problemsListForSearch;
			problemsListForSearch = null;
		}
		return problemsList;
	}

	private List<ListProblemJsonDTO> getListProblemDTO(List<Problem> problemList) {
		ListProblemJsonDTO tmp = new ListProblemJsonDTO();
		Problem node;
		List<ListProblemJsonDTO> tmpList = new ArrayList<ListProblemJsonDTO>();

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy", new Locale("ES"));
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
			tmp.setEquations(node.getEquations().replace(CommonConstants.SEPARATOR, "</br>"));
			if (node.getSolution() != null) {
				tmp.setSolution(node.getSolution().replace(CommonConstants.SEPARATOR, "</br>"));
			}
			tmpList.add(tmp);
		}
		return tmpList;
	}
}
