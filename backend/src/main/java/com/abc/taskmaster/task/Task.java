package com.abc.taskmaster.task;

import com.abc.taskmaster.employee.Employee;
import com.abc.taskmaster.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task extends BaseEntity {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Status status;
    private Priority priority;

    @JsonIgnore
    @OneToMany(mappedBy = "task")
    private List<TaskLabel> labels;

    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments;

    @JsonIgnore
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Employee assignee;
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Employee creator;
}
