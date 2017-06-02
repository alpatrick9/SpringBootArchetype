package mg.developer.springboot.service;

import org.springframework.stereotype.Service;

import mg.developer.springboot.model.User;
import mg.developer.springboot.repository.UserRepository;

@Service("userService")
public class UserServiceImpl extends ServiceAbs<User, Integer, UserRepository> implements UserService {

	@Override
	public User findUserByEmail(String email) {
		return this.repository.findByEmail(email);
	}

}
