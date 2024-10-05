package com.spring.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.PhotoBean;

import com.spring.repository.*;


@Controller
public class imageController {
	@GetMapping(value="/show")
	public ModelAndView showPhoto() {
		PhotoBean bean=new PhotoBean();
		
		return new ModelAndView("photo","photoObj",bean);
	}
	
	@PostMapping("/AddPhoto")
	public String photoAdd(@ModelAttribute("photoObj")@Validated PhotoBean obj,BindingResult br,Model m) throws IOException, SQLException {
		byte[] imageobj;
		String name;
		PhotoRepo repo=new PhotoRepo();
		imageobj=obj.getPhoto().getBytes();
		name = obj.getName();
		int i=repo.addPhoto(imageobj,name);
		if(i>0) {
			System.out.println("insert Successful");
		}else {
			System.out.println("Insert Fail");
		
		}
		
		 // Redirect to a success or another page after upload
        return "photo"; // Redirect back to the upload form
	}
	
    // Show the list of uploaded photos
    @GetMapping("/viewPhotos")
    public String viewPhotos(Model m) throws SQLException, IOException {
        PhotoRepo repo = new PhotoRepo();
        List<PhotoBean> imageList = repo.viewPhoto(new PhotoBean());
        m.addAttribute("photoList", imageList);
        return "viewphoto"; // Renders viewphoto.jsp
    }
	
	
}
