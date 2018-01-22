package pl.kamilszopa.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.kamilszopa.model.Attendance;
import pl.kamilszopa.model.Student;

public interface AttendanceRepository extends CrudRepository<Attendance, Long>{
	
	List<Attendance> findByStudent(Student student);
}
