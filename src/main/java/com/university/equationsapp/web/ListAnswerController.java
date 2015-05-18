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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.university.equationsapp.common.constants.CommonConstants;
import com.university.equationsapp.domain.Answer;
import com.university.equationsapp.service.AnswerManager;
import com.university.equationsapp.web.json.DTOToJsonObject;
import com.university.equationsapp.web.json.ListAnswerJsonDTO;

@Controller
public class ListAnswerController {

	private static final Logger logger = LoggerFactory.getLogger(ListAnswerController.class);

	@Autowired
	private AnswerManager answerManager;

	@RequestMapping(value = "/listanswer.htm", method = RequestMethod.GET)
	public String printWelcome(@ModelAttribute("answer") Answer answer, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		return "listanswer";

	}

	@RequestMapping(value = "/listanswerpagination.htm", method = RequestMethod.GET, produces = "application/json", headers = "Accept=*/*")
	public @ResponseBody String springPaginationDataTables(HttpServletRequest request, HttpServletResponse response) {
		//Need to fix special ascii characters showed in datatable
		response.setContentType("application/json; charset=iso-8859-1");

		//Create page list data
		List<ListAnswerJsonDTO> answersList = new ArrayList<ListAnswerJsonDTO>();
		try {
			answersList = getListAnswerDTO(answerManager.getAnswerList());
		} catch (Exception ex) {
			logger.error("There has been an error recovering the problems", ex);
		}

		int answerSize = answersList.size();
		DTOToJsonObject<ListAnswerJsonDTO> answerJsonObject = new DTOToJsonObject<ListAnswerJsonDTO>();
		//Set Total display record
		answerJsonObject.setiTotalDisplayRecords(answerSize);
		//Set Total record
		answerJsonObject.setiTotalRecords(answerSize);
		answerJsonObject.setAaData(answersList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(answerJsonObject);

		return json2;
	}

	private List<ListAnswerJsonDTO> getListAnswerDTO(List<Answer> answerList) {
		ListAnswerJsonDTO tmp = new ListAnswerJsonDTO();
		Answer node;
		List<ListAnswerJsonDTO> tmpList = new ArrayList<ListAnswerJsonDTO>();

		SimpleDateFormat format = new SimpleDateFormat(CommonConstants.DATE_TIME_FORMAT, new Locale(
				CommonConstants.LOCALE_ES));
		Iterator<Answer> itAnswer = answerList.iterator();

		while (itAnswer.hasNext()) {
			node = itAnswer.next();
			tmp = new ListAnswerJsonDTO();
			tmp.setProblemTitle(node.getProblemRef().getTitle());
			tmp.setStudentName(node.getStudentRef().getName());
			tmp.setAnswerDate(format.format(node.getAnswerDate()));
			tmp.setSolution(node.getSolution().replace(CommonConstants.SEPARATOR, CommonConstants.HTML_BR));
			tmp.setSteps(node.getSteps());
			tmpList.add(tmp);
		}
		return tmpList;
	}
}
