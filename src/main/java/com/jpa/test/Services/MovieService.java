package com.jpa.test.Services;

import java.util.List;

import com.jpa.test.Entities.Movie;

public interface MovieService {
	
	public String addMovie(Movie mov);
	public List<Movie> viewMovie();
	

}
