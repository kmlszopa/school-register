package pl.kamilszopa.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
	@ElementCollection(targetClass = Integer.class)
	private List<Integer> grades;

	public SubjectGrades(Subject subject) {
		this.subject = subject;
		this.grades = generateGrades();
	}

	public SubjectGrades() {
	}

	private List<Integer> generateGrades() {
		Random rand = new Random();
		List<Integer> gradesList = new ArrayList<>();
		for (int i = 0; i < rand.nextInt(10); i++) {
			gradesList.add(rand.nextInt(6) + 1);
		}
		return gradesList;
	}
}
