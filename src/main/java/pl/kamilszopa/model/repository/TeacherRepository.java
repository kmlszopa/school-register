package pl.kamilszopa.model.repository;

import org.springframework.data.repository.CrudRepository;

import pl.kamilszopa.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {

}