package com.jinengo.reporting.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "V_AggrUserFigurePerTransporation")
public class AggrUserFigurePerTransportation {
	
	@Id
	private int id;
	private int jinengoUserID;
	private int year;
	private int month;
	private String transportation;
	private String need;
	private int countRoutes;
	private int countSubroutes;
	private float sumDistance;
	private int sumTime;
	private int sumTimeUsable;
	private float sumCosts;
	private float sumEcoImpact;
	private String cluster;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJinengoUserID() {
		return jinengoUserID;
	}
	public void setJinengoUserID(int jinengoUserID) {
		this.jinengoUserID = jinengoUserID;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public String getNeed() {
		return need;
	}
	public void setNeed(String need) {
		this.need = need;
	}
	public int getCountRoutes() {
		return countRoutes;
	}
	public void setCountRoutes(int countRoutes) {
		this.countRoutes = countRoutes;
	}
	public int getCountSubroutes() {
		return countSubroutes;
	}
	public void setCountSubroutes(int countSubroutes) {
		this.countSubroutes = countSubroutes;
	}
	public float getSumDistance() {
		return sumDistance;
	}
	public void setSumDistance(float sumDistance) {
		this.sumDistance = sumDistance;
	}
	public int getSumTime() {
		return sumTime;
	}
	public void setSumTime(int sumTime) {
		this.sumTime = sumTime;
	}
	public int getSumTimeUsable() {
		return sumTimeUsable;
	}
	public void setSumTimeUsable(int sumTimeUsable) {
		this.sumTimeUsable = sumTimeUsable;
	}
	public float getSumCosts() {
		return sumCosts;
	}
	public void setSumCosts(float sumCosts) {
		this.sumCosts = sumCosts;
	}
	public float getSumEcoImpact() {
		return sumEcoImpact;
	}
	public void setSumEcoImpact(float sumEcoImpact) {
		this.sumEcoImpact = sumEcoImpact;
	}
	public String getCluster() {
		return cluster;
	}
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	public AggrUserFigurePerTransportation(int id, int jinengoUserID, int year,
			int month, String transportation, String need, int countRoutes,
			int countSubroutes, float sumDistance, int sumTime,
			int sumTimeUsable, float sumCosts, float sumEcoImpact,
			String cluster) {
		super();
		this.id = id;
		this.jinengoUserID = jinengoUserID;
		this.year = year;
		this.month = month;
		this.transportation = transportation;
		this.need = need;
		this.countRoutes = countRoutes;
		this.countSubroutes = countSubroutes;
		this.sumDistance = sumDistance;
		this.sumTime = sumTime;
		this.sumTimeUsable = sumTimeUsable;
		this.sumCosts = sumCosts;
		this.sumEcoImpact = sumEcoImpact;
		this.cluster = cluster;
	}
	
}
