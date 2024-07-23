package com.jpa.test.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.jpa.test.Entities.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class NavController {
	
	@GetMapping("map-register")
	public String mapRegister() {
		return "register";
	}
	
	@GetMapping("map-login")
	public String maplogin() {
		return "login";		
	}
	@GetMapping("map-addmovie")
	public String mapAddMovie() {
		return "addmovie";		
	}
	
    @GetMapping("map-deleteuser")
    public String mapDeleteUser() {
        return "deleteuser";        
    }
	@GetMapping("update-profile")
	public String updateProfile()
	{
		return "updateprofile";
	}
	
    @GetMapping("home")
    public String home() {
        
        return "home";
    }
	
	@GetMapping("adminhome")
	public String admin()
	{
		return "adminhome";
	}
	@GetMapping("user-update-profile")
	public String updateProfileUser()
	{
		return "userprofileupdate";
	}
	

}
