package com.app.event.repository;

import com.app.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends HsqlRepository<Event, Long> {
}
