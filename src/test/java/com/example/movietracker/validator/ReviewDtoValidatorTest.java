package com.example.movietracker.validator;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.Movie;
import com.example.movietracker.entity.Review;
import com.example.movietracker.entity.User;
import com.example.movietracker.exception.ResourceForbiddenException;
import com.example.movietracker.exception.ResourceNotFoundException;
import com.example.movietracker.repository.MovieRepository;
import com.example.movietracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.example.movietracker.TestConstants.MOVIE_ID;
import static com.example.movietracker.TestConstants.USER_ID;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewDtoValidatorTest {

    @InjectMocks
    public ReviewDtoValidator reviewDtoValidator;

    @Mock
    public MovieRepository movieRepository;

    @Mock
    public UserRepository userRepository;

    @Test
    public void validate_validDto_passes() {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUserId(USER_ID);
        reviewDto.setMovieId(MOVIE_ID);
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        Set<Movie> watched = new HashSet<>();
        watched.add(movie);
        User user = new User();
        user.setWatched(watched);

        when(movieRepository.existsById(MOVIE_ID)).thenReturn(true);
        when(userRepository.existsById(USER_ID)).thenReturn(true);
        when(movieRepository.getOne(MOVIE_ID)).thenReturn(movie);
        when(userRepository.getOne(USER_ID)).thenReturn(user);

        assertDoesNotThrow(() -> reviewDtoValidator.validate(reviewDto));
        verify(movieRepository).existsById(MOVIE_ID);
        verify(userRepository).existsById(USER_ID);
        verify(movieRepository).getOne(MOVIE_ID);
        verify(userRepository).getOne(USER_ID);
    }

    @Test
    public void validate_unexistentMovieId_throwsMovieNotFound() {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setMovieId(MOVIE_ID);

        when(movieRepository.existsById(MOVIE_ID)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> reviewDtoValidator.validate(reviewDto));
        verify(movieRepository).existsById(MOVIE_ID);
    }

    @Test
    public void validate_unexistentUserId_throwsUserNotFound() {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUserId(USER_ID);
        reviewDto.setMovieId(MOVIE_ID);

        when(movieRepository.existsById(MOVIE_ID)).thenReturn(true);
        when(userRepository.existsById(USER_ID)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> reviewDtoValidator.validate(reviewDto));
        verify(movieRepository).existsById(MOVIE_ID);
        verify(userRepository).existsById(USER_ID);
    }

    @Test
    public void validate_reviewNotWatchedMovie_throwsResourceForbiddenException() {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUserId(USER_ID);
        reviewDto.setMovieId(MOVIE_ID);
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        User user = new User();
        user.setWatched(new HashSet<>());

        when(movieRepository.existsById(MOVIE_ID)).thenReturn(true);
        when(userRepository.existsById(USER_ID)).thenReturn(true);
        when(movieRepository.getOne(MOVIE_ID)).thenReturn(movie);
        when(userRepository.getOne(USER_ID)).thenReturn(user);

        assertThrows(ResourceForbiddenException.class, () -> reviewDtoValidator.validate(reviewDto));
        verify(movieRepository).existsById(MOVIE_ID);
        verify(userRepository).existsById(USER_ID);
        verify(movieRepository).getOne(MOVIE_ID);
        verify(userRepository).getOne(USER_ID);
    }

    @Test
    public void validate_reviewReviewedMovie_throwsResourceForbiddenException() {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setUserId(USER_ID);
        reviewDto.setMovieId(MOVIE_ID);
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        Set<Movie> watched = new HashSet<>();
        watched.add(movie);
        User user = new User();
        user.setWatched(watched);
        Review review = new Review();
        User userWithId = new User(); //otherwise get cycle
        userWithId.setId(USER_ID);
        review.setUser(userWithId);
        movie.setReviews(new ArrayList<>());
        movie.getReviews().add(review);

        when(movieRepository.existsById(MOVIE_ID)).thenReturn(true);
        when(userRepository.existsById(USER_ID)).thenReturn(true);
        when(movieRepository.getOne(MOVIE_ID)).thenReturn(movie);
        when(userRepository.getOne(USER_ID)).thenReturn(user);

        assertThrows(ResourceForbiddenException.class, () -> reviewDtoValidator.validate(reviewDto));
        verify(movieRepository).existsById(MOVIE_ID);
        verify(userRepository).existsById(USER_ID);
        verify(movieRepository).getOne(MOVIE_ID);
        verify(userRepository).getOne(USER_ID);
    }

}