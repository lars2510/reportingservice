package com.jinengo.reporting.service.platform;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.jinengo.reporting.model.user.AggrUserFigure;
import com.jinengo.reporting.model.user.AggrUserFigurePerTransportation;

/**
 * Data Access Object to get the platform data from the data warehouse
 * 
 * @author lars schuettemeyer
 *
 */
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
	public List<AggrUserFigure> getKeyFigureSum(String keyFigure, int year) {
		
		Session session = getSessionFactoryDw().openSession();
		String keyColumn = StringEscapeUtils.escapeSql(keyFigure);
		
		String hql = "select sum(" + keyColumn + ") / count(distinct JinengoUserID), year, month " +
						"from AggrUserFigure " +
						"where year = :year " +
						"group by year, month " +
						"order by month";
		
		Query query = session.createQuery(hql);
		query.setParameter("year", year);
		
		List<AggrUserFigure> res = query.list(); 
		session.close();
		return res;
	}
	
	/**
	 * Average platform figures per km for a given year, grouped by month
	 * 
	 * @param keyFigure
	 * @param year
	 * @return list - list of avg platform figures
	 */
	public List<AggrUserFigure> getKeyFigureAvg(String keyFigure, int year) {
		
		Session session = getSessionFactoryDw().openSession();
		String keyColumn = StringEscapeUtils.escapeSql(keyFigure);
		
		String hql = "select sum(" + keyColumn + ") / sum(sumDistance), year, month " +
						"from AggrUserFigure " +
						"where year = :year " +
						"group by year, month " +
						"order by month";
		
		Query query = session.createQuery(hql);
		query.setParameter("year", year);
		
		List<AggrUserFigure> res = query.list(); 
		session.close();
		return res;
	}
	
	/**
	 * Get key figures for each transportation type
	 * 
	 * @param keyFigure
	 * @param year
	 * @param month
	 * @return list - list of key figures
	 */
	public List<AggrUserFigurePerTransportation> getTransportation(String keyFigure, int year, int month) {

		Session session = getSessionFactoryDw().openSession();
		String keyColumn = StringEscapeUtils.escapeSql(keyFigure);
		
		String hql = "select sum(" + keyColumn + ") / count(distinct JinengoUserID), year, transportation " +
						"from AggrUserFigurePerTransportation " +
						"where year = :year " +
						"group by year, transportation " +
						"order by transportation asc";
		
		Query query = session.createQuery(hql);
		query.setParameter("year", year);
		
		List<AggrUserFigurePerTransportation> res = query.list(); 
		session.close();
		return res;
	}	
	
	public SessionFactory getSessionFactoryDw() {
		return sessionFactoryDw;
	}

	public void setSessionFactoryDw(SessionFactory sessionFactoryDw) {
		this.sessionFactoryDw = sessionFactoryDw;
	}
}
