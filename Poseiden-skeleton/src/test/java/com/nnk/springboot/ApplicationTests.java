package com.nnk.springboot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Test de la classe Application.
 * Vérifie que le contexte Spring Boot démarre correctement.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplicationTests {

    /**
     * Vérifie que le contexte Spring se charge sans exception.
     * Cela valide que toutes les configurations Spring Boot sont cohérentes.
     */
    @Test
    void contextLoads() {
        // Si le contexte ne peut pas se charger, ce test échouera automatiquement
    }

    /**
     * Vérifie que la méthode main() exécute correctement le démarrage de l'application.
     * On ne cherche pas à vérifier la logique interne de SpringApplication.run(),
     * mais seulement que la méthode est bien appelable sans exception.
     */
    @Test
    void main_ShouldStartApplicationWithoutException() {
        assertDoesNotThrow(() -> Application.main(new String[] {}));
    }
}
