package com.abc.taskmaster.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDTOMapperTest {

    private EmployeeDTOMapper employeeDTOMapper;

    @BeforeEach
    public void setUp() {
        employeeDTOMapper = new EmployeeDTOMapper();
    }

    @Test
    public void shouldMapEmployeeToEmployeeDTO() {
        Employee employee = new Employee("username", "firstName", "lastName", "email", "password", Gender.MALE);
        EmployeeDTO employeeDTO = employeeDTOMapper.apply(employee);
        assertEquals(employee.getUsername(), employeeDTO.username());
        assertEquals(employee.getFirstName(), employeeDTO.firstName());
        assertEquals(employee.getLastName(), employeeDTO.lastName());
        assertEquals(employee.getEmail(), employeeDTO.email());
        assertEquals(employee.getRoles(), employeeDTO.roles());
        assertEquals(employee.getAvatarUrl(), employeeDTO.avatarUrl());
        assertEquals(employee.getGender(), employeeDTO.gender());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenEmployeeIsNull() {
        var exception =assertThrows(NullPointerException.class, () -> employeeDTOMapper.apply(null));
        assertEquals("Employee cannot be null", exception.getMessage());
    }

}