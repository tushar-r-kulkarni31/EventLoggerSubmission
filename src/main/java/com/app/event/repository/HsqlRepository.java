package com.app.event.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface HsqlRepository<T, ID> extends CrudRepository<T, ID> {
}
