package pl.kamilszopa.service;

import pl.kamilszopa.model.Parent;

public interface ParentService {
	public Parent findParentByEmail(String email);

	public void saveParent(Parent user);
}
