package com.revtaskmanagement.RevTask.Repository;

import com.revtaskmanagement.RevTask.DTO.TaskDetailDTO;

import java.util.List;

public interface TaskRepositoryCustom {
    List<TaskDetailDTO> findAllTaskDTOs();
    TaskDetailDTO findTaskDTOById(Long id);
    List<TaskDetailDTO> findTasksByProjectId(Long projectId);
}
