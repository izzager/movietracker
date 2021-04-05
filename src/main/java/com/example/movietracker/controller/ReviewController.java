package com.example.movietracker.controller;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("reviews")
    public ReviewDto addReview(@RequestBody ReviewDto reviewDto) {
        //TODO security
        Long userId = 1L;
        reviewDto.setUserId(userId);
        return reviewService.addReview(reviewDto);
    }

    @GetMapping("movie/{id}/reviews")
    public List<ReviewDto> getMovieReviews(@PathVariable Long id) {
        return reviewService.getReviewsForMovie(id);
    }
}
