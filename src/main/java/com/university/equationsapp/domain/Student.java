package com.university.equationsapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the students database table.
 * 
 */
@Entity
@Table(name = "students")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idStudents")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idStudents;

	@Column(name = "email", length = 50)
	private String email;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "numDoc", length = 12)
	private String numDoc;

	@Column(name = "password", length = 40)
	private String password;

	public int getIdStudents() {
		return this.idStudents;
	}

	public void setIdStudents(int idStudents) {
		this.idStudents = idStudents;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumDoc() {
		return this.numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}