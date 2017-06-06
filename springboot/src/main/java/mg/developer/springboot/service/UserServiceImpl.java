package mg.developer.springboot.service;

import javax.annotation.PostConstruct;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mg.developer.springboot.constant.Role;
import mg.developer.springboot.model.User;
import mg.developer.springboot.repository.UserRepository;

@Service("userService")
public class UserServiceImpl extends ServiceAbs<User, Integer, UserRepository> implements UserService {

	@Override
	public User findUserByEmail(String email) {
		return this.repository.findByEmail(email);
	}

	@PostConstruct
	private void intiUser() {
		if(this.repository.count() == 0) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			User user = new User();
			user.setName("Default admin");
			user.setEmail("admin@admin.com");
			user.setPassword(passwordEncoder.encode("Admin123"));
			user.setRole(Role.ROLE_ADMIN.name());
			this.saveOrUpdate(user);
			System.out.println("User initialise");
		} else {
			System.out.println("User alredy update");
		}
	}

}
