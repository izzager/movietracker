package com.example.movietracker.validator;

import com.example.movietracker.dto.ReviewDto;
import com.example.movietracker.entity.Movie;
import com.example.movietracker.exception.ResourceForbiddenException;
import com.example.movietracker.exception.ResourceNotFoundException;
import com.example.movietracker.repository.MovieRepository;
import com.example.movietracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewDtoValidator implements DtoValidator<ReviewDto> {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    @Override
    public void validate(ReviewDto dto) {
        if (!movieRepository.existsById(dto.getMovieId())) {
            throw new ResourceNotFoundException("Movie not found");
        }
        if (!userRepository.existsById(dto.getUserId())) {
            throw new ResourceNotFoundException("User not found");
        }
        Movie movie = movieRepository.getOne(dto.getMovieId());
        if (!userRepository.getOne(dto.getUserId()).getWatched()
                .contains(movie)) {
            throw new ResourceForbiddenException("You haven't watched this movie");
        }
        if (movie.getReviews()
                .stream()
                .anyMatch(review
                        -> review.getUser().getId().equals(dto.getUserId()))) {
            throw new ResourceForbiddenException("You have already reviewed this movie");
        }
    }

}
