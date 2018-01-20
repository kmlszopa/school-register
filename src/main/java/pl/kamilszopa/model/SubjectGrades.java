package pl.kamilszopa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class SubjectGrades {

	@Id
	@GeneratedValue
	private Long id;
	@OneToOne
	private Subject subject;
	@ElementCollection(targetClass=Integer.class)
	private List<Integer> grades;

	public SubjectGrades(Subject subject) {
		this.subject = subject;
		this.grades = new ArrayList<>();
	}

	public SubjectGrades() {
	}

}
