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
        assertEquals(1, ruleNameService.getAllRuleNames().size());
    }

    @Test
    void saveRuleName_ShouldCallRepository() {
        RuleName rule = new RuleName();
        when(ruleNameRepository.save(rule)).thenReturn(rule);
        assertEquals(rule, ruleNameService.saveRuleName(rule));
    }

    @Test
    void existsById_ShouldReturnTrue() {
        when(ruleNameRepository.existsById(1)).thenReturn(true);
        assertTrue(ruleNameService.existsById(1));
    }
}
