package com.abc.taskmaster.task;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    void createTask(TaskRequest taskRequest);

    TaskDTO getTaskById(UUID id);

    List<TaskDTO> getAllTasks(Pageable pageable);

    void addLabelToTask(UUID id, UUID labelId);

    void removeLabelFromTask(UUID id, UUID labelId);

    void assignTask(UUID id, UUID userId);

    void removeUserFromTask(UUID id, UUID userId);

    void updateTask(UUID id, TaskRequest updateTaskRequest);

    void deleteTask(UUID id);

    String uploadAttachment(UUID id, MultipartFile file);
}
