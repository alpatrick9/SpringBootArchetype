package mg.developer.springboot.service;

import java.util.List;

public interface IService<E, PK> {
	
	public List<E> getAll();
	
	public void saveOrUpdate(E entity);
	
	public void delete(PK key);
	
	public E findOne(PK key);
}
