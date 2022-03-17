package org.jeansamuel.swagg.controller;

import org.jeansamuel.swagg.controller.api.CommandeFournisseurApi;
import org.jeansamuel.swagg.dto.CommandeFournisseurDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.services.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi{

    private CommandeFournisseurService commandeFournisseurService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService commandeFournisseurService) {
        this.commandeFournisseurService = commandeFournisseurService;
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) throws InvalidEntityException, EntityNotFoundException {
        return commandeFournisseurService.save(commandeFournisseurDto);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) throws EntityNotFoundException {
        return commandeFournisseurService.findById(id);
    }

    @Override
    public CommandeFournisseurDto findByCode(String code) throws EntityNotFoundException {
        return commandeFournisseurService.findByCode(code);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        commandeFournisseurService.delete(id);

    }
}
