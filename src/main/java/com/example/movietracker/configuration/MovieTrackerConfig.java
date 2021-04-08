package com.example.movietracker.configuration;

import com.example.movietracker.converterToDto.MovieConverter;
import com.example.movietracker.converterToDto.ReviewConverter;
import com.example.movietracker.converterToDto.UserConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieTrackerConfig {

    @Bean
    public ModelMapper defaultModelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.addConverter(new MovieConverter());
        modelMapper.addConverter(new UserConverter());
        modelMapper.addConverter(new ReviewConverter());
        return modelMapper;
    }
}
