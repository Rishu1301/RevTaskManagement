//package com.revtaskmanagement.RevTask.Service;
//
//import com.revtaskmanagement.RevTask.DTO.*;
//import com.revtaskmanagement.RevTask.Entity.*;
//import com.revtaskmanagement.RevTask.Repository.*;
//import jakarta.persistence.EntityNotFoundException;
//import jakarta.transaction.Transactional;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//@Transactional
//public class IntegrationTests {
//
//    @Autowired
//    private TeamMemberService teamMemberService;
//
//    @Autowired
//    private ProjectService projectService;
//
//    @Autowired
//    private TaskService taskService;
//
//    @Autowired
//    private ClientService clientService;
//
//    @Autowired
//    private AdminService adminService;
//
//    @Autowired
//    private TeamMemberRepository teamMemberRepository;
//
//    @Autowired
//    private ProjectRepository projectRepository;
//
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @BeforeEach
//    public void setUp() {
//        teamMemberRepository.deleteAll();
//        projectRepository.deleteAll();
//        taskRepository.deleteAll();
//        clientRepository.deleteAll();
//        adminRepository.deleteAll();
//    }
//
//    // TeamMemberService Tests
//    @Test
//    public void testCreateAndGetTeamMember() {
//        TeamMember teamMember = new TeamMember();
//        teamMember.setEmail("team@example.com");
//        teamMember.setRole("Developer");
//        teamMember.setActive(true);
//
//        TeamMember createdTeamMember = teamMemberService.createTeamMember(teamMember);
//        TeamMemberDTO fetchedTeamMember = teamMemberService.getTeamMemberById(createdTeamMember.getId());
//
//        assertEquals("team@example.com", fetchedTeamMember.getEmail());
//        assertEquals("Developer", fetchedTeamMember.getRole());
//    }
//
//    @Test
//    public void testDeactivateAndActivateTeamMember() {
//        TeamMember teamMember = new TeamMember();
//        teamMember.setEmail("team@example.com");
//        teamMember.setRole("Developer");
//        teamMember.setActive(true);
//
//        TeamMember createdTeamMember = teamMemberRepository.save(teamMember);
//
//        teamMemberService.deactivateTeamMember(createdTeamMember.getId());
//        assertFalse(teamMemberRepository.findById(createdTeamMember.getId()).get().isActive());
//
//        teamMemberService.activateTeamMember(createdTeamMember.getId());
//        assertTrue(teamMemberRepository.findById(createdTeamMember.getId()).get().isActive());
//    }
//
//    // ProjectService Tests
//    @Test
//    public void testCreateAndGetProject() {
//        Project project = new Project();
//        project.setName("Project A");
//        project.setDescription("Description A");
//
//        Project createdProject = projectService.createProject(project);
//        ProjectDTO fetchedProject = projectService.getProjectDTOById(createdProject.getId());
//
//        assertEquals("Project A", fetchedProject.getName());
//        assertEquals("Description A", fetchedProject.getDescription());
//    }
//
//    @Test
//    public void testUpdateProject() {
//        Project project = new Project();
//        project.setName("Project A");
//        project.setDescription("Description A");
//
//        Project createdProject = projectService.createProject(project);
//        createdProject.setName("Project B");
//        createdProject.setDescription("Description B");
//
//        Project updatedProject = projectService.updateProject(createdProject.getId(), createdProject);
//        assertEquals("Project B", updatedProject.getName());
//        assertEquals("Description B", updatedProject.getDescription());
//    }
//
//    // TaskService Tests
//    @Test
//    public void testCreateAndGetTask() {
//        Task task = new Task();
//        task.setTitle("Task A");
//        task.setDescription("Description A");
//
//        Task createdTask = taskService.createTask(task);
//        TaskDetailDTO fetchedTask = taskService.getTaskById(createdTask.getId());
//
//        assertEquals("Task A", fetchedTask.getTitle());
//        assertEquals("Description A", fetchedTask.getDescription());
//    }
//
//    @Test
//    public void testUpdateTask() {
//        Task task = new Task();
//        task.setTitle("Task A");
//        task.setDescription("Description A");
//
//        Task createdTask = taskService.createTask(task);
//        createdTask.setTitle("Task B");
//        createdTask.setDescription("Description B");
//
//        Task updatedTask = taskService.updateTask(createdTask.getId(), createdTask);
//        assertEquals("Task B", updatedTask.getTitle());
//        assertEquals("Description B", updatedTask.getDescription());
//    }
//
//    // ClientService Tests
//    @Test
//    public void testCreateAndGetClient() {
//        Client client = new Client();
//        client.setName("Client A");
//        client.setEmail("client@example.com");
//
//        Client createdClient = clientRepository.save(client);
//        ClientDTO fetchedClient = clientService.getClientWithProjectsById(createdClient.getId());
//
//        assertEquals("Client A", fetchedClient.getName());
//        assertEquals("client@example.com", fetchedClient.getEmail());
//    }
//
//    // AdminService Tests
//    @Test
//    public void testCreateAndGetAdmin() {
//        Admin admin = new Admin();
//        admin.setUsername("admin");
//        admin.setPassword("password");
//        admin.setEmail("admin@example.com");
//
//        Admin createdAdmin = adminService.createAdmin(admin);
//        Admin fetchedAdmin = adminService.getAdminById(createdAdmin.getId());
//
//        assertEquals("admin", fetchedAdmin.getUsername());
//        assertEquals("admin@example.com", fetchedAdmin.getEmail());
//    }
//
//    @Test
//    public void testDeleteAdmin() {
//        Admin admin = new Admin();
//        admin.setUsername("admin");
//        admin.setPassword("password");
//        admin.setEmail("admin@example.com");
//
//        Admin createdAdmin = adminService.createAdmin(admin);
//        adminService.deleteAdmin(createdAdmin.getId());
//
//        assertThrows(EntityNotFoundException.class, () -> adminService.getAdminById(createdAdmin.getId()));
//    }
//}


