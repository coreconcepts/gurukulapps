package com.guru.handlers;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.guru.model.Event;
@Repository
public interface EventRepositoryCustom extends CrudRepository<Event, Integer> {
	
	public Iterable<Event> findByEventName(String lessons);
}
