package mg.developer.springboot.repository;

import java.io.Serializable;
import java.util.List;

public interface EntityRepository<E, PK extends Serializable> {
	
	public List<E> findAll();
	
	public void saveOrUpdate(E entity);
	
	public void delete(PK key);
	
	public E find(PK key);
	
	public Long count();
	
}
