package com.example.movietracker.controller;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.User;
import com.example.movietracker.security.UserContext;
import com.example.movietracker.service.MovieService;
import com.example.movietracker.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import static com.example.movietracker.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {

    @InjectMocks
    MovieController movieController;

    @Mock
    MovieService movieService;

    @Mock
    UserService userService;

    @Mock
    UserContext userContext;
    
    @Test
    public void markAsWatched() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername(USERNAME);
        Authentication authentication = Mockito.mock(Authentication.class);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);

        when(userContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(USERNAME);
        when(userService.findByUsername(USERNAME)).thenReturn(user);
        when(movieService.markAsWatched(MOVIE_ID, USER_ID)).thenReturn(movieDto);
        MovieDto result = movieController.markAsWatched(MOVIE_ID);

        assertEquals(result.getId(), MOVIE_ID);
        verify(userContext).getAuthentication();
        verify(userService).findByUsername(USERNAME);
        verify(movieService).markAsWatched(MOVIE_ID, USER_ID);
    }

    @Test
    public void addToWishlist() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername(USERNAME);
        Authentication authentication = Mockito.mock(Authentication.class);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);

        when(userContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(USERNAME);
        when(userService.findByUsername(USERNAME)).thenReturn(user);
        when(movieService.addToWishlist(MOVIE_ID, USER_ID)).thenReturn(movieDto);
        MovieDto result = movieController.addToWishlist(MOVIE_ID);

        assertEquals(result.getId(), MOVIE_ID);
        verify(userContext).getAuthentication();
        verify(userService).findByUsername(USERNAME);
        verify(movieService).addToWishlist(MOVIE_ID, USER_ID);
    }

    @Test
    public void removeFromWishlist() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername(USERNAME);
        Authentication authentication = Mockito.mock(Authentication.class);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);

        when(userContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(USERNAME);
        when(userService.findByUsername(USERNAME)).thenReturn(user);
        when(movieService.removeFromWishlist(MOVIE_ID, USER_ID)).thenReturn(movieDto);
        MovieDto result = movieController.removeFromWishlist(MOVIE_ID);

        assertEquals(result.getId(), MOVIE_ID);
        verify(userContext).getAuthentication();
        verify(userService).findByUsername(USERNAME);
        verify(movieService).removeFromWishlist(MOVIE_ID, USER_ID);
    }

}