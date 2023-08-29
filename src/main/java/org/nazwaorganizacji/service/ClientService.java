package org.nazwaorganizacji.service;

import org.nazwaorganizacji.DTO.ClientRequest;
import org.nazwaorganizacji.DTO.ClientResponse;
import org.nazwaorganizacji.repository.ClientRepository;
import org.nazwaorganizacji.repository.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper mapper;

    @Autowired
    public ClientService(
            ClientRepository clientRepository,
            ClientMapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public void save(ClientRequest clientRequest) {
        Client client = null;
        clientRepository.save(client);


    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public ClientResponse findResponseByEmail(String email) {
        Client client = clientRepository.findByEmail(email);
        return mapper.map(client);
    }
}