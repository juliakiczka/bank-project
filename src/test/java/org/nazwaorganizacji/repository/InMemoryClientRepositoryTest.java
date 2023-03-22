package org.nazwaorganizacji.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nazwaorganizacji.Client;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;

public class InMemoryClientRepositoryTest {
    private InMemoryClientRepository repository;
    private HashSet<Client> clients;

    @BeforeEach
    public void setup() {
        clients = new HashSet<>();
        repository = new InMemoryClientRepository(clients);
    }


    @Test
    public void verifyIfUserIsAddingCorrectlyToTheRepository() {
//        given - to co mamy (obiekty, które chcemy przetestować)
        Client client = new Client("Alek", "a@a.pl", 100);
        Client expectedClient = new Client("Alek", "a@a.pl", 100);
//        when - czyli jeżeli coś zrobimy to czego oczekujemy
        repository.save(client);
//        then - to czego oczekujemy
        Client actualClient = clients.stream().findFirst().get();
        assertEquals(expectedClient, actualClient);


    }


}
