package org.nazwaorganizacji.service;

import org.nazwaorganizacji.Client;
import org.nazwaorganizacji.repository.ClientRepository;

public class BankService {
    private ClientRepository clientRepository;

    public BankService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public void transfer(String fromEmail, String toEmail, double amount) {

    }


}
