package com.jinengo.reporting.service.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jinengo.reporting.model.user.AggrUserFigures;

@Repository
public class AggrUserFiguresDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<AggrUserFigures> getAggrUsers() {
		String hql = "from AggrUserFigures";
		Query query = getSessionFactory().openSession().createQuery(hql);
		List<AggrUserFigures> aggrUsers = query.list();
		
		return aggrUsers;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
