package com.app.event.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EventState {
    @JsonProperty("STARTED")
    STARTED,
    @JsonProperty("FINISHED")
    FINISHED
}
