package com.spring.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

import com.google.protobuf.Value;
import com.spring.model.*;

public class UserRepository {
	public int insertUser(UserBean bean)
	{
		int i = 0;
		
		Connection con = UserConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("insert into user (name, email, password) values (?, ?, ?);");
			ps.setString(1, bean.getName());
			ps.setString(2, bean.getEmail());
			ps.setString(3, bean.getPassword());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Insert error :" + e);
		}
		
		return i;
	}
	
	public boolean checkEmail(String email)
	{
		boolean check = false;
		Connection con = UserConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user where email = ?;");
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				check = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("check email : "+ e.getMessage());
		}
		return check;
	}
	
	
	public UserBean LoginUser(UserBean bean)
	{
		
		UserBean user = null;
		Connection con = UserConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("select * from user where email = ? and password = ?;");
			ps.setString(1, bean.getEmail());
			ps.setString(2, bean.getPassword());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				user = new UserBean();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Login user : "+ e.getMessage());
		}
		
		return user;
	}
	

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
}
