package pl.kamilszopa.model.repository;

import org.springframework.data.repository.CrudRepository;

import pl.kamilszopa.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
	Teacher findBySurNameAndFirstName(String surName, String firstName);
	Teacher findByEmailAdress(String emailAdress);

}
