package com.jinengo.reporting.model.user;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jinengo user details
 * @author lars schuettemeyer
 *
 */
@Entity
@Table(name = "A_SOURCE_JinengoUser")
public class JinengoUser {
	
	@Id
	int id;
	Date timeRegistered;
	Date timeInactive;
	String name;
	int gender;
	String email;
	Date birthdate;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTimeRegistered() {
		return timeRegistered;
	}
	public void setTimeRegistered(Date timeRegistered) {
		this.timeRegistered = timeRegistered;
	}
	public Date getTimeInactive() {
		return timeInactive;
	}
	public void setTimeInactive(Date timeInactive) {
		this.timeInactive = timeInactive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
}
