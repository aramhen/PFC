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

import com.university.equationsapp.domain.Teacher;
import com.university.equationsapp.service.TeacherManager;

@Controller
public class HomeController {

	@Autowired
	private TeacherManager teacherManager;

	@RequestMapping(value = "/home.htm")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Map<String, Object> myModel = new HashMap<String, Object>();

		//TODO You have to implement here the recover of the teacher when this app is installed in the server
		//We recover the teacher
		int idTeacher = 7;

		Teacher teacher = teacherManager.findByidTeachers(idTeacher);
		myModel.put("teacherName", teacher.getName());

		return new ModelAndView("home", "model", myModel);
	}

}
