package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @InjectMocks
    private BidListService bidListService;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll_ShouldReturnList() {
        when(bidListRepository.findAll()).thenReturn(List.of(new BidList()));
        assertEquals(1, bidListService.findAll().size());
    }

    @Test
    void findById_ShouldReturnOptional() {
        BidList bid = new BidList();
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));
        assertTrue(bidListService.findById(1).isPresent());
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        BidList bid = new BidList();
        when(bidListRepository.save(bid)).thenReturn(bid);
        assertEquals(bid, bidListService.save(bid));
    }

    @Test
    void deleteById_ShouldCallRepository() {
        bidListService.deleteById(1);
        verify(bidListRepository, times(1)).deleteById(1);
    }
}
