package com.abc.taskmaster.employee;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    Optional<Employee> findById(UUID id);
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByUsername(String username);
    boolean existsById(UUID id);
    boolean existsByEmail(String email);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Employee e SET e.avatarUrl = ?2 WHERE e.id = ?1")
    void updateAvatarUrl(UUID employeeId, String avatarUrl);
}
