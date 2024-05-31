package com.revtaskmanagement.RevTask.Service;

import com.revtaskmanagement.RevTask.DTO.ProjectManagerDTO;
import com.revtaskmanagement.RevTask.Entity.ProjectManager;
import com.revtaskmanagement.RevTask.Repository.ProjectManagerRepository;
import com.revtaskmanagement.RevTask.Service.ProjectManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class ProjectManagerServiceTest {

    @Mock
    private ProjectManagerRepository projectManagerRepository;

    @InjectMocks
    private ProjectManagerService projectManagerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProjectManagers() {
        // Given
        List<ProjectManagerDTO> expectedProjectManagers = Arrays.asList(
                new ProjectManagerDTO(1L, "John", "john@example.com"),
                new ProjectManagerDTO(2L, "Alice", "alice@example.com")
        );
        when(projectManagerRepository.findAllProjectManagersDTO()).thenReturn(expectedProjectManagers);

        // When
        List<ProjectManagerDTO> actualProjectManagers = projectManagerService.getAllProjectManagers();

        // Then
        assertNotNull(actualProjectManagers);
        assertEquals(expectedProjectManagers.size(), actualProjectManagers.size());
        assertEquals(expectedProjectManagers, actualProjectManagers);
    }

    @Test
    void testGetProjectManagerById() {
        // Given
        Long id = 1L;
        ProjectManagerDTO expectedProjectManager = new ProjectManagerDTO(id, "John", "john@example.com");
        when(projectManagerRepository.findProjectManagerByIdDTO(id)).thenReturn(expectedProjectManager);

        // When
        ProjectManagerDTO actualProjectManager = projectManagerService.getProjectManagerById(id);

        // Then
        assertNotNull(actualProjectManager);
        assertEquals(expectedProjectManager.getId(), actualProjectManager.getId());
        assertEquals(expectedProjectManager.getUsername(), actualProjectManager.getUsername());
        assertEquals(expectedProjectManager.getEmail(), actualProjectManager.getEmail());
    }

    @Test
    void testCreateProjectManager() {
        // Given
        ProjectManager projectManager = new ProjectManager();
        projectManager.setUsername("John");
        projectManager.setPassword("password");
        projectManager.setEmail("john@example.com");

        when(projectManagerRepository.save(any(ProjectManager.class))).thenReturn(projectManager);

        // When
        ProjectManager createdProjectManager = projectManagerService.createProjectManager(projectManager);

        // Then
        assertNotNull(createdProjectManager);
        assertEquals(projectManager.getUsername(), createdProjectManager.getUsername());
        assertEquals(projectManager.getPassword(), createdProjectManager.getPassword());
        assertEquals(projectManager.getEmail(), createdProjectManager.getEmail());
    }

    @Test
    void testUpdateProjectManager() {
        // Given
        Long id = 1L;
        ProjectManager existingProjectManager = new ProjectManager();
        existingProjectManager.setId(id);
        existingProjectManager.setUsername("John");
        existingProjectManager.setPassword("password");
        existingProjectManager.setEmail("john@example.com");

        ProjectManager updatedProjectManagerDetails = new ProjectManager();
        updatedProjectManagerDetails.setUsername("Alice");
        updatedProjectManagerDetails.setPassword("newpassword");
        updatedProjectManagerDetails.setEmail("alice@example.com");

        when(projectManagerRepository.findById(id)).thenReturn(Optional.of(existingProjectManager));
        when(projectManagerRepository.save(any(ProjectManager.class))).thenReturn(updatedProjectManagerDetails);

        // When
        ProjectManager updatedProjectManager = projectManagerService.updateProjectManager(id, updatedProjectManagerDetails);

        // Then
        assertNotNull(updatedProjectManager);
        assertEquals(updatedProjectManagerDetails.getUsername(), updatedProjectManager.getUsername());
        assertEquals(updatedProjectManagerDetails.getPassword(), updatedProjectManager.getPassword());
        assertEquals(updatedProjectManagerDetails.getEmail(), updatedProjectManager.getEmail());
    }
}