package com.java_mentor.pizzacrm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.java_mentor.pizzacrm.repository")
public class JpaConfig {
}
