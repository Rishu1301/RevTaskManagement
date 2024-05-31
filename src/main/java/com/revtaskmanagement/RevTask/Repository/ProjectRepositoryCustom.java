package com.revtaskmanagement.RevTask.Repository;

import com.revtaskmanagement.RevTask.DTO.ProjectDTO;

import java.util.List;

public interface ProjectRepositoryCustom {
    List<ProjectDTO> findAllProjectDTOs();
    ProjectDTO findProjectDTOById(Long id);
}
