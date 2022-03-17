package org.jeansamuel.swagg.services;

import org.jeansamuel.swagg.dto.CommandeClientDto;
import org.jeansamuel.swagg.dto.EntrepriseDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto save(EntrepriseDto entrepriseDto) throws InvalidEntityException;

    EntrepriseDto findById(Integer id) throws EntityNotFoundException;

    List<EntrepriseDto> findAll();

    void delete(Integer id);

}
