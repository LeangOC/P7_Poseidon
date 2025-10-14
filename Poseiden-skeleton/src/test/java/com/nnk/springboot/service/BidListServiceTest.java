package com.nnk.springboot.service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

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
        List<BidList> result = bidListService.findAll();

        assertEquals(1, result.size());
        verify(bidListRepository, times(1)).findAll();
    }

    @Test
    void findById_ShouldReturnOptional() {
        BidList bid = new BidList();
        bid.setBidListId(1);
        when(bidListRepository.findById(1)).thenReturn(Optional.of(bid));

        Optional<BidList> result = bidListService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getBidListId());
        verify(bidListRepository, times(1)).findById(1);
    }

    @Test
    void findById_ShouldReturnEmptyOptional_WhenNotFound() {
        when(bidListRepository.findById(99)).thenReturn(Optional.empty());

        Optional<BidList> result = bidListService.findById(99);

        assertTrue(result.isEmpty());
        verify(bidListRepository).findById(99);
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        BidList bid = new BidList();
        when(bidListRepository.save(bid)).thenReturn(bid);

        BidList result = bidListService.save(bid);

        assertEquals(bid, result);
        verify(bidListRepository).save(bid);
    }

    @Test
    void update_ShouldUpdateAndReturnSavedEntity() {
        // Arrange : BidList existant et mis à jour
        BidList existing = new BidList();
        existing.setBidListId(1);
        existing.setAccount("OldAccount");
        existing.setType("OldType");

        BidList updated = new BidList();
        updated.setBidListId(1);
        updated.setAccount("NewAccount");
        updated.setType("NewType");

        when(bidListRepository.findById(1)).thenReturn(Optional.of(existing));
        when(bidListRepository.save(any(BidList.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        BidList result = bidListService.update(updated);

        // Assert
        assertEquals("NewAccount", result.getAccount());
        assertEquals("NewType", result.getType());
        verify(bidListRepository).findById(1);
        verify(bidListRepository).save(existing);
    }

    @Test
    void update_ShouldThrowException_WhenNotFound() {
        BidList updated = new BidList();
        updated.setBidListId(999);

        when(bidListRepository.findById(999)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> bidListService.update(updated));

        assertEquals("BidList not found with id: 999", exception.getMessage());
        verify(bidListRepository).findById(999);
        verify(bidListRepository, never()).save(any());
    }

    @Test
    void deleteById_ShouldCallRepository() {
        bidListService.deleteById(1);
        verify(bidListRepository, times(1)).deleteById(1);
    }
}
