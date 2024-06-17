package com.abc.taskmaster.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Department {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<Employee> members;

}
