package org.jeansamuel.swagg.services;

import org.jeansamuel.swagg.dto.ArticleDto;
import org.jeansamuel.swagg.dto.ClientDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;

import java.util.List;

public interface ClientService {

    ClientDto save(ClientDto clientDto) throws InvalidEntityException;

    ClientDto findById(Integer id) throws EntityNotFoundException;

    List<ClientDto> findAll();

    void delete(Integer id);
}
