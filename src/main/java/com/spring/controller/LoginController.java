package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.LoginBean;
import com.spring.model.UserBean;
import com.spring.repository.UserRepository;

import org.springframework.ui.Model;


@Controller
public class LoginController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping(value = "/")
	public ModelAndView showLogin()
	{
		return new ModelAndView("Login","loginObj",new LoginBean());
	}
	
	@PostMapping(value = "dologin")
	public String doLogin(@ModelAttribute("loginObj")LoginBean lobean, Model m, HttpSession session)
	{
		boolean result = repo.checkEmail(lobean.getEmail());
		
		if(result)
		{
			UserBean bean = new UserBean();
			bean.setEmail(lobean.getEmail());
			bean.setPassword(lobean.getPassword());
			
			UserBean obj = repo.LoginUser(bean);
			
			if(obj == null)
			{
				 m.addAttribute("error", "Invalid Password!");
				 return "Login";
			}
			else
			{
				session.setAttribute("name", obj.getName());
				session.setAttribute("email", obj.getEmail());
				session.setAttribute("id", obj.getId());
				System.out.println("User Role: " + obj.getRole());
				
				if(obj.getRole().equals("admin"))
				{		
					return "adminview";
				}
				else
				{
					return "redirect:user/welcome";
				}
			
				
			}
		}
		else
		{
			 m.addAttribute("error", "Invalid!");
			 return "Login";
		}
	}
	
}
