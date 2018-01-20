package pl.kamilszopa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Subject {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	public Subject(String name) {
		this.name = name;
	}

	public Subject() {
	}

}
