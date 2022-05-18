package com.app.event.eventlogger;

import com.app.event.entity.Event;
import com.app.event.pojo.LogDetail;
import com.app.event.service.EventService;
import com.app.event.service.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InitProgram implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitProgram.class);

    @Autowired
    private FileReader fileReader;

    @Autowired
    private EventService eventService;

    public void startProgram(String filePath) {
        LOGGER.info("Process started log file >> " + filePath);

        // Parse log file
        List<LogDetail> lstLogDetails = fileReader.parseLogFile(filePath);

        // Convert the data into map
        Map<String, Event> mapEvents = lstLogDetails.stream().map(l -> l).collect(Collectors.toMap(l-> l.getId(), l->{
            return new Event(l.getId(), l.getTimestamp(), l.getType(), l.getHost(), false);
        }, (oldValue, newValue)->{
            if(oldValue == null) {
                return newValue;
            } else {
                if(oldValue.getEventDuration() > newValue.getEventDuration()) {
                    oldValue.setEventDuration(oldValue.getEventDuration() - newValue.getEventDuration());
                } else {
                    oldValue.setEventDuration(newValue.getEventDuration() - oldValue.getEventDuration());
                }

                if(oldValue.getEventDuration() > 4) {
                    oldValue.setAlert(true);
                }
            }
            return oldValue;
        }));

        LOGGER.info("Data before save >> " + mapEvents);

        try {
            mapEvents = eventService.saveEvent(mapEvents);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        LOGGER.info("Data after save >> " + mapEvents);

        LOGGER.info("Process completed log file >> " + filePath);
    }

    @Override
    public void run(String... args) throws Exception {
        if(args.length > 0 && args[0].trim().length() > 0) {
            startProgram(args[0]);
        }
    }
}
