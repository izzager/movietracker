package com.example.movietracker.controller;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MovieController {

    private final MovieService movieService;

    @PostMapping("movies/{id}/watched")
    public MovieDto markAsWatched(@PathVariable Long id) {
        //TODO security
        Long userId = 1L;
        return movieService.markAsWatched(id, userId);
    }

    @PostMapping("movies/{id}/wishlist")
    public MovieDto addToWishlist(@PathVariable Long id) {
        //TODO security
        Long userId = 1L;
        return movieService.addToWishlist(id, userId);
    }

    @DeleteMapping("movies/{id}/wishlist")
    public MovieDto removeFromWishlist(@PathVariable Long id) {
        //TODO security
        Long userId = 1L;
        return movieService.removeFromWishlist(id, userId);
    }

}
