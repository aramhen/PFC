package com.university.equationsapp.web.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

	private List<String> stepsList = new ArrayList<String>();

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

	public List<String> getStepsList() {
		return stepsList;
	}

	public void setStepsList(List<String> stepsList) {
		this.stepsList = stepsList;
	}

}
