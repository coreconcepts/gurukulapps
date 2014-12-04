package com.guru.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guru.exceptions.EventNotFound;
import com.guru.model.Event;
import com.guru.repository.EventRepositoryCustom;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	UserService uServ;
	
	@Resource
	private EventRepositoryCustom eventRepository;

	@Override
	@Transactional
	public Event create(Event event) {
		Event createdEvent = event;
		// Temporary arrangement.
		event.setAuthorId(1000);
		event.setCreateDate(new Date());
		
		//uServ.create(null);
		
		return eventRepository.save(createdEvent);
	}
	
	@Override
	@Transactional
	public Event findById(int id) {
		return eventRepository.findOne(id);
	}

	@Override
	@Transactional(rollbackFor=EventNotFound.class)
	public Event delete(int id) throws EventNotFound {
		Event deletedEvent = eventRepository.findOne(id);
		
		if (deletedEvent == null)
			throw new EventNotFound();
		
		eventRepository.delete(deletedEvent);
		return deletedEvent;
	}

	@Override
	@Transactional
	public Iterable<Event> findAll() {
		
			return eventRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor=EventNotFound.class)
	public Event update(Event event) throws EventNotFound {
//		Event updatedEvent = eventRepository.findOne(event.getId());
//		
//		if (updatedEvent == null)
//			throw new EventNotFound();
//		
//		updatedEvent.setName(event.getName());
//		updatedEvent.setEmplNumber(event.getEmplNumber());
//		return updatedEvent;
		return null;
	}

	@Override
	public Iterable<Event> findByEventName(String eventName) {
		return eventRepository.findByEventName(eventName);
	}

}
