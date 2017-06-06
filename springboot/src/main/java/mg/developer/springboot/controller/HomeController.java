package mg.developer.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mg.developer.springboot.model.Todo;
import mg.developer.springboot.service.TodoService;

@Controller
@RequestMapping("/secure")
public class HomeController {

	@Autowired
	TodoService todoService;
	
	@RequestMapping("/")
	String index(Model model, @RequestParam(value = "id", required = false)String id) {
		
		model.addAttribute("title", "Home page");
		model.addAttribute("todos", todoService.getAll());
		
		Todo todo = new Todo();
		
		if (id != null && !id.isEmpty()) {
			todo = todoService.findOne(Integer.parseInt(id));
		}
		
		model.addAttribute("todo", todo);
		return "home/home";
	}
	
	@RequestMapping(value="/add_todo", method=RequestMethod.POST)
	ModelAndView createTodo(@ModelAttribute("todo") Todo todo) {
		if(todo.getId() != null) {
			Todo toUpdate = todoService.findOne(todo.getId());
			toUpdate.bindFormData(todo);
			todo = toUpdate;
		}
		todoService.saveOrUpdate(todo);
		return new ModelAndView("redirect:/secure/");
	}
	
	@RequestMapping("/delete_todo")
	ModelAndView deleteTodo(@RequestParam(value="id")Integer id) {
		todoService.delete(id);
		return new ModelAndView("redirect:/secure/");
	}
	
}
