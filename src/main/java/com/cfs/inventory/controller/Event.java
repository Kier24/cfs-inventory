package com.cfs.inventory.controller;

class Event {
	private String title;
	private String start;
	private String end;
	
	Event(String title,String start){
		this.title=title;
		this.start=start;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
}
