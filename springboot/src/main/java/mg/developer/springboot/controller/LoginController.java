package mg.developer.springboot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

	@RequestMapping("/login")
	String loginPage(Model model,
			@RequestParam(value="error", required=false, defaultValue="false") boolean error,
			@RequestParam(value="logout", required=false, defaultValue="false") boolean logout,
			HttpSession session) {
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
		return "login/login";
	}
	
	@RequestMapping("/access-denied")
	String accessDenied(Model model) {
		model.addAttribute("title", "Accès refuser");
		return "login/access_denied";
	}
}
