package pl.kamilszopa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Student {
	@Id
	@GeneratedValue
	protected Long id;
	private String surName;
	private String firstName;
	@OneToOne
	private SchoolClass schoolClass;
	@OneToOne
	private Parent parent;
	@ElementCollection(targetClass=SubjectGrades.class)
	private List<SubjectGrades> grades;

	public Student() {
	}

	public Student(String surName, String firstName, SchoolClass schoolClass,
			Parent parent, List<SubjectGrades> grades) {
		this.surName = surName;
		this.firstName = firstName;
		this.schoolClass = schoolClass;
		this.parent = parent;
		this.grades = grades;
	}

}
