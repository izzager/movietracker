package com.example.movietracker.mapper;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovieMapper implements Mapper<Movie, MovieDto> {

    private final ReviewMapper reviewMapper;

    @Override
    public MovieDto toDto(Movie entity) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(entity.getId());
        movieDto.setName(entity.getName());
        movieDto.setYear(entity.getYear());
        movieDto.setLink(entity.getLink());
        movieDto.setReviews(entity.getReview()
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList()));
        return movieDto;
    }

}
