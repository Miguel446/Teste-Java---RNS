package com.rns.testes.java.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.rns.testes.java.model.GenericEntity;

/**
 * Todas as Services que manipulam entidades persistidas devem herdar essa
 * classe. Aqui estamos implementando métodos básicos para adição, update e
 * delete fÃ­sico. Se necessário sobrescreva os métodos na sua service.
 *
 * @param <T> entidade
 * @param <I> classe do id da entidade
 */
@Service
public abstract class AbstractGenericServicePersistence<D extends JpaRepository<T, I>, T extends GenericEntity<I>, I extends Serializable>
		implements IGenericService<T, I> {

	@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
	@Autowired
	D dao;

	@Override
	public T save(T t) {
		return dao.save(t);
	}

	@Override
	public T update(T t) {
		if (t.getId() == null) {
			throw new UnsupportedOperationException("Esse objeto nao possui um id, realize um save.");
		}

		Optional<T> old = dao.findById(t.getId());
		if (old.isPresent()) {
			t.setVersao(old.get().getVersao());
			return dao.save(t);
		} else {
			throw new UnsupportedOperationException("Objeto nao encontrado");
		}

	}

	@Override
	public void delete(T t) {
		dao.delete(t);
	}

	@Override
	public void delete(I i) {
		dao.deleteById(i);
	}

	@Override
	public List<T> findAll() {
		return dao.findAll();
	}

	@Override
	public T findById(I i) {
		Optional<T> t = dao.findById(i);
		if (t.isPresent())
			return t.get();
		else
			throw new UnsupportedOperationException("Objeto nao encontrado");
	}
}
