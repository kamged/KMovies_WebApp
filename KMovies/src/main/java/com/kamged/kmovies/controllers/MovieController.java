package com.kamged.kmovies.controllers;

import com.kamged.kmovies.models.Movie;
import com.kamged.kmovies.services.IMovieService;
import com.kamged.kmovies.services.OMDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/kmovies")
public class MovieController {
    @Autowired
    IMovieService _movieService;

    @GetMapping("/")
    public String IndexView(Model model) {
        model.addAttribute("numMovies", _movieService.getAllMovies().size());
        /*model.addAttribute("numTVSeries", _movieService.getAllTVSeries().size());*/

        return "index";
    }

    @GetMapping("/movies")
    public String MovieView(Model model) {
        model.addAttribute("movies", _movieService.getAllMovies());
        model.addAttribute("numMovies", _movieService.getAllMovies().size());
        model.addAttribute("newmovie", new Movie());

        return "MovieView";
    }

    @PostMapping("/movies/save")
    public String CreateMovie(Movie movie, Model model) {
        String[] res = OMDbService.OMDbRequest(movie.getTitle(), movie.getYear());

        if(!res[0].equals("False") && !_movieService.MovieExists(movie.getTitle(), movie.getYear())) {
            movie.setResult(res[0]);
            movie.setType(res[1]);
            movie.setPoster(res[2]);

            _movieService.save(movie);
        } else {
            model.addAttribute("movies", _movieService.getAllMovies());
            model.addAttribute("numMovies", _movieService.getAllMovies().size());
            model.addAttribute("newmovie", new Movie());
            model.addAttribute("err", "Movie exists or not found");

            return "MovieView";
        }

        return "redirect:/kmovies/movies/";
    }

    @RequestMapping(value = "/movies/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String DeleteMovie(@PathVariable Integer id) {
        _movieService.delete(id);

        return "redirect:/kmovies/movies/";
    }

    @GetMapping("/movies/details/{id}")
    public String MovieDetailsView(@PathVariable Integer id, Model model) {
        model.addAttribute("movie", _movieService.get(id));
        model.addAttribute("edit", "false");

        return "MovieDetailsView";
    }

    @GetMapping("/movies/edit/{id}")
    public String MovieEditView(@PathVariable Integer id, Model model) {
        model.addAttribute("movie", _movieService.get(id));
        model.addAttribute("edit", "true");

        return "MovieDetailsView";
    }

    @RequestMapping(value = "/movies/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String UpdateClient(Movie movie) {
        _movieService._update(movie);

        return "redirect:/kmovies/movies/";
    }
}