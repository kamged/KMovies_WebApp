package com.kamged.kmovies.services;

import com.kamged.kmovies.models.Movie;
import com.kamged.kmovies.models.TVSeries;
import com.kamged.kmovies.repositories.IMovieRepo;
import com.kamged.kmovies.repositories.ITVSeriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class MovieService implements IMovieService {
    @Autowired
    IMovieRepo _movieRepo;

    /*@Autowired
    ITVSeriesRepo _seriesRepo;*/

    public List<Movie> getAllMovies() {
        return _movieRepo.findAll();
    }

    public void save(Movie movie) { _movieRepo.save(movie); }

    public void delete(Integer id) { _movieRepo.deleteById(id); }

    public Movie get(Integer id) { return _movieRepo.getById(id); }

    public void _update(Movie movie) {_movieRepo._update(movie.getId(), movie.getTitle(), movie.getYear(), movie.getRating());}

    public boolean MovieExists(String title, String year) {
        return (getAllMovies().stream()
                              .filter((mv) -> (mv.getTitle().equals(title) == true && mv.getYear().equals(year) == true))
                              .count() > 0) ? true : false;
    }

    /*public List<TVSeries> getAllTVSeries() {
        return _seriesRepo.findAll();
    }*/
}
