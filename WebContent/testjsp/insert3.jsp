<%@page import="com.stockapp.hibernate.User"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page import="org.hibernate.cfg.Configuration"%>


<%
	out.println("Insert started 2");
	String email = "abc2@gmail.com";
	Session sess = null;
	Transaction transaction = null;
	try {
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
		Configuration configuration = new Configuration();
		SessionFactory sessionFactory = configuration.configure("hibernate-prod.cfg.xml").buildSessionFactory();
		//sess = sessionFactory.openSession();
		//transaction = sess.beginTransaction();
		//sess.save(user);
	} catch (Exception e) {
		//out.println("There was an exception ");
		//e.printStackTrace();
	} finally {
		//transaction.commit();
		//sess.close();
		//HibernateHelper.commitTransaction();
		//HibernateHelper.closeSession();
	}		
	out.println("Insert ended . Added " + email);
%>
