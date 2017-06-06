package mg.developer.springboot.controller;

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

import mg.developer.springboot.constant.Role;
import mg.developer.springboot.model.Password;
import mg.developer.springboot.model.User;
import mg.developer.springboot.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {
	
	@Autowired
	UserService userService;

	@RequestMapping("/users")
	String index(Model model, 
			@RequestParam(value = "update", required = false, defaultValue = "false") Boolean update,
			@RequestParam(value = "changepass", required = false, defaultValue = "false") Boolean changePass,
			@RequestParam(value = "errorpass", required = false, defaultValue = "false") Boolean errorpass,
			@RequestParam(value = "id", required = false) Integer id) {
		model.addAttribute("title", "User list");
		
		User user = new User();
		if(id != null) {
			user = userService.findOne(id);
			if(update) {
				model.addAttribute("roles", Role.values());
			}
			if(changePass) {
				Password password = new Password();
				password.setEmail(user.getEmail());
				password.setUserId(user.getId());
				model.addAttribute("password", password);
				user = new User();
			}
		}
		model.addAttribute("errorpass", errorpass);
		model.addAttribute("changepass", changePass);
		model.addAttribute("update", update);
		model.addAttribute("user", user);
		model.addAttribute("users", userService.getAll());
		
		return "/admin/users";
	}
	
	@RequestMapping(value="/add_user", method=RequestMethod.POST)
	ModelAndView addUser(@ModelAttribute("user") User user) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER.name());
		userService.saveOrUpdate(user);
		return new ModelAndView("redirect:/admin/users");
	}
	
	@RequestMapping("/del_user")
	ModelAndView deleteUser(@RequestParam("id")Integer id) {
		userService.delete(id);
		return new ModelAndView("redirect:/admin/users");
	}
	
	@RequestMapping("/user_status")
	ModelAndView changeStatusUser(@RequestParam("id")Integer id) {
		User user = userService.findOne(id);
		user.setActive(!user.getActive());
		userService.saveOrUpdate(user);
		return new ModelAndView("redirect:/admin/users");
	}
	
	@RequestMapping("/udpate_user")
	ModelAndView updateUser(@ModelAttribute("user") User user) {
		User toUpdate = userService.findOne(user.getId());
		toUpdate.bindFormData(user);
		userService.saveOrUpdate(toUpdate);
		return new ModelAndView("redirect:/admin/users");
	}
	
	@RequestMapping("/update_pass")
	ModelAndView changePassword(@ModelAttribute("password") Password password) {
		User user = userService.findOne(password.getUserId());
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(!passwordEncoder.matches(password.getOldPassword(), user.getPassword()))
			return new ModelAndView("redirect:/admin/users?changepass=true&errorpass=true&id="+password.getUserId());
		user.setPassword(passwordEncoder.encode(password.getNewPassword()));
		userService.saveOrUpdate(user);
		return new ModelAndView("redirect:/admin/users");
	}
	
}
