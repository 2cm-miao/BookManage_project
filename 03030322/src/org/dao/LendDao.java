package org.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import org.db.DBConn;
import org.model.Lend;

public class LendDao {
	Connection conn;
	public List selectLend(String readerId,int pageNow,int pageSize){
		List list=new ArrayList();
		PreparedStatement pstmt;
		try {
			conn=DBConn.getConn();
			pstmt = conn.prepareStatement("select top "+pageSize+"l.bookId,l.ISBN,b.bookName,b.publisher,"
					+ "b.price,l.Itime from lend as l,book as b where readerId=? and b.ISBN=l.ISBN and"
					+ "l.bookId not int(select top "+pageSize*(pageNow-1)
					+ "l.bookId from lend as l)");
			
			pstmt.setString(1,readerId);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()){
				Lend lend=new Lend();
				lend.setBookId(rs.getString(1));
				lend.setISBN(rs.getString(2));
				lend.setBookName(rs.getString(3));
				lend.setPublisher(rs.getString(4));
				lend.setPrice(rs.getFloat(5));
				lend.setlTime(rs.getDate(6));
				list.add(lend);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			DBConn.closeConn();
		}
	}
	
	public int selectLendSize(String readerId){
		try {
			conn=DBConn.getConn();
			PreparedStatement pstmt=conn.prepareStatement("select count(*) from lend where readerId=?");
			pstmt.setString(1, readerId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				int pageCount=rs.getInt(1);
				return pageCount;
			}
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			DBConn.closeConn();
		}
		
	}
	
	public Lend selectByBookId(String bookId){
		try {
			conn=DBConn.getConn();
			PreparedStatement pstmt=conn.prepareStatement("select * from lend where bookId=?");
			pstmt.setString(1, bookId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				Lend lend=new Lend();
				lend.setBookId(rs.getString(1));
				lend.setReaderId(rs.getString(2));
				lend.setISBN(rs.getString(3));
				lend.setlTime(rs.getDate(4));
				return lend;
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			DBConn.closeConn();
		}
	}
	
	public boolean addLend(Lend lend){
		try {
			conn=DBConn.getConn();
			PreparedStatement pstmt=conn.prepareStatement("insert into lend values(?,?,?,?");
			pstmt.setString(1, lend.getBookId());
			pstmt.setString(2, lend.getReaderId());
			pstmt.setString(3, lend.getISBN());
			pstmt.setDate(4,new Date(lend.getlTime().getTime()));
			pstmt.execute();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally{
			DBConn.closeConn();
		}
		
	}
	
	public Lend selectByBookISBN(String ISBN){
		try {
			conn=DBConn.getConn();
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement("select * from lend where ISBN=?");
			pstmt.setString(1, ISBN);
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()){
				Lend lend=new Lend();
				lend.setBookId(rs.getString(1));
				lend.setReaderId(rs.getString(2));
				lend.setISBN(rs.getString(3));
				lend.setlTime(rs.getDate(4));
				return lend;
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			DBConn.closeConn();
		}
	}
}
