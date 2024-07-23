package com.jpa.test.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.test.Entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
