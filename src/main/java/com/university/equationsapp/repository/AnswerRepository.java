package com.university.equationsapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.university.equationsapp.domain.Answer;
import com.university.equationsapp.domain.Problem;
import com.university.equationsapp.domain.Student;

public interface AnswerRepository extends CrudRepository<Answer, Integer>, AnswerRepositoryCustom {

	@Transactional
	public void deleteByProblemRef(Problem problemRef);

	public List<Answer> findByProblemRefAndStudentRef(Problem problemRef, Student studentRef);

	public List<Answer> findByStudentRef(Student studentRef);

}
