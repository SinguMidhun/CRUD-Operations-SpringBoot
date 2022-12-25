package com.midhun.crudoperations.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.midhun.crudoperations.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer>{
	
	public Movie findByName(String name);
	
	public List<Movie> findByRatingBetween(double v1, double v2);
	
	@Query(value = "SELECT * FROM MOVIE WHERE ID = 1",nativeQuery = true)
	public Movie customFind();
	
}
