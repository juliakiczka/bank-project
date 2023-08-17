package org.nazwaorganizacji.service;

import org.nazwaorganizacji.DTO.ClientRequest;
import org.nazwaorganizacji.DTO.ClientResponse;
import org.nazwaorganizacji.repository.entity.Account;
import org.nazwaorganizacji.repository.entity.Client;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component//ta klasa ma być traktowana jako bean
public class ClientMapper {
    public ClientResponse map(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .accounts(client.getAccounts().stream().map(Account::getId).collect(Collectors.toList()))
                .build();
    }

    public Client map(ClientRequest clientRequest) {
        return Client.builder()
                .name(clientRequest.getName())
                .email(clientRequest.getEmail())
                .build();
    }
}
