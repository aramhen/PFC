package com.university.equationsapp.web;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.university.equationsapp.domain.Answer;
import com.university.equationsapp.service.AnswerManager;
import com.university.equationsapp.service.ProblemManager;
import com.university.equationsapp.service.TeacherManager;
import com.university.equationsapp.web.dto.AnswerToJsonObject;

@Controller
public class ListAnswerController {

	@Autowired
	private ProblemManager problemManager;

	@Autowired
	private TeacherManager teacherManager;

	@Autowired
	private AnswerManager answerManager;

	@RequestMapping(value = "/listanswer.htm", method = RequestMethod.GET)
	public String printWelcome(@ModelAttribute("answer") Answer answer, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		return "listanswer";

	}

	@RequestMapping(value = "/listanswerpagination.htm", method = RequestMethod.GET, produces = "application/json", headers="Accept=*/*")
	public @ResponseBody String springPaginationDataTables(HttpServletRequest request) throws IOException {

		//Fetch the page number from client
		Integer pageNumber = 0;
		if (null != request.getParameter("iDisplayStart"))
			pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;

		//Fetch search parameter
		String searchParameter = request.getParameter("sSearch");

		//Fetch Page display length
		Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

		//Create page list data
		List<Answer> answersList = answerManager.getAnswerList();

		//Here is server side pagination logic. Based on the page number you could make call 
		//to the data base create new list and send back to the client. For demo I am shuffling 
		//the same list to show data randomly
		if (pageNumber == 1) {
			Collections.shuffle(answersList);
		} else if (pageNumber == 2) {
			Collections.shuffle(answersList);
		} else {
			Collections.shuffle(answersList);
		}

		//Search functionality: Returns filtered list based on search parameter
		answersList = getListBasedOnSearchParameter(searchParameter, answersList);

		AnswerToJsonObject personJsonObject = new AnswerToJsonObject();
		//Set Total display record
		personJsonObject.setiTotalDisplayRecords(500);
		//Set Total record
		personJsonObject.setiTotalRecords(500);
		personJsonObject.setAaData(answersList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(personJsonObject);

		return json2;
	}

	private List<Answer> getListBasedOnSearchParameter(String searchParameter, List<Answer> answersList) {

		if (null != searchParameter && !searchParameter.equals("")) {
			List<Answer> answersListForSearch = new ArrayList<Answer>();
			searchParameter = searchParameter.toUpperCase();
			for (Answer answer : answersList) {

				Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateF = formatter.format(answer.getAnswerDate());

				if (String.valueOf(answer.getIdAnswers()).indexOf(searchParameter) != -1
						|| answer.getProblemRef().getTitle().toUpperCase().indexOf(searchParameter) != -1
						|| answer.getStudentRef().getName().toUpperCase().indexOf(searchParameter) != -1
						|| dateF.toUpperCase().indexOf(searchParameter) != -1
						|| answer.getSolution().toUpperCase().indexOf(searchParameter) != -1
						|| answer.getSteps().toUpperCase().indexOf(searchParameter) != -1) {
					answersListForSearch.add(answer);
				}

			}
			answersList = answersListForSearch;
			answersListForSearch = null;
		}
		return answersList;
	}
}