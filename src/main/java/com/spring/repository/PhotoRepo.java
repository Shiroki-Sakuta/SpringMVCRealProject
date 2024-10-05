package com.spring.repository;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.spring.model.PhotoBean;
public class PhotoRepo {
	
	public int addPhoto(byte[] photoByte, String name) {
		int i=0;
		Connection con=UserConnection.getConnection();
		String query="Insert into user_images (name, image) values (?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1,name);
			ps.setBytes(2, photoByte);
			i=ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Insert Error: "+e.getMessage());
		}
		return i;
	}
	
	public List<PhotoBean> viewPhoto(PhotoBean photo) throws SQLException, IOException {
		List<PhotoBean> photoList = new ArrayList<>();
		Connection con=UserConnection.getConnection();
		String query="Select * from user_images";
		
		PreparedStatement ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			PhotoBean bean=new PhotoBean();
			bean.setId(rs.getInt("id"));
			bean.setName(rs.getString("name"));
			Blob blob=rs.getBlob("image");
			byte[] photoByte = blob.getBytes(1, (int) blob.length());
			bean.setPhotoBytes(photoByte);
			photoList.add(bean);
			
		}
		return photoList;
	}
		
}
