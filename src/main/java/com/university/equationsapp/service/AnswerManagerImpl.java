package com.university.equationsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.equationsapp.domain.Answer;
import com.university.equationsapp.repository.AnswerRepository;
import com.university.equationsapp.repository.ProblemRepository;

@Service
public class AnswerManagerImpl implements AnswerManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private ProblemRepository problemRepository;

	public List<Answer> getAnswerList() {
		return answerRepository.getAnswerList();
	}

	public void deleteByProblemRef(int problemRef) {
		answerRepository.deleteByProblemRef(problemRepository.findOne(problemRef));
	}

	public void setProblemRepository(ProblemRepository problemRepository) {
		this.problemRepository = problemRepository;
	}

	public void setAnswerRepository(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}
}
