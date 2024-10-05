package com.spring.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.BookBean;
import com.spring.repository.BookRepository;
import com.spring.repository.UserConnection;

import org.springframework.ui.Model;


@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookRepository bookRepo;
	
	@GetMapping(value = "/showbook")
	public String showAllBook(Model m)
	{
		bookRepo = new BookRepository();
		List<BookBean> list = bookRepo.showAllBooks();
		
		if(list.size() > 0 || list.size() == 0)
		{
			m.addAttribute("list", list);
			
		}
		else
		{
			m.addAttribute("error", "No book available!");
		}
		
		return "booklist";
	}
	
	@GetMapping(value = "/addbook")
	public ModelAndView addBook()
	{
		return new ModelAndView("addBook","bookObj",new BookBean());
	}
	
	@PostMapping(value = "/addbookp")
	public  String addBookp(@ModelAttribute("bookObj")BookBean obj, Model m )
	{
		
		  if(obj.getTitle().isEmpty() || obj.getAuthor().isEmpty() || obj.getPrice() <= 0 || obj.getStatus() <= 0) 
		  {
			  m.addAttribute("error", "Fill all field!");
			  return "addBook";
		  }
		  else {
			  bookRepo = new BookRepository();
				int i = bookRepo.addBook(obj);
				
				if(i > 0)
				{
					m.addAttribute("error", "Add Successful.");
					return "redirect:showbook";
				}
				 else 
				  { 
					  m.addAttribute("error", "fill the blank");
					  return "addBook";
				  } 
		  }
		
		
		
	}
	
	@GetMapping(value = "/editbook/{id}")
	public String selectBookById(@PathVariable("id")int id, Model m)
	{
		
			BookBean obj = bookRepo.showBookbyId(id);
			
			m.addAttribute("obj", obj);
			return "editbook";
		
	}
	
	@PostMapping(value = "/updatebook")
	public String updateBook(@ModelAttribute("obj")BookBean obj, Model m)
	{
					  
				  if(obj.getTitle().equals("") || obj.getAuthor().equals("") || obj.getPrice() <= 0) 
				  {
					  m.addAttribute("error", "Fill all field!");
					  return "editbook";
				  } 
				  else 
				  { 
						/*
						 * BookBean bean = new BookBean(); bean.setId(obj.getId());
						 * bean.setTitle(obj.getTitle()); bean.setAuthor(obj.getAuthor());
						 * bean.setPrice(obj.getAuthor());
						 */
					  
					  bookRepo = new BookRepository(); 
					  int i = bookRepo.updateBook(obj);
					  
					  if(i > 0) 
					  { 
						  return "redirect:showbook";// Use redirect to show updated list
					  } 
					  else 
					  { 
						  m.addAttribute("error", "fill the blank");
						  return "editbook";
					  
					  } 
				  
				  }
	}
	
	
	@PostMapping(value = "/showaddcard")
	public String showSelectedBooks(@RequestParam("selectedBooks") List<Integer> selectedBookIds, Model m)
	{
		// Retrieve the selected books from the repository
		List<BookBean> selectedBooks = new ArrayList<BookBean>();
		
		for(Integer id : selectedBookIds)
		{
			BookBean book = bookRepo.showBookbyId(id);
			selectedBooks.add(book);
		}
		 // Add the selected books to the model
		m.addAttribute("selectedBooks" ,selectedBooks);	
		return "selectedBooks";
	}
	
	@PostMapping(value = "/placeOrder")
	 public String placeOrder(@RequestParam("finalBooks") List<Integer> bookIds, Model model, HttpSession session) {
	        // Logic to place an order (e.g., save the selection to the database)
		String email = (String)session.getAttribute("email");
		int userId = (int) session.getAttribute("id");
		
		if(email == null)
		{
			 model.addAttribute("error", "User not logged in.");
		        return "Login";
		}
		
		 // Retrieve user ID from the database using the email
	    
	    
	    Connection con = UserConnection.getConnection();
		/* int userId = bookRepo.getUserIdByEmail(email); */
	    
	    try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO `order` (book_id, user_id) VALUES (?, ?)");
			
			for(int bookId : bookIds)
			{
				ps.setInt(1, bookId);
				ps.setInt(2, userId);
				ps.addBatch();
			}
			
			ps.executeBatch();
			 model.addAttribute("message", "Order placed successfully!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	        // You can also add more attributes to the model as needed
	        model.addAttribute("message", "Order placed successfully!");
	        return "orderConfirmation";
	    }
	
}
