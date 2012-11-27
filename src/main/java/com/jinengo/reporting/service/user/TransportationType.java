package com.jinengo.reporting.service.user;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TransportationType {
	
	@Autowired
	private SessionFactory sessionFactory;

	public int userCount() {
		String hql = "select count(*) from SimpleUserModel";
		Query query = getSessionFactory().openSession().createQuery(hql);
		
		return ((Long) query.uniqueResult()).intValue();
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
