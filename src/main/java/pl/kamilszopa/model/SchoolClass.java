package pl.kamilszopa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class SchoolClass {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToOne
	private Teacher teacher;

	public SchoolClass() {
	}

	public SchoolClass(String name, Teacher teacher) {
		this.name = name;
		this.teacher = teacher;
	}

}
