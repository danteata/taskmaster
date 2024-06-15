package com.abc.taskmaster.task;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class TaskLabel {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
}
