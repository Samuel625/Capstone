package com.capstone.capstone1.controller;

	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.SessionAttributes;
	import org.springframework.web.multipart.MultipartFile;
	import org.springframework.web.servlet.mvc.support.RedirectAttributes;

	import com.capstone.capstone1.WebUtils;
	import com.capstone.capstone1.model.Users;
	import com.capstone.capstone1.repository.UserRepository;

	@Controller
	@SessionAttributes("loggedInUser")
	public class UploadController {

	private static Logger log= LoggerFactory.getLogger(UploadController.class);

	@Autowired
	WebUtils webUtils;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/addimages")
	public String add(@RequestParam("file") MultipartFile file, @RequestParam long id, Model model) {
	       
		Pattern ext = Pattern.compile("([^\\s]+(\\.(?i)(png|jpg))$)");
	try {

	  if(file != null && file.isEmpty()){
	  model.addAttribute("error", "Error No file Selected "); 
	      return "redirect:profile"; 
	      } 
	  if(file.getSize()>1073741824){
	  model.addAttribute("error","File size "+file.getSize()+"KB excceds max allowed, try another photo ");
	  return "redirect:profile"; 
	      } 
	  Matcher mtch = ext.matcher(file.getOriginalFilename());
	 
	  if (!mtch.matches()) { model.addAttribute("error", "Invalid Image type "); 
	      return "redirect:profile";	 
	  }

	//save image
	webUtils.addProfilePhoto(file, id, "users");
	model.addAttribute("loggedInUser", userRepository.findById(id).get());
	model.addAttribute("msg", "Upload success "+ file.getSize()+" KB");

	} catch (Exception e) {
	//e.printStackTrace);
	}

	return "profile";
	}

	@PostMapping("uploadMultipleFiles")
	    public String uploadMultipleFiles(RedirectAttributes red,
	    @RequestParam long id,
	    @RequestParam("uploadingFiles")
	    MultipartFile[] files){
	     
	try {
	webUtils.multiplesave(files, id);
	log.info("Fules  {}", files.toString() );
	red.addFlashAttribute("success", "Images udded size"+ files.length);
	} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	                 	
	    return "redirect:/profile";	
	    }



	@GetMapping("setasprofile")
	String setprofile(RedirectAttributes red, 
	@RequestParam String email, @RequestParam String image, Model model) {	    
	    
	    try {	
	    Users customer= userRepository.findByEmail(email);
	    customer.setImage(image);
	    userRepository.save(customer);
	    red.addFlashAttribute("success", "Image set to "+ image);
	} catch (Exception e) {
	e.printStackTrace();
	}
	 
	    return "redirect:/profile";	
	}

	@GetMapping("deleteimage")
	String removeImage(RedirectAttributes red, @RequestParam long id,
	@RequestParam String image, Model model) {	    
	    
	    try {	
	    webUtils.removefiles(id, image);
	    red.addFlashAttribute("success", "Image deleted");
	    
	} catch (Exception e) {
	e.printStackTrace();
	}
	 
	    return "redirect:/profile";	
	}

	@ModelAttribute("user")
	Users initModel() {
	return new Users();	
	}


}
