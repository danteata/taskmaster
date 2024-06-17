package com.abc.taskmaster.employee;

import com.abc.taskmaster.auth.AuthenticationResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    AuthenticationResponse createEmployee(EmployeeRegistrationRequest employeeRegistrationRequest);

    EmployeeDTO getEmployeeById(UUID id);

    List<EmployeeDTO> getAllEmployees(Pageable pageable);

    void updateEmployee(UUID id, EmployeeUpdateRequest employeeUpdateRequest);

    void deleteEmployee(UUID id);

    void uploadAvatar(UUID id, MultipartFile avatar);

    byte[] getAvatar(UUID id);
}
