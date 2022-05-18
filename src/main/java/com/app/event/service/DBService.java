package com.app.event.service;

public interface DBService<R> {

    public Object save(Object o, R repository);
}
