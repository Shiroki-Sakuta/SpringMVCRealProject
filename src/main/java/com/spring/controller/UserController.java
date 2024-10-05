package com.spring.controller;

import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.apache.tomcat.jni.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.BookBean;
import com.spring.model.UserBean;
import com.spring.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository repo;
	@GetMapping(value = "/register")
	public ModelAndView showRegister()
	{
		return new ModelAndView("Register","userObj",new UserBean());
	}
	
	
	@PostMapping(value = "/insert")
	public String doRegister(@ModelAttribute("userObj")UserBean obj, Model m )
	{
		int i = repo.insertUser(obj);
		
		if(i > 0)
		{
			return "login";
		}
		else
		{
			m.addAttribute("error", "Insert fail");
			return "register";
		}
	}
	
	@GetMapping(value = "/welcome")
	public String ShowAllBook(Model m)
	{
		repo = new UserRepository();
		
		List<BookBean> list = repo.showAllBooks();
		if(list.size() > 0 || list.size() == 0)
		{
			m.addAttribute("list", list);
			
		}
		else
		{
			m.addAttribute("error", "No book available!");
		}
		
		return "welcome";
	}
	
}
