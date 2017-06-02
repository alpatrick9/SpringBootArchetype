package mg.developer.springboot.repository;

import org.springframework.stereotype.Service;

import mg.developer.springboot.model.User;
import mg.developer.springboot.repository.jpa.UserJpaRepository;

@Service("userRepository")
public class UserRepositoryImpl extends EntityRepositoryAbs<User, Integer, UserJpaRepository> implements UserRepository {

	@Override
	public User findByEmail(String email) {
		return this.jpaRepository.findByEmail(email);
	}

}
