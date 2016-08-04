package com.stockapp.generateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.stockapp.hibernate.User;
import com.stockapp.hibernate.UserDao;

public class RegisterProcess implements GenerateView {
	public String buildContent(HttpServletRequest req) {
		//Do the business processing
		System.out.println("In RegisterProcess.java");
		String email = req.getParameter("EMAIL");
		String password = req.getParameter("PWORD");
		String firstName = req.getParameter("FNAME");
		String lastName = req.getParameter("LNAME");
		String balanceStr = "100000";
		Double balance = 0d;
		try {
			balance = Double.parseDouble(balanceStr);
		} catch (NumberFormatException e) {
			
		}
		User user = new User(email, password, firstName, lastName, balance);
		ApplicationContext context =
			  WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession().getServletContext());
		UserDao dao = (UserDao) context.getBean("UserDao");
		try {
			dao.saveOrUpdate(user);
		} catch (Exception e) {
			System.out.println("There was an exception in saving " + e.getMessage());
		}
		return "Login";
	}
}
