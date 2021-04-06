package com.example.movietracker.mapper;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.Review;
import com.example.movietracker.validator.MovieValidator;
import com.example.movietracker.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper implements Mapper<Review, ReviewDto> {

    private final UserValidator userValidator;
    private final MovieValidator movieValidator;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ReviewDto toDto(Review entity) {
        ReviewDto reviewDto = new ReviewDto();
        modelMapper.map(entity, reviewDto);
        reviewDto.setMovieId(entity.getMovie().getId());
        reviewDto.setUserId(entity.getUser().getId());
        reviewDto.setDate(entity.getCreationDate());
        return reviewDto;
    }

    public Review toEntity(ReviewDto reviewDto) {
        Review review = new Review();
        modelMapper.map(reviewDto, review);
        review.setUser(userValidator.getUser(reviewDto.getUserId()));
        review.setMovie(movieValidator.getMovie(reviewDto.getMovieId()));
        return review;
    }

}
