package com.abc.taskmaster.auth;

import com.abc.taskmaster.security.JWTProvider;
import com.abc.taskmaster.employee.Employee;
import com.abc.taskmaster.employee.EmployeeDTO;
import com.abc.taskmaster.employee.EmployeeDTOMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final JWTProvider jwtProvider;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 EmployeeDTOMapper employeeDTOMapper,
                                 JWTProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.employeeDTOMapper = employeeDTOMapper;
        this.jwtProvider = jwtProvider;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        Employee principal = (Employee) authentication.getPrincipal();
        EmployeeDTO employeeDTO = employeeDTOMapper.apply(principal);
        String token = jwtProvider.issueToken(employeeDTO.username(), employeeDTO.roles());
        return new AuthenticationResponse(token, employeeDTO);
    }

}