package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RuleNameController.class)
@AutoConfigureMockMvc(addFilters = false) //Cela désactive les filtres Spring Security
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"}) // pour simuler l'authentification
class RuleNameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleNameService ruleNameService;

    @Test
    void home_ShouldReturnListView() throws Exception {
        mockMvc.perform(get("/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/list"));
    }

    @Test
    void update_ShouldReturnUpdateView() throws Exception {
        Mockito.when(ruleNameService.getRuleNameById(1)).thenReturn(Optional.of(new RuleName()));
        mockMvc.perform(get("/ruleName/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("ruleName/update"));
    }

    @Test
    void validate_ShouldRedirectOnSuccess() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .param("name", "Rule1")
                        .param("description", "desc"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void validate_ShouldRedirectOnSuccess_WhenNoValidationErrors() throws Exception {
        mockMvc.perform(post("/ruleName/validate")
                        .param("name", "RuleTest")
                        .param("description", "Desc")
                        .param("json", "testJson")
                        .param("template", "temp")
                        .param("sqlStr", "sql")
                        .param("sqlPart", "part"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }


    @Test
    void updateRuleName_ShouldRedirectOnSuccess() throws Exception {
        Mockito.when(ruleNameService.getRuleNameById(1)).thenReturn(Optional.of(new RuleName()));
        mockMvc.perform(post("/ruleName/update/1")
                        .param("name", "RuleUpdated"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }

    @Test
    void updateRuleName_ShouldRedirectOnSuccess_WhenNoValidationErrors() throws Exception {
        Mockito.when(ruleNameService.getRuleNameById(1)).thenReturn(Optional.of(new RuleName()));

        mockMvc.perform(post("/ruleName/update/1")
                        .param("name", "RuleUpdated")
                        .param("description", "Desc updated")
                        .param("json", "jsonData")
                        .param("template", "templ")
                        .param("sqlStr", "sql")
                        .param("sqlPart", "part"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }


    @Test
    void deleteRuleName_ShouldRedirectAfterDeletion() throws Exception {
        Mockito.when(ruleNameService.getRuleNameById(1)).thenReturn(Optional.of(new RuleName()));
        mockMvc.perform(get("/ruleName/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/ruleName/list"));
    }


}
