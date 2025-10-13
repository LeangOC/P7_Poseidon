package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
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
@WebMvcTest(CurveController.class)
@AutoConfigureMockMvc(addFilters = false) //Cela désactive les filtres Spring Security
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"}) // pour simuler l'authentification
class CurveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService curvePointService;

    @Test
    void list_ShouldReturnListView() throws Exception {
        mockMvc.perform(get("/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/list"));
    }

    @Test
    void addForm_ShouldReturnAddView() throws Exception {
        mockMvc.perform(get("/curvePoint/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/add"));
    }

    @Test
    void updateForm_ShouldReturnUpdateView() throws Exception {
        Mockito.when(curvePointService.findById(1)).thenReturn(Optional.of(new CurvePoint()));
        mockMvc.perform(get("/curvePoint/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("curvePoint/update"));
    }
    @Test
    void validate_ShouldRedirectOnSuccess() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "1")
                        .param("term", "10")
                        .param("value", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void validate_ShouldRedirectOnSuccess_WhenNoValidationErrors() throws Exception {
        mockMvc.perform(post("/curvePoint/validate")
                        .param("curveId", "1")
                        .param("term", "10")
                        .param("value", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }


    @Test
    void updateBid_ShouldRedirectOnSuccess() throws Exception {
        Mockito.when(curvePointService.findById(1)).thenReturn(Optional.of(new CurvePoint()));
        mockMvc.perform(post("/curvePoint/update/1")
                        .param("curveId", "1")
                        .param("term", "10")
                        .param("value", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

    @Test
    void updateBid_ShouldRedirectOnSuccess_WhenNoValidationErrors() throws Exception {
        Mockito.when(curvePointService.findById(1)).thenReturn(Optional.of(new CurvePoint()));
        mockMvc.perform(post("/curvePoint/update/1")
                        .param("curveId", "1")
                        .param("term", "10")
                        .param("value", "20"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }
    @Test
    void deleteBid_ShouldRedirectAfterDeletion() throws Exception {
        mockMvc.perform(get("/curvePoint/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/curvePoint/list"));
    }

}
