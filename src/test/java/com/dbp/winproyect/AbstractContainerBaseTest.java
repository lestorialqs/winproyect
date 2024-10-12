package com.dbp.winproyect;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertySource;

@DataJpaTest
@Testcontainers
public abstract class AbstractContainerBaseTest {
    private static final PostgreSQLContainer<?> postgresqlContainer;
    static {
        postgresqlContainer = new PostgreSQLContainer<>("postgres:latest")
                .withDatabaseName("e2eTestDb")
                .withUsername("e2e")
                .withPassword("e2e");
        postgresqlContainer.start();
    }
    @DynamicPropertySource
    static void overrideTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }
}
© 2024 Departamento Ciencia de Computación - Universidad de Ingeniería y Tecnología 11

