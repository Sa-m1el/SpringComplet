package org.jeansamuel.swagg.controller.api;

import io.swagger.annotations.Api;
import org.jeansamuel.swagg.dto.CommandeClientDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.jeansamuel.swagg.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandesclients")
public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT + "/commandesclients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeClientDto) throws InvalidEntityException, EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/commandesclients/{idCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer idCommandeClient) throws EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/commandesclients/codeCommandeClient", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code) throws EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/commandesclients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandesclients/delete/{idCommandeClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity delete(@PathVariable("idCommandeClient") Integer id);

}
