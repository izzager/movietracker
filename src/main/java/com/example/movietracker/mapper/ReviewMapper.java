package com.example.movietracker.mapper;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper implements Mapper<Review, ReviewDto> {

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

}
