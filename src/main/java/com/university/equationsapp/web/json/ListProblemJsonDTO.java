package com.university.equationsapp.web.json;

import java.io.Serializable;

public class ListProblemJsonDTO implements Serializable {

	/**
	 * ARH TODO habrá que ver las posibles transformaciones de equations, como meterle </br> entre cada ecuación. Si no
	 * hay solution no mostrar el botón de ver más.
	 */

	private static final long serialVersionUID = 1L;

	private String idProblem;
	private String problemTitle;
	private String teacherName;
	private String methodName;
	private String numVariables;
	private String uniqueAnswer;
	private String initDate;
	private String endDate;
	private String equations;
	private String solution;

	public String getIdProblem() {
		return idProblem;
	}

	public void setIdProblem(String idProblem) {
		this.idProblem = idProblem;
	}

	public String getProblemTitle() {
		return problemTitle;
	}

	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getNumVariables() {
		return numVariables;
	}

	public void setNumVariables(String numVariables) {
		this.numVariables = numVariables;
	}

	public String getUniqueAnswer() {
		return uniqueAnswer;
	}

	public void setUniqueAnswer(String uniqueAnswer) {
		this.uniqueAnswer = uniqueAnswer;
	}

	public String getInitDate() {
		return initDate;
	}

	public void setInitDate(String initDate) {
		this.initDate = initDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEquations() {
		return equations;
	}

	public void setEquations(String equations) {
		this.equations = equations;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
}
