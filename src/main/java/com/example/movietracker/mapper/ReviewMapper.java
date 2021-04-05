package com.example.movietracker.mapper;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.Review;
import com.example.movietracker.validator.MovieValidator;
import com.example.movietracker.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper implements Mapper<Review, ReviewDto> {

    private final UserValidator userValidator;
    private final MovieValidator movieValidator;

    @Override
    public ReviewDto toDto(Review entity) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(entity.getId());
        reviewDto.setMovieId(entity.getMovie().getId());
        reviewDto.setUserId(entity.getUser().getId());
        reviewDto.setComment(entity.getComment());
        reviewDto.setRating(entity.getRating());
        reviewDto.setDate(entity.getCreationDate());
        return reviewDto;
    }

    public Review toEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setUser(userValidator.getUser(reviewDto.getUserId()));
        review.setMovie(movieValidator.getMovie(reviewDto.getMovieId()));
        review.setRating(reviewDto.getRating());
        review.setComment(reviewDto.getComment());
        return review;
    }

}
