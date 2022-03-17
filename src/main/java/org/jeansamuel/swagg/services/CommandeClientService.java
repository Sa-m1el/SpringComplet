package org.jeansamuel.swagg.services;

import org.jeansamuel.swagg.dto.ArticleDto;
import org.jeansamuel.swagg.dto.CommandeClientDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto save(CommandeClientDto commandeClientDto) throws InvalidEntityException, EntityNotFoundException;

    CommandeClientDto findById(Integer id) throws EntityNotFoundException;

    CommandeClientDto findByCode(String code) throws EntityNotFoundException;

    List<CommandeClientDto> findAll();

    void delete(Integer id);

}
