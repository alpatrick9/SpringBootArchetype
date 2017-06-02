package mg.developer.springboot.service;

import mg.developer.springboot.model.User;

public interface UserService extends IService<User, Integer>{
	public User findUserByEmail(String email);
}
