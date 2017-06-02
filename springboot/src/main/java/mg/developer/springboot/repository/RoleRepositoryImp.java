package mg.developer.springboot.repository;

import org.springframework.stereotype.Service;

import mg.developer.springboot.model.Role;
import mg.developer.springboot.repository.jpa.RoleJpaRepository;

@Service("roleRepository")
public class RoleRepositoryImp extends EntityRepositoryAbs<Role, Integer, RoleJpaRepository> implements RoleRepository {

	@Override
	public Role findByRole(String role) {
		return this.jpaRepository.findByRole(role);
	}

}
