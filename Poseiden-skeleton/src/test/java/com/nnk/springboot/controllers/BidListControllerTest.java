package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.ui.ExtendedModelMap;


import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BidListController.class)
@AutoConfigureMockMvc(addFilters = false) //Cela désactive les filtres Spring Security
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"}) // pour simuler l'authentification
class BidListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void home_ShouldReturnListView() throws Exception {
        Mockito.when(bidListService.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/list"));
    }

    @Test
    void addForm_ShouldReturnAddView() throws Exception {
        mockMvc.perform(get("/bidList/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    void validate_ShouldRedirectOnSuccess() throws Exception {
        mockMvc.perform(post("/bidList/validate")
                        .param("account", "Test")
                        .param("type", "Type")
                        .param("bidQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void update_ShouldReturnUpdateView() throws Exception {
        Mockito.when(bidListService.findById(1)).thenReturn(Optional.of(new BidList()));
        mockMvc.perform(get("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }
    @Test
    void validate_ShouldReturnAddView_WhenValidationFails() throws Exception {
        mockMvc.perform(post("/bidList/validate"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/add"));
    }

    @Test
    void updateBid_ShouldRedirectOnSuccess() throws Exception {
        Mockito.when(bidListService.findById(1)).thenReturn(Optional.of(new BidList()));
        mockMvc.perform(post("/bidList/update/1")
                        .param("account", "Test")
                        .param("type", "Type")
                        .param("bidQuantity", "10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void updateBid_ShouldReturnUpdateView_WhenValidationFails() throws Exception {
        mockMvc.perform(post("/bidList/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("bidList/update"));
    }

    @Test
    void deleteBid_ShouldRedirectAfterDeletion() throws Exception {
        mockMvc.perform(get("/bidList/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/bidList/list"));
    }

    @Test
    void showUpdateForm_ShouldThrowException_WhenIdInvalid_DirectCall() {
        BidListController controller = new BidListController();
        ReflectionTestUtils.setField(controller, "bidListService", bidListService);

        Mockito.when(bidListService.findById(999)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            controller.showUpdateForm(999, new ExtendedModelMap());
        });
    }


}
