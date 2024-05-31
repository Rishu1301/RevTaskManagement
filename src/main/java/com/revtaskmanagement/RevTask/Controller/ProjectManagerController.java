package com.revtaskmanagement.RevTask.Controller;

//import com.revtaskmanagement.RevTask.Entity.ProjectManager;
//import com.revtaskmanagement.RevTask.Service.ProjectManagerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/project-managers")
//public class ProjectManagerController {
//
//    @Autowired
//    private ProjectManagerService projectManagerService;
//
//    @GetMapping
//    public List<ProjectManager> getAllProjectManagers() {
//        return projectManagerService.getAllProjectManagers();
//    }
//
//    @GetMapping("/{id}")
//    public ProjectManager getProjectManagerById(@PathVariable Long id) {
//        return projectManagerService.getProjectManagerById(id);
//    }
//
//    @PostMapping
//    public ProjectManager createProjectManager(@RequestBody ProjectManager projectManager) {
//        return projectManagerService.createProjectManager(projectManager);
//    }
//
//    @PutMapping("/{id}")
//    public ProjectManager updateProjectManager(@PathVariable Long id, @RequestBody ProjectManager projectManagerDetails) {
//        return projectManagerService.updateProjectManager(id, projectManagerDetails);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteProjectManager(@PathVariable Long id) {
//        projectManagerService.deleteProjectManager(id);
//    }
//}

import com.revtaskmanagement.RevTask.DTO.LoginRequestDTO;
import com.revtaskmanagement.RevTask.DTO.ProjectManagerDTO;
import com.revtaskmanagement.RevTask.Entity.ProjectManager;
import com.revtaskmanagement.RevTask.Service.ProjectManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project-managers")
public class ProjectManagerController {

    @Autowired
    private ProjectManagerService projectManagerService;

    @GetMapping("/getAllProjectManagers")
    public ResponseEntity<List<ProjectManagerDTO>> getAllProjectManagers() {

        List<ProjectManagerDTO> projectManagers=projectManagerService.getAllProjectManagers();
        if(projectManagers==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projectManagers,HttpStatus.OK);
    }


    @GetMapping("/getProjectManagaerById/{id}")
    public ResponseEntity<ProjectManagerDTO> getProjectManagerById(@PathVariable Long id) {
        ProjectManagerDTO projectManager=projectManagerService.getProjectManagerById(id);
        if(projectManager==null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(projectManager,HttpStatus.OK);
    }

    @PostMapping("/create-project-manager")
    public ResponseEntity<ProjectManager> createProjectManager(@RequestBody ProjectManager projectManager) {
        ProjectManager projectManager1 = projectManagerService.createProjectManager(projectManager);
        if(projectManager1==null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(projectManager1,HttpStatus.OK);
        }
    }

    @PutMapping("/updateProjectManager/{id}")
    public ResponseEntity<ProjectManager> updateProjectManager(@PathVariable Long id, @RequestBody ProjectManager projectManagerDetails) {
        if(projectManagerDetails==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ProjectManager projectManager= projectManagerService.updateProjectManager(id,projectManagerDetails);
        return new ResponseEntity<>(projectManager,HttpStatus.OK);
    }

//    @DeleteMapping("/deleteProject/{id}")
//    public void deleteProjectManager(@PathVariable Long id) {
//        projectManagerService.deleteProjectManager(id);
//    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        boolean isValid = projectManagerService.validateLogin(loginRequest.getEmail(), loginRequest.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login successful. Redirecting to home page...");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


}
