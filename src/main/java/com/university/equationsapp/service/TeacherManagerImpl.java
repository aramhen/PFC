package com.university.equationsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.equationsapp.domain.Teacher;
import com.university.equationsapp.repository.TeacherRepository;

@Service
public class TeacherManagerImpl implements TeacherManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TeacherRepository teacherRepository;

	public Teacher getTeacherByName(String teacherName) {
		return teacherRepository.findByName(teacherName);
	}

	public void setTeacherRepository(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	public List<Teacher> getTeacherList() {
		return this.teacherRepository.getTeacherList();
	}

}
