package com.university.equationsapp.service;

import java.io.Serializable;
import java.util.List;

import com.university.equationsapp.domain.Teacher;

public interface TeacherManager extends Serializable {

	public List<Teacher> getTeacherList();

	public Teacher findByName(String teacherName);
	
	public Teacher findByidTeachers(int idTeacher);

}
