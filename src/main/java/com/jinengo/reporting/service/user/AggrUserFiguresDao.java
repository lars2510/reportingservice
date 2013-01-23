package com.jinengo.reporting.service.user;

import java.util.List;

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
import com.jinengo.reporting.model.user.JinengoUser;


@Repository
public class AggrUserFiguresDao {
	
	@Autowired
	@Qualifier("sessionFactoryDW")
	private SessionFactory sessionFactoryDw;
	
	@Autowired
	private UserAuthenticationDao userAuthenticationDao;
	
	/**
	 * Average user figures for a given year, grouped by month
	 * 
	 * @param userEmail
	 * @param keyFigure
	 * @param year
	 * @return list - list of user figures
	 */
	public List<AggrUserFigures> getKeyFigures(String userEmail, String keyFigure, int year) {
		
		Session s = getSessionFactoryDw().openSession();
		Criteria crit = s.createCriteria(AggrUserFigures.class);
		
		int userId = userAuthenticationDao.getUserIdByEmail(userEmail);
		
		// select user
		crit.add( Restrictions.eq("jinengoUserID", userId) );
		
		crit.add( Restrictions.eq("year", year) );
		
		// group by year and month and set keyFigure e.g. avgEcoImpact
		crit.setProjection( Projections.projectionList()
	        .add( Projections.sum(keyFigure), keyFigure )
	        .add( Projections.groupProperty("year"), "year" )
	        .add( Projections.groupProperty("month"), "month" )
	    );
		
		List<AggrUserFigures> res = crit.list();
		
		return res;
	}
	
	/**
	 * Average platform figures for a given year, grouped by month
	 * 
	 * @param keyFigure
	 * @param year
	 * @return list - list of avg platform figures
	 */
	public List<AggrPlatformFigures> getPlatformKeyFigures(String keyFigure, int year) {
		
		Session s = getSessionFactoryDw().openSession();
		Criteria crit = s.createCriteria(AggrPlatformFigures.class);
		
		crit.add( Restrictions.eq("year", year) );
		
		// group by year and month and set keyFigure e.g. avgEcoImpact
		crit.setProjection( Projections.projectionList()
	        .add( Projections.sum(keyFigure), keyFigure )
	        .add( Projections.groupProperty("year"), "year" )
	        .add( Projections.groupProperty("month"), "month" )
	    );
		
		List<AggrPlatformFigures> res = crit.list();
		
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
	public List<AggrPlatformFigures> getPlatformTransportation(String keyFigure, int year, int month) {
		
		Session s = getSessionFactoryDw().openSession();
		Criteria crit = s.createCriteria(AggrPlatformFigures.class);
		
		if( month > 0 ) {
			crit.add( Restrictions.eq("year", year) );
		} else {
			crit.add( Restrictions.eq("year", year) );
			crit.add( Restrictions.eq("month", month) );
			crit.setProjection(Projections.property(keyFigure));
		}
		
		List<AggrPlatformFigures> res = crit.list();
		
		return res;
	}	
	
	public SessionFactory getSessionFactoryDw() {
		return sessionFactoryDw;
	}

	public void setSessionFactoryDw(SessionFactory sessionFactoryDw) {
		this.sessionFactoryDw = sessionFactoryDw;
	}
}
