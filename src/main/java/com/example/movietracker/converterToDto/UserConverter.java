package com.example.movietracker.converterToDto;

import com.example.movietracker.dto.UserDto;
import com.example.movietracker.entity.User;
import lombok.NoArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserConverter implements Converter<User, UserDto> {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto convert(MappingContext<User, UserDto> mappingContext) {
        User entity = mappingContext.getSource();
        UserDto userDto = mappingContext.getDestination();
        if (userDto == null) {
            userDto = new UserDto();
        }
        modelMapper.map(entity, userDto);
        return userDto;
    }
}
