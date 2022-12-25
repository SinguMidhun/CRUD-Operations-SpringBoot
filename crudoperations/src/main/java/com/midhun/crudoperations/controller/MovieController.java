package com.midhun.crudoperations.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.midhun.crudoperations.entity.Movie;
import com.midhun.crudoperations.error.MovieNotFoundException;
import com.midhun.crudoperations.service.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	//Loggers
	private Logger log = LoggerFactory.getLogger(MovieController.class);
	
	@GetMapping(path = "/")
	private String home() {
		return "Home Page";
	}
	
	@GetMapping(path = "/movies")
	private List<Movie> getMovies() {
		log.info(getClass().getName() +" Trying to get all movies");
		return movieService.getAllMovies();
	}
	
	@RequestMapping(path = "/movie/{id}", method = RequestMethod.GET)
	private Movie getMovieById(@PathVariable int id) throws MovieNotFoundException{
		log.info(getClass().getName() +" Trying to get movie by id");
		return movieService.getMovieById(id);
	}
	
	@PostMapping("/add")
	private String saveMovie(@RequestBody Movie movie) {
		log.info(getClass().getName() + " Trying to save a new movie");
		movieService.saveMovie(movie);
		return "saved successfully";
	}
	
	@PutMapping("/update/{id}")
	private String updateMovie(@PathVariable int id,@RequestBody Movie movie) 
	throws MovieNotFoundException{
		log.info(getClass().getName() + " Trying to update by movie id");
		movieService.updateMovie(id,movie);
		return "Updated Successfully";
	}
	
	@DeleteMapping(path = "/delete/{id}")
	private String deleteMovie(@PathVariable int id) 
	throws MovieNotFoundException{
		log.info(getClass().getName() + " Trying to delete movie by id");
		movieService.deleteMovie(id);
		return "Deleted Successfully";
	}
	
	@GetMapping("/movies/{name}")
	private Movie getMovieByName(@PathVariable String name)
	throws MovieNotFoundException{
		log.info(getClass().getName() + " Trying to get movie by name");
		return movieService.getMovieByName(name);
	}
	
	@GetMapping("/movies/{lr}/{hr}")
	private List<Movie> getMoviesByRating(@PathVariable(name = "lr") double lowrating,
			@PathVariable(name = "hr") double highrating) {
		log.info(getClass().getName() + " Trying to get movies by rating");
		return movieService.getMoviesByRating(lowrating,highrating);
	}
	
	@GetMapping("/custom")
	private Movie customQuery() {
		log.info(getClass().getName() + " Trying to get movie whose id is 1");
		return movieService.customQuery();
	}
	
}
