package com.university.equationsapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.university.equationsapp.domain.Teacher;

public interface TeacherRepository  extends CrudRepository<Teacher, Integer>, TeacherRepositoryCustom {

}
