package mg.developer.springboot.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mg.developer.springboot.model.Todo;

@Repository("todoJpaRepository")
public interface TodoJpaRepository extends JpaRepository<Todo, Integer> {
}
