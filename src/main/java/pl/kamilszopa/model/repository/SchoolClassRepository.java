package pl.kamilszopa.model.repository;

import org.springframework.data.repository.CrudRepository;

import pl.kamilszopa.model.SchoolClass;
import pl.kamilszopa.model.Teacher;

public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {
	SchoolClass getSchoolClassByTeacher(Teacher teacher);
}
