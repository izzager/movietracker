package com.example.movietracker.controller;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.security.UserContext;
import com.example.movietracker.service.ReviewService;
import com.example.movietracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final UserService userService;
    private final UserContext userContext;

    @PostMapping("reviews")
    public ReviewDto addReview(@Validated @RequestBody ReviewDto reviewDto) {
        Long userId = userService.findByUsername(userContext.getAuthentication().getName()).getId();
        reviewDto.setUserId(userId);
        return reviewService.addReview(reviewDto);
    }

    @GetMapping("movie/{id}/reviews")
    public List<ReviewDto> getMovieReviews(@PathVariable Long id) {
        return reviewService.getReviewsForMovie(id);
    }
}
