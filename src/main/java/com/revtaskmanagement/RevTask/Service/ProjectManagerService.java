package com.revtaskmanagement.RevTask.Service;

//import com.revtaskmanagement.RevTask.Entity.ProjectManager;
//import com.revtaskmanagement.RevTask.Repository.ProjectManagerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProjectManagerService {
//    @Autowired
//    private ProjectManagerRepository projectManagerRepository;
//
//    public List<ProjectManager> getAllProjectManagers() {
//        return projectManagerRepository.findAll();
//    }
//
//    public ProjectManager getProjectManagerById(Long id) {
//        return projectManagerRepository.findById(id).orElseThrow();
//    }
//
//    public ProjectManager createProjectManager(ProjectManager projectManager) {
//        return projectManagerRepository.save(projectManager);
//    }
//
//    public ProjectManager updateProjectManager(Long id, ProjectManager projectManagerDetails) {
//        ProjectManager projectManager = projectManagerRepository.findById(id).orElseThrow();
//        projectManager.setUsername(projectManagerDetails.getUsername());
//        projectManager.setPassword(projectManagerDetails.getPassword());
//        projectManager.setEmail(projectManagerDetails.getEmail());
//        return projectManagerRepository.save(projectManager);
//    }
//
//    public void deleteProjectManager(Long id) {
//        projectManagerRepository.deleteById(id);
//    }
//}

import com.revtaskmanagement.RevTask.DTO.ProjectManagerDTO;
import com.revtaskmanagement.RevTask.Entity.Admin;
import com.revtaskmanagement.RevTask.Entity.ProjectManager;
import com.revtaskmanagement.RevTask.Repository.ProjectManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectManagerService {

    @Autowired
    private ProjectManagerRepository projectManagerRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProjectManagerService.class);

    public List<ProjectManagerDTO> getAllProjectManagers() {
        logger.info("Fetching all project managers");
        List<ProjectManagerDTO> projectManagers = projectManagerRepository.findAllProjectManagersDTO();
        if (projectManagers == null || projectManagers.isEmpty()) {
            logger.warn("No project managers found");
        } else {
            logger.info("Found {} project managers", projectManagers.size());
        }
        return projectManagers;
    }

    public ProjectManagerDTO getProjectManagerById(Long id) {
        logger.info("Fetching project manager by ID: {}", id);
        ProjectManagerDTO projectManager = projectManagerRepository.findProjectManagerByIdDTO(id);
        if (projectManager == null) {
            logger.warn("Project manager with ID {} not found", id);
        } else {
            logger.info("Found project manager with ID: {}", id);
        }
        return projectManager;
    }

    public ProjectManager createProjectManager(ProjectManager projectManager) {
        logger.info("Creating new project manager: {}", projectManager);
        ProjectManager savedProjectManager = projectManagerRepository.save(projectManager);
        if (savedProjectManager == null) {
            logger.error("Failed to create project manager: {}", projectManager);
        } else {
            logger.info("Created project manager: {}", savedProjectManager);
        }
        return savedProjectManager;
    }

    public ProjectManager updateProjectManager(Long id, ProjectManager projectManagerDetails) {
        logger.info("Updating project manager with ID: {}", id);
        ProjectManager projectManager = projectManagerRepository.findById(id).orElseThrow(() -> {
            logger.error("Project manager with ID {} not found", id);
            return new RuntimeException("Project manager not found");
        });
        projectManager.setUsername(projectManagerDetails.getUsername());
        projectManager.setPassword(projectManagerDetails.getPassword());
        projectManager.setEmail(projectManagerDetails.getEmail());
        ProjectManager updatedProjectManager = projectManagerRepository.save(projectManager);
        logger.info("Updated project manager with ID: {}", id);
        return updatedProjectManager;
    }

//    public boolean validateLogin(String username, String password) {
//        return projectManagerRepository.findByEmail(email)
//                .map(projectManager -> projectManager.getPassword().equals(password))
//                .orElse(false);
//    }


    public boolean validateLogin(String email, String password) {
        return projectManagerRepository.findByEmail(email)
                .map(projectManager -> projectManager.getPassword().equals(password))
                .orElse(false);
}
//    public boolean validateLogin(String email, String password) {
//        Optional<ProjectManager> pmOpt = projectManagerRepository.findByEmail(email);
//        if (pmOpt.isPresent()) {
//            ProjectManager projectManager = pmOpt.get();
//            return password.equal(projectManager.getPassword());
//        }
//        return false;
//    }
}
