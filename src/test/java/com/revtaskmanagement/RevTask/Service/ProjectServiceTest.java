package com.revtaskmanagement.RevTask.Service;

import com.revtaskmanagement.RevTask.Entity.*;
import com.revtaskmanagement.RevTask.DTO.*;
import com.revtaskmanagement.RevTask.Repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProjectServiceTest {

    @MockBean
    private ProjectRepository projectRepository;

    @MockBean
    private ProjectManagerRepository projectManagerRepository;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private ProjectService projectService;

    @Test
    public void testGetProjectDTOById() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Project A");
        project.setDescription("Description A");
        project.setTasks(Collections.emptyList());

        ProjectManager projectManager = new ProjectManager();
        projectManager.setId(1L);
        projectManager.setUsername("Manager A");

        Client client = new Client();
        client.setId(1L);
        client.setName("Client A");

        project.setProjectManager(projectManager);
        project.setClient(client);

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        ProjectDTO projectDTO = projectService.getProjectDTOById(1L);

        Assertions.assertNotNull(projectDTO);
        Assertions.assertEquals("Project A", projectDTO.getName());
    }

//    @Test
//    public void testGetAllProjects() {
//        Project project1 = new Project();
//        project1.setId(1L);
//        project1.setName("Project A");
//        project1.setDescription("Description A");
//        project1.setTasks(Collections.emptyList());
//
//        Project project2 = new Project();
//        project2.setId(2L);
//        project2.setName("Project B");
//        project2.setDescription("Description B");
//        project2.setTasks(Collections.emptyList());
//
//        List<Project> projects = Arrays.asList(project1, project2);
//
//        Mockito.when(projectRepository.findAll()).thenReturn(projects);
//
//        List<ProjectDTO> projectDTOs = projectService.getAllProjects();
//
//        Assertions.assertNotNull(projectDTOs);
//        Assertions.assertEquals(2, projectDTOs.size());
//    }

    @Test
    public void testDeleteProject() {
        Project project = new Project();
        project.setId(1L);

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(project));

        projectService.deleteProject(1L);

        Mockito.verify(projectRepository, Mockito.times(1)).delete(project);
    }

    @Test
    public void testCreateProject() {
        Project project = new Project();
        project.setId(1L);
        project.setName("Project A");
        project.setDescription("Description A");
        project.setTasks(Collections.emptyList());

        ProjectManager projectManager = new ProjectManager();
        projectManager.setId(1L);
        projectManager.setUsername("Manager A");

        Client client = new Client();
        client.setId(1L);
        client.setName("Client A");

        project.setProjectManager(projectManager);
        project.setClient(client);

        Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(project);

        Project createdProject = projectService.createProject(project);

        Assertions.assertNotNull(createdProject);
        Assertions.assertEquals("Project A", createdProject.getName());
    }

    @Test
    public void testUpdateProject() {
        Project existingProject = new Project();
        existingProject.setId(1L);
        existingProject.setName("Project A");
        existingProject.setDescription("Description A");
        existingProject.setTasks(Collections.emptyList());

        Project updatedProjectDetails = new Project();
        updatedProjectDetails.setName("Updated Project A");
        updatedProjectDetails.setDescription("Updated Description A");
        updatedProjectDetails.setTasks(Collections.emptyList());

        Mockito.when(projectRepository.findById(1L)).thenReturn(Optional.of(existingProject));
        Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(existingProject);

        Project updatedProject = projectService.updateProject(1L, updatedProjectDetails);

        Assertions.assertNotNull(updatedProject);
        Assertions.assertEquals("Updated Project A", updatedProject.getName());
    }
}
