package com.app.event.service;

import com.app.event.entity.Event;
import com.app.event.repository.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class EventServiceImpl implements  EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventServiceImpl.class);

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private DBService dbService;

    @Override
    public Map<String, Event> saveEvent(Map<String, Event> e) {
        LOGGER.info("Saving the data to the DB");

        e.forEach((key, event) ->{
            dbService.save(event, eventRepository);
            LOGGER.debug("Event Saved to DB >> " + key);
        });

        LOGGER.info("Data saved successfully to the DB");

        return e;
    }
}
