package com.stockapp.hibernate;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.stockapp.dao.HibernateHelper;

import java.util.*;

public class TransactionDao {
	private static final String HIBERNATE_CLASS = "com.stockapp.hibernate.Transaction";
	Session sess = null;
	protected final Log logger = LogFactory.getLog(getClass());
	
	public List<Transaction> get(final Transaction transaction) throws Exception {
		try {
			HibernateHelper.beginTransaction();
			sess = HibernateHelper.getSession();
			Criteria crit = sess.createCriteria(Class.forName(HIBERNATE_CLASS))
				.add(Restrictions.eq("email", transaction.getEmail()));
			List<Transaction> results = crit.list();
			logger.info("No of transactions for " + transaction.getEmail() + " = " + results.size());
			return results;
		} catch (Exception e) {
			logger.error("TransactionDao: There was an exception " + e.getMessage());
			HibernateHelper.rollbackTransaction();
			throw e;
		} finally {
			HibernateHelper.commitTransaction();
			HibernateHelper.closeSession();
		}
	}
}
