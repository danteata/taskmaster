package com.abc.taskmaster.employee;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record EmployeeRegistrationRequest(
        String username,
        String firstName,
        String lastName,
        @NotEmpty
        String email,
        String password,
        List<Role> roles,
        Gender gender
) {
}