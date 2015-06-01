package com.university.equationsapp.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.university.equationsapp.domain.Student;
import com.university.equationsapp.service.StudentManager;

@Controller
public class StudentHomeController {

	@Autowired
	private StudentManager studentManager;

	@RequestMapping(value = "/studenthome.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();

		//TODO You have to implement here the recover of the student when this app is installed in the server
		//We recover the student
		int idStudent = 7;

		Student student = studentManager.findByIdStudents(idStudent);
		myModel.put("studentName", student.getName());

		return new ModelAndView("studenthome", "model", myModel);
	}

}
