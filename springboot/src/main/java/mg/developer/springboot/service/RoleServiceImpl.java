package mg.developer.springboot.service;

import org.springframework.stereotype.Service;

import mg.developer.springboot.model.Role;
import mg.developer.springboot.repository.RoleRepository;

@Service("roleService")
public class RoleServiceImpl extends ServiceAbs<Role, Integer, RoleRepository> implements RoleService {

	@Override
	public Role findByRole(String role) {
		return this.repository.findByRole(role);
	}

}
