package com.midhun.crudoperations.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.midhun.crudoperations.entity.Movie;
import com.midhun.crudoperations.error.MovieNotFoundException;
import com.midhun.crudoperations.repository.MovieRepository;

@SpringBootTest
class MovieServiceTest {

	@Autowired
	private MovieService movieService;
	private static Movie dummyMovie;

	@MockBean
	private MovieRepository movieRepository;

	@BeforeEach
	public static void setUp() {
		dummyMovie = new Movie();
		dummyMovie.setName("movie name");
		dummyMovie.setId(1);
		dummyMovie.setRating(5.0);
		dummyMovie.setTotalBudget(200);
	}

	@Test
	void testGetAllMovies() {
		List<Movie> dummyRes = new ArrayList<>();
		dummyRes.add(dummyMovie);
		when(movieRepository.findAll()).thenReturn(dummyRes);
		List<Movie> expected = movieService.getAllMovies();
		for (Movie m : expected) {
			assertEquals(m.getName(), dummyMovie.getName());
		}
	}

	@Test
	void testGetMovieById() {
		Optional<Movie> optional = Optional.of(dummyMovie);
		when(movieRepository.findById(1)).thenReturn(optional);
		Movie expected = null;
		try {
			expected = movieService.getMovieById(1);
		} catch (MovieNotFoundException e) {
			e.printStackTrace();
		}
		assertNotNull(expected);
		assertEquals(expected.getName(), dummyMovie.getName());
	}

	@Test
	void testSaveMovie() {
		when(movieRepository.save(dummyMovie)).thenReturn(dummyMovie);
		movieService.saveMovie(dummyMovie);
	}

	@Test
	void testUpdateMovie() {
		Movie existingMovie = spy(new Movie());
		existingMovie.setId(2);
		existingMovie.setName("avatar");
		existingMovie.setRating(1.0);
		existingMovie.setTotalBudget(500);
		Optional<Movie> optional = Optional.of(existingMovie);
		when(movieRepository.findById(1)).thenReturn(optional);
		try {
			movieService.updateMovie(1, dummyMovie);
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(existingMovie, times(1)).setName("avatar");
	}

	@Test
	void testDeleteMovie() {
		doNothing().when(movieRepository).deleteById(1);
		try {
			movieService.deleteMovie(1);
		} catch (MovieNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetMovieByName() {
		when(movieRepository.findByName("avatar")).thenReturn(dummyMovie);
		Movie expected = null;
		try {
			expected = movieService.getMovieByName("avatar");
		} catch (MovieNotFoundException e) {
			e.printStackTrace();
		}
		assertNotNull(expected);
		assertEquals(expected.getName(), dummyMovie.getName());
	}

	@Test
	void testGetMoviesByRating() {
		List<Movie> dummyRes = new ArrayList<>();
		dummyRes.add(dummyMovie);
		when(movieRepository.findByRatingBetween(4.0, 4.7)).thenReturn(dummyRes);
		List<Movie> expectedList = new ArrayList<>();
		expectedList = movieService.getMoviesByRating(4.0, 4.7);
		assertNotNull(expectedList);
		assertEquals(1, expectedList.size());
		for (Movie m : expectedList) {
			assertEquals(m.getName(), dummyMovie.getName());
		}
	}

	@Test
	void testCustomQuery() {
		when(movieRepository.customFind()).thenReturn(dummyMovie);
		Movie expected = null;
		expected = movieService.customQuery();
		assertNotNull(expected);
		assertEquals(expected.getName(), dummyMovie.getName());
	}

}
