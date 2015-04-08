package com.university.equationsapp.service;

import java.io.Serializable;

import com.university.equationsapp.domain.Student;

public interface StudentManager extends Serializable {

	public Student findByIdStudents(int idStudents);

}
