package org.jeansamuel.swagg.services;

import org.jeansamuel.swagg.dto.CommandeClientDto;
import org.jeansamuel.swagg.dto.CommandeFournisseurDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;

import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) throws InvalidEntityException, EntityNotFoundException;

    CommandeFournisseurDto findById(Integer id) throws EntityNotFoundException;

    CommandeFournisseurDto findByCode(String code) throws EntityNotFoundException;

    List<CommandeFournisseurDto> findAll();

    void delete(Integer id);

}
