package com.revtaskmanagement.RevTask.Service;

import com.revtaskmanagement.RevTask.DTO.TaskDetailDTO;
import com.revtaskmanagement.RevTask.Entity.Task;
import com.revtaskmanagement.RevTask.Entity.TeamMember;
import com.revtaskmanagement.RevTask.Exception.ResourceNotFoundException;
import com.revtaskmanagement.RevTask.Repository.TaskRepository;
import com.revtaskmanagement.RevTask.Repository.TeamMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    public List<TaskDetailDTO> getAllTasks() {
        logger.info("Fetching all tasks");
        List<TaskDetailDTO> tasks = taskRepository.findAllTaskDTOs();
        logger.info("Fetched {} tasks", tasks.size());
        return tasks;
    }

    public TaskDetailDTO getTaskById(Long id) {
        logger.info("Fetching task with id {}", id);
        TaskDetailDTO task = taskRepository.findTaskDTOById(id);
        if (task == null) {
            logger.error("Task not found with id {}", id);
            throw new ResourceNotFoundException("Task not found with id: " + id);
        }
        logger.info("Fetched task with id {}", id);
        return task;
    }

    public Task createTask(Task task) {
        logger.info("Creating new task with title {}", task.getTitle());
        Task createdTask = taskRepository.save(task);
        logger.info("Created task with id {}", createdTask.getId());
        return createdTask;
    }

    public Task updateTask(Long id, Task taskDetails) {
        logger.info("Updating task with id {}", id);
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Task not found with id {}", id);
                    return new ResourceNotFoundException("Task not found with id: " + id);
                });

        existingTask.setTitle(taskDetails.getTitle());
        existingTask.setDescription(taskDetails.getDescription());
        existingTask.setStatus(taskDetails.getStatus());
        existingTask.setPriority(taskDetails.getPriority());
        existingTask.setAssignDate(taskDetails.getAssignDate());
        existingTask.setDueDate(taskDetails.getDueDate());
        existingTask.setAssignedTo(taskDetails.getAssignedTo());
        existingTask.setProject(taskDetails.getProject());

        Task updatedTask = taskRepository.save(existingTask);
        logger.info("Updated task with id {}", updatedTask.getId());
        return updatedTask;
    }

    public void deleteTask(Long id) {
        logger.info("Deleting task with id {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Task not found with id {}", id);
                    return new ResourceNotFoundException("Task not found with id: " + id);
                });
        taskRepository.delete(task);
        logger.info("Deleted task with id {}", id);
    }

    public List<TaskDetailDTO> getTasksByProjectId(Long projectId) {
        logger.info("Fetching tasks for project with id {}", projectId);
        return taskRepository.findTasksByProjectId(projectId);
    }

    public Task createTask1(Task task, Long memberId) {
        TeamMember teamMember = teamMemberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid team member ID: " + memberId));
        task.setAssignedTo(teamMember);
        return taskRepository.save(task);
    }
}



//
//import com.revtaskmanagement.RevTask.DTO.TaskDetailDTO;
//import com.revtaskmanagement.RevTask.Entity.Task;
//import com.revtaskmanagement.RevTask.Exception.ResourceNotFoundException;
//import com.revtaskmanagement.RevTask.Repository.TaskRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TaskService {
//    @Autowired
//    private TaskRepository taskRepository;
//
////    public List<Task> getAllTasks() {
////
////        return taskRepository.findAll();
////    }
////
////    public Task getTaskById(Long id) {
////
////        return taskRepository.findById(id).orElseThrow();
////    }
//
//
//    //---------------------------------------------------------working here
//
//
////    public List<TaskDTO> getAllTasks() {
////        List<Task> tasks = taskRepository.findAll();
////        return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
////    }
////
////    public TaskDTO getTaskById(Long id) {
////        Task task = taskRepository.findById(id)
////                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
////        return convertToDTO(task);
////    }
//
////    private TaskDTO convertToDTO(Task task) {
////        TaskDTO dto = new TaskDTO();
////        dto.setId(task.getId());
////        dto.setTitle(task.getTitle());
////        dto.setDescription(task.getDescription());
////        dto.setStatus(task.getStatus());
////        dto.setPriority(task.getPriority());
////        dto.setAssignDate(task.getAssignDate());
////        dto.setDueDate(task.getDueDate());
////        dto.setAssignedTo(task.getAssignedTo().getUsername()); // assuming you want the username of the assigned team member
////        dto.setProjectId(task.getProject().getId());
////        return dto;
////    }
//
////    public List<TaskDTO> getAllTasks() {
////        List<Task> tasks = taskRepository.findAll();
////        return tasks.stream().map(this::convertToDTO).collect(Collectors.toList());
////    }
////
////    public TaskDTO getTaskById(Long id) {
////        Task task = taskRepository.findById(id)
////                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
////        return convertToDTO(task);
////    }
//
//
//    public List<TaskDetailDTO> getAllTasks() {
//        return taskRepository.findAllTaskDTOs();
//    }
//
//    public TaskDetailDTO getTaskById(Long id) {
//        return taskRepository.findTaskDTOById(id);
//    }
//
//    public Task createTask(Task task) {
//
//        return taskRepository.save(task);
//    }
//
////    public Task updateTask(Long id, Task taskDetails) {
////        Task task = taskRepository.findById(id).orElseThrow();
////        task.setTitle(taskDetails.getTitle());
////        task.setDescription(taskDetails.getDescription());
////        task.setStatus(taskDetails.getStatus());
////        task.setPriority(taskDetails.getPriority());
////        task.setAssignedTo(taskDetails.getAssignedTo());
////        task.setAssignDate(taskDetails.getAssignDate());
////        task.setDueDate(taskDetails.getDueDate());
////        return taskRepository.save(task);
////    }
//
////    public void deleteTask(Long id) {
////
////        taskRepository.deleteById(id);
////    }
//
//    public Task updateTask(Long id, Task taskDetails) {
//        Task existingTask = taskRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
//
//        existingTask.setTitle(taskDetails.getTitle());
//        existingTask.setDescription(taskDetails.getDescription());
//        existingTask.setStatus(taskDetails.getStatus());
//        existingTask.setPriority(taskDetails.getPriority());
//        existingTask.setAssignDate(taskDetails.getAssignDate());
//        existingTask.setDueDate(taskDetails.getDueDate());
//        existingTask.setAssignedTo(taskDetails.getAssignedTo());
//        existingTask.setProject(taskDetails.getProject());
//
//        return taskRepository.save(existingTask);
//    }
//
//    public void deleteTask(Long id) {
//        Task task = taskRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
//        taskRepository.delete(task);
//    }
//}
