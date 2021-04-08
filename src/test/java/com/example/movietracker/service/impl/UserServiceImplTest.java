package com.example.movietracker.service.impl;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.Movie;
import com.example.movietracker.entity.User;
import com.example.movietracker.exception.ResourceNotFoundException;
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
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    ModelMapper modelMapper;

    @Mock
    UserRepositoryHelper userRepositoryHelper;

    @Mock
    UserRepository userRepository;

    @Test
    public void findNextMovieInWishlist_ok() {
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
        when(modelMapper.map(movie, MovieDto.class)).thenReturn(movieDto);
        MovieDto result = userService.findNextMovieInWishlist(USER_ID);

        assertEquals(result.getId(), MOVIE_ID);
        verify(userRepositoryHelper).ensureUserExists(USER_ID);
        verify(modelMapper).map(movie, MovieDto.class);
    }

    @Test
    public void findNextMovieInWishlist_emptyWishlist() {
        User user = new User();
        user.setId(USER_ID);
        List<Movie> wishlist = new ArrayList<>();
        user.setWishlist(wishlist);

        when(userRepositoryHelper.ensureUserExists(USER_ID)).thenReturn(user);

        assertThrows(ResourceNotFoundException.class, () -> userService.findNextMovieInWishlist(USER_ID));
        verify(userRepositoryHelper).ensureUserExists(USER_ID);
    }

    @Test
    public void getWatchedMovies_ok() {
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
        when(modelMapper.map(movie, MovieDto.class)).thenReturn(movieDto);
        List<MovieDto> result = userService.getWatchedMovies(USER_ID);

        assertEquals(result.size(), watched.size());
        assertEquals(result.get(0).getId(), MOVIE_ID);
        verify(userRepositoryHelper).ensureUserExists(USER_ID);
        verify(modelMapper).map(movie, MovieDto.class);

    }

    @Test
    public void getWishList_ok() {
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
        when(modelMapper.map(movie, MovieDto.class)).thenReturn(movieDto);
        List<MovieDto> result = userService.getWishList(USER_ID);

        assertEquals(result.size(), wishlist.size());
        assertEquals(result.get(0).getId(), MOVIE_ID);
        verify(userRepositoryHelper).ensureUserExists(USER_ID);
        verify(modelMapper).map(movie, MovieDto.class);
    }

}