package com.abc.taskmaster.task;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record TaskRequest(
        @NotBlank
     String title,
     String description,
     @Future(message = "Due date must be in the future")
     LocalDate dueDate,
     Status status,
     Priority priority
) {
}
