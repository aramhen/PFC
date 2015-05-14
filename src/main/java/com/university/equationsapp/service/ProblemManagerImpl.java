package com.university.equationsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.equationsapp.common.constants.CommonConstants;
import com.university.equationsapp.domain.Problem;
import com.university.equationsapp.repository.MethodRepository;
import com.university.equationsapp.repository.ProblemRepository;
import com.university.equationsapp.repository.TeacherRepository;
import com.university.equationsapp.web.dto.CreateProblemDTO;

@Service
public class ProblemManagerImpl implements ProblemManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProblemRepository problemRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private MethodRepository methodRepository;

	public Problem getProblemByNumVariables(int numVariables) {
		return problemRepository.findByNumVariables(numVariables);
	}

	public List<Problem> getProblemList() {
		return this.problemRepository.getProblemList();
	}

	//Here we parse the web object createProblemDTO to a repository object Problem
	public void createProblem(CreateProblemDTO createProblemDTO) {
		Problem problem = new Problem();
		problem.setEndDate(createProblemDTO.getEndDate());
		problem.setInitDate(createProblemDTO.getInitDate());
		problem.setUniqueAnswer(!createProblemDTO.isUniqueAnswer());
		problem.setNumVariables(createProblemDTO.getNumVariables());
		problem.setTitle(createProblemDTO.getTitle());

		StringBuilder sb = new StringBuilder();
		//TODO ARH validaciones de si viene vacio etc mal o ya viene comprobado?
		sb.append(createProblemDTO.getEquation1().trim());
		if (problem.getNumVariables() > 1) {
			sb.append(CommonConstants.SEPARATOR).append(createProblemDTO.getEquation2().trim());
		}
		if (problem.getNumVariables() > 2) {
			sb.append(CommonConstants.SEPARATOR).append(createProblemDTO.getEquation3().trim());
		}
		problem.setEquations(sb.toString());

		if (createProblemDTO.isSolutionCheck()) {
			sb = new StringBuilder();
			sb.append(CommonConstants.SOLUTION_VARIABLEX).append(createProblemDTO.getVariableX().trim());
			if (problem.getNumVariables() > 1) {
				sb.append(CommonConstants.SEPARATOR).append(CommonConstants.SOLUTION_VARIABLEY).append(createProblemDTO.getVariableY().trim());
			}

			if (problem.getNumVariables() > 2) {
				sb.append(CommonConstants.SEPARATOR).append(CommonConstants.SOLUTION_VARIABLEZ).append(createProblemDTO.getVariableZ().trim());
			}
			problem.setSolution(sb.toString());
		}

		//TODO ARH habrá que ver si hay que hacer aquí control de errores o ya viene controlado de fuera que no me metan nada que no sea mio
		problem.setMethodRef(methodRepository.findByIdMethods(createProblemDTO.getIdMethod()));

		int idTeacher = createProblemDTO.getIdTeacher();
		//TODO ARH !!!!!!!!! Ahora mismo seteo a pincho a un profesor mientras veo como lo recupero
		idTeacher = 1; //TODO ARH <<<<---- Esto hay que quitarlo!!!!!
		problem.setTeacherRef(teacherRepository.findOne(idTeacher));

		problemRepository.save(problem);

	}

	public Problem findByIdProblems(int idProblems) {
		return problemRepository.findByIdProblems(idProblems);
	}

	public void deleteProblem(int idProblem) {
		problemRepository.delete(idProblem);
	}

	public void setProblemRepository(ProblemRepository problemRepository) {
		this.problemRepository = problemRepository;
	}

	public void setTeacherRepository(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	public void setMethodRepository(MethodRepository methodRepository) {
		this.methodRepository = methodRepository;
	}
}
