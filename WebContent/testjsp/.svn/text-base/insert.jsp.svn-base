<%@page import="java.sql.*"%>
<%@page import="com.stockapp.util.*"%>
<%@page import="com.stockapp.hibernate.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%
	out.println("Insert started");
	try {
		String email = "abc@gmail.com";
		String password = "abc";
		String firstName = "ABC";
		String lastName = "USER";
		String balanceStr = "100000";
		Double balance = 0d;
		try {
			balance = Double.parseDouble(balanceStr);
		} catch (NumberFormatException e) {
			
		}
		User user = new User(email, password, firstName, lastName, balance);
		ApplicationContext context =
			  WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		UserDao dao = (UserDao) context.getBean("UserDao");
		dao.saveOrUpdate(user);
	} catch (Exception e) {
		out.println("There was an exception ");
		e.printStackTrace();
	}
	out.println("Insert ended . Added abc@gmail.com");
%>
