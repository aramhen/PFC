package com.university.equationsapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.university.equationsapp.common.constants.CommonConstants;
import com.university.equationsapp.domain.Answer;
import com.university.equationsapp.domain.Problem;
import com.university.equationsapp.repository.AnswerRepository;
import com.university.equationsapp.repository.ProblemRepository;
import com.university.equationsapp.repository.StudentRepository;
import com.university.equationsapp.web.dto.StudentSolveProblemDTO;

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

	//Here we parse the web object StudentSolveProblemDTO to a repository object Answer
	public void createAnswer(StudentSolveProblemDTO studentSolveProblemDTO) {
		Answer answer = new Answer();
		Problem problem = problemRepository.findOne(Integer.parseInt(studentSolveProblemDTO.getIdProblem()));
		answer.setProblemRef(problem);
		answer.setAnswerDate(new Date());

		StringBuilder sb = new StringBuilder();
		sb.append(studentSolveProblemDTO.getVariableX().trim());
		if (problem.getNumVariables() > 1) {
			sb.append(CommonConstants.SEPARATOR).append(studentSolveProblemDTO.getVariableY().trim());
		}

		if (problem.getNumVariables() > 2) {
			sb.append(CommonConstants.SEPARATOR).append(studentSolveProblemDTO.getVariableZ().trim());
		}
		answer.setSolution(sb.toString());
		//TODO ARH me faltan los pasos
		answer.setSteps("");
		answer.setStudentRef(studentRepository.findOne(Integer.parseInt(studentSolveProblemDTO.getIdStudent())));
		answerRepository.save(answer);
	}

}
