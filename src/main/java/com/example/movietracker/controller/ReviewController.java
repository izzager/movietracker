package com.example.movietracker.controller;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.service.ReviewService;
import com.example.movietracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;

    @PostMapping("reviews")
    public ReviewDto addReview(@RequestBody ReviewDto reviewDto,
                               Principal auth) {
        Long userId = userService.findByUsername(auth.getName()).getId();
        reviewDto.setUserId(userId);
        return reviewService.addReview(reviewDto);
    }

    @GetMapping("movie/{id}/reviews")
    public List<ReviewDto> getMovieReviews(@PathVariable Long id) {
        return reviewService.getReviewsForMovie(id);
    }
}
