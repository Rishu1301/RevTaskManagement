package com.revtaskmanagement.RevTask.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.revtaskmanagement.RevTask.DTO.ClientDTO;
import com.revtaskmanagement.RevTask.Entity.Client;
import com.revtaskmanagement.RevTask.Repository.ClientRepository;
import com.revtaskmanagement.RevTask.Service.ClientService;
import com.revtaskmanagement.RevTask.Service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ClientService clientService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllClientsWithProjects() {
        // Mock data
        Client client1 = new Client(1L, "Client1", "client1@example.com", new ArrayList<>());
        Client client2 = new Client(2L, "Client2", "client2@example.com", new ArrayList<>());
        List<Client> clients = List.of(client1, client2);

        when(clientRepository.findAll()).thenReturn(clients);

        // Test the service method
        List<ClientDTO> result = clientService.getAllClientsWithProjects();

        // Verify the result
        assertEquals(2, result.size());
        assertEquals("Client1", result.get(0).getName());
        assertEquals("Client2", result.get(1).getName());
        // Add more assertions as needed
    }

    @Test
    public void testGetClientWithProjectsById() {
        // Mock data
        Client client = new Client(1L, "Client1", "client1@example.com", new ArrayList<>());
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        // Test the service method
        ClientDTO result = clientService.getClientWithProjectsById(1L);

        // Verify the result
        assertEquals("Client1", result.getName());
        // Add more assertions as needed
    }
}
