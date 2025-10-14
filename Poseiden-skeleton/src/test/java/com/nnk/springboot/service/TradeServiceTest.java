package com.nnk.springboot.service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private TradeService tradeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnList() {
        when(tradeRepository.findAll()).thenReturn(List.of(new Trade()));

        List<Trade> result = tradeService.findAll();

        assertEquals(1, result.size());
        verify(tradeRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnOptional_WhenExists() {
        Trade trade = new Trade();
        when(tradeRepository.findById(1)).thenReturn(Optional.of(trade));

        Optional<Trade> result = tradeService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(trade, result.get());
        verify(tradeRepository, times(1)).findById(1);
    }

    @Test
    void findById_ShouldReturnEmpty_WhenNotExists() {
        when(tradeRepository.findById(99)).thenReturn(Optional.empty());

        Optional<Trade> result = tradeService.findById(99);

        assertTrue(result.isEmpty());
        verify(tradeRepository, times(1)).findById(99);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);

        Trade saved = tradeService.save(trade);

        assertEquals(trade, saved);
        verify(tradeRepository, times(1)).save(trade);
    }

    @Test
    void deleteById_ShouldCallRepository() {
        tradeService.deleteById(1);
        verify(tradeRepository, times(1)).deleteById(1);
    }

    @Test
    void existsById_ShouldReturnTrue_WhenExists() {
        when(tradeRepository.existsById(1)).thenReturn(true);

        assertTrue(tradeService.existsById(1));
        verify(tradeRepository, times(1)).existsById(1);
    }

    @Test
    void existsById_ShouldReturnFalse_WhenNotExists() {
        when(tradeRepository.existsById(99)).thenReturn(false);

        assertFalse(tradeService.existsById(99));
        verify(tradeRepository, times(1)).existsById(99);
    }
}
