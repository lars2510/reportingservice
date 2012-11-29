package com.jinengo.reporting.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
//@Table(name = "board")
public class TransportationType {

	@Id
	//@Column(name = "keyword_news_id")
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TransportationType(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public TransportationType() {
		
	}
	
	
}
