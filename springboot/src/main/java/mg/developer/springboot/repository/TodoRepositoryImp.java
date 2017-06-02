package mg.developer.springboot.repository;

import org.springframework.stereotype.Service;

import mg.developer.springboot.model.Todo;
import mg.developer.springboot.repository.jpa.TodoJpaRepository;

@Service("todoRepository")
public class TodoRepositoryImp extends EntityRepositoryAbs<Todo, Integer, TodoJpaRepository> implements TodoRepository {

}
