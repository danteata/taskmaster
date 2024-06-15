package com.abc.taskmaster.task;

import com.abc.taskmaster.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    Optional<Task> findById(UUID id);

    List<Task> findAllByAssigneeId(UUID id);

    List<Task> findAllByStatus(Status status);

    List<Task> findAllByPriority(Priority priority);

//    List<Task> findByTitleContainingIgnoreCase(String partialTitle, Employee employee);

//    @Query("SELECT t FROM Task t JOIN t.department d WHERE t = :employee")
//    List<Task> findByDepartment(@Param("employee") Employee employee);

//    List<Task> findByDepartmentContainingOrOwner(Employee employee, Employee owner);
}
