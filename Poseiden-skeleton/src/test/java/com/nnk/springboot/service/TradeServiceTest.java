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
        assertEquals(1, tradeService.findAll().size());
    }

    @Test
    void findById_ShouldReturnOptional() {
        when(tradeRepository.findById(1)).thenReturn(Optional.of(new Trade()));
        assertTrue(tradeService.findById(1).isPresent());
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        Trade trade = new Trade();
        when(tradeRepository.save(trade)).thenReturn(trade);
        assertEquals(trade, tradeService.save(trade));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        tradeService.deleteById(1);
        verify(tradeRepository, times(1)).deleteById(1);
    }
}
