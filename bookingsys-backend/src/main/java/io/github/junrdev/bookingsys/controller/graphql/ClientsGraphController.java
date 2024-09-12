package io.github.junrdev.bookingsys.controller.graphql;

import io.github.junrdev.bookingsys.domain.dto.ClientDto;
import io.github.junrdev.bookingsys.model.Client;
import io.github.junrdev.bookingsys.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClientsGraphController {

    private final ClientService clientService;

    @Autowired
    public ClientsGraphController(ClientService clientService) {
        this.clientService = clientService;
    }

    @QueryMapping
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    @QueryMapping
    public Client getClientById(@Argument String id){
        return clientService.getClientById(id);
    }

    @MutationMapping
    public Client addClient(@Argument ClientDto dto){
        return clientService.createClient(dto);
    }

}
