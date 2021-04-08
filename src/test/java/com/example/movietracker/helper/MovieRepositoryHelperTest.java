package com.example.movietracker.helper;

import com.example.movietracker.entity.Movie;
import com.example.movietracker.exception.ResourceNotFoundException;
import com.example.movietracker.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.movietracker.TestConstants.MOVIE_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieRepositoryHelperTest {

    @InjectMocks
    private MovieRepositoryHelper movieRepositoryHelper;

    @Mock
    private MovieRepository movieRepository;

    @Test
    public void ensureMovieExists_true() {
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);

        when(movieRepository.findById(MOVIE_ID)).thenReturn(Optional.of(movie));
        Movie result = movieRepositoryHelper.ensureMovieExists(MOVIE_ID);

        assertEquals(result.getId(), MOVIE_ID);
        verify(movieRepository).findById(MOVIE_ID);
    }

    @Test
    public void ensureMovieExists_false() {
        when(movieRepository.findById(MOVIE_ID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> movieRepositoryHelper.ensureMovieExists(MOVIE_ID));
    }
}