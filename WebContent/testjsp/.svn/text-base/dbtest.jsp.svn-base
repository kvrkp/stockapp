<%@page import="java.sql.*"%>
<%@page import="com.stockapp.util.*"%>

<%
String driver = "com.mysql.jdbc.Driver";
String rootUrl = HttpHelper.getRootUrl(request);

String url = "";
String user = "";
String pwd = ""; //This depends on each machine

if (rootUrl.startsWith("http://localhost")) {
	url = "jdbc:mysql://localhost/stockapp";
	user = "root";
	pwd = "admin001"; //This depends on each machine	
} else {
	url = "jdbc:mysql://stockapp.db.3357152.hostedresource.com/stockapp";
	user = "stockapp";
	pwd = "Admin001"; //This depends on each machine	
}

try {
	Class.forName(driver);
	Connection conn = DriverManager.getConnection(url, user, pwd);
	
	String sql = "select * from stock";
	Statement stmt = conn.createStatement();
	ResultSet rs = stmt.executeQuery(sql);
	out.println("<table>");
	while (rs.next()) {
		String stockCode = rs.getString("stock_code");
		String stockName = rs.getString("stock_name");
		out.println("<tr>");
		out.println("<td>" + stockCode + "</td>");
		out.println("<td>" + stockName + "</td>");		
		out.println("</tr>");
	}
	out.println("</table>");
	
	sql = "select * from users";
	stmt = conn.createStatement();
	rs = stmt.executeQuery(sql);
	out.println("<table>");
	while (rs.next()) {
		String email = rs.getString("email");
		String password = rs.getString("password");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String balance = rs.getString("balance");
		out.println("<tr>");
		out.println("<td>" + email + "</td>");
		out.println("<td>" + password + "</td>");
		out.println("<td>" + firstName + "</td>");
		out.println("<td>" + lastName + "</td>");
		out.println("<td>" + balance + "</td>");
		out.println("</tr>");
	}
	out.println("</table>");
	
	System.out.println("db connection test was successful");
} catch (SQLException e) {
	out.println("Exception: " + e.getMessage());
}

%>
