package com.stockapp.dao;

/*
 * Updated by Mahesh
 */
import java.sql.*;

public class DBTest {
	public void process() {
		String url = "jdbc:oracle:thin:@12.119.12.78:1521:orcl";
		String user = "stockuser";
		String pwd = "stockpwd"; //This depends on each machine
		String sql = "select * FROM STOCK";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Integer stockId = rs.getInt("stock_id");
				String stockCode = rs.getString("stock_code");
				System.out.println("stock id = " + stockId + " code = " + stockCode);
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
		DBTest test = new DBTest();
		System.out.println("Starting db connection test");
		test.process();
	}
}
