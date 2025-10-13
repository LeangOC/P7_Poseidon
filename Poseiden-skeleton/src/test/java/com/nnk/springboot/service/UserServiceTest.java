package com.nnk.springboot.service;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.repositories.DBUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private DBUserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldEncodePassword_AndSaveUser() {
        DBUser user = new DBUser();
        user.setPassword("rawPassword");

        when(passwordEncoder.encode("rawPassword")).thenReturn("encodedPassword");
        when(userRepository.save(any(DBUser.class))).thenAnswer(inv -> inv.getArgument(0));

        DBUser saved = userService.save(user);

        assertEquals("encodedPassword", saved.getPassword());
        verify(passwordEncoder).encode("rawPassword");
        verify(userRepository).save(any(DBUser.class));
    }

    @Test
    void findById_ShouldReturnUser() {
        DBUser user = new DBUser();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        assertTrue(userService.findById(1).isPresent());
    }

    @Test
    void deleteById_ShouldCallRepository() {
        userService.deleteById(1);
        verify(userRepository).deleteById(1);
    }

    @Test
    void update_ShouldEncodePasswordIfChanged() {
        DBUser existing = new DBUser();
        existing.setId(1);
        existing.setPassword("old");

        DBUser updated = new DBUser();
        updated.setId(1);
        updated.setPassword("newPass");
        updated.setFullname("John Doe");
        updated.setUsername("john");
        updated.setRole("USER");

        when(userRepository.findById(1)).thenReturn(Optional.of(existing));
        when(passwordEncoder.encode("newPass")).thenReturn("encoded");
        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        DBUser result = userService.update(updated);
        assertEquals("encoded", result.getPassword());
    }
}
