package com.abc.taskmaster.employee;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class EmployeeDTOMapper implements Function<Employee, EmployeeDTO> {

    @Override
    public EmployeeDTO apply(Employee employee) {
        if(employee == null) {
            throw new NullPointerException("Employee cannot be null");
        }

        return new EmployeeDTO(
                employee.getId(),
                employee.getUsername(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getRoles(),
                employee.getAvatarUrl(),
                employee.getGender()
        );
    }
}
