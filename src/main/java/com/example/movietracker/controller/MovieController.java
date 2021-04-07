package com.example.movietracker.controller;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.service.MovieService;
import com.example.movietracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("movies")
public class MovieController {

    private final MovieService movieService;
    private final UserService userService;

    @PostMapping("{id}/watched")
    public MovieDto markAsWatched(@PathVariable Long id,
                                  Principal auth) {
        Long userId = userService.findByUsername(auth.getName()).getId();
        return movieService.markAsWatched(id, userId);
    }

    @PostMapping("{id}/wishlist")
    public MovieDto addToWishlist(@PathVariable Long id,
                                  Principal auth) {
        Long userId = userService.findByUsername(auth.getName()).getId();
        return movieService.addToWishlist(id, userId);
    }

    @DeleteMapping("{id}/wishlist")
    public MovieDto removeFromWishlist(@PathVariable Long id,
                                       Principal auth) {
        Long userId = userService.findByUsername(auth.getName()).getId();
        return movieService.removeFromWishlist(id, userId);
    }

}
