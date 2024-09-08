package io.github.junrdev.bookingsystem.service;

import io.github.junrdev.bookingsystem.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    // Create a new Client
    Client createClient(Client client);

    // Retrieve all Clients
    List<Client> getAllClients();

    // Retrieve a single Client by ID
    Optional<Client> getClientById(Long id);

    // Update an existing Client
    Client updateClient(Long id, Client updatedClient);

    // Delete a Client by ID
    void deleteClient(Long id);

    void deactivateClient(Long id, Client client);
}
