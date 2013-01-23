package com.jinengo.reporting.service.user;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jinengo.reporting.controller.web.HomeController;
import com.jinengo.reporting.model.user.JinengoUser;
import com.jinengo.reporting.model.user.UserAuthenticationModel;

@Repository
public class UserAuthenticationDao {
	
	@Autowired
	@Qualifier("sessionFactoryCRM")
	private SessionFactory sessionFactory;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Get user id from jinengo database by email
	 * 
	 * @param userEmail
	 * @return id - jinengo user id
	 */
	public int getUserIdByEmail(String userEmail) {
		// open session
		Session s = getSessionFactory().openSession();
		
		// create criteria
		Criteria crit = s.createCriteria(JinengoUser.class);

		// select user by mail
		crit.add( Restrictions.eq("email", userEmail) );
		
		JinengoUser user = (JinengoUser) crit.list().get(0);
		
		return user.getId();
	}
	
	@Transactional
	public boolean saveAuthModel(UserAuthenticationModel userModel) {
		
		// open session
		Session s = getSessionFactory().openSession();
		
	    try {
	    	Transaction transaction = s.beginTransaction();
		    s.save(userModel);
		    transaction.commit();
	    }
	    catch (RuntimeException e) {
	    	try{
	    		s.getTransaction().rollback();
    		}catch(RuntimeException rbe){
    			logger.error("Couldn’t roll back transaction", rbe);
    		}
    		throw e; 
	    }
	    finally {
	    	if(s != null){
	    		s.close();
	    	}
	        
	    }
		
		return true;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}

