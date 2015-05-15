package com.university.equationsapp.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(AnswerManagerImpl.class);

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
		sb.append(CommonConstants.SOLUTION_VARIABLEX).append(studentSolveProblemDTO.getVariableX().trim());
		if (problem.getNumVariables() > 1) {
			sb.append(CommonConstants.SEPARATOR).append(CommonConstants.SOLUTION_VARIABLEY)
					.append(studentSolveProblemDTO.getVariableY().trim());
		}

		if (problem.getNumVariables() > 2) {
			sb.append(CommonConstants.SEPARATOR).append(CommonConstants.SOLUTION_VARIABLEZ)
					.append(studentSolveProblemDTO.getVariableZ().trim());
		}
		answer.setSolution(sb.toString());
		//TODO ARH me faltan los pasos
		//answer.setSteps("$\\begin{pmatrix} a & b \\\\ c & d \\end{pmatrix}$");
		answer.setSteps(parseSteps(studentSolveProblemDTO.getStepsList()));
		answer.setStudentRef(studentRepository.findOne(Integer.parseInt(studentSolveProblemDTO.getIdStudent())));

		System.out.println(studentSolveProblemDTO.getStepsList());
		answerRepository.save(answer);
	}

	private String parseSteps(List<String> stepsList) {
		Iterator<String> it = stepsList.iterator();
		StringBuilder sb1 = new StringBuilder();
		while (it.hasNext()) {
			List<String> node = Arrays.asList(it.next().split(CommonConstants.SEPARATOR_FOR_SPLIT));
			StringBuilder sb2 = new StringBuilder();
			if ((node.get(0).contains(CommonConstants.M1x2)) && (node.size() == 3)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append(CommonConstants.HTML_AMP)
						.append(node.get(2)).append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.M1x3)) && (node.size() == 4)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append(CommonConstants.HTML_AMP)
						.append(node.get(2)).append(CommonConstants.HTML_AMP).append(node.get(3))
						.append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.M1x4)) && (node.size() == 5)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append(CommonConstants.HTML_AMP)
						.append(node.get(2)).append(CommonConstants.HTML_AMP).append(node.get(3))
						.append(CommonConstants.HTML_AMP).append(node.get(4)).append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.M2x1)) && (node.size() == 3)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append("\\\\").append(node.get(2))
						.append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.M2x2)) && (node.size() == 5)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append(CommonConstants.HTML_AMP)
						.append(node.get(3)).append("\\\\").append(node.get(2)).append(CommonConstants.HTML_AMP)
						.append(node.get(4)).append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.M2x3)) && (node.size() == 7)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append(CommonConstants.HTML_AMP)
						.append(node.get(3)).append(CommonConstants.HTML_AMP).append(node.get(5)).append("\\\\")
						.append(node.get(2)).append(CommonConstants.HTML_AMP).append(node.get(4))
						.append(CommonConstants.HTML_AMP).append(node.get(6)).append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.M2x4)) && (node.size() == 9)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append(CommonConstants.HTML_AMP)
						.append(node.get(3)).append(CommonConstants.HTML_AMP).append(node.get(5))
						.append(CommonConstants.HTML_AMP).append(node.get(7)).append("\\\\").append(node.get(2))
						.append(CommonConstants.HTML_AMP).append(node.get(4)).append(CommonConstants.HTML_AMP)
						.append(node.get(6)).append(CommonConstants.HTML_AMP).append(node.get(8))
						.append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.M3x1)) && (node.size() == 4)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append("\\\\").append(node.get(2)).append("\\\\")
						.append(node.get(3)).append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.M3x2)) && (node.size() == 7)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append(CommonConstants.HTML_AMP)
						.append(node.get(4)).append("\\\\").append(node.get(2)).append(CommonConstants.HTML_AMP)
						.append(node.get(5)).append("\\\\").append(node.get(3)).append(CommonConstants.HTML_AMP)
						.append(node.get(6)).append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.M3x3)) && (node.size() == 10)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append(CommonConstants.HTML_AMP)
						.append(node.get(4)).append(CommonConstants.HTML_AMP).append(node.get(7)).append("\\\\")
						.append(node.get(2)).append(CommonConstants.HTML_AMP).append(node.get(5))
						.append(CommonConstants.HTML_AMP).append(node.get(8)).append("\\\\").append(node.get(3))
						.append(CommonConstants.HTML_AMP).append(node.get(6)).append(CommonConstants.HTML_AMP)
						.append(node.get(9)).append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.M3x4)) && (node.size() == 13)) {
				sb2.append("$\\begin{pmatrix} ").append(node.get(1)).append(CommonConstants.HTML_AMP)
						.append(node.get(4)).append(CommonConstants.HTML_AMP).append(node.get(7))
						.append(CommonConstants.HTML_AMP).append(node.get(10)).append("\\\\").append(node.get(2))
						.append(CommonConstants.HTML_AMP).append(node.get(5)).append(CommonConstants.HTML_AMP)
						.append(node.get(8)).append(CommonConstants.HTML_AMP).append(node.get(11)).append("\\\\")
						.append(node.get(3)).append(CommonConstants.HTML_AMP).append(node.get(6))
						.append(CommonConstants.HTML_AMP).append(node.get(9)).append(CommonConstants.HTML_AMP)
						.append(node.get(12)).append("\\end{pmatrix}$");

			} else if ((node.get(0).contains(CommonConstants.FORM1)) && (node.size() == 2)) {
				sb2.append("<div>$ ").append(node.get(1)).append(" $</div>");

			} else if ((node.get(0).contains(CommonConstants.FORM2)) && (node.size() == 3)) {
				sb2.append("<div style='margin-bottom: 2px;'>$ ").append(node.get(1)).append(" $</div>").append("<div>$ ").append(node.get(2))
						.append(" $</div>");

			} else if ((node.get(0).contains(CommonConstants.FORM3)) && (node.size() == 4)) {
				sb2.append("<div style='margin-bottom: 2px;'>$ ").append(node.get(1)).append(" $</div>").append("<div style='margin-bottom: 2px;'>$ ").append(node.get(2))
						.append(" $</div>").append("<div>$ ").append(node.get(3)).append(" $</div>");

			} else if ((node.get(0).contains(CommonConstants.RESTFORM)) && (node.size() == 4)) {
				sb2.append("<div>$ ")
						.append(node.get(1))
						.append(" $</div><div>")
						.append("<label style='margin-left: -7%; margin-right: 6%;'>-</label>")
						.append("$ ")
						.append(node.get(2))
						.append(" $</div>")
						.append("<div style='border-bottom: 1px solid rgb(204, 204, 204);  margin: 0px auto 1%; width: 33%;'></div>")
						.append("$ ").append(node.get(3)).append(" $");

			} else if ((node.get(0).contains(CommonConstants.SUMFORM)) && (node.size() == 4)) {
				sb2.append("<div>$ ")
						.append(node.get(1))
						.append(" $</div><div>")
						.append("<label style='margin-left: -7%; margin-right: 6%;'>+</label>")
						.append("$ ")
						.append(node.get(2))
						.append(" $</div>")
						.append("<div style='border-bottom: 1px solid rgb(204, 204, 204);  margin: 0px auto 1%; width: 33%;'></div>")
						.append("$ ").append(node.get(3)).append(" $");
			} else {
				logger.error(CommonConstants.ERROR_MSG_PARSING + node);
			}
			sb1.append(sb2).append("<div style='margin: 3% auto; width: 15%; border: 1px solid #2e6da4;'/>");
		}

		return sb1.toString();

	}
}
