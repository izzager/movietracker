package com.example.movietracker.configuration;

import com.example.movietracker.mapperToDto.MovieMapper;
import com.example.movietracker.mapperToDto.ReviewMapper;
import com.example.movietracker.mapperToDto.UserMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieTrackerConfig {

    @Bean
    public ModelMapper defaultModelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.addConverter(new MovieMapper());
        modelMapper.addConverter(new UserMapper());
        modelMapper.addConverter(new ReviewMapper());
        return modelMapper;
    }
}
