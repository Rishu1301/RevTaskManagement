package com.revtaskmanagement.RevTask.Controller;

//import com.revtaskmanagement.RevTask.Entity.Client;
//import com.revtaskmanagement.RevTask.Service.ClientService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/client")
//public class ClientController {
//
//    @Autowired
//    private ClientService clientService;
//
//    @GetMapping
//    public List<Client> getAllClients() {
//        return clientService.getAllClients();
//    }
//
//    @GetMapping("/{id}")
//    public Client getClientById(@PathVariable Long id) {
//        return clientService.getClientById(id);
//    }
//
//    @PostMapping
//    public Client createClient(@RequestBody Client client) {
//        return clientService.createClient(client);
//    }
//
//    @PutMapping("/{id}")
//    public Client updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
//        return clientService.updateClient(id, clientDetails);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteClient(@PathVariable Long id) {
//        clientService.deleteClient(id);
//    }
//}

import com.revtaskmanagement.RevTask.DTO.ClientDTO;
import com.revtaskmanagement.RevTask.Entity.Client;
import com.revtaskmanagement.RevTask.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {

        List<ClientDTO> client = clientService.getAllClientsWithProjects();
        if(client==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {

//        return clientService.getClientById(id);
        ClientDTO client = clientService.getClientWithProjectsById(id);
        if(client == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(client,HttpStatus.OK);
        }
    }
}
