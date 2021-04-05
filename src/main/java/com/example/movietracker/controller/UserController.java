package com.example.movietracker.controller;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("watched")
    public List<MovieDto> getWatchedMovies() {
        //TODO security
        Long userId = 1L;
        return userService.getWatchedMovies(userId);
    }

    @GetMapping("wishlist")
    public List<MovieDto> getWishList() {
        //TODO security
        Long userId = 1L;
        return userService.getWishList(userId);
    }

    @GetMapping("nextMovie")
    public MovieDto getNextMovie() {
        //TODO security
        Long userId = 1L;
        return userService.findNextMovieInWishlist(userId);
    }

}
