package com.nnk.springboot.service;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.repositories.DBUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomUserDetailsServiceTest {

    @Mock
    private DBUserRepository dbUserRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_ShouldReturnUserDetails_WhenUserExists() {
        // Arrange
        DBUser dbUser = new DBUser();
        dbUser.setUsername("admin");
        dbUser.setPassword("pass");
        dbUser.setRole("ADMIN");
        when(dbUserRepository.findByUsername("admin")).thenReturn(dbUser);

        // Act
        UserDetails details = customUserDetailsService.loadUserByUsername("admin");

        // Assert
        assertEquals("admin", details.getUsername());
        assertEquals("pass", details.getPassword());
        assertTrue(details.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
        verify(dbUserRepository, times(1)).findByUsername("admin");
    }

    @Test
    void loadUserByUsername_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        when(dbUserRepository.findByUsername("ghost")).thenReturn(null);

        // Act + Assert
        assertThrows(UsernameNotFoundException.class, () ->
                customUserDetailsService.loadUserByUsername("ghost"));
        verify(dbUserRepository, times(1)).findByUsername("ghost");
    }

    @Test
    void getGrantedAuthorities_ShouldReturnRoleList() throws Exception {
        // 🧠 On teste la méthode privée via réflexion pour 100% coverage
        var method = CustomUserDetailsService.class.getDeclaredMethod("getGrantedAuthorities", String.class);
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        List<GrantedAuthority> authorities =
                (List<GrantedAuthority>) method.invoke(customUserDetailsService, "USER");

        assertEquals(1, authorities.size());
        assertEquals("ROLE_USER", authorities.get(0).getAuthority());
    }
}
