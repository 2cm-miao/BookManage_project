package org.dao;
import java.sql.*;

import org.db.DBConn;
import org.model.Login;

public class LoginDao {
	Connection conn;
	public Login checkLogin(String name,String password){
		conn=DBConn.getConn();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement("select * from users where name=? and password=?");
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				Login login =new Login();
				login.setId(rs.getInt(1));
				login.setName(rs.getString(2));
				login.setPassword(rs.getString(3));
				login.setRole(rs.getBoolean(4));
				return login;
			}
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			DBConn.closeConn();
		}
	}
}
