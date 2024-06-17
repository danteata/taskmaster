package com.abc.taskmaster.auth;

import com.abc.taskmaster.employee.EmployeeRegistrationRequest;
import com.abc.taskmaster.employee.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody EmployeeRegistrationRequest user) {

        AuthenticationResponse response= employeeService.createEmployee(user);
        return ResponseEntity.ok(response);
    }
}
