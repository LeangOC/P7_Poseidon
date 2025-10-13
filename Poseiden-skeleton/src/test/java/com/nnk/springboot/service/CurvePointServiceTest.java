package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointService curvePointService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnList() {
        when(curvePointRepository.findAll()).thenReturn(List.of(new CurvePoint()));
        assertEquals(1, curvePointService.findAll().size());
    }

    @Test
    void findById_ShouldReturnOptional() {
        CurvePoint cp = new CurvePoint();
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(cp));
        assertTrue(curvePointService.findById(1).isPresent());
    }

    @Test
    void save_ShouldCallRepository() {
        CurvePoint cp = new CurvePoint();
        when(curvePointRepository.save(any())).thenReturn(cp);
        assertNotNull(curvePointService.save(cp));
        verify(curvePointRepository, times(1)).save(cp);
    }

    @Test
    void deleteById_ShouldThrow_WhenNotExists() {
        when(curvePointRepository.existsById(1)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> curvePointService.deleteById(1));
    }
}
