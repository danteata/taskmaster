package com.abc.taskmaster.employee;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from here!");
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeRegistrationRequest employeeRegistrationRequest) {
        employeeService.createEmployee(employeeRegistrationRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable("id") UUID id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);

        return ResponseEntity.ok(employee);

    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page = 0, size = 10, Sort.by("lastName").descending());
        List<EmployeeDTO> employees = employeeService.getAllEmployees(pageable);

        return ResponseEntity.ok(employees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        employeeService.updateEmployee(id, employeeUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable("id") UUID id, @RequestParam("avatar") MultipartFile avatar) {
        employeeService.uploadAvatar(id, avatar);
        return ResponseEntity.ok("Avatar uploaded successfully");
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity<byte[]> getAvatar(@PathVariable("id") UUID id) {
        byte[] avatar = employeeService.getAvatar(id);
        return ResponseEntity.ok(avatar);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.badRequest().body(errors);
    }
}
