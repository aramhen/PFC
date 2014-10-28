package com.university.equationsapp.service;

import java.io.Serializable;
import java.util.List;

import com.university.equationsapp.domain.Problem;

public interface ProblemManager extends Serializable {

	public List<Problem> getProblemList();

	public Problem getProblemByNumVariables(int numVariables);

}
