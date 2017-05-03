package org.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	
	public static Connection conn;
	/**
	 * 连接
	 * @return
	 */
	public static Connection getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","root");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 关闭
	 */
	public static void closeConn(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
