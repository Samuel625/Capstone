package com.capstone.capstone1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capstone.capstone1.WebUtils;
import com.capstone.capstone1.model.Users;
import com.capstone.capstone1.repository.UserRepository;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WebUtils webUtils;
	
	@GetMapping({"/","index"})
	//@ResponseBody
	public String index(Model model) {
		model.addAttribute("msg", "welcome to spring");
		model.addAttribute("succes", "this is me");
		model.addAttribute("users", userRepository.findAll());
		
		return "index";
	}
	
	@GetMapping("about")
	//@ResponseBody()
	String about(Model model) {
		model.addAttribute("msg", "about");
		return "about";
	}
	
	@GetMapping("service")
	String services(Model model) {
		model.addAttribute("msg", "Our Services");
		return "services";
	}
	
	@PostMapping("sendemail")
	public String sendemail(Model model, @RequestParam String email,
			@RequestParam String name,
			@RequestParam String subject,
			@RequestParam String message) {
		try {
		webUtils.sendMail("samuelmbugua71@yahoo.com", 
				message+ " From "+name, subject);
		model.addAttribute("msg", "email sent");
		} catch (Exception e) {
			model.addAttribute("msg", e);
		}
		
		return "services";
	}
	
	@GetMapping("name")
	String services(@RequestParam int id, Model model) {
		model.addAttribute("msg", "id");
		return "index";
	}
	
	@GetMapping("getname-{id}")
	public String getname(@PathVariable String id, Model model) {
		model.addAttribute("msg", id);
		return "index";
	}
	
	@PostMapping("whatisyourname")
	public String login(@RequestParam String lname,
	@RequestParam String fname, Model model) {
	model.addAttribute("msg", "Last Name "+ lname+" first name "+fname);

	return "index";	
	}
	
	

}
