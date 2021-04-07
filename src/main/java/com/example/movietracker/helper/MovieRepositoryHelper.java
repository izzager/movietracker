package com.example.movietracker.helper;

import com.example.movietracker.entity.Movie;
import com.example.movietracker.exception.ResourceNotFoundException;
import com.example.movietracker.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieRepositoryHelper {

    private final MovieRepository movieRepository;

    public Movie ensureMovieExists(Long movieId) {
        return movieRepository
                .findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
    }

}
