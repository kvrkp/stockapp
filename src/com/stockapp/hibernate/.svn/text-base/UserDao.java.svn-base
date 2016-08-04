package com.stockapp.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import com.stockapp.dao.HibernateHelper;

import java.util.*;

public class UserDao {
    Session sess = null;
	Transaction transaction = null;
	
	private static final String HIBERNATE_CLASS = "com.stockapp.hibernate.User";
	private final String ERROR_USER_NOT_EXIST = "User does not exist";
	private final String ERROR_PASSWORD_DOES_NOT_MATCH = "Password does not match";
	
	protected final Log logger = LogFactory.getLog(getClass());

	public void saveOrUpdate(final User user) throws Exception {
		try {
			HibernateHelper.beginTransaction();
			sess = HibernateHelper.getSession();
			sess.save(user);
		} catch (Exception e) {
			logger.error("BenchmarkDataWriter.addSavedIndex(): There was an exception " + e.getMessage());
			HibernateHelper.rollbackTransaction();
			throw e;
		} finally {
			HibernateHelper.commitTransaction();
			HibernateHelper.closeSession();
		}		
	}

	public boolean validate(User user) throws Exception {
		try {
			HibernateHelper.beginTransaction();
			sess = HibernateHelper.getSession();
			
			Criteria crit = sess.createCriteria(
					Class.forName(HIBERNATE_CLASS)).add(
					Restrictions.eq("email", user.getEmail()));
			List<User> results = crit.list();
			if ((results == null) || (results.size() == 0)) {
				System.out.println("User does not exist");
				return false;
				// check for password in the table
			} else {
				User dbuser = results.get(0);
				String dbpassword = dbuser.getPassword();
				if (dbpassword.equalsIgnoreCase(user.getPassword()))
					return true;
				else {							
					System.out.println("Invalid password");
					return false;
				}
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out
					.println("Class not found exception is raised in Hibernate Test");
		} catch (Exception e) {
			logger.error("BenchmarkDataWriter.addSavedIndex(): There was an exception " + e.getMessage());
			HibernateHelper.rollbackTransaction();
			throw e;
		} finally {
			HibernateHelper.commitTransaction();
			HibernateHelper.closeSession();
		}	
		return false;
	}
}
