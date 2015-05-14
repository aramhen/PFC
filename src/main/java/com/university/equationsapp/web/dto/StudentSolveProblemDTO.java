package com.university.equationsapp.web.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import com.university.equationsapp.web.constants.WebConstants;

public class StudentSolveProblemDTO implements Serializable {

	static final long serialVersionUID = 1L;

	@Size(min = WebConstants.SOLUTION_LENGTH_MIN, max = WebConstants.SOLUTION_LENGTH_MAX, message = "{createproblem.validation.solution.size}")
	private String variableX;
	@Size(min = WebConstants.SOLUTION_LENGTH_MIN, max = WebConstants.SOLUTION_LENGTH_MAX, message = "{createproblem.validation.solution.size}")
	private String variableY;
	@Size(min = WebConstants.SOLUTION_LENGTH_MIN, max = WebConstants.SOLUTION_LENGTH_MAX, message = "{createproblem.validation.solution.size}")
	private String variableZ;

	private String idProblem;
	private String idStudent;

	public String getVariableX() {
		return variableX;
	}

	public void setVariableX(String variableX) {
		this.variableX = variableX;
	}

	public String getVariableY() {
		return variableY;
	}

	public void setVariableY(String variableY) {
		this.variableY = variableY;
	}

	public String getVariableZ() {
		return variableZ;
	}

	public void setVariableZ(String variableZ) {
		this.variableZ = variableZ;
	}

	public String getIdProblem() {
		return idProblem;
	}

	public void setIdProblem(String idProblem) {
		this.idProblem = idProblem;
	}

	public String getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}

}
