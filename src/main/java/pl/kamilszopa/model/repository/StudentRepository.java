package pl.kamilszopa.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import pl.kamilszopa.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	
	Student findBySurNameAndFirstName(String surName, String firstName);
}
