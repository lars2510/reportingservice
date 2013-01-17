package com.jinengo.reporting.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_AggrUserFigures")
public class AggrUserFigures {
	
	@Id
	private int id;
	private int jinengoUserID;
	private int year;
	private int month;
	private String transportationType;
	private String need;
	private int countRoutes;
	private int countSubroutes;
	private float minDistance;
	private float maxDistance;
	private float avgDistance;
	private float sumDistance;
	private int minTime;
	private int maxTime;
	private int avgTime;
	private int sumTime;
	private int minTimeUsable;
	private int maxTimeUsable;
	private int avgTimeUsable;
	private int sumTimeUsable;
	private float minCosts;
	private float maxCosts;
	private float avgCosts;
	private float sumCosts;
	private float minEcoImpact;
	private float maxEcoImpact;
	private float avgEcoImpact;
	private float sumEcoImpact;
	
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
	public String getTransportationType() {
		return transportationType;
	}
	public void seTransportationType(String transportationType) {
		this.transportationType = transportationType;
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
	public float getMinDistance() {
		return minDistance;
	}
	public void setMinDistance(float minDistance) {
		this.minDistance = minDistance;
	}
	public float getMaxDistance() {
		return maxDistance;
	}
	public void setMaxDistance(float maxDistance) {
		this.maxDistance = maxDistance;
	}
	public float getAvgDistance() {
		return avgDistance;
	}
	public void setAvgDistance(float avgDistance) {
		this.avgDistance = avgDistance;
	}
	public float getSumDistance() {
		return sumDistance;
	}
	public void setSumDistance(float sumDistance) {
		this.sumDistance = sumDistance;
	}
	public int getMinTime() {
		return minTime;
	}
	public void setMinTime(int minTime) {
		this.minTime = minTime;
	}
	public int getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}
	public int getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(int avgTime) {
		this.avgTime = avgTime;
	}
	public int getSumTime() {
		return sumTime;
	}
	public void setSumTime(int sumTime) {
		this.sumTime = sumTime;
	}
	public int getMinTimeUsable() {
		return minTimeUsable;
	}
	public void setMinTimeUsable(int minTimeUsable) {
		this.minTimeUsable = minTimeUsable;
	}
	public int getMaxTimeUsable() {
		return maxTimeUsable;
	}
	public void setMaxTimeUsable(int maxTimeUsable) {
		this.maxTimeUsable = maxTimeUsable;
	}
	public int getAvgTimeUsable() {
		return avgTimeUsable;
	}
	public void setAvgTimeUsable(int avgTimeUsable) {
		this.avgTimeUsable = avgTimeUsable;
	}
	public int getSumTimeUsable() {
		return sumTimeUsable;
	}
	public void setSumTimeUsable(int sumTimeUsable) {
		this.sumTimeUsable = sumTimeUsable;
	}
	public float getMinCosts() {
		return minCosts;
	}
	public void setMinCosts(float minCosts) {
		this.minCosts = minCosts;
	}
	public float getMaxCosts() {
		return maxCosts;
	}
	public void setMaxCosts(float maxCosts) {
		this.maxCosts = maxCosts;
	}
	public float getAvgCosts() {
		return avgCosts;
	}
	public void setAvgCosts(float avgCosts) {
		this.avgCosts = avgCosts;
	}
	public float getSumCosts() {
		return sumCosts;
	}
	public void setSumCosts(float sumCosts) {
		this.sumCosts = sumCosts;
	}
	public float getMinEcoImpact() {
		return minEcoImpact;
	}
	public void setMinEcoImpact(float minEcoImpact) {
		this.minEcoImpact = minEcoImpact;
	}
	public float getMaxEcoImpact() {
		return maxEcoImpact;
	}
	public void setMaxEcoImpact(float maxEcoImpact) {
		this.maxEcoImpact = maxEcoImpact;
	}
	public float getAvgEcoImpact() {
		return avgEcoImpact;
	}
	public void setAvgEcoImpact(float avgEcoImpact) {
		this.avgEcoImpact = avgEcoImpact;
	}
	public float getSumEcoImpact() {
		return sumEcoImpact;
	}
	public void setSumEcoImpact(float sumEcoImpact) {
		this.sumEcoImpact = sumEcoImpact;
	}
	public AggrUserFigures(int id, int jinengoUserID, int year, int month,
			String transportationType, String need, int countRoutes,
			int countSubroutes, float minDistance, float maxDistance,
			float avgDistance, float sumDistance, int minTime, int maxTime,
			int avgTime, int sumTime, int minTimeUsable, int maxTimeUsable,
			int avgTimeUsable, int sumTimeUsable, float minCosts,
			float maxCosts, float avgCosts, float sumCosts, float minEcoImpact,
			float maxEcoImpact, float avgEcoImpact, float sumEcoImpact) {
		super();
		this.id = id;
		this.jinengoUserID = jinengoUserID;
		this.year = year;
		this.month = month;
		this.transportationType = transportationType;
		this.need = need;
		this.countRoutes = countRoutes;
		this.countSubroutes = countSubroutes;
		this.minDistance = minDistance;
		this.maxDistance = maxDistance;
		this.avgDistance = avgDistance;
		this.sumDistance = sumDistance;
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.avgTime = avgTime;
		this.sumTime = sumTime;
		this.minTimeUsable = minTimeUsable;
		this.maxTimeUsable = maxTimeUsable;
		this.avgTimeUsable = avgTimeUsable;
		this.sumTimeUsable = sumTimeUsable;
		this.minCosts = minCosts;
		this.maxCosts = maxCosts;
		this.avgCosts = avgCosts;
		this.sumCosts = sumCosts;
		this.minEcoImpact = minEcoImpact;
		this.maxEcoImpact = maxEcoImpact;
		this.avgEcoImpact = avgEcoImpact;
		this.sumEcoImpact = sumEcoImpact;
	}
	public AggrUserFigures() {
		
	}
	
}
