package com.jinengo.reporting.service.platform;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.jinengo.reporting.model.user.AggrPlatformFigures;
import com.jinengo.reporting.model.user.AggrUserFigures;


@Repository
public class PlatformFiguresDao {
	
	@Autowired
	@Qualifier("sessionFactoryDW")
	private SessionFactory sessionFactoryDw;
	
	/**
	 * Platform figures for a given year, grouped by month
	 * 
	 * @param keyFigure
	 * @param year
	 * @return list - list of avg platform figures
	 */
	public List<AggrPlatformFigures> getKeyFigureSum(String keyFigure, int year) {
		
		Session session = getSessionFactoryDw().openSession();
		String keyColumn = StringEscapeUtils.escapeSql(keyFigure);
		
		String hql = "select sum(" + keyColumn + ") / count(distinct JinengoUserID), year, month " +
						"from AggrUserFigures " +
						"where year = :year " +
						"group by year, month";
		
		Query query = session.createQuery(hql);
		query.setParameter("year", year);
		
		return query.list();
	}
	
	/**
	 * Average platform figures per km for a given year, grouped by month
	 * 
	 * @param keyFigure
	 * @param year
	 * @return list - list of avg platform figures
	 */
	public List<AggrPlatformFigures> getKeyFigureAvg(String keyFigure, int year) {
		
		Session session = getSessionFactoryDw().openSession();
		String keyColumn = StringEscapeUtils.escapeSql(keyFigure);
		
		String hql = "select sum(" + keyColumn + ") / sum(sumDistance), year, month " +
						"from AggrPlatformFigures " +
						"where year = :year " +
						"group by year, month";
		
		Query query = session.createQuery(hql);
		query.setParameter("year", year);
		
		return query.list();
	}
	
	/**
	 * Get key figures for each transportation type
	 * 
	 * @param keyFigure
	 * @param year
	 * @param month
	 * @return list - list of key figures
	 */
	public List<AggrPlatformFigures> getTransportation(String keyFigure, int year, int month) {

		Session session = getSessionFactoryDw().openSession();
		String keyColumn = StringEscapeUtils.escapeSql(keyFigure);
		
		String hql = "select sum(" + keyColumn + "), year, transportationType " +
						"from AggrPlatformFigures " +
						"where year = :year " +
						"group by year, transportationType";
		
		Query query = session.createQuery(hql);
		query.setParameter("year", year);
		
		return query.list();
	}	
	
	public SessionFactory getSessionFactoryDw() {
		return sessionFactoryDw;
	}

	public void setSessionFactoryDw(SessionFactory sessionFactoryDw) {
		this.sessionFactoryDw = sessionFactoryDw;
	}
}
