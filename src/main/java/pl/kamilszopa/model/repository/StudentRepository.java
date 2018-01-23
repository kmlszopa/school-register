package pl.kamilszopa.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import pl.kamilszopa.model.SchoolClass;
import pl.kamilszopa.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	
	Student findBySurNameAndFirstName(String surName, String firstName);
	List<Student> getStudentsBySchoolClass(SchoolClass schoolClass);
}
