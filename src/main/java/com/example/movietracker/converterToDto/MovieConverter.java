package com.example.movietracker.converterToDto;

import com.example.movietracker.dto.MovieDto;
import com.example.movietracker.entity.Movie;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class MovieConverter implements Converter<Movie, MovieDto> {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public MovieDto convert(MappingContext<Movie, MovieDto> mappingContext) {
        Movie entity = mappingContext.getSource();
        MovieDto movieDto = mappingContext.getDestination();
        if (movieDto == null) {
            movieDto = new MovieDto();
        }
        modelMapper.map(entity, movieDto);
        return movieDto;
    }
}
