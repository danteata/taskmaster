package com.abc.taskmaster.employee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest");

    @Autowired
    EmployeeRepository underTest;

    @Test
    void shouldStartPostgresDB() {
        assertThat(postgreSQLContainer.isRunning()).isTrue();
        assertThat(postgreSQLContainer.isCreated()).isTrue();
    }

//    @BeforeEach
//    void setUp() {
//        underTest.deleteAll();
//        List<Employee> employees = List.of(
//                new Employee("test", "test", "test", "test", "test", Gender.MALE),
//                new Employee("test", "test", "test", "test", "test", Gender.MALE),
//                new Employee("test", "test", "test", "test", "test", Gender.MALE)
//        );
//    }

    @Test
    void shouldUpdateAvatarUrl() {
        // given
        Employee employee = new Employee("userwithoutavatar", "test", "test", "userwithoutavatar@abccorp.com", "test", Gender.MALE);
        Employee savedEmployee = underTest.save(employee);

        // when
        underTest.updateAvatarUrl(savedEmployee.getId(), "example-avatar-url.png");

        // then
        assertThat(underTest.findByEmail(employee.getEmail()).get().getAvatarUrl()).isEqualTo("example-avatar-url.png");
    }

    @Test
    void shouldReturnTrueWhenEmailExists() {
        // given
        Employee employee = new Employee("userwithoutavatar", "test", "test", "userwithoutavatar@abccorp.com", "test", Gender.MALE);
        Employee savedEmployee = underTest.save(employee);

        // then
        assertThat(underTest.existsByEmail(savedEmployee.getEmail())).isTrue();
    }
}