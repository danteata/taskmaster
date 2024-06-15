package com.abc.taskmaster.employee;

import com.abc.taskmaster.security.JWTProvider;
import com.abc.taskmaster.service.S3Buckets;
import com.abc.taskmaster.service.S3Service;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeDTOMapper employeeDTOMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JWTProvider jwtProvider;
    @Mock
    private S3Service s3Service;
    @Mock
    private S3Buckets s3Buckets;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public void createEmployee() {
        //Given
        var employeeRegistrationRequest = new EmployeeRegistrationRequest(
                "username",
                "firstName",
                "lastName",
                "email",
                "password",
                List.of(Role.USER),
                Gender.MALE
        );

        var employee = Employee.builder()
                .username(employeeRegistrationRequest.username())
                .firstName(employeeRegistrationRequest.firstName())
                .lastName(employeeRegistrationRequest.lastName())
                .email(employeeRegistrationRequest.email())
                .password(employeeRegistrationRequest.password())
                .build();

        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employee);

        //When
        employeeService.createEmployee(employeeRegistrationRequest);

        //When


        //Then

    }

    public void updateEmployee() {

    }

    public void deleteEmployee() {

    }

    public void uploadAvatar() {

    }

    public void getAvatar() {
    }
}