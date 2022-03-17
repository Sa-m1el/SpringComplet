package org.jeansamuel.swagg.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jeansamuel.swagg.dto.ArticleDto;
import org.jeansamuel.swagg.dto.CategoryDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.jeansamuel.swagg.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie", notes = "Cette méthode permet d'enregistrer ou modifier une categorie", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie est crée ou modifier"),
            @ApiResponse(code = 404, message = "La categorie n'est pas valide")
    })
    CategoryDto save(@RequestBody CategoryDto categoryDto) throws InvalidEntityException;

    @GetMapping(value = APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie", notes = "Cette méthode permet de chercher une categorie", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie à ete trouvé dans la base de donnée"),
            @ApiResponse(code = 404, message = "Aucune categorie dans la base de donnée avec l'ID")
    })
    CategoryDto findById(@PathVariable("idCategory")Integer idCategory) throws EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/categories/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie", notes = "Cette méthode permet de chercher une categorie", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie à ete trouvé dans la base de donnée"),
            @ApiResponse(code = 404, message = "Aucune categorie dans la base de donnée avec l'ID")
    })
    CategoryDto findByCode(@PathVariable("codeCategory")String code) throws EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste de categorie", notes = "Cette méthode permet de chercher et renvoyer les categories qui existent dans la BDD", responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des categories ou liste vide")
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "supprimer une categorie", notes = "Cette méthode permet  de supprimer une category par ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie à ete supprimé dans la BDD")
    })
    void delete(@PathVariable("idCategory")Integer id);

}
