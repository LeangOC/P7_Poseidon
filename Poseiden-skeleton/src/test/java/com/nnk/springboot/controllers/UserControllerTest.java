package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.service.UserService;
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
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false) //Cela désactive les filtres Spring Security
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"}) // pour simuler l'authentification
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void list_ShouldReturnListView() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"));
    }

    @Test
    void add_ShouldReturnAddView() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    void update_ShouldReturnUpdateView() throws Exception {
        Mockito.when(userService.findById(1)).thenReturn(Optional.of(new DBUser()));
        mockMvc.perform(get("/user/update/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/update"));
    }


    @Test
    void validate_ShouldRedirectOnSuccess() throws Exception {
        // On simule que l'enregistrement fonctionne
        DBUser savedUser = new DBUser();
        savedUser.setId(1);
        savedUser.setUsername("test");
        savedUser.setFullname("Test User");
        savedUser.setPassword("Password123!");
        savedUser.setRole("ADMIN");

        Mockito.when(userService.save(Mockito.any(DBUser.class))).thenReturn(savedUser);

        mockMvc.perform(post("/user/validate")
                        .param("username", "test")
                        .param("fullname", "Test User")
                        .param("password", "Password123!") // ✅ mot de passe valide
                        .param("role", "ADMIN"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }


    @Test
    void validate_ShouldReturnAddView_WhenValidationFails() throws Exception {
        mockMvc.perform(post("/user/validate"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/add"));
    }

    @Test
    void updateUser_ShouldRedirectOnSuccess() throws Exception {
        // --- Arrange ---
        DBUser existingUser = new DBUser();
        existingUser.setId(1);
        existingUser.setUsername("olduser");
        existingUser.setFullname("Old User");
        existingUser.setPassword("Password123!");
        existingUser.setRole("USER");

        DBUser updatedUser = new DBUser();
        updatedUser.setId(1);
        updatedUser.setUsername("UpdatedUser");
        updatedUser.setFullname("Updated Fullname");
        updatedUser.setPassword("Password123!");
        updatedUser.setRole("ADMIN");

        // Le user existe déjà
        Mockito.when(userService.findById(1)).thenReturn(Optional.of(existingUser));

        // Le service update renvoie le user mis à jour
        Mockito.when(userService.update(Mockito.any(DBUser.class))).thenReturn(updatedUser);

        // --- Act & Assert ---
        mockMvc.perform(post("/user/update/1")
                        .param("username", "UpdatedUser")
                        .param("fullname", "Updated Fullname")
                        .param("password", "Password123!")
                        .param("role", "ADMIN"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    @Test
    void updateUser_ShouldReturnUpdateView_WhenValidationFails() throws Exception {
        DBUser existingUser = new DBUser();
        existingUser.setId(1);
        existingUser.setUsername("olduser");
        existingUser.setFullname("Old User");
        existingUser.setPassword("Password123!");
        existingUser.setRole("USER");

        Mockito.when(userService.findById(1)).thenReturn(Optional.of(existingUser));

        mockMvc.perform(post("/user/update/1")
                        .param("username", "") // Champs vides => échec de validation
                        .param("fullname", "")
                        .param("password", "")
                        .param("role", ""))
                .andExpect(status().isOk()) // On attend la vue "update" (pas de redirection)
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeExists("user"));
    }



    @Test
    void deleteUser_ShouldRedirectAfterDeletion() throws Exception {
        mockMvc.perform(get("/user/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }
}
