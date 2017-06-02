package mg.developer.springboot.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mg.developer.springboot.repository.EntityRepository;

public abstract class ServiceAbs<E, PK extends Serializable, R extends EntityRepository<E, PK>> implements IService<E, PK>{

	@Autowired
	protected R repository;
	
	@Override
	public List<E> getAll() {
		return repository.findAll();
	}

	@Override
	public void saveOrUpdate(E entity) {
		repository.saveOrUpdate(entity);
	}

	@Override
	public void delete(PK key) {
		repository.delete(key);
	}

	@Override
	public E findOne(PK key) {
		return repository.find(key);
	}

}
