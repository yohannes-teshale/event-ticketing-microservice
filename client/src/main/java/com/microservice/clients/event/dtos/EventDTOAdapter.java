package com.microservice.clients.event.dtos;

import com.microservice.clients.event.dtos.valueObjects.Event;


public class EventDTOAdapter {
    public static Event getEvent(EventRequest eventRequest){
        Event event= new Event();
        event.setDescription(eventRequest.getDescription());
        event.setName(eventRequest.getName());
        event.setStartdate(eventRequest.getStartdate());
        event.setEndDate(eventRequest.getEndDate());
        event.setAgeRestriction(eventRequest.getAgeRestriction());
        event.setLocation(eventRequest.getLocation());
        event.setEventType(eventRequest.getEventType());
        event.setTicketItemsList(eventRequest.getTicketItemsList());
        return event;
    }

}
