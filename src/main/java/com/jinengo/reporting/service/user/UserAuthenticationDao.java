package com.jinengo.reporting.service.user;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jinengo.reporting.model.user.AggrUserFigures;
import com.jinengo.reporting.model.user.UserAuthenticationModel;

@Repository
public class UserAuthenticationDao {
	@Autowired
	@Qualifier("sessionFactoryCRM")
	private SessionFactory sessionFactory;

	
	@Transactional
	public boolean saveOrUpdate(UserAuthenticationModel userModel) {
		
		// open session
		Session s = getSessionFactory().openSession();
		
		 Transaction transaction = s.beginTransaction();
		    transaction.begin();
		    s.save(userModel);
		    transaction.commit();
		    s.close();
		
		
		
		return true;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

