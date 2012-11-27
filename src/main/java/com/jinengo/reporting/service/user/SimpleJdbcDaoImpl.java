package com.jinengo.reporting.service.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jinengo.reporting.model.user.TransportationType;

@Repository
public class SimpleJdbcDaoImpl {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<TransportationType> userCount() {
		String hql = "from TransportationType";
		Query query = getSessionFactory().openSession().createQuery(hql);
		List<TransportationType> transportations = query.list();
		
		return transportations;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
