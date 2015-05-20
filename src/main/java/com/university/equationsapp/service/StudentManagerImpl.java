package com.university.equationsapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.equationsapp.domain.Student;
import com.university.equationsapp.repository.StudentRepository;

@Service
public class StudentManagerImpl implements StudentManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	private StudentRepository studentRepository;

	public Student findByIdStudents(int idStudents) {
		return studentRepository.findByIdStudents(idStudents);
	}

	public void setStudentRepository(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

}
