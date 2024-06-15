package com.abc.taskmaster.task;

import com.abc.taskmaster.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Attachment {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String url;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Employee user;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
