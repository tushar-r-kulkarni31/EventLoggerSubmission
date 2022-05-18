package com.app.event.service;

import org.springframework.stereotype.Service;

@Service
public class HsqlDbService<R extends org.springframework.data.repository.CrudRepository> implements DBService<R>{

    @Override
    public Object save(Object o, R repository) {
        return repository.save(o);
    }
}
