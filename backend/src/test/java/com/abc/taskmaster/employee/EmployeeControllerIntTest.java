package com.abc.taskmaster.employee;

import com.abc.taskmaster.auth.AuthenticationRequest;
import com.abc.taskmaster.auth.AuthenticationResponse;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

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


    private static final Random RANDOM = new Random();
    private static final String EMPLOYEES_PATH = "/api/v1/employees";
    private static final String EMPLOYEE_REGISTER_PATH = "/api/v1/auth/signup";

    @Test
    void canStartPostgresDB() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
        assertThat(postgreSQLContainer.isCreated()).isTrue();
    }

    @Test
    void shouldCreateEmployee() {
        // Given
        // create registration EmployeeRegistrationRequest
        Faker faker = new Faker();
        Name fakerName = faker.name();
        String username = String.valueOf(faker.funnyName());

        String name = fakerName.fullName();
        String lastName = fakerName.lastName();
        String firstName = fakerName.firstName();
        String email = fakerName.lastName() + "-" + UUID.randomUUID() + "@abccorp.com";
        Gender gender = RANDOM.nextInt(1, 100) % 2 == 0 ? Gender.MALE : Gender.FEMALE;
        String password = "password";

        EmployeeRegistrationRequest employeeRegistrationRequest = new EmployeeRegistrationRequest(
                username, firstName, lastName, email, password, null, gender
        );

        // send a post userRegistrationRequest
        AuthenticationResponse response = webTestClient.post()
                .uri(EMPLOYEE_REGISTER_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Mono.just(employeeRegistrationRequest),
                        EmployeeRegistrationRequest.class
                )
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<AuthenticationResponse>() {
                })
                .returnResult()
                .getResponseBody();

        String jwtToken = response.token();
        EmployeeDTO registeredEmployee = response.user();
        assertThat(registeredEmployee.email()).isEqualTo(email);

        // get all employees
        List<EmployeeDTO> allEmployees = webTestClient.get()
                .uri(EMPLOYEES_PATH)
                .accept(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, String.format("Bearer %s", jwtToken))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<EmployeeDTO>() {
                })
                .returnResult()
                .getResponseBody();

        List <String> emails = allEmployees.stream().map(EmployeeDTO::email).toList();
        assertThat(registeredEmployee.email()).isIn(emails);
        assertThat(allEmployees.size()).isEqualTo(10);
        assertThat(allEmployees.get(0).email()).isEqualTo(email);
        UUID id = allEmployees.stream()
                .filter(employee -> employee.email().equals(email))
                .map(EmployeeDTO::id)
                .findFirst()
                .orElseThrow();

        // make sure that employee is present
        EmployeeDTO expectedEmployee = new EmployeeDTO(
                id,
                username,
                firstName,
                lastName,
                email,
                List.of(Role.USER),
                null,
                gender
        );

        assertThat(expectedEmployee).isIn(allEmployees);
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