package com.app.event.service;

import com.app.event.entity.Event;
import com.app.event.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface EventService {

    public Map<String, Event> saveEvent(Map<String, Event> e);
}
