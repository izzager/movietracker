package com.example.movietracker.service.impl;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.Movie;
import com.example.movietracker.entity.User;
import com.example.movietracker.exception.ResourceForbiddenException;
import com.example.movietracker.helper.MovieRepositoryHelper;
import com.example.movietracker.helper.UserRepositoryHelper;
import com.example.movietracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.movietracker.TestConstants.MOVIE_ID;
import static com.example.movietracker.TestConstants.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MovieServiceImplTest {

    @InjectMocks
    MovieServiceImpl movieService;

    @Mock
    UserRepository userRepository;

    @Mock
    ModelMapper modelMapper;

    @Mock
    MovieRepositoryHelper movieRepositoryHelper;

    @Mock
    UserRepositoryHelper userRepositoryHelper;

    @Test
    public void markAsWatched_passes() {
        User user = new User();
        user.setId(USER_ID);
        Set<Movie> watched = new HashSet<>();
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        user.setWatched(watched);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);

        when(userRepositoryHelper.ensureUserExists(USER_ID)).thenReturn(user);
        when(movieRepositoryHelper.ensureMovieExists(MOVIE_ID)).thenReturn(movie);
        when(modelMapper.map(movie, MovieDto.class)).thenReturn(movieDto);
        MovieDto result = movieService.markAsWatched(MOVIE_ID, USER_ID);

        assertEquals(result.getId(), movie.getId());
        verify(userRepositoryHelper).ensureUserExists(USER_ID);
        verify(movieRepositoryHelper).ensureMovieExists(MOVIE_ID);
        verify(modelMapper).map(movie, MovieDto.class);
    }

    @Test
    public void markAsWatched_alreadyWatched_throwsResourceForbiddenException() {
        User user = new User();
        user.setId(USER_ID);
        Set<Movie> watched = new HashSet<>();
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        watched.add(movie);
        user.setWatched(watched);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);

        when(userRepositoryHelper.ensureUserExists(USER_ID)).thenReturn(user);
        when(movieRepositoryHelper.ensureMovieExists(MOVIE_ID)).thenReturn(movie);

        assertThrows(ResourceForbiddenException.class, () -> movieService.markAsWatched(MOVIE_ID, USER_ID));
        verify(userRepositoryHelper).ensureUserExists(USER_ID);
        verify(movieRepositoryHelper).ensureMovieExists(MOVIE_ID);
    }

    @Test
    public void addToWishlist_notAddedMovie_passes() {
        User user = new User();
        user.setId(USER_ID);
        List<Movie> wishlist = new ArrayList<>();
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        user.setWishlist(wishlist);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);

        when(userRepositoryHelper.ensureUserExists(USER_ID)).thenReturn(user);
        when(movieRepositoryHelper.ensureMovieExists(MOVIE_ID)).thenReturn(movie);
        when(modelMapper.map(movie, MovieDto.class)).thenReturn(movieDto);
        MovieDto result = movieService.addToWishlist(MOVIE_ID, USER_ID);

        assertEquals(result.getId(), movie.getId());
        verify(userRepositoryHelper).ensureUserExists(USER_ID);
        verify(movieRepositoryHelper).ensureMovieExists(MOVIE_ID);
        verify(modelMapper).map(movie, MovieDto.class);
    }

    @Test
    public void addToWishlist_alreadyAddedMovie_throwsResourceForbiddenException() {
        User user = new User();
        user.setId(USER_ID);
        List<Movie> wishlist = new ArrayList<>();
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        wishlist.add(movie);
        user.setWishlist(wishlist);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);

        when(userRepositoryHelper.ensureUserExists(USER_ID)).thenReturn(user);
        when(movieRepositoryHelper.ensureMovieExists(MOVIE_ID)).thenReturn(movie);

        assertThrows(ResourceForbiddenException.class, () -> movieService.addToWishlist(MOVIE_ID, USER_ID));
        verify(userRepositoryHelper).ensureUserExists(USER_ID);
        verify(movieRepositoryHelper).ensureMovieExists(MOVIE_ID);
    }

    @Test
    public void removeFromWishlist_movieInWishlist_pass() {
        User user = new User();
        user.setId(USER_ID);
        List<Movie> wishlist = new ArrayList<>();
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        wishlist.add(movie);
        user.setWishlist(wishlist);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);

        when(userRepositoryHelper.ensureUserExists(USER_ID)).thenReturn(user);
        when(movieRepositoryHelper.ensureMovieExists(MOVIE_ID)).thenReturn(movie);
        when(modelMapper.map(movie, MovieDto.class)).thenReturn(movieDto);
        MovieDto result = movieService.removeFromWishlist(MOVIE_ID, USER_ID);

        assertEquals(result.getId(), movie.getId());
        verify(userRepositoryHelper).ensureUserExists(USER_ID);
        verify(movieRepositoryHelper).ensureMovieExists(MOVIE_ID);
        verify(modelMapper).map(movie, MovieDto.class);
    }

    @Test
    public void removeFromWishlist_notAddedToWishlist_throwsResourceForbiddenException() {
        User user = new User();
        user.setId(USER_ID);
        List<Movie> wishlist = new ArrayList<>();
        user.setWishlist(wishlist);
        Movie movie = new Movie();
        movie.setId(MOVIE_ID);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);

        when(userRepositoryHelper.ensureUserExists(USER_ID)).thenReturn(user);
        when(movieRepositoryHelper.ensureMovieExists(MOVIE_ID)).thenReturn(movie);

        assertThrows(ResourceForbiddenException.class, () -> movieService.removeFromWishlist(MOVIE_ID, USER_ID));
        verify(userRepositoryHelper).ensureUserExists(USER_ID);
        verify(movieRepositoryHelper).ensureMovieExists(MOVIE_ID);
    }

}