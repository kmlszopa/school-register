package pl.kamilszopa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Note {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private Teacher author;
	@ManyToOne
	private Student student;
	private String title;
	private String description;

	public Note() {
	}

	public Note(Long id, Teacher author, Student student, String title, String description) {
		super();
		this.id = id;
		this.author = author;
		this.student = student;
		this.title = title;
		this.description = description;
	}

}
