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
        if (amount < 0) {
            throw new IllegalArgumentException("Negative amount is not allowed!");
        }
        if (fromEmail.equals(toEmail)) {
            throw new RuntimeException("fromEmail and toEmail cannot be equal!");
        }
        Client fromClient = findByEmail(fromEmail);
        Client toClient = findByEmail(toEmail);

        if (amount > 0 && fromClient.getBalance() - amount >= 0) {
            fromClient.setBalance(fromClient.getBalance() - amount);
            toClient.setBalance(toClient.getBalance() + amount);
        } else {
            throw new NoSufficientFundsException("Not enough funds!");
        }


    }


}
