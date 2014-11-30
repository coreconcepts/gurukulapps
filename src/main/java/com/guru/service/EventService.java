package com.guru.service;

import java.util.List;

import com.guru.exceptions.EventNotFound;
import com.guru.model.Event;

public interface EventService {
	
	public Event create(Event event);
	public Event delete(int id) throws EventNotFound;
	public Iterable<Event> findAll();
	public Event update(Event event) throws EventNotFound;
	public Event findById(int id);
	public Iterable<Event> findByEventName(String eventName);

}
