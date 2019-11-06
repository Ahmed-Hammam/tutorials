package com.orders.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.orders.core.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {

	Optional<T> findByCode(String code);

	Void deleteByCode(String code);
}
