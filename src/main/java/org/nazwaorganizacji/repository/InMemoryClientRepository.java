package org.nazwaorganizacji.repository;

import org.nazwaorganizacji.Client;

import java.util.Set;

public class InMemoryClientRepository implements ClientRepository {
    private Set<Client> clients;

    public InMemoryClientRepository(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public void save(Client client) {
        clients.add(client);
    }

    @Override
    public Client findByEmail(String email) {
        return clients
                .stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst()
                .get();

    }
}
