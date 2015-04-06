package com.university.equationsapp.service;

import java.io.Serializable;
import java.util.List;

import com.university.equationsapp.domain.Answer;

public interface AnswerManager extends Serializable {

	public List<Answer> getAnswerList();

	public void deleteByProblemRef(int idProblem);

	public List<Answer> findByProblemRefAndStudentRef(int idProblem, int idStudent);

}
