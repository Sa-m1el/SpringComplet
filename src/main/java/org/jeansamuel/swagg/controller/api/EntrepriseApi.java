package org.jeansamuel.swagg.controller.api;

import io.swagger.annotations.Api;
import org.jeansamuel.swagg.dto.EntrepriseDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.jeansamuel.swagg.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/entreprises")
public interface EntrepriseApi {

    @PostMapping(value = APP_ROOT + "/entreprises/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto save(@RequestBody EntrepriseDto entrepriseDto) throws InvalidEntityException;

    @GetMapping(value = APP_ROOT + "/entreprises/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    EntrepriseDto findById(@PathVariable Integer id) throws EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/entreprises/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/entreprises/delete/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idEntreprise")Integer id);

}
