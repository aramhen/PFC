package com.university.equationsapp.service;

import java.io.Serializable;
import java.util.List;

import com.university.equationsapp.domain.Problem;
import com.university.equationsapp.web.dto.CreateProblemDTO;

public interface ProblemManager extends Serializable {

	public List<Problem> getProblemList();

	public Problem findByNumVariables(int numVariables);

	public void createProblem(CreateProblemDTO createProblemDTO);

	public void deleteProblem(int idProblem);

	public Problem findByIdProblems(int idProblems);

}
