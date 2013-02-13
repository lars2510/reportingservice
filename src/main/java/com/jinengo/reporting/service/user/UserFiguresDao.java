package com.jinengo.reporting.service.user;

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


@Repository
public class UserFiguresDao {
	
	@Autowired
	@Qualifier("sessionFactoryDW")
	private SessionFactory sessionFactoryDw;
	
	/**
	 * Monthly sum of a user specific keyfigure for a given year
	 * 
	 * @param userId
	 * @param keyFigure
	 * @param year
	 * @return list - list of user figures
	 */
	public List<AggrUserFigure> getKeyFigureSum(int userId, String keyFigure, int year) {
	
		Session session = getSessionFactoryDw().openSession();
		String keyColumn = StringEscapeUtils.escapeSql(keyFigure);
		
		String hql = "select sum(" + keyColumn + "), year, month " +
						"from AggrUserFigure " +
						"where jinengoUserID = :userId " +
						"and year = :year " +
						"group by year, month";
		
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("year", year);
		
		List<AggrUserFigure> res = query.list(); 
		session.close();
		return res;
	}
	
	/**
	 * Monthly keyfigure average per km for a given year
	 * 
	 * @param userId
	 * @param keyFigure
	 * @param year
	 * @return list - list of user figures
	 */
	public List<AggrUserFigure> getKeyFigureAvg(int userId, String keyFigure, int year) {
	
		Session session = getSessionFactoryDw().openSession();
		String keyColumn = StringEscapeUtils.escapeSql(keyFigure);
		String hql = "select sum(" + keyColumn + ") / sum(sumDistance), year, month " +
						"from AggrUserFigure " +
						"where jinengoUserID = :userId " +
						"and year = :year " +
						"group by year, month";
		
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("year", year);
		
		List<AggrUserFigure> res = query.list(); 
		session.close();
		return res;
	}
	
	/**
	 * Monthly Advantages and Disadvantages to the best/worst possible route
	 * 
	 * @param userId
	 * @param keyFigure
	 * @param year
	 * @return list - list of user figures
	 */
	public List<AggrUserFigure> getBalance(int userId, String keyFigure, int year) {
	
		Session session = getSessionFactoryDw().openSession();
		String keyColumn = StringEscapeUtils.escapeSql(keyFigure);
		String keyColumnBest = keyColumn + "BestOption";
		String keyColumnWorst = keyColumn + "WorstOption";
		String hql = "select " + keyColumn + ", " + keyColumnBest + " , " + keyColumnWorst + ", year, month " +
						"from AggrUserFigure " +
						"where jinengoUserID = :userId " +
						"and year = :year";
		
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("year", year);
		
		List<AggrUserFigure> res = query.list(); 
		session.close();
		return res;
		
	}
	
	/**
	 * Get key figures for each transportation type
	 * 
	 * @param userId
	 * @param keyFigure
	 * @param year
	 * @param month
	 * @return list - list of key figures
	 */
	public List<AggrUserFigurePerTransportation> getTransportation(int userId, String keyFigure, int year, int month) {
		
		Session session = getSessionFactoryDw().openSession();
		String keyColumn = StringEscapeUtils.escapeSql(keyFigure);
		
		String hql = "select sum(" + keyColumn + "), year, transportation " +
						"from AggrUserFigurePerTransportation " +
						"where year = :year " +
						"and jinengoUserID = :userId " +
						"group by year, transportation " +
						"order by transportation asc";
		
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
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
