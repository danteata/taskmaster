package com.abc.taskmaster.task;

import com.abc.taskmaster.exception.ResourceNotFoundException;
import com.abc.taskmaster.service.S3Buckets;
import com.abc.taskmaster.service.S3Service;
import com.abc.taskmaster.util.UploadUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskDTOMapper taskDTOMapper;
    private S3Buckets s3Buckets;
    private S3Service s3Service;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskDTOMapper taskDTOMapper, S3Buckets s3Buckets, S3Service s3Service) {
        this.taskRepository = taskRepository;
        this.taskDTOMapper = taskDTOMapper;
        this.s3Buckets = s3Buckets;
        this.s3Service = s3Service;
    }

    @Override
    public void createTask(TaskRequest taskRequest) {
        Task task = Task.builder()
                .title(taskRequest.title())
                .description(taskRequest.description())
                .priority(taskRequest.priority())
                .status(taskRequest.status())
                .build();
        taskRepository.save(task);
    }

    @Override
    public TaskDTO getTaskById(UUID id) {
        return taskRepository.findById(id).map(taskDTOMapper).orElseThrow(() ->
                new ResourceNotFoundException("Task not found with id: [%s] ".formatted(id)));
    }

    @Override
    public List<TaskDTO> getAllTasks(Pageable pageable) {
        Page<Task> tasks = taskRepository.findAll(pageable);
        return tasks.stream().map(taskDTOMapper).collect(Collectors.toList());
    }

    @Override
    public void addLabelToTask(UUID id, UUID labelId) {

    }

    @Override
    public void removeLabelFromTask(UUID id, UUID labelId) {

    }

    @Override
    public void assignTask(UUID id, UUID userId) {

    }

    @Override
    public void removeUserFromTask(UUID id, UUID userId) {

    }

    @Override
    public void updateTask(UUID id, TaskRequest updateTaskRequest) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task not found with id: [%s] ".formatted(id)));
//        Task taskPayload = TaskMapper.mapToTask(taskDto);
//        Task updatedTask = taskRepository.save(taskPayload);
    }

    @Override
    public void deleteTask(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);

    }


    @Override
    public String uploadAttachment(UUID id, MultipartFile file) {
        Task task = taskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task not found with id: " + id));
        String attachmentUrl = uploadAttachmentFunction.apply(id, file);
        Attachment attachment = new Attachment();
        attachment.setUrl(attachmentUrl);
        task.getAttachments().add(attachment);
        taskRepository.save(task);
        return attachmentUrl;
    }

    private final BiFunction<UUID, MultipartFile, String> uploadAttachmentFunction = (id, file) -> {
        String fileName = id + UploadUtils.fileExtension.apply(file.getOriginalFilename());
        try {
            s3Service.putObject(
                    s3Buckets.getAttachment(),
                    "task-attachments/%s/%s".formatted(id, fileName),
                    file.getBytes()
            );
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload avatar image", e);
        }
    };
}
