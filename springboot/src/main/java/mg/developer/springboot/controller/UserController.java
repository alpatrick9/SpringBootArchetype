package mg.developer.springboot.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mg.developer.springboot.constant.Role;
import mg.developer.springboot.model.Password;
import mg.developer.springboot.model.User;
import mg.developer.springboot.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@RequestMapping("/users")
	String index(Model model, 
			@RequestParam(value = "update", required = false, defaultValue = "false") Boolean update,
			@RequestParam(value = "changepass", required = false, defaultValue = "false") Boolean changePass,
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
		model.addAttribute("changepass", changePass);
		model.addAttribute("update", update);
		model.addAttribute("user", user);
		model.addAttribute("users", userService.getAll());
		
		return "/admin/users";
	}
	
	@RequestMapping(value="/add_user", method=RequestMethod.POST)
	ModelAndView addUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER.name());
		try {
			userService.saveOrUpdate(user);
		} catch (DataIntegrityViolationException e) {
			 redirectAttributes.addFlashAttribute("dataIntegrityError", "Erreur d'enregistrement: email "+user.getEmail()+" déjà existant!");
		} catch (ConstraintViolationException e) {
			redirectAttributes.addFlashAttribute("dataIntegrityError", "Address email ou password non valide: password au moins 5 caractères");
		}
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
	ModelAndView updateUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
		User toUpdate = userService.findOne(user.getId());
		toUpdate.bindFormData(user);
		try{
			userService.saveOrUpdate(toUpdate);
		} catch (DataIntegrityViolationException e) {
			 redirectAttributes.addFlashAttribute("dataIntegrityError", "Erreur d'enregistrement: email "+user.getEmail()+" déjà existant!");
		} catch (ConstraintViolationException e) {
			redirectAttributes.addFlashAttribute("dataIntegrityError", "Address email ou password non valide: password au moins 5 caractères");
		}
		return new ModelAndView("redirect:/admin/users");
	}
	
	@RequestMapping("/update_pass")
	ModelAndView changePassword(@ModelAttribute("password") Password password, RedirectAttributes redirectAttributes) {
		User user = userService.findOne(password.getUserId());
		if(!passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
			redirectAttributes.addFlashAttribute("errorChangePassword", "Changement de mot de passe interrompue: ancien mot de passe incorrect");
			return new ModelAndView("redirect:/admin/users?changepass=true&id="+password.getUserId());
		}
		user.setPassword(passwordEncoder.encode(password.getNewPassword()));
		userService.saveOrUpdate(user);
		return new ModelAndView("redirect:/admin/users");
	}
	
}
