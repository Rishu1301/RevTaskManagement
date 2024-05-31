package com.revtaskmanagement.RevTask.Service;

import com.revtaskmanagement.RevTask.DTO.ClientDTO;
import com.revtaskmanagement.RevTask.DTO.ProjectCustomDTO;
import com.revtaskmanagement.RevTask.Entity.Client;
import com.revtaskmanagement.RevTask.Repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProjectService projectService;

    public List<ClientDTO> getAllClientsWithProjects() {
        logger.info("Fetching all clients with projects");
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> clientDTOs = clients.stream()
                .map(this::mapToClientDTO)
                .collect(Collectors.toList());
        logger.info("Fetched {} clients", clientDTOs.size());
        return clientDTOs;
    }

    private ClientDTO mapToClientDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setName(client.getName());
        clientDTO.setEmail(client.getEmail());
        List<ProjectCustomDTO> projectDTOs = client.getProjects().stream()
                .map(project -> new ProjectCustomDTO(project.getId(), project.getName()))
                .collect(Collectors.toList());
        clientDTO.setProjects(projectDTOs); // Set projects in ClientDTO
        logger.debug("Mapped client ID {} to ClientDTO with {} projects", client.getId(), projectDTOs.size());
        return clientDTO;
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public ClientDTO getClientWithProjectsById(Long clientId) {
        logger.info("Fetching client with ID {}", clientId);
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            ClientDTO clientDTO = mapToClientDTO(client);
            logger.info("Fetched client with ID {}", clientId);
            return clientDTO;
        } else {
            logger.error("Client not found with ID {}", clientId);
            throw new RuntimeException("Client not found with ID: " + clientId);
        }
    }
}


// working fine

//import com.revtaskmanagement.RevTask.DTO.ClientDTO;
//import com.revtaskmanagement.RevTask.DTO.ProjectCustomDTO;
//import com.revtaskmanagement.RevTask.DTO.ProjectDTO;
//import com.revtaskmanagement.RevTask.Entity.Client;
//import com.revtaskmanagement.RevTask.Repository.ClientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class ClientService {
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Autowired
//    private ProjectService projectService;
////    running
//
//    public List<ClientDTO> getAllClientsWithProjects() {
//        List<Client> clients = clientRepository.findAll();
//        return clients.stream()
//                .map(this::mapToClientDTO)
//                .collect(Collectors.toList());
//    }
//
//    private ClientDTO mapToClientDTO(Client client) {
//        ClientDTO clientDTO = new ClientDTO();
//        clientDTO.setId(client.getId());
//        clientDTO.setName(client.getName());
//        clientDTO.setEmail(client.getEmail());
//        List<ProjectCustomDTO> projectDTOs = client.getProjects().stream()
//                .map(project -> new ProjectCustomDTO(project.getId(), project.getName()))
//                .collect(Collectors.toList());
//        clientDTO.setProjects(projectDTOs); // Set projects in ClientDTO
//        return clientDTO;
//    }
//
//    public ClientDTO getClientWithProjectsById(Long clientId) {
//        Optional<Client> optionalClient = clientRepository.findById(clientId);
//        if (optionalClient.isPresent()) {
//            Client client = optionalClient.get();
//            return mapToClientDTO(client);
//        } else {
//            throw new RuntimeException("Client not found with ID: " + clientId);
//        }
//    }

//        private ClientDTO mapToClientDTO(Client client) {
//            ClientDTO clientDTO = new ClientDTO();
//            clientDTO.setId(client.getId());
//            clientDTO.setName(client.getName());
//            clientDTO.setEmail(client.getEmail());
//            List<ProjectCustomDTO> projectDTOs = client.getProjects().stream()
//                    .map(project -> new ProjectCustomDTO(project.getId(), project.getName()))
//                    .collect(Collectors.toList());
//            clientDTO.setProjects(projectDTOs); // Set projects in ClientDTO
//            return clientDTO;
//        }

//
//        return new ProjectDTO(
//                project.getId(),
//                project.getName(),
//                project.getDescription(),
//                project.getClient().getName(),
//                project.getProjectManager().getId(),
//                project.getProjectManager().getUsername(),
//                taskDetails
//        );
//
//}



//    public ClientDTO getClientById(Long id) {
//
//        return clientRepository.findClientDTOByIdWithProjects(id);
//    }


//    public Client createClient(Client client) {
//        return clientRepository.save(client);
//    }
//
//    public Client updateClient(Long id, Client clientDetails) {
//        Client client = clientRepository.findById(id).orElseThrow();
//        client.setName(clientDetails.getName());
//        client.setEmail(clientDetails.getEmail());
//        return clientRepository.save(client);
//    }

//    public boolean deleteClient(Long id) {
//        clientRepository.deleteById(id);
//        return true;
//    }
