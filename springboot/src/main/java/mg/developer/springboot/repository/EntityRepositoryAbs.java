package mg.developer.springboot.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class EntityRepositoryAbs<E, PK extends Serializable, J extends JpaRepository<E, PK>> implements EntityRepository<E, PK> {

	@Autowired
	protected J jpaRepository;
	
	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public List<E> findAll() {
		return jpaRepository.findAll();
	}

	@Override
	public void saveOrUpdate(E entity) {
		jpaRepository.save(entity);
	}

	@Override
	@Transactional
	public void delete(PK key) {
		jpaRepository.delete(key);
	}

	@Override
	public E find(PK key) {
		return jpaRepository.getOne(key);
	}

	@Override
	public Long count() {
		return jpaRepository.count();
	}
	
}
