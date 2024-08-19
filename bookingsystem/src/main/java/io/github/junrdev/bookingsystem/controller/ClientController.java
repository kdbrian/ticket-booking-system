package io.github.junrdev.bookingsystem.controller;

import io.github.junrdev.bookingsystem.model.Client;
import io.github.junrdev.bookingsystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
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
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok)
                .orElseThrow(() -> new ClassCastException("Client Not found with id " + id));
    }

    // Update an existing Client
    @PatchMapping("/{id}/update")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        try {
            Client client = clientService.updateClient(id, updatedClient);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Client by ID
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}/deactivate")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id, @RequestBody Client client) {
        clientService.deactivateClient(id, client);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}