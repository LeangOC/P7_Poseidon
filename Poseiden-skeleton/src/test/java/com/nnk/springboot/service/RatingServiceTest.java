package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnList() {
        when(ratingRepository.findAll()).thenReturn(List.of(new Rating()));
        assertEquals(1, ratingService.findAll().size());
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        Rating rating = new Rating();
        when(ratingRepository.save(rating)).thenReturn(rating);
        assertEquals(rating, ratingService.save(rating));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        ratingService.deleteById(1);
        verify(ratingRepository, times(1)).deleteById(1);
    }
}
