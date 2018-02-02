package com.events.processor.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Event implements Serializable {

    private String eventId;

    private String visitorId;

    private Map<Integer, Integer> trackedExperiments = new HashMap<>();

    public Event() {

    }

    public Event(String eventId, String visitorId, Map<Integer, Integer> trackedExperiments) {
        this.eventId = eventId;
        this.visitorId = visitorId;
        this.trackedExperiments.putAll(trackedExperiments);
    }

    public String getEventId() {
        return eventId;
    }


    public String getVisitorId() {
        return this.visitorId;
    }

    public Map<Integer,Integer> getTrackedExperiments() {
        return this.trackedExperiments;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return eventId != null ? eventId.equals(event.eventId) : event.eventId == null;

    }

    @Override
    public int hashCode() {
        return eventId != null ? eventId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", visitorId='" + visitorId + '\'' +
                ", trackedExperiments=" + trackedExperiments +
                '}';
    }
}
