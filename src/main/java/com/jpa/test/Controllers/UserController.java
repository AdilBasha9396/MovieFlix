package com.jpa.test.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jpa.test.Entities.Movie;
import com.jpa.test.Entities.User;
import com.jpa.test.Services.MovieService;
import com.jpa.test.Services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	

	@Autowired
	UserService userv;
	
	@Autowired
	MovieService movserv;
	
	@PostMapping("register")
	public String addUsers(@ModelAttribute User usr) {
		boolean status = userv.emailExits(usr.getEmail());
		if(status == true) {
			return "regfail";
		}
		else {
			userv.addUsers(usr);
			return "regsuccess";
		}
	}
	
	@PostMapping("login")
	public String validateUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
		boolean loginStatus =userv.checkUser(email, password);
		if(loginStatus==true ) {
			session.setAttribute("email", email);
			
			if(email.equals("admin@gmail.com"))
			{
				
				return "adminhome";
			}
			else 
			{
				return "home";
			}
		}
		else 
		{
			return "loginfail";
		}
	}
	@GetMapping("viewuser")
	public String viewUser(Model model) {
		List<User> userList = userv.viewUser();
		model.addAttribute("user",userList);
		return "viewuser";
	}
	
	@GetMapping("deleteUser")
    public String deleteUser(@RequestParam int userId, Model model) {
        boolean isDeleted = userv.deleteUser(userId);
        if(isDeleted) {
            model.addAttribute("message", "User deleted successfully");
        } else {
            model.addAttribute("message", "User not found");
        }
        //System.out.println("Deletion is success");
        return "deletesuccess"; // Redirect to admin home or an appropriate page
    }
	
	@GetMapping("exploremovies")
	public String exploreMovie(Model model, HttpSession session) {
		
		String email = (String)session.getAttribute("email");

		User usr=userv.getUser(email);
		
		
		if(usr.isPremium() == true)
		{
			List<Movie> movieList = movserv.viewMovie();
			model.addAttribute("movie", movieList);
			return "viewmovieuser";
		}
		else
		{
			return "payment";
		}
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
	 @PostMapping("update-profile")
	    public String updateProfile(@ModelAttribute User usr, HttpSession session) {
	        String email = (String) session.getAttribute("email");
	        userv.updateUser(usr, email);
	        return "profileupdatesuccess";
	 }
	 @PostMapping("user-update-profile")
	    public String updateProfileUser(@ModelAttribute User usr, HttpSession session) {
	        String email = (String) session.getAttribute("email");
	        userv.updateUser(usr, email);
	        return "userprofileupdatesuccess";
	 }


}
