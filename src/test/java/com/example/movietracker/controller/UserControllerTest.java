package com.example.movietracker.controller;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.User;
import com.example.movietracker.security.UserContext;
import com.example.movietracker.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

import static com.example.movietracker.TestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    UserContext userContext;

    @Test
    public void getWatchedMovies() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername(USERNAME);
        Authentication authentication = Mockito.mock(Authentication.class);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);
        List<MovieDto> movieDtoList = new ArrayList<>();
        movieDtoList.add(movieDto);

        when(userContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(USERNAME);
        when(userService.findByUsername(USERNAME)).thenReturn(user);
        when(userService.getWatchedMovies(USER_ID)).thenReturn(movieDtoList);
        List<MovieDto> result = userController.getWatchedMovies();

        assertEquals(result.size(), movieDtoList.size());
        assertEquals(result.get(0).getId(), MOVIE_ID);
        verify(userContext).getAuthentication();
        verify(userService).findByUsername(USERNAME);
        verify(userService).getWatchedMovies(USER_ID);
    }

    @Test
    public void getWishList() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername(USERNAME);
        Authentication authentication = Mockito.mock(Authentication.class);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);
        List<MovieDto> movieDtoList = new ArrayList<>();
        movieDtoList.add(movieDto);

        when(userContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(USERNAME);
        when(userService.findByUsername(USERNAME)).thenReturn(user);
        when(userService.getWishList(USER_ID)).thenReturn(movieDtoList);
        List<MovieDto> result = userController.getWishList();

        assertEquals(result.size(), movieDtoList.size());
        assertEquals(result.get(0).getId(), MOVIE_ID);
        verify(userContext).getAuthentication();
        verify(userService).findByUsername(USERNAME);
        verify(userService).getWishList(USER_ID);
    }

    @Test
    public void getNextMovie() {
        User user = new User();
        user.setId(USER_ID);
        user.setUsername(USERNAME);
        Authentication authentication = Mockito.mock(Authentication.class);
        MovieDto movieDto = new MovieDto();
        movieDto.setId(MOVIE_ID);

        when(userContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(USERNAME);
        when(userService.findByUsername(USERNAME)).thenReturn(user);
        when(userService.findNextMovieInWishlist(USER_ID)).thenReturn(movieDto);
        MovieDto result = userController.getNextMovie();

        assertEquals(result.getId(), MOVIE_ID);
        verify(userContext).getAuthentication();
        verify(userService).findByUsername(USERNAME);
        verify(userService).findNextMovieInWishlist(USER_ID);
    }

}