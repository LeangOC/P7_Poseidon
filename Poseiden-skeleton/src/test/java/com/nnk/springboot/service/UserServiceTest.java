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

    // --- 🔹 TEST SAVE ---
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

    // --- 🔹 TEST FIND ALL ---
    @Test
    void findAll_ShouldReturnUserList() {
        when(userRepository.findAll()).thenReturn(List.of(new DBUser(), new DBUser()));

        List<DBUser> result = userService.findAll();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    // --- 🔹 TEST FIND BY ID ---
    @Test
    void findById_ShouldReturnUser_WhenExists() {
        DBUser user = new DBUser();
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        Optional<DBUser> result = userService.findById(1);

        assertTrue(result.isPresent());
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotExists() {
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        Optional<DBUser> result = userService.findById(99);

        assertTrue(result.isEmpty());
        verify(userRepository, times(1)).findById(99);
    }

    // --- 🔹 TEST UPDATE ---
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
        assertEquals("John Doe", result.getFullname());
        assertEquals("john", result.getUsername());
        verify(passwordEncoder, times(1)).encode("newPass");
        verify(userRepository, times(1)).save(any(DBUser.class));
    }

    @Test
    void update_ShouldKeepOldPassword_WhenNewPasswordIsBlank() {
        DBUser existing = new DBUser();
        existing.setId(1);
        existing.setPassword("oldPassword");

        DBUser updated = new DBUser();
        updated.setId(1);
        updated.setPassword("  "); // blank
        updated.setFullname("Jane");
        updated.setUsername("jane");
        updated.setRole("ADMIN");

        when(userRepository.findById(1)).thenReturn(Optional.of(existing));
        when(userRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        DBUser result = userService.update(updated);

        assertEquals("oldPassword", result.getPassword());
        assertEquals("Jane", result.getFullname());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, times(1)).save(existing);
    }

    @Test
    void update_ShouldThrowException_WhenUserNotFound() {
        DBUser updated = new DBUser();
        updated.setId(99);
        updated.setPassword("pwd");

        when(userRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.update(updated));
        verify(userRepository, times(1)).findById(99);
        verify(userRepository, never()).save(any());
    }

    // --- 🔹 TEST DELETE ---
    @Test
    void deleteById_ShouldCallRepository() {
        userService.deleteById(1);
        verify(userRepository).deleteById(1);
    }
}
