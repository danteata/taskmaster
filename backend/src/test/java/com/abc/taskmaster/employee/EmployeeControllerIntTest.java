package com.abc.taskmaster.employee;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class EmployeeControllerIntTest {

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest");


    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WebTestClient webTestClient;


    private static final String EMPLOYEES_PATH = "/api/v1/employees";

    @Test
    void canStartPostgresDB() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
        assertThat(postgreSQLContainer.isCreated()).isTrue();
    }

    @Test
    void shouldGetAllEmployees() {
        webTestClient.get()
                .uri(EMPLOYEES_PATH)
                .exchange()
                .expectStatus().isOk();


    }

    @Test
    void shouldUpdateEmployee() {
        // given
        Employee existingEmployee = employeeRepository.save( new Employee("test", "test", "test", "existinguser@abccorp.com", "test", Gender.MALE));
        EmployeeUpdateRequest employeeUpdateRequest = new EmployeeUpdateRequest("test", "test", "newLastName", "existinguser@abccorp.com");

        // when
        webTestClient.patch()
                .uri(EMPLOYEES_PATH + "/" + existingEmployee.getId())
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Mono.just(employeeUpdateRequest),
                        EmployeeUpdateRequest.class
                )
                .exchange()
                .expectStatus()
                .isOk();
    }
}