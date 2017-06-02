package mg.developer.springboot.repository;

import mg.developer.springboot.model.User;

public interface UserRepository extends EntityRepository<User, Integer>{
	User findByEmail(String email);
}
