package com.university.equationsapp.web.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import com.university.equationsapp.common.validation.CreateProblemAnnotation;
import com.university.equationsapp.web.constants.WebConstants;

@CreateProblemAnnotation
public class CreateProblemDTO implements Serializable {

	static final long serialVersionUID = 1L;

	@NotNull
	@Min(value = 1, message = "{createproblem.validation.idmethod}")
	private int idMethod;

	//We validate that the number of variables is between the range we set, originally the range is between 1 and 3
	@NotNull
	@Range(min = WebConstants.NUM_VARIABLES_MIN, max = WebConstants.NUM_VARIABLES_MAX, message = "{createproblem.validation.numvariables}")
	private int numVariables;

	@Size(min = WebConstants.EQUATION_LENGTH_MIN, max = WebConstants.EQUATION_LENGTH_MAX, message = "{createproblem.validation.equation.size}")
	private String equation1;
	@Size(min = WebConstants.EQUATION_LENGTH_MIN, max = WebConstants.EQUATION_LENGTH_MAX, message = "{createproblem.validation.equation.size}")
	private String equation2;
	@Size(min = WebConstants.EQUATION_LENGTH_MIN, max = WebConstants.EQUATION_LENGTH_MAX, message = "{createproblem.validation.equation.size}")
	private String equation3;

	@Size(min = WebConstants.SOLUTION_LENGTH_MIN, max = WebConstants.SOLUTION_LENGTH_MAX, message = "{createproblem.validation.solution.size}")
	private String variableX;
	@Size(min = WebConstants.SOLUTION_LENGTH_MIN, max = WebConstants.SOLUTION_LENGTH_MAX, message = "{createproblem.validation.solution.size}")
	private String variableY;
	@Size(min = WebConstants.SOLUTION_LENGTH_MIN, max = WebConstants.SOLUTION_LENGTH_MAX, message = "{createproblem.validation.solution.size}")
	private String variableZ;

	private boolean solutionCheck;

	@NotNull(message = "{createproblem.validation.initDate}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date initDate;

	@NotNull(message = "{createproblem.validation.endDate}")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	private boolean uniqueAnswer;

	@NotNull
	private int idTeacher;

	@NotBlank(message = "{createproblem.validation.title}")
	@Size(min = WebConstants.TITLE_LENGTH_MIN, max = WebConstants.TITLE_LENGTH_MAX, message = "{createproblem.validation.title.size}")
	private String title;

	public int getIdMethod() {
		return idMethod;
	}

	public void setIdMethod(int idMethod) {
		this.idMethod = idMethod;
	}

	public int getNumVariables() {
		return numVariables;
	}

	public void setNumVariables(int numVariables) {
		this.numVariables = numVariables;
	}

	public String getEquation1() {
		return equation1;
	}

	public void setEquation1(String equation1) {
		this.equation1 = equation1.trim();
	}

	public String getEquation2() {
		return equation2;
	}

	public void setEquation2(String equation2) {
		this.equation2 = equation2.trim();
	}

	public String getEquation3() {
		return equation3;
	}

	public void setEquation3(String equation3) {
		this.equation3 = equation3.trim();
	}

	public String getVariableX() {
		return variableX;
	}

	public void setVariableX(String variableX) {
		this.variableX = variableX.trim();
	}

	public String getVariableY() {
		return variableY;
	}

	public void setVariableY(String variableY) {
		this.variableY = variableY.trim();
	}

	public String getVariableZ() {
		return variableZ;
	}

	public void setVariableZ(String variableZ) {
		this.variableZ = variableZ.trim();
	}

	public Date getInitDate() {
		return initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isUniqueAnswer() {
		return uniqueAnswer;
	}

	public void setUniqueAnswer(boolean uniqueAnswer) {
		this.uniqueAnswer = uniqueAnswer;
	}

	public boolean isSolutionCheck() {
		return solutionCheck;
	}

	public void setSolutionCheck(boolean solutionCheck) {
		this.solutionCheck = solutionCheck;
	}

	public int getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(int idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title.trim();
	}

}
