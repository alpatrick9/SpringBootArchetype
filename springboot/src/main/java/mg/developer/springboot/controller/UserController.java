package mg.developer.springboot.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mg.developer.springboot.model.Role;
import mg.developer.springboot.model.User;
import mg.developer.springboot.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/registration/users")
	String index(Model model) {
		model.addAttribute("title", "User list");
		
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("users", userService.getAll());
		
		return "/admin/users";
	}
	
	@RequestMapping(value="/registration/add_user", method=RequestMethod.POST)
	ModelAndView addUser(@ModelAttribute("user") User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = new Role();
		role.setRole("ROLE_ADMIN");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		userService.saveOrUpdate(user);
		return new ModelAndView("redirect:/registration/users");
	}
	
	@RequestMapping("/registration/del_user")
	ModelAndView deleteUser(@RequestParam("id")Integer id) {
		userService.delete(id);
		return new ModelAndView("redirect:/registration/users");
	}
}
