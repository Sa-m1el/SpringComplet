package org.jeansamuel.swagg.controller;

import org.jeansamuel.swagg.controller.api.EntrepriseApi;
import org.jeansamuel.swagg.dao.EntrepriseRepository;
import org.jeansamuel.swagg.dto.EntrepriseDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EntrepriseController implements EntrepriseApi {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseController(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) throws InvalidEntityException {
        return entrepriseRepository.save(entrepriseDto);
    }

    @Override
    public EntrepriseDto findById(Integer id) throws EntityNotFoundException {
        return null;
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
