package com.spring.security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.security.entity.User;
import com.spring.security.repository.UserRepo;
import com.spring.security.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
    
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		
		if(p != null) {
			String email = p.getName();
		    User user = userRepo.findByEmail(email);
		    m.addAttribute("user",user);
		}
		
	}
	
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/signin")
	public String login() {
		return "login";
	}
	
//	@GetMapping("/user/profile")
//	public String profile(Principal p, Model m) {
//		
//		String email = p.getName();
//	    User user = userRepo.findByEmail(email);
//	    m.addAttribute("user",user);
//		
//		return "profile";
//	}
//	
//	@GetMapping("/user/home")
//	public String home() {
//		return "home";
//	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session) {
	User u   = 	userService.saveUser(user);
	if(u != null) {
		session.setAttribute("msg", "Register successfully");
	}else {
		session.setAttribute("msg", "Something wrong in server ");
	}
		return "redirect:/register";
	}
	
}
