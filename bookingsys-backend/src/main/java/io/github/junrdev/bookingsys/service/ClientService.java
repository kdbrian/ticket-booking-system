package io.github.junrdev.bookingsys.service;

import io.github.junrdev.bookingsys.domain.dto.ClientDto;
import io.github.junrdev.bookingsys.model.Client;

import java.util.List;

public interface ClientService {

    // Create a new Client
    Client createClient(ClientDto dto);

    // Retrieve all Clients
    List<Client> getAllClients();

    // Retrieve a single Client by ID
    Client getClientById(String id);

    // Update an existing Client
    Client updateClient(String id, ClientDto updatedClient);

    // Delete a Client by ID
    void deleteClient(String id);

    void deactivateClient(String id, ClientDto client);
}
