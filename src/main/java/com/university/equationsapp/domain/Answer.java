package com.university.equationsapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the answers database table.
 * 
 */
@Entity
@Table(name="answers")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idAnswers")
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int idAnswers;

	@Column(name = "answerDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date answerDate;

	@Column(name = "solution")
	private String solution;

	@Column(name = "steps")
	@Lob
	private String steps;

	//many-to-one association to Problem
	@ManyToOne
    @JoinColumn(name = "problem", table = "problems", referencedColumnName = "idProblems", nullable = false)
	private Problem problemRef;

	//many-to-one association to Student
	@ManyToOne
	@JoinColumn(name = "student", table = "students", referencedColumnName = "idStudents", nullable = false)
	private Student studentRef;

	public int getIdAnswers() {
		return this.idAnswers;
	}

	public void setIdAnswers(int idAnswers) {
		this.idAnswers = idAnswers;
	}

	public Date getAnswerDate() {
		return this.answerDate;
	}

	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}

	public String getSolution() {
		return this.solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getSteps() {
		return this.steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public Problem getProblemRef() {
		return this.problemRef;
	}

	public void setProblemRef(Problem problemRef) {
		this.problemRef = problemRef;
	}

	public Student getStudentRef() {
		return this.studentRef;
	}

	public void setStudentRef(Student studentRef) {
		this.studentRef = studentRef;
	}

}