package com.nnk.springboot.service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RuleNameServiceTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    private RuleNameService ruleNameService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllRuleNames_ShouldReturnList() {
        when(ruleNameRepository.findAll()).thenReturn(List.of(new RuleName()));
        List<RuleName> result = ruleNameService.getAllRuleNames();

        assertEquals(1, result.size());
        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    void getRuleNameById_ShouldReturnOptional_WhenExists() {
        RuleName rule = new RuleName();
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(rule));

        Optional<RuleName> result = ruleNameService.getRuleNameById(1);

        assertTrue(result.isPresent());
        assertEquals(rule, result.get());
        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    void getRuleNameById_ShouldReturnEmpty_WhenNotExists() {
        when(ruleNameRepository.findById(99)).thenReturn(Optional.empty());

        Optional<RuleName> result = ruleNameService.getRuleNameById(99);

        assertTrue(result.isEmpty());
        verify(ruleNameRepository, times(1)).findById(99);
    }

    @Test
    void saveRuleName_ShouldCallRepository() {
        RuleName rule = new RuleName();
        when(ruleNameRepository.save(rule)).thenReturn(rule);

        RuleName saved = ruleNameService.saveRuleName(rule);

        assertEquals(rule, saved);
        verify(ruleNameRepository, times(1)).save(rule);
    }

    @Test
    void deleteRuleName_ShouldCallRepositoryDeleteById() {
        ruleNameService.deleteRuleName(1);
        verify(ruleNameRepository, times(1)).deleteById(1);
    }

    @Test
    void existsById_ShouldReturnTrue() {
        when(ruleNameRepository.existsById(1)).thenReturn(true);
        assertTrue(ruleNameService.existsById(1));
        verify(ruleNameRepository, times(1)).existsById(1);
    }

    @Test
    void existsById_ShouldReturnFalse() {
        when(ruleNameRepository.existsById(99)).thenReturn(false);
        assertFalse(ruleNameService.existsById(99));
        verify(ruleNameRepository, times(1)).existsById(99);
    }
}
