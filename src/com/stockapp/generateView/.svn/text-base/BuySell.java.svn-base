package com.stockapp.generateView;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.stockapp.hibernate.Transaction;
import com.stockapp.hibernate.TransactionDao;

import java.util.*;

public class BuySell implements GenerateView {
	protected final Log logger = LogFactory.getLog(getClass());
	
	public String buildContent(HttpServletRequest req) {
		String email = req.getParameter("EMAIL");
		Transaction transaction = new Transaction(email);
		ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession().getServletContext());
		TransactionDao dao = (TransactionDao) context.getBean("TransactionDao");
		List<Transaction> tranList = null;
		
		try {
			tranList = dao.get(transaction);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Transaction t : tranList) {
			logger.info(t.toString());
		}
		req.setAttribute("transactionList", tranList);		
		return "BuySell";
	}
}
