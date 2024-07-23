package com.jpa.test.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.test.Entities.Movie;
import com.jpa.test.Repositories.MovieRepository;

@Service
public class MovieServiceImplementation implements MovieService{

	@Autowired
	MovieRepository movrepo;

	@Override
	public String addMovie(Movie mov) {
	movrepo.save(mov);
		
			return "movie is added";
	}

	@Override
	public List<Movie> viewMovie() {
	List<Movie>movieList=movrepo.findAll();
		return movieList;
	}	
	
}
