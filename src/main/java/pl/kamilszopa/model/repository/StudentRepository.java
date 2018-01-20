package pl.kamilszopa.model.repository;

import org.springframework.data.repository.CrudRepository;

import pl.kamilszopa.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
