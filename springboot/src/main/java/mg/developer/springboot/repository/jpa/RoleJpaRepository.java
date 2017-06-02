package mg.developer.springboot.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.developer.springboot.model.Role;

@Repository("roleJpaRepository")
public interface RoleJpaRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);
}
