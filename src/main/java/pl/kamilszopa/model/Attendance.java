package pl.kamilszopa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
public class Attendance {
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private Student student;
	@ElementCollection(targetClass = Boolean.class)
	private List<Boolean> attendanceOnClass;
	private Date day;

	public Attendance(Student student, Date date) {
		this.student = student;
		this.attendanceOnClass = new ArrayList<Boolean>();
		this.day = date;
	}

	public Attendance() {
	}

	public void changeAttendance(Integer attendanceIndex) {
		if (this.attendanceOnClass.get(attendanceIndex)) {
			this.attendanceOnClass.set(attendanceIndex, false);
		} else {
			this.attendanceOnClass.set(attendanceIndex, true);
		}
	}


}
