package mg.developer.springboot.service;

import org.springframework.stereotype.Service;

import mg.developer.springboot.model.Todo;
import mg.developer.springboot.repository.TodoRepository;

@Service("todoService")
public class TodoServiceImpl extends ServiceAbs<Todo, Integer, TodoRepository> implements TodoService {
	
}
