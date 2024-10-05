package com.spring.repository;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.spring.model.*;

public class BookRepository {
	
	public List<BookBean> showAllBooks()
	{
		List<BookBean> bookList = new ArrayList<BookBean>();
		
		
		BookBean book = null;
		Connection con = UserConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from book where status <> 0;");
			
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				book = new BookBean();
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setPrice(rs.getDouble(4));
				
				bookList.add(book);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ArrayList : "+ e.getMessage());
		}
		return bookList;
	}
	
	
	
	public BookBean showBookbyId(int bookId)
	{
		
		
		BookBean book = null;
		Connection con = UserConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from book where id = ?;");
			ps.setInt(1, bookId);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				book = new BookBean();
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setPrice(rs.getDouble(4));
				
				
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Show by Id : "+ e.getMessage());
		}
		
		return book;
	}
	
	
	public int addBook(BookBean bean)
	{
		int i = 0;
		Connection con = UserConnection.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("insert into book (title, author, price, status) values (?, ?, ?, ?);");
			ps.setString(1,	bean.getTitle());
			ps.setString(2, bean.getAuthor());
			ps.setDouble(3, bean.getPrice());
			ps.setInt(4, bean.getStatus());
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return i;
	}
	
	
	
	
	public int updateBook(BookBean bean)
	{
		int i = 0;
		Connection con = UserConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update book set title = ?, author = ?, price= ? where id = ?;");
			ps.setString(1, bean.getTitle());
			ps.setString(2, bean.getAuthor());
			ps.setDouble(3, bean.getPrice());
			ps.setInt(4, bean.getId());
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("update book : " + e.getMessage());
		}
		
		return i;
	}
	
	
	public int deleteBook(int id)
	{
		int i = 0;
		Connection con = UserConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("update book set status = 0 where id = ?;");
			
			ps.setInt(1, id);
			
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("delete book : " + e.getMessage());
		}
		
		return i;
	}
	
	
	public int getUserIdByEmail(String Email)
	{
		  int userId = -1;
		    Connection con = UserConnection.getConnection();
		    
		    try {
				PreparedStatement ps = con.prepareStatement("SELECT id FROM user WHERE email = ?");
				ps.setString(1, Email);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next())
				{
					userId = rs.getInt("id");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    return userId;
	}
	
	
}
