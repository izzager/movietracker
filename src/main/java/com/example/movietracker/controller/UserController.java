package com.example.movietracker.controller;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @GetMapping("watched")
    public List<MovieDto> getWatchedMovies(Principal auth) {
        Long userId = userService.findByUsername(auth.getName()).getId();
        return userService.getWatchedMovies(userId);
    }

    @GetMapping("wishlist")
    public List<MovieDto> getWishList(Principal auth) {
        Long userId = userService.findByUsername(auth.getName()).getId();
        return userService.getWishList(userId);
    }

    @GetMapping("nextMovie")
    public MovieDto getNextMovie(Principal auth) {
        Long userId = userService.findByUsername(auth.getName()).getId();
        return userService.findNextMovieInWishlist(userId);
    }

}
