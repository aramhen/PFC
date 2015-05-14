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
import com.university.equationsapp.repository.StudentRepository;

@Controller
public class StudentHomeController {
	
	@Autowired
	private StudentRepository studentRepository;

	@RequestMapping(value = "/studenthome.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();

		//TODO ARH IMPORTANTE ESTOY SETEANDO EL IDSTUDENT A FUEGO, HAY QUE VER DE DONDE RECUPERARLO
		//We recover the student
		int idStudent = 7;
		
		Student student = studentRepository.findOne(idStudent);
		myModel.put("studentName", student.getName());

		return new ModelAndView("studenthome", "model", myModel);
	}

}
