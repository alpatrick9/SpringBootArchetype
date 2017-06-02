package mg.developer.springboot.service;

import mg.developer.springboot.model.Role;

public interface RoleService extends IService<Role, Integer>{
	Role findByRole(String role);
}
