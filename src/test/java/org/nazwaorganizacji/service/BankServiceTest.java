package org.nazwaorganizacji.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nazwaorganizacji.repository.SpringJpaClientRepository;
import org.nazwaorganizacji.repository.entity.Account;
import org.nazwaorganizacji.repository.entity.Client;

import static java.util.Collections.singletonList;
import static org.mockito.Mockito.*;

public class BankServiceTest {

    private BankService service;
    private SpringJpaClientRepository repository;

    @BeforeEach
    public void setup() {
        repository = mock(SpringJpaClientRepository.class);
        ClientMapper mapper = mock(ClientMapper.class);
        service = new BankService(repository, mapper);
    }

    @Test
    public void transfer_allParamsOk_fundsTransferred() {
        //given
        String emailFrom = "a@a.pl";
        String emailTo = "b@b.pl";
        final Client clientFrom = new Client(
                "Alek",
                emailFrom,
                singletonList(new Account(1000, "PLN"))
        );
        final Client clientTo = new Client(
                "Bartek",
                emailTo,
                singletonList(new Account(500, "PLN"))
        );
        final double amount = 100;
        when(repository.findByEmail(emailFrom)).thenReturn(clientFrom);
        when(repository.findByEmail(emailTo)).thenReturn(clientTo);
        //when
        service.transfer(emailFrom, emailTo, amount);
        //then
        final Client actualFromClient = service.findByEmail(emailFrom);
        final Client actualToClient = service.findByEmail(emailTo);
        Client expectedClientFrom = new Client(
                "Alek",
                emailFrom,
                singletonList(new Account(900, "PLN"))
        );
        Client expectedClientTo = new Client(
                "Bartek",
                emailTo,
                singletonList(new Account(600, "PLN"))
        );
        verify(repository).save(expectedClientFrom);
        verify(repository).save(expectedClientTo);
    }


    @Test
    public void transfer_allFunds_fundsTransferred() {
        //given
        String emailFrom = "a@a.pl";
        String emailTo = "b@b.pl";
        final Client clientFrom = new Client("Alek", emailFrom, singletonList(new Account(1000, "PLN")));
        final Client clientTo = new Client("Bartek", emailTo, singletonList(new Account(500, "PLN")));
        final double amount = 1000;
        when(repository.findByEmail(emailFrom)).thenReturn(clientFrom);
        when(repository.findByEmail(emailTo)).thenReturn(clientTo);
        //when
        service.transfer(emailFrom, emailTo, amount);
        //then
        final Client actualFromClient = service.findByEmail(emailFrom);
        final Client actualToClient = service.findByEmail(emailTo);
        Client expectedClientFrom = new Client("Alek", emailFrom, singletonList(new Account(0, "PLN")));
        Client expectedClientTo = new Client("Bartek", emailTo, singletonList(new Account(1500, "PLN")));
        verify(repository).save(expectedClientFrom);
        verify(repository).save(expectedClientTo);
//        final SoftAssertions softAssertions = new SoftAssertions();
//        softAssertions.assertThat(expectedClientFrom).isEqualTo(actualFromClient);
//        softAssertions.assertThat(expectedClientTo).isEqualTo(actualToClient);
//        softAssertions.assertAll();
    }

    @Test
    public void transfer_notEnoughFunds_fundsTransferred_thrownNoSufficientFundsException() {
        //given
        String emailFrom = "a@a.pl";
        String emailTo = "b@b.pl";
        final Client clientFrom = new Client("Alek", emailFrom, singletonList(new Account(100, "PLN")));
        final Client clientTo = new Client("Bartek", emailTo, singletonList(new Account(500, "PLN")));
        final double amount = 1000;
        when(repository.findByEmail(emailFrom)).thenReturn(clientFrom);
        when(repository.findByEmail(emailTo)).thenReturn(clientTo);
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
        final double amount = -1000;
        //when+then
        Assertions
                .assertThrows(IllegalArgumentException.class,
                        () -> service.transfer(emailFrom, emailTo, amount));
    }

    @Test
    public void transfer_toSameClient_thrownException() {
//        given
        String email = "a@a.pl";
        //when+then
        Assertions.assertThrows(Exception.class, () -> service.transfer(email, email, 10));

    }

    @Test
    public void withdraw_correctAmount_balanceChangeCorrectly() {
        //given
        final String email = "a@a.pl";
        final Client client = new Client("Alek", email, singletonList(new Account(100, "PLN")));
        when(repository.findByEmail(email)).thenReturn(client);
        //when
        service.withdraw(email, 50);
        //then
        Client expectedClient = new Client("Alek", email, singletonList(new Account(50, "PLN")));
        verify(repository).save(expectedClient);
//        final Client actualClient = ;
//        Assertions.assertEquals(expectedClient, actualClient);
    }

}

