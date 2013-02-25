package com.jinengo.reporting.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * aggregated user figures model
 * 
 * @author lars schuettemeyer
 *
 */
@Entity
@Table(name = "V_AggrUserFigure")
public class AggrUserFigure {
	
	@Id
	private int id;
	private int jinengoUserID;
	private int year;
	private int month;
	private String need;
	private int countRoutes;
	private int countSubroutes;
	private float sumDistance;
	private int sumTime;
	private int sumTimeBestOption;
	private int sumTimeWorstOption;
	private int sumTimeUsable;
	private int sumTimeUsableBestOption;
	private int sumTimeUsableWorstOption;
	private float sumCosts;
	private float sumCostsBestOption;
	private float sumCostsWorstOption;
	private float sumEcoImpact;
	private float sumEcoImpactBestOption;
	private float sumEcoImpactWorstOption;
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
	public int getSumTimeBestOption() {
		return sumTimeBestOption;
	}
	public void setSumTimeBestOption(int sumTimeBestOption) {
		this.sumTimeBestOption = sumTimeBestOption;
	}
	public int getSumTimeWorstOption() {
		return sumTimeWorstOption;
	}
	public void setSumTimeWorstOption(int sumTimeWorstOption) {
		this.sumTimeWorstOption = sumTimeWorstOption;
	}
	public int getSumTimeUsable() {
		return sumTimeUsable;
	}
	public void setSumTimeUsable(int sumTimeUsable) {
		this.sumTimeUsable = sumTimeUsable;
	}
	public int getSumTimeUsableBestOption() {
		return sumTimeUsableBestOption;
	}
	public void setSumTimeUsableBestOption(int sumTimeUsableBestOption) {
		this.sumTimeUsableBestOption = sumTimeUsableBestOption;
	}
	public int getSumTimeUsableWorstOption() {
		return sumTimeUsableWorstOption;
	}
	public void setSumTimeUsableWorstOption(int sumTimeUsableWorstOption) {
		this.sumTimeUsableWorstOption = sumTimeUsableWorstOption;
	}
	public float getSumCosts() {
		return sumCosts;
	}
	public void setSumCosts(float sumCosts) {
		this.sumCosts = sumCosts;
	}
	public float getSumCostsBestOption() {
		return sumCostsBestOption;
	}
	public void setSumCostsBestOption(float sumCostsBestOption) {
		this.sumCostsBestOption = sumCostsBestOption;
	}
	public float getSumCostsWorstOption() {
		return sumCostsWorstOption;
	}
	public void setSumCostsWorstOption(float sumCostsWorstOption) {
		this.sumCostsWorstOption = sumCostsWorstOption;
	}
	public float getSumEcoImpact() {
		return sumEcoImpact;
	}
	public void setSumEcoImpact(float sumEcoImpact) {
		this.sumEcoImpact = sumEcoImpact;
	}
	public float getSumEcoImpactBestOption() {
		return sumEcoImpactBestOption;
	}
	public void setSumEcoImpactBestOption(float sumEcoImpactBestOption) {
		this.sumEcoImpactBestOption = sumEcoImpactBestOption;
	}
	public float getSumEcoImpactWorstOption() {
		return sumEcoImpactWorstOption;
	}
	public void setSumEcoImpactWorstOption(float sumEcoImpactWorstOption) {
		this.sumEcoImpactWorstOption = sumEcoImpactWorstOption;
	}
	public String getCluster() {
		return cluster;
	}
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	public AggrUserFigure(int id, int jinengoUserID, int year, int month,
			String need, int countRoutes, int countSubroutes,
			float sumDistance, int sumTime, int sumTimeBestOption,
			int sumTimeWorstOption, int sumTimeUsable,
			int sumTimeUsableBestOption, int sumTimeUsableWorstOption,
			float sumCosts, float sumCostsBestOption,
			float sumCostsWorstOption, float sumEcoImpact,
			float sumEcoImpactBestOption, float sumEcoImpactWorstOption,
			String cluster) {
		super();
		this.id = id;
		this.jinengoUserID = jinengoUserID;
		this.year = year;
		this.month = month;
		this.need = need;
		this.countRoutes = countRoutes;
		this.countSubroutes = countSubroutes;
		this.sumDistance = sumDistance;
		this.sumTime = sumTime;
		this.sumTimeBestOption = sumTimeBestOption;
		this.sumTimeWorstOption = sumTimeWorstOption;
		this.sumTimeUsable = sumTimeUsable;
		this.sumTimeUsableBestOption = sumTimeUsableBestOption;
		this.sumTimeUsableWorstOption = sumTimeUsableWorstOption;
		this.sumCosts = sumCosts;
		this.sumCostsBestOption = sumCostsBestOption;
		this.sumCostsWorstOption = sumCostsWorstOption;
		this.sumEcoImpact = sumEcoImpact;
		this.sumEcoImpactBestOption = sumEcoImpactBestOption;
		this.sumEcoImpactWorstOption = sumEcoImpactWorstOption;
		this.cluster = cluster;
	}
	
}
