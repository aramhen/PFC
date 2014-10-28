package com.university.equationsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.equationsapp.domain.Problem;
import com.university.equationsapp.repository.ProblemRepository;

@Service
public class ProblemManagerImpl implements ProblemManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProblemRepository problemRepository;

	public Problem getProblemByNumVariables(int numVariables) {
		return problemRepository.findByNumVariables(numVariables);
	}

	public void setProblemRepository(ProblemRepository problemRepository) {
		this.problemRepository = problemRepository;
	}

	public List<Problem> getProblemList() {
		return this.problemRepository.getProblemList();
	}

}
