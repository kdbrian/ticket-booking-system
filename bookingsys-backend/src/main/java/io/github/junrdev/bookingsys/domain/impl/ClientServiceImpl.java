package io.github.junrdev.bookingsys.domain.impl;

import io.github.junrdev.bookingsys.domain.dto.ClientDto;
import io.github.junrdev.bookingsys.model.Client;
import io.github.junrdev.bookingsys.repository.ClientRepository;
import io.github.junrdev.bookingsys.service.ClientService;
import io.github.junrdev.bookingsys.util.mappers.ClientMapper;
import io.github.junrdev.bookingsystem.error.model.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public Client createClient(ClientDto client) {
        client.setIdentification(UUID.randomUUID().toString());
        return clientRepository.save(clientMapper.toEntity(client));
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(String id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client " + id + " not found."));
    }

    @Override
    public Client updateClient(String id, ClientDto dto) {
        return clientRepository.findById(id)
                .map(client -> {
                    //transform save return
                    if (dto.getKyc() != null)
                        client.setKyc(dto.getKyc());

                    if (dto.getEmail() != null)
                        client.setEmail(dto.getEmail());

                    if (dto.getPhone() != null)
                        client.setPhone(dto.getPhone());

                    if (dto.getLocation() != null)
                        client.setLocation(dto.getLocation());

                    return clientRepository.save(client);
                })
                .orElseThrow(() -> new NotFoundException(String.format("Client with id %s not found.", id)));
    }

    @Override
    public void deleteClient(String id) {
        if (clientRepository.existsById(id))
            clientRepository.deleteById(id);
        else
            throw new NotFoundException(String.format("Client with id %s not found.", id));
    }

    @Override
    public void deactivateClient(String id, ClientDto dto) {
        clientRepository.findById(id)
                .map(client -> {
                    client.setActive(false);
                    return clientRepository.save(client);
                })
                .orElseThrow(() -> new NotFoundException(String.format("Client with id %s not found.", id)));
    }
}
