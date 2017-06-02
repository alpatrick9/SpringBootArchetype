package mg.developer.springboot.repository;

import mg.developer.springboot.model.Role;

public interface RoleRepository extends EntityRepository<Role, Integer> {
	Role findByRole(String role);
}