package com.revtaskmanagement.RevTask.Service;

import com.revtaskmanagement.RevTask.DTO.*;
import com.revtaskmanagement.RevTask.Entity.*;
import com.revtaskmanagement.RevTask.Exception.ResourceNotFoundException;
import com.revtaskmanagement.RevTask.Repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IntegrationTests {

    @Autowired
    private TeamMemberService teamMemberService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;

    @BeforeEach
    public void setUp() {
        // Clean up existing data
        teamMemberRepository.deleteAll();
        projectRepository.deleteAll();
        taskRepository.deleteAll();
        clientRepository.deleteAll();
        adminRepository.deleteAll();
    }
        @Test
        public void testIntegration () {
            // Create a team member
            TeamMember teamMember = new TeamMember();
            teamMember.setUsername("testUser");
            teamMember.setPassword("password");
            teamMember.setEmail("test@example.com");
            teamMember.setRole("ROLE_USER");

            TeamMember createdTeamMember = teamMemberService.createTeamMember(teamMember);
            assertNotNull(createdTeamMember.getId());

            // Create a project
            Project project = new Project();
            project.setName("Project A");
            project.setDescription("Description A");

            Project createdProject = projectService.createProject(project);
            assertNotNull(createdProject.getId());

            // Create a task
            Task task = new Task();
            task.setTitle("Task 1");
            task.setDescription("Description 1");
            task.setStatus("Open");
            task.setPriority("High");
            task.setAssignedTo(createdTeamMember);
            task.setProject(createdProject);

            Task createdTask = taskService.createTask(task);
            assertNotNull(createdTask.getId());

            // Create a client
            Client client = new Client();
            client.setName("Client A");
            client.setEmail("client@example.com");

            Client createdClient = clientService.createClient(client);
            assertNotNull(createdClient.getId());

            // Create an admin
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("password");
            admin.setEmail("admin@example.com");

            Admin createdAdmin = adminService.createAdmin(admin);
            assertNotNull(createdAdmin.getId());

            // Test retrieval of created entities
            List<TeamMemberDTO> teamMembers = teamMemberService.getAllTeamMembers();
            assertEquals(1, teamMembers.size());

            List<ProjectDTO> projects = projectService.getAllProjects();
            assertEquals(1, projects.size());

            List<TaskDetailDTO> tasks = taskService.getAllTasks();
            assertEquals(1, tasks.size());

            List<ClientDTO> clients = clientService.getAllClientsWithProjects();
            assertEquals(1, clients.size());

            // Test updating an entity
            createdProject.setDescription("Updated Description A");
            Project updatedProject = projectService.updateProject(createdProject.getId(), createdProject);
            assertEquals("Updated Description A", updatedProject.getDescription());

            // Test deleting an entity
            taskService.deleteTask(createdTask.getId());
            assertThrows(ResourceNotFoundException.class, () -> {
                taskService.getTaskById(createdTask.getId());
            });
        }
    }
