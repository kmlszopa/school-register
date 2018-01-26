package pl.kamilszopa.model.repository;

import org.springframework.data.repository.CrudRepository;

import pl.kamilszopa.model.Parent;
import pl.kamilszopa.model.Teacher;

public interface ParentRepository extends CrudRepository<Parent, Long> {

	Parent findByEmailAdress(String emailAdress);
}
