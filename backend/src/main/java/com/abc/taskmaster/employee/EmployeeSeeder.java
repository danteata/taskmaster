package com.abc.taskmaster.employee;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
class EmployeeSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(EmployeeSeeder.class);
    @Autowired
    private final ObjectMapper objectMapper;
    @Autowired
    private final EmployeeRepository employeeRepository;

    EmployeeSeeder(ObjectMapper objectMapper, EmployeeRepository employeeRepository) {
        this.objectMapper = objectMapper;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if(employeeRepository.count() == 0){
            String POSTS_JSON = "/data/employees.json";
            log.info("Loading employees into database from JSON: {}", POSTS_JSON);
            try (InputStream inputStream = TypeReference.class.getResourceAsStream(POSTS_JSON)) {
                List<Employee> employees = objectMapper.readValue(inputStream, new TypeReference<List<Employee>>(){});
                employeeRepository.saveAll(employees);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        }
    }

}