package pl.kamilszopa.model.repository;

import org.springframework.data.repository.CrudRepository;

import pl.kamilszopa.model.Parent;
import pl.kamilszopa.model.Teacher;

public interface AdultRepository extends CrudRepository<Parent, Long> {
	Parent findBySurNameAndFirstName(String surName, String firstName);

	Parent findByEmailAdress(String emailAdress);
}
