package com.revtaskmanagement.RevTask.Repository;

import com.revtaskmanagement.RevTask.DTO.ProjectDTO;
import com.revtaskmanagement.RevTask.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
