package com.example.movietracker.mapper;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovieMapper implements Mapper<Movie, MovieDto> {

    private final ReviewMapper reviewMapper;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public MovieDto toDto(Movie entity) {
        MovieDto movieDto = new MovieDto();
        modelMapper.map(entity, movieDto);
        movieDto.setReviews(entity.getReview()
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList()));
        return movieDto;
    }

}
