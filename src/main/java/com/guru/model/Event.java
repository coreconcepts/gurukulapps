package com.guru.model;


 import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import common.DataBean;
 
 @Entity
 public class Event implements DataBean{
 	
 	@Id
 	@GeneratedValue(strategy=GenerationType.AUTO)
 	private int id;
 	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String eventName;
 	private String eventGenre;
 	private Date eventStartTime;
 	private Date eventEndTime;
 	private String eventLocation;
 	private String eventDetails;
 	private String eventSelfRating;
 	private String lessons;
	private int authorId;
	private Date createDate;
 	
 	public Event(){}
 	public Event(String lessons){
 		this.lessons=lessons;
 	}
 	
 	public Event(String eventName, String eventGenre, Date eventStartDate,
 			Date eventEndDate, String eventLocation, String eventDetails,
 			String eventSelfRating, String lessons) {
 		super();
 		this.eventName = eventName;
 		this.eventGenre = eventGenre;
 		this.eventStartTime = eventStartDate;
 		this.eventEndTime = eventEndDate;
 		this.eventLocation = eventLocation;
 		this.eventDetails = eventDetails;
 		this.eventSelfRating = eventSelfRating;
 		this.lessons = lessons;
 	}
 
 	public String getEventName() {
 		return eventName;
 	}
 	public void setEventName(String eventName) {
 		this.eventName = eventName;
 	}
 	public String getEventGenre() {
 		return eventGenre;
 	}
 	public void setEventGenre(String eventGenre) {
 		this.eventGenre = eventGenre;
 	}
 	public Date getEventStartTime() {
 		return eventStartTime;
 	}
 	public void setEventStartTime(Date eventStartTime) {
 		this.eventStartTime = eventStartTime;
 	}
 	public Date getEventEndTime() {
 		return eventEndTime;
 	}
 	public void setEventEndTime(Date eventEndTime) {
 		this.eventEndTime = eventEndTime;
 	}
 	public String getEventLocation() {
 		return eventLocation;
 	}
 	public void setEventLocation(String eventLocation) {
 		this.eventLocation = eventLocation;
 	}
 	public String getEventDetails() {
 		return eventDetails;
 	}
 	public void setEventDetails(String eventDetails) {
 		this.eventDetails = eventDetails;
 	}
 	public String getEventSelfRating() {
 		return eventSelfRating;
 	}
 	public void setEventSelfRating(String eventSelfRating) {
 		this.eventSelfRating = eventSelfRating;
 	}
 	public String getLessons() {
 		return lessons;
 	}
 	public void setLessons(String lessons) {
 		this.lessons = lessons;
 	}

	public void setAuthorId(int i) {
		this.authorId = i;
		
	}
	public int getAuthorId() {
		return this.authorId;
		
	}

	public void setCreateDate(Date date) {
		this.createDate = date;
		
	}
	public Date getCreateDate(){
		return this.createDate;
	}


 	
 	
 
 }
