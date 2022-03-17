package org.jeansamuel.swagg.services;

import org.jeansamuel.swagg.dto.CommandeClientDto;
import org.jeansamuel.swagg.dto.VentesDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;

import java.util.List;

public interface VentesService {

    VentesDto save(VentesDto ventesDto) throws InvalidEntityException, EntityNotFoundException;

    VentesDto findById(Integer id) throws EntityNotFoundException;

    VentesDto findByCode(String code) throws EntityNotFoundException;

    List<VentesDto> findAll();

    void delete(Integer id);

}
