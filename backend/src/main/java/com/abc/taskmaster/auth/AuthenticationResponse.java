package com.abc.taskmaster.auth;

import com.abc.taskmaster.employee.EmployeeDTO;

public record AuthenticationResponse (
        String token,
        EmployeeDTO employeeDTO
){
}