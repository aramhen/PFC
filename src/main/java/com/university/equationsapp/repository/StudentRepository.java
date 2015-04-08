package com.university.equationsapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.university.equationsapp.domain.Student;

public interface StudentRepository  extends CrudRepository<Student, Integer>, StudentRepositoryCustom {
	
	public Student findByIdStudents(int idStudents);

}
