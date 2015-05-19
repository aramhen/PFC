package com.university.equationsapp.service;

import java.io.Serializable;
import java.util.List;

import com.university.equationsapp.domain.Answer;
import com.university.equationsapp.web.dto.StudentSolveProblemDTO;

public interface AnswerManager extends Serializable {

	public List<Answer> getAnswerList();

	public void deleteByProblemRef(int idProblem);

	public List<Answer> findByProblemRefAndStudentRef(int idProblem, int idStudent);
	
	public List<Answer> findByStudentRef(int idStudent);

	public void createAnswer(StudentSolveProblemDTO studentSolveProblemDTO, int idProblem, int idStudent);

}
