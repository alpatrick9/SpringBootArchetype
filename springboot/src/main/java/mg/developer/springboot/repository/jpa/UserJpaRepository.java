package mg.developer.springboot.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.developer.springboot.model.User;

@Repository("userJpaRepository")
public interface UserJpaRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);
}
