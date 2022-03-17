package org.jeansamuel.swagg.controller;

import org.jeansamuel.swagg.controller.api.ClientApi;
import org.jeansamuel.swagg.dto.ClientDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController implements ClientApi {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public ClientDto save(ClientDto clientDto) throws InvalidEntityException {
        return clientService.save(clientDto);
    }

    @Override
    public ClientDto findById(Integer idClient) throws EntityNotFoundException {
        return clientService.findById(idClient);
    }

    @Override
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @Override
    public void delete(Integer idClient) {
        clientService.delete(idClient);
    }
}
