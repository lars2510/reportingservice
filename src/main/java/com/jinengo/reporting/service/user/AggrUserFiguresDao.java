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

import com.jinengo.reporting.model.user.AggrUserFigures;


@Repository
public class AggrUserFiguresDao {
	
	@Autowired
	@Qualifier("sessionFactoryDW")
	private SessionFactory sessionFactory;

	public List<AggrUserFigures> getAggrUsers() {
		String hql = "from AggrUserFigures";
		Query query = getSessionFactory().openSession().createQuery(hql);
		
		List<AggrUserFigures> aggrUsers = query.list();
		
		return aggrUsers;
	}
	
	public List<AggrUserFigures> getAggrUsers(String userId, String keyFigure) {
		
		// open session
		Session s = getSessionFactory().openSession();
		
		// add table for sql criteria
		Criteria crit = s.createCriteria(AggrUserFigures.class);
		
		// select user
		crit.add( Restrictions.eq("userHistoricID", Integer.parseInt(userId)) );
		
		// group by year and month and set keyFigure e.g. avgEcoImpact
		crit.setProjection( Projections.projectionList()
	        .add( Projections.sum(keyFigure), keyFigure )
	        .add( Projections.groupProperty("year"), "year" )
	        .add( Projections.groupProperty("month"), "month" )
	    );
		
		List<AggrUserFigures> res = crit.list();
		
		return res;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
