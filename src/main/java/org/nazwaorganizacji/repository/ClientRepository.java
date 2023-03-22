package org.nazwaorganizacji.repository;

import org.nazwaorganizacji.Client;

public interface ClientRepository {
    void save(Client client);
    Client findByEmail(String email);



}
