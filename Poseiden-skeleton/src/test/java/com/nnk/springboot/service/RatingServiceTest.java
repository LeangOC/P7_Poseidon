package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

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
        List<Rating> result = ratingService.findAll();

        assertEquals(1, result.size());
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnOptional_WhenEntityExists() {
        Rating rating = new Rating();
        when(ratingRepository.findById(1)).thenReturn(Optional.of(rating));

        Optional<Rating> result = ratingService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(rating, result.get());
        verify(ratingRepository, times(1)).findById(1);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenEntityDoesNotExist() {
        when(ratingRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Rating> result = ratingService.findById(99);

        assertTrue(result.isEmpty());
        verify(ratingRepository, times(1)).findById(99);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        Rating rating = new Rating();
        when(ratingRepository.save(rating)).thenReturn(rating);

        Rating result = ratingService.save(rating);

        assertEquals(rating, result);
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    void deleteById_ShouldCallRepository() {
        ratingService.deleteById(1);
        verify(ratingRepository, times(1)).deleteById(1);
    }
}
