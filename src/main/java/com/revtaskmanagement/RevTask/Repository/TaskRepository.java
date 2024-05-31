package com.revtaskmanagement.RevTask.Repository;

import com.revtaskmanagement.RevTask.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>, TaskRepositoryCustom{
}
