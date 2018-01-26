package pl.kamilszopa.model.repository;

import org.springframework.data.repository.CrudRepository;

import pl.kamilszopa.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
	Role findByRole(String role);
}
