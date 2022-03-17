package org.jeansamuel.swagg.controller.api;

import io.swagger.annotations.Api;
import org.jeansamuel.swagg.dto.CommandeFournisseurDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.jeansamuel.swagg.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandesfournisseurs")
public interface CommandeFournisseurApi {

    @PostMapping(value = APP_ROOT + "/commandesfournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto commandeFournisseurDto) throws InvalidEntityException, EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/commandesfournisseurs/{idCommandeFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findById(@PathVariable Integer id) throws EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/commandesfournisseurs/codeCommandeFournisseur", produces = MediaType.APPLICATION_JSON_VALUE)
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code) throws EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/commandesfournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandesfournisseurs/delete/{idCommandeFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    void delete(@PathVariable("idCommandeFournisseur") Integer id);
}
