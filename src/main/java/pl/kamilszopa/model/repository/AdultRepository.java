package pl.kamilszopa.model.repository;

import org.springframework.data.repository.CrudRepository;

import pl.kamilszopa.model.Parent;

public interface AdultRepository extends CrudRepository<Parent, Long> {

}
