package com.hy.jsf;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManagement {

	static Connection con = null;
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String url = "jdbc:mysql://localhost:3306/jsf";
	static String user = "root";
	static String password = "Aa123456";

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

}
