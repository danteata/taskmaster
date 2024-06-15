package com.abc.taskmaster.employee;

public record EmployeeUpdateRequest(
        String username,
        String firstName,
        String lastName,
        String email
//        String avatarUrl,
) {
}