package com.jinengo.reporting.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Jinengo user friends model
 * @author lars schuettemeyer
 *
 */
@Entity
@Table(name = "JinengoUserFriend")
public class JinengoUserFriend {
	
	@Id
	private int id;
	private int jinengoUserID_user;
	private int jinengoUserID_friend;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJinengoUserID_user() {
		return jinengoUserID_user;
	}
	public void setJinengoUserID_user(int jinengoUserID_user) {
		this.jinengoUserID_user = jinengoUserID_user;
	}
	public int getJinengoUserID_friend() {
		return jinengoUserID_friend;
	}
	public void setJinengoUserID_friend(int jinengoUserID_friend) {
		this.jinengoUserID_friend = jinengoUserID_friend;
	}
	public JinengoUserFriend(int id, int jinengoUserID_user,
			int jinengoUserID_friend) {
		super();
		this.id = id;
		this.jinengoUserID_user = jinengoUserID_user;
		this.jinengoUserID_friend = jinengoUserID_friend;
	}

}

