package org.nazwaorganizacji.service;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nazwaorganizacji.Client;
import org.nazwaorganizacji.repository.InMemoryClientRepository;

import java.util.HashSet;

public class BankServiceTest {

    private BankService service;
    private HashSet<Client> clients;

    @BeforeEach
    public void setup() {
        clients = new HashSet<>();
        service = new BankService(new InMemoryClientRepository(clients));
    }

    @Test
    public void transfer_allParamsOk_fundsTransferred() {
        //given
        String emailFrom = "a@a.pl";
        String emailTo = "b@b.pl";
        final Client clientFrom = new Client("Alek", emailFrom, 1000);
        final Client clientTo = new Client("Bartek", emailTo, 500);
        final double amount = 100;
        clients.add(clientFrom);
        clients.add(clientTo);
        //when
        service.transfer(emailFrom, emailTo, amount);
        //then
        final Client actualFromClient = service.findByEmail(emailFrom);
        final Client actualToClient = service.findByEmail(emailTo);
        Client expectedClientFrom = new Client("Alek", emailFrom, 900);
        Client expectedClientTo = new Client("Bartek", emailTo, 600);
        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedClientFrom).isEqualTo(actualFromClient);
        softAssertions.assertThat(expectedClientTo).isEqualTo(actualToClient);
        softAssertions.assertAll();
    }

    @Test
    public void transfer_allFunds_fundsTransferred() {
        //given
        String emailFrom = "a@a.pl";
        String emailTo = "b@b.pl";
        final Client clientFrom = new Client("Alek", emailFrom, 1000);
        final Client clientTo = new Client("Bartek", emailTo, 500);
        final double amount = 1000;
        clients.add(clientFrom);
        clients.add(clientTo);
        //when
        service.transfer(emailFrom, emailTo, amount);
        //then
        final Client actualFromClient = service.findByEmail(emailFrom);
        final Client actualToClient = service.findByEmail(emailTo);
        Client expectedClientFrom = new Client("Alek", emailFrom, 0);
        Client expectedClientTo = new Client("Bartek", emailTo, 1500);
        final SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(expectedClientFrom).isEqualTo(actualFromClient);
        softAssertions.assertThat(expectedClientTo).isEqualTo(actualToClient);
        softAssertions.assertAll();
    }

    @Test
    public void transfer_notEnoughFunds_fundsTransferred_thrownNoSufficientFundsException() {
        //given
        String emailFrom = "a@a.pl";
        String emailTo = "b@b.pl";
        final Client clientFrom = new Client("Alek", emailFrom, 100);
        final Client clientTo = new Client("Bartek", emailTo, 500);
        final double amount = 1000;
        clients.add(clientFrom);
        clients.add(clientTo);
        //when+then
        Assertions
                .assertThrows(NoSufficientFundsException.class,
                        () -> service.transfer(emailFrom, emailTo, amount));
    }

    @Test
    public void transfer_negativeAmount_fundsTransferred_thrownIllegalArgumentException() {
        //given
        String emailFrom = "a@a.pl";
        String emailTo = "b@b.pl";
        final Client clientFrom = new Client("Alek", emailFrom, 100);
        final Client clientTo = new Client("Bartek", emailTo, 500);
        final double amount = -1000;
        clients.add(clientFrom);
        clients.add(clientTo);
        //when+then
        Assertions
                .assertThrows(IllegalArgumentException.class,
                        () -> service.transfer(emailFrom, emailTo, amount));
    }
    @Test
    public void transfer_toSameClient_thrownException(){
//        given
        String email = "a@a.pl";
        final Client client= new Client("Alek", email, 100);
        clients.add(client);
        //when+then
        Assertions.assertThrows(Exception.class,() -> service.transfer(email,email,10));





    }

}

