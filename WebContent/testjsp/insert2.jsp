<%@page import="java.sql.*"%>
<%@page import="com.stockapp.util.*"%>
<%@page import="com.stockapp.hibernate.*"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.orm.hibernate3.HibernateCallback"%>
<%@page import="org.springframework.orm.hibernate3.HibernateTemplate"%>
<%@page import="org.hibernate.*"%>
<%@page import="org.springframework.orm.hibernate3.LocalSessionFactoryBean" %>

<%
	out.println("Insert started");
	try {
		String email = "abc2@gmail.com";
		String password = "abc";
		String firstName = "ABC";
		String lastName = "USER";
		String balanceStr = "100000";
		Double balance = 0d;
		try {
			balance = Double.parseDouble(balanceStr);
		} catch (NumberFormatException e) {
			
		}
		final User user = new User(email, password, firstName, lastName, balance);
		ApplicationContext context =
			  WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
		HibernateTemplate hibernateTemplate = (HibernateTemplate) context.getBean("hibernateTemplate");
		
		HibernateCallback callback = new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.saveOrUpdate(user);
				return null;
			}
		};
		hibernateTemplate.execute(callback);
	} catch (Exception e) {
		out.println("There was an exception " + e.getMessage());
		out.println(e.getStackTrace().toString());
	}
	out.println("Insert ended . Added abc2@gmail.com");
%>
