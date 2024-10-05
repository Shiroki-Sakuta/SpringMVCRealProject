package com.spring.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.tomcat.util.log.UserDataHelper.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.repository.UserConnection;

@Controller
public class ImageUploadController {
	
	
	
	
	
	@RequestMapping(value="/image", method = RequestMethod.GET)
	public String showImage() 
	{
		return "image";//url = "test.jsp"
	}
	
	@PostMapping("/uploadImage")
	public String uploadImage(@RequestParam("name") String name, @RequestParam("imageFile") MultipartFile file) {
        if (file.isEmpty()) {
            // Handle the case where the user doesn't select a file
            return "redirect:uploadFailure";
        }

        try (Connection conn = UserConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_images (name, image) VALUES (?, ?)")) {
        	
            // Get the image file content as byte array
            byte[] imageBytes = file.getBytes();

            // Set the parameters for the SQL query
            stmt.setString(1, name);
            stmt.setBytes(2, imageBytes);

            // Execute the query
            stmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
            return "redirect:uploadFailure"; // In case of an error
        } catch (SQLException e) {
            e.printStackTrace();
            return "redirect:uploadFailure"; // In case of a database error
        }

        return "uploadSuccess";  // If the upload is successful
    }
}
