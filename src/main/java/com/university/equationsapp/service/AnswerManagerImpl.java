package com.university.equationsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.equationsapp.domain.Answer;
import com.university.equationsapp.repository.AnswerRepository;
import com.university.equationsapp.repository.ProblemRepository;
import com.university.equationsapp.repository.StudentRepository;

@Service
public class AnswerManagerImpl implements AnswerManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private ProblemRepository problemRepository;

	@Autowired
	private StudentRepository studentRepository;

	public List<Answer> getAnswerList() {
		return answerRepository.getAnswerList();
	}

	public void deleteByProblemRef(int idProblem) {
		answerRepository.deleteByProblemRef(problemRepository.findOne(idProblem));
	}

	public List<Answer> findByProblemRefAndStudentRef(int idProblem, int idStudent) {
		return answerRepository.findByProblemRefAndStudentRef(problemRepository.findOne(idProblem),
				studentRepository.findOne(idStudent));
	}

	public List<Answer> findByStudentRef(int idStudent) {
		return answerRepository.findByStudentRef(studentRepository.findOne(idStudent));
	}

	public void setProblemRepository(ProblemRepository problemRepository) {
		this.problemRepository = problemRepository;
	}

	public void setAnswerRepository(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}

}
