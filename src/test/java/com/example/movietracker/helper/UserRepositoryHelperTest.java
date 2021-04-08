package com.example.movietracker.helper;

import com.example.movietracker.entity.User;
import com.example.movietracker.exception.ResourceNotFoundException;
import com.example.movietracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.movietracker.TestConstants.USER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryHelperTest {

    @InjectMocks
    public UserRepositoryHelper userRepositoryHelper;

    @Mock
    public UserRepository userRepository;

    @Test
    public void ensureMovieExists_true() {
        User user = new User();
        user.setId(USER_ID);

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        User result = userRepositoryHelper.ensureUserExists(USER_ID);

        assertEquals(result.getId(), USER_ID);
        verify(userRepository).findById(USER_ID);
    }

    @Test
    public void ensureMovieExists_false() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> userRepositoryHelper.ensureUserExists(USER_ID));
    }

}