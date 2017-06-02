package mg.developer.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {
	
	@RequestMapping("/")
	ModelAndView home() {
		return new ModelAndView("redirect:/secure/");
	}

}
