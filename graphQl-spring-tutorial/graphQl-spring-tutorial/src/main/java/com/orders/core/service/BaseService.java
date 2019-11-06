package com.orders.core.service;

import java.util.Optional;

public interface BaseService<T, C> {

	/*
	 * default T insert(T t, JpaRepository<T, I> repo) { return repo.save(t); }
	 */

	T insert(T entity);

	Optional<T> selectByEntityCode(C code);

	Void remove(C code);
}
