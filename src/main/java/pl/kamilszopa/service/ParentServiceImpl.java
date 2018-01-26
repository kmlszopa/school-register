package pl.kamilszopa.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pl.kamilszopa.model.Parent;
import pl.kamilszopa.model.Role;
import pl.kamilszopa.model.repository.ParentRepository;
import pl.kamilszopa.model.repository.RoleRepository;

@Service
public class ParentServiceImpl implements ParentService {

	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Parent findParentByEmail(String email) {
		return parentRepository.findByEmailAdress(email);
	}


	@Override
	public void saveParent(Parent parent) {
		parent.setPassword(parent.getPassword());
		parent.setActive(1);
		Role parentRole = roleRepository.findByRole("PARENT");
		parent.setRoles(new HashSet<Role>(Arrays.asList(parentRole)));
		parentRepository.save(parent);
		
	}

}