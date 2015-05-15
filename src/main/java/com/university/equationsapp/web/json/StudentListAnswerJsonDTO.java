package com.university.equationsapp.web.json;

import java.io.Serializable;

public class StudentListAnswerJsonDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String problemTitle;
	private String teacherName;
	private String answerDate;
	private String solution;
	private String equations;
	private String steps;

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

	public String getEquations() {
		return equations;
	}

	public void setEquations(String equations) {
		this.equations = equations;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

}
