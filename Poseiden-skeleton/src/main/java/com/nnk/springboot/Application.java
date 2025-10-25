package com.nnk.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principale de l’application Spring Boot.
 * <p>
 * Elle sert de point d’entrée à l’application et initialise le
 * contexte Spring Boot, en configurant automatiquement
 * les composants, services et dépendances du projet.
 * </p>
 *
 * <p>
 * L’annotation {@link SpringBootApplication} regroupe plusieurs fonctionnalités :
 * <ul>
 *   <li>{@code @Configuration} – indique que la classe contient des définitions de beans Spring,</li>
 *   <li>{@code @EnableAutoConfiguration} – active la configuration automatique de Spring Boot,</li>
 *   <li>{@code @ComponentScan} – active la détection automatique des composants dans le package courant.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Pour démarrer l’application, exécuter la méthode {@link #main(String[])}.
 * </p>
 *
 * @author [Ton nom]
 * @version 1.0
 * @since 1.0
 */
@SpringBootApplication
public class Application {

	/**
	 * Point d’entrée principal de l’application Spring Boot.
	 *
	 * @param args arguments passés en ligne de commande
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
