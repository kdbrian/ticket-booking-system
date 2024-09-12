package io.github.junrdev.bookingsys.controller.rest;

import io.github.junrdev.bookingsys.domain.dto.ClientDto;
import io.github.junrdev.bookingsys.model.Client;
import io.github.junrdev.bookingsys.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Create a new Client
    @PostMapping("/new")
    public ResponseEntity<Client> createClient(@RequestBody ClientDto client) {
        Client createdClient = clientService.createClient(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    // Retrieve all Clients
    @GetMapping("/")
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // Retrieve a single Client by ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable String id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    // Update an existing Client
    @PatchMapping("/{id}/update")
    public ResponseEntity<Client> updateClient(@PathVariable String id, @RequestBody ClientDto updatedClient) {
        try {
            Client client = clientService.updateClient(id, updatedClient);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Client by ID
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deleteClient(@PathVariable String id, @RequestBody ClientDto client) {
        clientService.deactivateClient(id, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}