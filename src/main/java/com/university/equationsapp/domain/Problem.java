package com.university.equationsapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the problems database table.
 * 
 */
@Entity
@Table(name="problems")
@NamedQuery(name="Problem.findAll", query="SELECT p FROM Problem p")
public class Problem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idProblems;

	private String ecuations;

	@Temporal(TemporalType.DATE)
	private Date endDate;

	@Temporal(TemporalType.DATE)
	private Date initDate;

	private String numVariables;

	private String solution;

	@Column(nullable = false, columnDefinition = "TINYINT", length = 1)
	private boolean uniqueAnswer;

	//bi-directional many-to-one association to Method
	@ManyToOne
	@JoinColumn(name="method")
	private Method methodBean;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	@JoinColumn(name="teacher")
	private Teacher teacherBean;

	public int getIdProblems() {
		return this.idProblems;
	}

	public void setIdProblems(int idProblems) {
		this.idProblems = idProblems;
	}

	public String getEcuations() {
		return this.ecuations;
	}

	public void setEcuations(String ecuations) {
		this.ecuations = ecuations;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getInitDate() {
		return this.initDate;
	}

	public void setInitDate(Date initDate) {
		this.initDate = initDate;
	}

	public String getNumVariables() {
		return this.numVariables;
	}

	public void setNumVariables(String numVariables) {
		this.numVariables = numVariables;
	}

	public String getSolution() {
		return this.solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public boolean isUniqueAnswer() {
		return this.uniqueAnswer;
	}

	public void setUniqueAnswer(boolean uniqueAnswer) {
		this.uniqueAnswer = uniqueAnswer;
	}

	public Method getMethodBean() {
		return this.methodBean;
	}

	public void setMethodBean(Method methodBean) {
		this.methodBean = methodBean;
	}

	public Teacher getTeacherBean() {
		return this.teacherBean;
	}

	public void setTeacherBean(Teacher teacherBean) {
		this.teacherBean = teacherBean;
	}

}