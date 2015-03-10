package com.university.equationsapp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.university.equationsapp.domain.Answer;

public interface AnswerRepository extends CrudRepository<Answer, Integer>, AnswerRepositoryCustom {

	public List<Answer> getAnswerList();

}
