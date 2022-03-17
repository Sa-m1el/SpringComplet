package org.jeansamuel.swagg.controller;

import org.jeansamuel.swagg.controller.api.CommandeClientApi;
import org.jeansamuel.swagg.dto.CommandeClientDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.services.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService commandeClientService;

    @Autowired
    public CommandeClientController(CommandeClientService commandeClientService) {
        this.commandeClientService = commandeClientService;
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto commandeClientDto) throws InvalidEntityException, EntityNotFoundException {
        /*return ResponseEntity.ok(commandeClientService.save(commandeClientDto));*/
        return ResponseEntity.status(HttpStatus.OK).body(commandeClientService.save(commandeClientDto));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findById(Integer id) throws EntityNotFoundException {
        return ResponseEntity.ok(commandeClientService.findById(id));
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) throws EntityNotFoundException {
        return ResponseEntity.ok(commandeClientService.findByCode(code));
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        return ResponseEntity.ok(commandeClientService.findAll());
    }

    @Override
    public ResponseEntity delete(Integer id) {
        commandeClientService.delete(id);
        return ResponseEntity.ok().build();

    }
}
