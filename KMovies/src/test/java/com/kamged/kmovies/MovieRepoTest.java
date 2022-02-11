package com.kamged.kmovies;

import com.kamged.kmovies.models.Movie;
import com.kamged.kmovies.repositories.IMovieRepo;
import com.kamged.kmovies.services.IMovieService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class MovieRepoTest {
    @Autowired
    private IMovieRepo movieRepo;

    @Autowired
    private IMovieService movieService;

    @Test
    public void checkIfMovieListIsGreaterZero()
    {
        List<Movie> movieList = movieRepo.findAll();

        Assertions.assertTrue(movieList.size() > 0);
    }

    @Test
    public void checkSaving()
    {
         Movie m = new Movie();
         m.setTitle("Demo Movie");
         m.setYear("Demo Year");
         m.setType("Movie");
         m.setPoster("Demo Poster");
         m.setResult("True");

         Assertions.assertNotNull(movieRepo.save(m));
    }

    @Test void checkIfMovieIsExists()
    {
        boolean res = movieService.MovieExists("Matrix", "1999");

        Assertions.assertFalse(res);
    }
}
