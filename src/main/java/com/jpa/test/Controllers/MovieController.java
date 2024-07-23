package com.jpa.test.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.jpa.test.Entities.Movie;
import com.jpa.test.Entities.User;
import com.jpa.test.Services.MovieService;
import com.jpa.test.Services.UserService;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MovieController {
    
    @Autowired
    MovieService movserv;

    @Autowired
    UserService userv;
    
    @PostMapping("addmovie")
    public String addMovie(@ModelAttribute Movie mov) {
        movserv.addMovie(mov);
        return "addmoviesuccess";
    }
    
    @GetMapping("viewmovie")
    public String viewMovie(Model model) {
        List<Movie> movieList = movserv.viewMovie();
        model.addAttribute("mov", movieList);
        return "viewmovie";
    }
    
    @GetMapping("viewmovieuser")
    public String viewMovieUser(Model model) {
        List<Movie> movieList = movserv.viewMovie();
        model.addAttribute("mov", movieList);
        return "viewmovieuser";
    }
    
    @PostMapping("home")
    public String update(@ModelAttribute User usr, HttpSession session) {
        String email = (String) session.getAttribute("email");
        userv.updateUser(usr, email);
        return "adminhome";
    }
}
