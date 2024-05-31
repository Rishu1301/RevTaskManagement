package com.revtaskmanagement.RevTask.Controller;

import com.revtaskmanagement.RevTask.DTO.ProjectDTO;
import com.revtaskmanagement.RevTask.Entity.Project;
import com.revtaskmanagement.RevTask.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {

//        return projectService.getAllProjects();
        List<ProjectDTO> projectDTOList = projectService.getAllProjects();
        if(projectDTOList==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(projectDTOList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {

//        return projectService.getProjectDTOById(id);

        ProjectDTO projectDTO = projectService.getProjectDTOById(id);
        if(projectDTO==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {

//        return projectService.createProject(project);
        Project project1 = projectService.createProject(project);
        if(project1==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestBody Project projectDetails) {
//        return projectService.updateProject(id, projectDetails);
        Project project = projectService.updateProject(id, projectDetails);
        if(project==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


   @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {

      projectService.deleteProject(id);
    }
}



