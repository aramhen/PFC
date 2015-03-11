package com.university.equationsapp.web.dto;

import java.io.Serializable;

public class ListAnswerDTO implements Serializable {

	/**
	 * ARH TODO hay que poner solution en formato bonito, ya que de la bbdd vendrá separado por pipes en steps hay que
	 * intentar poner un enlace que abra un modal de bootstrap que muestre el codigo matematico hay que ver como
	 * transformar el formato de answerDate
	 */

	private static final long serialVersionUID = 1L;

	private String problemTitle;
	private String studentName;
	private String answerDate;
	private String solution;
	private String steps;

	public String getProblemTitle() {
		return problemTitle;
	}

	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(String answerDate) {
		this.answerDate = answerDate;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

}
