package com.stockapp.dao;

import java.sql.*;

public class MySqlTest {
	public void process() {
		//String url = "jdbc:mysql://127.0.0.1:3306/stockapp";
		String url = "jdbc:mysql://localhost/stockapp";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String pwd = "admin001"; //This depends on each machine
		String sql = "select * FROM STOCK";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String stockCode = rs.getString("stock_code");
				String stockName = rs.getString("stock_name");
				System.out.println("stock code = " + stockCode + " name = " + stockName);
			}
			System.out.println("db connection test was successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("db connection test had a problem");
		} catch (Exception e) {
			
		}
	}
	
	public static void main(String args[]) {
		MySqlTest test = new MySqlTest();
		System.out.println("Starting db connection test");
		test.process();
	}
}
