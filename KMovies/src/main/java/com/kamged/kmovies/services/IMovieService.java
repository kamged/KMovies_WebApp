package com.kamged.kmovies.services;

import com.kamged.kmovies.models.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMovieService {
    List<Movie> getAllMovies();
    void save(Movie movie);
    void delete(Integer id);
    Movie get(Integer id);
    void _update(Movie movie);
    boolean MovieExists(String title, String year);
}
