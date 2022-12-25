package com.midhun.crudoperations.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.midhun.crudoperations.entity.Movie;
import com.midhun.crudoperations.error.MovieNotFoundException;
import com.midhun.crudoperations.repository.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}
	
	public Movie getMovieById(int id) throws MovieNotFoundException{
		Optional<Movie> requestedMovie = movieRepository.findById(id);
		if(requestedMovie.isPresent()) {
			return requestedMovie.get();
		}
		throw new MovieNotFoundException("Movie with id : "+id+" is not found");
	}

	public void saveMovie(Movie movie) {
		movieRepository.save(movie);
	}

	public void updateMovie(int id, Movie movie) throws MovieNotFoundException{
		Optional<Movie> movieOptional = movieRepository.findById(id);
		
		if(movieOptional.isEmpty()) {
			throw new MovieNotFoundException("Could n't find movie with id "+id);
		}
		
		Movie existingMovie = movieOptional.get();
		
		if(Objects.nonNull(movie.getName()) && existingMovie.getName()!=movie.getName()
				&& !movie.getName().equalsIgnoreCase("")) {
			existingMovie.setName(movie.getName());
		}
		
		if(Objects.nonNull(movie.getRating()) && existingMovie.getRating()!= movie.getRating()
				&& movie.getRating()!=0.0) {
			existingMovie.setRating(movie.getRating());
		}
		
		if(Objects.nonNull(movie.getTotalBudget()) && existingMovie.getTotalBudget() != movie.getTotalBudget()
				&& movie.getTotalBudget()!=0) {
			existingMovie.setTotalBudget(movie.getTotalBudget());
		}
		
		movieRepository.save(existingMovie);
	}
	
	public void deleteMovie(int id) throws MovieNotFoundException{
		try {
			movieRepository.deleteById(id);
		}catch(Exception ex) {
			throw new MovieNotFoundException("Movie with id : "+id+" is not found");
		}
	}

	public Movie getMovieByName(String name) throws MovieNotFoundException{
		Movie requestedMovie =  movieRepository.findByName(name);
		if(requestedMovie!=null) return requestedMovie;
		throw new MovieNotFoundException("Movie with name : "+ name+" is not found");
	}

	public List<Movie> getMoviesByRating(double lowrating, double highrating) {
		return movieRepository.findByRatingBetween(lowrating, highrating);
	}

	public Movie customQuery() {
		return movieRepository.customFind();
	}

}
