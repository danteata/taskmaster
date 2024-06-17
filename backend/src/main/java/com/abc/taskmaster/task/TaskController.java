package com.abc.taskmaster.task;

import com.abc.taskmaster.employee.EmployeeDTO;
import com.abc.taskmaster.employee.EmployeeRegistrationRequest;
import com.abc.taskmaster.employee.EmployeeUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        taskService.createTask(taskRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getEmployee(@PathVariable("id") UUID id) {
        TaskDTO task = taskService.getTaskById(id);

        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page = 0, size = 10, Sort.by("dueDate").descending());
        List<TaskDTO> tasks = taskService.getAllTasks(pageable);

        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody TaskRequest taskRequest) {
        taskService.updateTask(id, taskRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }
}
