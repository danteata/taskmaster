package com.abc.taskmaster.employee;

import com.abc.taskmaster.auth.AuthenticationResponse;
import com.abc.taskmaster.security.JWTProvider;
import com.abc.taskmaster.exception.DuplicateResourceException;
import com.abc.taskmaster.exception.ResourceNotFoundException;
import com.abc.taskmaster.service.S3Buckets;
import com.abc.taskmaster.service.S3Service;
import com.abc.taskmaster.util.UploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
//@Data
//@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDTOMapper employeeDTOMapper;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;
    private S3Service s3Service;
    private S3Buckets s3Buckets;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeDTOMapper employeeDTOMapper, PasswordEncoder passwordEncoder, JWTProvider jwtProvider, S3Service s3Service, S3Buckets s3Buckets) {
        this.employeeRepository = employeeRepository;
        this.employeeDTOMapper = employeeDTOMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.s3Service = s3Service;
        this.s3Buckets = s3Buckets;
    }

    public AuthenticationResponse createEmployee(EmployeeRegistrationRequest employeeRegistrationRequest) {
        String email = employeeRegistrationRequest.email();
        if(employeeRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("Email already exists");
        }

        Employee employee = Employee.builder()
                .username(employeeRegistrationRequest.username())
                .firstName(employeeRegistrationRequest.firstName())
                .lastName(employeeRegistrationRequest.lastName())
                .email(employeeRegistrationRequest.email())
                .password(passwordEncoder.encode(employeeRegistrationRequest.password()))
                .gender(employeeRegistrationRequest.gender())
                .roles(employeeRegistrationRequest.roles() != null ? employeeRegistrationRequest.roles() : List.of(Role.USER))
                .build();

        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDTO employeeDTO = employeeDTOMapper.apply(savedEmployee);
        String token =jwtProvider.issueToken(employeeRegistrationRequest.username(), Map.of("roles", employee.getRoles()));
        return new AuthenticationResponse(token,employeeDTO);
    }

    @Override
    public EmployeeDTO getEmployeeById(UUID id) {
          return employeeRepository.findById(id).map(employeeDTOMapper).orElseThrow(() ->
                new ResourceNotFoundException("Student not found with id: [%s]".formatted(id)));
    }

    @Override
    public List<EmployeeDTO> getAllEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.stream().map(employeeDTOMapper).collect(Collectors.toList());
    }

    @Override
    public void updateEmployee(UUID id, EmployeeUpdateRequest employeeUpdateRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: [%s]".formatted(id)));
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(UUID id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Employee not found with id: [%s] ".formatted(id)));
        employeeRepository.delete(employee);
    }

    @Override
    public void uploadAvatar(UUID id, MultipartFile file) {
        if(!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found with id: [%s]".formatted(id));
        }
        String photoUrl = uploadAvatarFunction.apply(id, file);
//        student.setPhotoUrl(photoUrl);
//        employeeRepository.save(student);
        employeeRepository.updateAvatarUrl(id, photoUrl);
    }

    @Override
    public byte[] getAvatar(UUID id) {
        return new byte[0];
    }


//    private final Function<String, String> fileExtension = fileName -> Optional.of(fileName).filter(name -> name.contains(".")).map(name -> "." + name.substring(fileName.lastIndexOf(".") + 1)).orElse(".png");

    private final BiFunction<UUID, MultipartFile, String> uploadAvatarFunction = (id, file) -> {
        String fileName = id + UploadUtils.fileExtension.apply(file.getOriginalFilename());
        try {
            s3Service.putObject(
                    s3Buckets.getAvatar(),
                    "profile-images/%s/%s".formatted(id, fileName),
                    file.getBytes()
            );
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload avatar image", e);
        }
    };
}
