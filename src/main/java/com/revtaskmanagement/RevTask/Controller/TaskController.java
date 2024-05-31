package com.revtaskmanagement.RevTask.Controller;

import com.revtaskmanagement.RevTask.DTO.TaskDTO;
import com.revtaskmanagement.RevTask.DTO.TaskDetailDTO;
import com.revtaskmanagement.RevTask.Entity.Task;
import com.revtaskmanagement.RevTask.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

//    @GetMapping
//    public List<Task> getAllTasks() {
//
//        return taskService.getAllTasks();
//    }
//
//    @GetMapping("/{id}")
//    public Task getTaskById(@PathVariable Long id) {
//
//        return taskService.getTaskById(id);
//    }

    @GetMapping
    public List<TaskDetailDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskDetailDTO getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

//    @PostMapping
//    public Task createTask(@RequestBody Task task) {
//
//        return taskService.createTask(task);
//    }
//
//    @PutMapping("/{id}")
//    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
//        return taskService.updateTask(id, taskDetails);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteTask(@PathVariable Long id) {
//
//        taskService.deleteTask(id);
//    }


    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        return taskService.updateTask(id, taskDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/task/{projectId}")
    public List<TaskDetailDTO> getTasksByProjectId(@PathVariable Long projectId) {
        return taskService.getTasksByProjectId(projectId);
    }

    @PostMapping("/createTasks")
    public ResponseEntity<Task> createTask1(@RequestBody Task task, @RequestParam Long memberId) {
//        return taskService.createTask1(task, memberId);
        Task createdTask = taskService.createTask1(task, memberId);
        System.out.println("mem id" + memberId);

        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);

    }
}
