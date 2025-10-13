package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TradeController.class)
@AutoConfigureMockMvc(addFilters = false) //Cela désactive les filtres Spring Security
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"}) // pour simuler l'authentification
class TradeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    @Test
    void list_ShouldReturnListView() throws Exception {
        mockMvc.perform(get("/trade/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/list"));
    }

    @Test
    void add_ShouldReturnAddView() throws Exception {
        mockMvc.perform(get("/trade/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    void update_ShouldReturnUpdateView() throws Exception {
        Mockito.when(tradeService.findById(1)).thenReturn(Optional.of(new Trade()));
        mockMvc.perform(get("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
    }
    @Test
    void validate_ShouldRedirectOnSuccess() throws Exception {
        mockMvc.perform(post("/trade/validate")
                        .param("account", "Test")
                        .param("type", "Type")
                        .param("buyQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

    @Test
    void validate_ShouldReturnAddView_WhenValidationFails() throws Exception {
        mockMvc.perform(post("/trade/validate"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/add"));
    }

    @Test
    void updateTrade_ShouldRedirectOnSuccess() throws Exception {
        Trade existingTrade = new Trade();
        existingTrade.setTradeId(1);
        Mockito.when(tradeService.findById(1)).thenReturn(Optional.of(existingTrade));

        mockMvc.perform(post("/trade/update/1")
                        .param("account", "TestAccount")
                        .param("type", "TypeA")
                        .param("buyQuantity", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }



    @Test
    void updateTrade_ShouldReturnUpdateView_WhenValidationFails() throws Exception {
        mockMvc.perform(post("/trade/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("trade/update"));
    }

    @Test
    void deleteTrade_ShouldRedirectAfterDeletion() throws Exception {
        mockMvc.perform(get("/trade/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/trade/list"));
    }

}
