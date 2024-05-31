package com.revtaskmanagement.RevTask.Service;

import com.revtaskmanagement.RevTask.DTO.TaskDetailDTO;
import com.revtaskmanagement.RevTask.Entity.Task;
import com.revtaskmanagement.RevTask.Entity.TeamMember; // Change to the correct class if necessary
import com.revtaskmanagement.RevTask.Entity.Project;
import com.revtaskmanagement.RevTask.Exception.ResourceNotFoundException;
import com.revtaskmanagement.RevTask.Repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Calendar;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task1;
    private Task task2;
    private TaskDetailDTO taskDetailDTO1;
    private TaskDetailDTO taskDetailDTO2;

    @BeforeEach
    public void setUp() {
        TeamMember teamMember = new TeamMember(); // Change to the correct class if necessary
        teamMember.setUsername("testUser");

        Project project = new Project();
        project.setId(1L);

        Calendar cal = Calendar.getInstance();

        task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Task 1");
        task1.setDescription("Description 1");
        task1.setStatus("Open");
        task1.setPriority("High");
        task1.setAssignDate(new Date());
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 7);
        task1.setDueDate(cal.getTime());
        task1.setAssignedTo(teamMember);
        task1.setProject(project);

        task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Task 2");
        task2.setDescription("Description 2");
        task2.setStatus("Closed");
        task2.setPriority("Low");
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -10);
        task2.setAssignDate(cal.getTime());
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -3);
        task2.setDueDate(cal.getTime());
        task2.setAssignedTo(teamMember);
        task2.setProject(project);

        cal.setTime(new Date());
        Date task1DueDate = cal.getTime();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date task2DueDate = cal.getTime();

        taskDetailDTO1 = new TaskDetailDTO(1L, "Task 1", "Description 1", "Open", "High", new Date(), task1DueDate, "testUser", 1L);
        taskDetailDTO2 = new TaskDetailDTO(2L, "Task 2", "Description 2", "Closed", "Low", cal.getTime(), task2DueDate, "testUser", 1L);
    }

    @Test
    public void testGetAllTasks() {
        when(taskRepository.findAllTaskDTOs()).thenReturn(Arrays.asList(taskDetailDTO1, taskDetailDTO2));

        List<TaskDetailDTO> tasks = taskService.getAllTasks();

        assertNotNull(tasks);
        assertEquals(2, tasks.size());
        verify(taskRepository, times(1)).findAllTaskDTOs();
    }

    @Test
    public void testGetTaskById() {
        when(taskRepository.findTaskDTOById(1L)).thenReturn(taskDetailDTO1);

        TaskDetailDTO task = taskService.getTaskById(1L);

        assertNotNull(task);
        assertEquals("Task 1", task.getTitle());
        verify(taskRepository, times(1)).findTaskDTOById(1L);
    }

    @Test
    public void testCreateTask() {
        when(taskRepository.save(task1)).thenReturn(task1);

        Task createdTask = taskService.createTask(task1);

        assertNotNull(createdTask);
        assertEquals("Task 1", createdTask.getTitle());
        verify(taskRepository, times(1)).save(task1);
    }

    @Test
    public void testUpdateTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        when(taskRepository.save(task1)).thenReturn(task1);

        Task updatedTaskDetails = new Task();
        updatedTaskDetails.setTitle("Updated Task 1");
        updatedTaskDetails.setDescription("Updated Description 1");
        updatedTaskDetails.setStatus("In Progress");
        updatedTaskDetails.setPriority("Medium");
        updatedTaskDetails.setAssignDate(new Date());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 14);
        updatedTaskDetails.setDueDate(cal.getTime());
        updatedTaskDetails.setAssignedTo(task1.getAssignedTo());
        updatedTaskDetails.setProject(task1.getProject());

        Task updatedTask = taskService.updateTask(1L, updatedTaskDetails);

        assertNotNull(updatedTask);
        assertEquals("Updated Task 1", updatedTask.getTitle());
        assertEquals("Updated Description 1", updatedTask.getDescription());
        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).save(task1);
    }

    @Test
    public void testDeleteTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));
        doNothing().when(taskRepository).delete(task1);

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).delete(task1);
    }

//    @Test
//    public void testGetTaskByIdNotFound() {
//        when(taskRepository.findTaskDTOById(1L)).thenReturn(null);
//
//        assertThrows(ResourceNotFoundException.class, () -> {
//            taskService.getTaskById(1L);
//        });
//    }

    @Test
    public void testUpdateTaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        Task updatedTaskDetails = new Task();
        updatedTaskDetails.setTitle("Updated Task 1");

        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.updateTask(1L, updatedTaskDetails);
        });
    }

    @Test
    public void testDeleteTaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            taskService.deleteTask(1L);
        });
    }
}