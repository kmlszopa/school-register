package pl.kamilszopa.model.repository;

import org.springframework.data.repository.CrudRepository;

import pl.kamilszopa.model.Note;

public interface NoteRepository extends CrudRepository<Note, Long>{

}
