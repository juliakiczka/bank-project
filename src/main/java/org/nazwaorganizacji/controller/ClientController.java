package org.nazwaorganizacji.controller;

import lombok.RequiredArgsConstructor;
import org.nazwaorganizacji.DTO.ClientRequest;
import org.nazwaorganizacji.DTO.ClientResponse;
import org.nazwaorganizacji.service.ClientService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClientController {
    private final ClientService service;

    @GetMapping(path = "/api/user")
    public ResponseEntity<ClientResponse> findByEmail(@RequestParam String email) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("EXAMPLE_HEADER", "DUMMY_VALUE");
        return new ResponseEntity<>(service.findResponseByEmail(email), httpHeaders, HttpStatus.ACCEPTED);
    }

    //    drugi sposób mniej znany
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addClient(@RequestBody ClientRequest client) {
        service.save(client);
    }

}
