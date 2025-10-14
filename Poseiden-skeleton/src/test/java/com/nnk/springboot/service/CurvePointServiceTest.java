package com.nnk.springboot.service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

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

        List<CurvePoint> result = curvePointService.findAll();

        assertEquals(1, result.size());
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnOptional() {
        CurvePoint cp = new CurvePoint();
        cp.setId(1);
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(cp));

        Optional<CurvePoint> result = curvePointService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getId());
        verify(curvePointRepository).findById(1);
    }

    @Test
    void save_ShouldSetCreationDateAndCallRepository() {
        CurvePoint cp = new CurvePoint();
        when(curvePointRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        CurvePoint result = curvePointService.save(cp);

        assertNotNull(result.getCreationDate(), "Creation date should be set before saving");
        verify(curvePointRepository, times(1)).save(cp);
    }

    @Test
    void update_ShouldUpdateExistingCurvePoint() {
        // Arrange
        CurvePoint existing = new CurvePoint();
        existing.setId(1);
        existing.setCurveId(10);
        existing.setTerm(1.0);
        existing.setValue(5.0);
        existing.setCreationDate(Timestamp.from(Instant.now().minusSeconds(1000)));

        CurvePoint updated = new CurvePoint();
        updated.setCurveId(20);
        updated.setTerm(2.0);
        updated.setValue(10.0);
        updated.setAsOfDate(Timestamp.from(Instant.now()));

        when(curvePointRepository.findById(1)).thenReturn(Optional.of(existing));
        when(curvePointRepository.save(any(CurvePoint.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        CurvePoint result = curvePointService.update(1, updated);

        // Assert
        assertEquals(20, result.getCurveId());
        assertEquals(2.0, result.getTerm());
        assertEquals(10.0, result.getValue());
        assertNotNull(result.getCreationDate());
        verify(curvePointRepository).findById(1);
        verify(curvePointRepository).save(existing);
    }

    @Test
    void update_ShouldThrowException_WhenNotFound() {
        CurvePoint updated = new CurvePoint();
        when(curvePointRepository.findById(99)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> curvePointService.update(99, updated));

        assertEquals("CurvePoint with ID 99 not found", ex.getMessage());
        verify(curvePointRepository).findById(99);
        verify(curvePointRepository, never()).save(any());
    }

    @Test
    void deleteById_ShouldDelete_WhenExists() {
        when(curvePointRepository.existsById(1)).thenReturn(true);

        curvePointService.deleteById(1);

        verify(curvePointRepository).deleteById(1);
    }

    @Test
    void deleteById_ShouldThrow_WhenNotExists() {
        when(curvePointRepository.existsById(1)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> curvePointService.deleteById(1));

        verify(curvePointRepository, never()).deleteById(anyInt());
    }
}
