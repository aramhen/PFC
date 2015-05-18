package com.university.equationsapp.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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
import com.university.equationsapp.common.constants.CommonConstants;
import com.university.equationsapp.common.utils.WebUtils;
import com.university.equationsapp.domain.Answer;
import com.university.equationsapp.repository.StudentRepository;
import com.university.equationsapp.service.AnswerManager;
import com.university.equationsapp.service.ProblemManager;
import com.university.equationsapp.service.TeacherManager;
import com.university.equationsapp.web.json.DTOToJsonObject;
import com.university.equationsapp.web.json.StudentListAnswerJsonDTO;

@Controller
public class StudentListAnswerController {

	@Autowired
	private ProblemManager problemManager;

	@Autowired
	private TeacherManager teacherManager;

	@Autowired
	private AnswerManager answerManager;

	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping(value = "/studentlistanswer.htm", method = RequestMethod.GET)
	public String printWelcome(@ModelAttribute("answer") Answer answer, BindingResult result, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {

		return "studentlistanswer";

	}

	@RequestMapping(value = "/studentlistanswerpagination.htm", method = RequestMethod.GET, produces = "application/json", headers = "Accept=*/*")
	public @ResponseBody String springPaginationDataTables(HttpServletRequest request, HttpServletResponse response) {
		//Need to fix special ascii characters showed in datatable
		response.setContentType("application/json; charset=iso-8859-1");

		//TODO ARH IMPORTANTE ESTOY SETEANDO EL IDSTUDENT A FUEGO, HAY QUE VER DE DONDE RECUPERARLO
		//We recover the student answers
		int idStudent = 7;
		//Create page list data
		List<StudentListAnswerJsonDTO> answersList = getstudentlistanswerDTO(answerManager.findByStudentRef(idStudent));

		int answerSize = answersList.size();
		DTOToJsonObject<StudentListAnswerJsonDTO> answerJsonObject = new DTOToJsonObject<StudentListAnswerJsonDTO>();
		//Set Total display record
		answerJsonObject.setiTotalDisplayRecords(answerSize);
		//Set Total record
		answerJsonObject.setiTotalRecords(answerSize);
		answerJsonObject.setAaData(answersList);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json2 = gson.toJson(answerJsonObject);

		return json2;
	}

	private List<StudentListAnswerJsonDTO> getstudentlistanswerDTO(List<Answer> answerList) {
		StudentListAnswerJsonDTO tmp = new StudentListAnswerJsonDTO();
		Answer node;
		List<StudentListAnswerJsonDTO> tmpList = new ArrayList<StudentListAnswerJsonDTO>();

		SimpleDateFormat format = new SimpleDateFormat(CommonConstants.DATE_TIME_FORMAT, new Locale(
				CommonConstants.LOCALE_ES));
		Iterator<Answer> itAnswer = answerList.iterator();

		while (itAnswer.hasNext()) {
			node = itAnswer.next();
			tmp = new StudentListAnswerJsonDTO();
			tmp.setProblemTitle(node.getProblemRef().getTitle());
			tmp.setTeacherName(node.getProblemRef().getTeacherRef().getName());
			tmp.setAnswerDate(format.format(node.getAnswerDate()));
			tmp.setEquations(WebUtils.equationsAndSolutionToMathJax(node.getProblemRef().getEquations()));
			tmp.setSolution(node.getSolution().replace(CommonConstants.SEPARATOR, CommonConstants.HTML_BR));
			tmp.setSteps(node.getSteps());
			tmpList.add(tmp);
		}
		return tmpList;
	}
}
