package com.abc.taskmaster.employee;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public record EmployeeDTO(
     UUID id,
     String username,
     String firstName,
     String lastName,
     @NotEmpty
     String email,
     List<Role> roles,
     String avatarUrl,
     Gender gender
) {
}
