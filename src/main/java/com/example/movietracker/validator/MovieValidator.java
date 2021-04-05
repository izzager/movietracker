package com.example.movietracker.validator;

import com.example.movietracker.entity.Movie;
import com.example.movietracker.exception.NotFoundException;
import com.example.movietracker.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieValidator {

    private final MovieRepository movieRepository;

    public Movie getMovie(Long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isEmpty()) {
            throw new NotFoundException("Movie not found");
        }
        return movieOptional.get();
    }

}
