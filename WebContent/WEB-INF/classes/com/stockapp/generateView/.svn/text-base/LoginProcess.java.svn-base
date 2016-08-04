package com.stockapp.generateView;

import javax.servlet.http.*;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.stockapp.hibernate.User;
import com.stockapp.hibernate.UserDao;
import com.stockapp.generateView.BuySell;

public class LoginProcess implements GenerateView {
	public String buildContent(HttpServletRequest req) {
		 String userid   = req.getParameter("EMAIL");
		 String password = req.getParameter("PWORD");
		 
		 System.out.println("In LoginProcess.java, uname=" + userid + ",pword=" + password);
		 User user = new User(userid, password, null, null, 0.0);
		 ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession().getServletContext());
		 UserDao dao = (UserDao) context.getBean("UserDao");
		 boolean isValid = false;
		 
		 try {
			 isValid = dao.validate(user);
		 } catch (Exception e) {
			 System.out.println("There was an exception " + e.getMessage());
		 }
		 req.setAttribute("valid", new Boolean(isValid));
		 if (isValid)
		 {
			 //We now have to get the user's transactions 
			 //and populate into request attribute
			 BuySell buySell = new BuySell();
			 buySell.buildContent(req);
			 return "BuySell";
		 } else {
			 return "Login";
		 }
			 
	}

}
