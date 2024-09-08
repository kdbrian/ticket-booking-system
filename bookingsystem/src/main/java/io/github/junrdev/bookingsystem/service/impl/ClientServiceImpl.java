package io.github.junrdev.bookingsystem.service.impl;

import io.github.junrdev.bookingsystem.error.model.ClientNotFoundException;
import io.github.junrdev.bookingsystem.model.Client;
import io.github.junrdev.bookingsystem.repository.ClientRepository;
import io.github.junrdev.bookingsystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Create a new Client
    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Retrieve all Clients
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Retrieve a single Client by ID
    @Override
    public Optional<Client> getClientById(Long id) {
        if (clientRepository.existsById(id))
            return clientRepository.findById(id);
        throw new ClientNotFoundException("Client not found");
    }

    // Update an existing Client
    @Override
    public Client updateClient(Long id, Client updatedClient) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setFullName(updatedClient.getFullName().trim());
                    client.setEmail(updatedClient.getEmail().trim());
                    client.setPhone(updatedClient.getPhone().trim());
                    client.setLocation(updatedClient.getLocation());
                    client.setKyc(updatedClient.getKyc());
                    return clientRepository.save(client);
                }).orElseThrow(() ->
                        new ClientNotFoundException("Client not found")
                );
    }

    // Delete a Client by ID
    @Override
    public void deleteClient(Long id) {
        if (clientRepository.existsById(id))
            clientRepository.deleteById(id);
        else
            throw new ClientNotFoundException("Client not found");
    }

    @Override
    public void deactivateClient(Long id, Client client) {
        if (clientRepository.existsById(id)) {
            client.setActive(false);
            clientRepository.save(client);
        } else
            throw new ClientNotFoundException("Client not found");

    }
}
