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
	@Qualifier("sessionFactoryCRM")
	private SessionFactory sessionFactoryCrm;
	
	public int getUserIdByEmail(String userEmail) {
		// open session
		Session s = getSessionFactoryCrm().openSession();
		
		// create criteria
		Criteria crit = s.createCriteria(JinengoUser.class);

		// select user by mail
		crit.add( Restrictions.eq("email", userEmail) );
		
		JinengoUser user = (JinengoUser) crit.list().get(0);
		
		return user.getId();
	}
	
	public List<AggrUserFigures> getKeyFigures(String userEmail, String keyFigure) {
		
		Session s = getSessionFactoryDw().openSession();
		Criteria crit = s.createCriteria(AggrUserFigures.class);
		
		int userId = getUserIdByEmail(userEmail);
		
		// select user
		crit.add( Restrictions.eq("jinengoUserID", userId) );
		
		crit.add( Restrictions.eq("year", 2012) );
		
		// group by year and month and set keyFigure e.g. avgEcoImpact
		crit.setProjection( Projections.projectionList()
	        .add( Projections.sum(keyFigure), keyFigure )
	        .add( Projections.groupProperty("year"), "year" )
	        .add( Projections.groupProperty("month"), "month" )
	    );
		
		List<AggrUserFigures> res = crit.list();
		
		return res;
	}
	
	public List<AggrUserFigures> getPlattformKeyFigures(String keyFigure) {
		
		Session s = getSessionFactoryDw().openSession();
		Criteria crit = s.createCriteria(AggrPlatformFigures.class);
		
		crit.add( Restrictions.eq("year", 2012) );
		
		// group by year and month and set keyFigure e.g. avgEcoImpact
		crit.setProjection( Projections.projectionList()
	        .add( Projections.sum(keyFigure), keyFigure )
	        .add( Projections.groupProperty("year"), "year" )
	        .add( Projections.groupProperty("month"), "month" )
	    );
		
		List<AggrUserFigures> res = crit.list();
		
		return res;
	}
	
	public SessionFactory getSessionFactoryCrm() {
		return sessionFactoryCrm;
	}

	public void setSessionFactoryCrm(SessionFactory sessionFactoryCrm) {
		this.sessionFactoryCrm = sessionFactoryCrm;
	}
	
	public SessionFactory getSessionFactoryDw() {
		return sessionFactoryDw;
	}

	public void setSessionFactoryDw(SessionFactory sessionFactoryDw) {
		this.sessionFactoryDw = sessionFactoryDw;
	}
}
