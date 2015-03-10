package com.university.equationsapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.university.equationsapp.domain.Problem;

public interface ProblemRepository extends CrudRepository<Problem, Integer>, ProblemRepositoryCustom {

	Problem findByNumVariables(int numVariables);

	Problem findByIdProblems(int idProblems);

}
