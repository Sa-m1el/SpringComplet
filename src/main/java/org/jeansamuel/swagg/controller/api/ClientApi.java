package org.jeansamuel.swagg.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jeansamuel.swagg.dto.CategoryDto;
import org.jeansamuel.swagg.dto.ClientDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.jeansamuel.swagg.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/clients")
public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un client", notes = "Cette méthode permet d'enregistrer ou modifier un client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client est crée ou modifier"),
            @ApiResponse(code = 404, message = "Le client n'est pas valide")
    })
    ClientDto save(@RequestBody ClientDto clientDto) throws InvalidEntityException;

    @GetMapping(value = APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client par ID", notes = "Cette méthode permet de chercher un client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client à ete trouvé dans la base de donnée"),
            @ApiResponse(code = 404, message = "Aucune client dans la base de donnée avec l'ID")
    })
    ClientDto findById(@PathVariable("idClient")Integer idClient) throws EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des clients", notes = "Cette méthode permet de chercher et renvoyer les clients qui existent dans la BDD", responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des clients ou liste vide")
    })
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClients}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "supprimer un client", notes = "Cette méthode permet  de supprimer un client par ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client à ete supprimé dans la BDD")
    })
    void delete(@PathVariable("idClient")Integer idClient);
}
