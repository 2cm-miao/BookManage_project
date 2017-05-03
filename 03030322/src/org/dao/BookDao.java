package org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.db.DBConn;
import org.model.Book;

public class BookDao {
	Connection conn;
	public Book selectBook(String ISBN){
		try {
			conn=DBConn.getConn();
			PreparedStatement pstmt=conn.prepareStatement("select * from book where ISBN=?");
			pstmt.setString(1, ISBN);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				Book book=new Book();
				book.setISBN(rs.getString(1));
				book.setBookName(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setPublisher(rs.getString(4));
				book.setPrice(rs.getFloat(5));
				book.setCnum(rs.getInt(6));
				book.setSnum(rs.getInt(7));
				book.setSummary(rs.getString(8));
				book.setPhoto(rs.getBytes(9));
				return book;
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
	
	public boolean updateBook(Book book){
		try {
			conn=DBConn.getConn();
			PreparedStatement pstmt=conn.prepareStatement("update book set bookName=?,"
					+ "author=?,publisher=?,price=?cnum=?,summary=?,photo=?,where ISBN=?");
			
			pstmt.setString(1, book.getBookName());
			pstmt.setString(2, book.getAuthor());
			pstmt.setString(3, book.getPublisher());
			pstmt.setFloat(4, book.getPrice());
			pstmt.setInt(5, book.getCnum());
			pstmt.setInt(6, book.getSnum());
			System.out.println(book.getSnum());
			pstmt.setString(7,book.getSummary());
			pstmt.setBytes(8, book.getPhoto());
			pstmt.setString(9, book.getISBN());
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
	
	public boolean deleteBook(String ISBN){
		try {
			conn=DBConn.getConn();
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement("delete from book where ISBN=?");
			pstmt.setString(1, ISBN);
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
	
	public boolean addBook(Book book){
		try {
			conn=DBConn.getConn();
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement("insert into book values(?,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, book.getISBN());
			pstmt.setString(2, book.getBookName());
			pstmt.setString(3, book.getAuthor());
			pstmt.setString(4, book.getPublisher());
			pstmt.setFloat(5, book.getPrice());
			pstmt.setInt(6, book.getCnum());
			pstmt.setInt(7, book.getSnum());
			pstmt.setString(7, book.getSummary());
			pstmt.setBytes(9, book.getPhoto());
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
}
