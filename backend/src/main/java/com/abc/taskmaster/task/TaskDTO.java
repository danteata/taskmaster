package com.abc.taskmaster.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private UUID id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Status status;
    private Priority priority;
}
