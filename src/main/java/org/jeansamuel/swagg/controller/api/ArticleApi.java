package org.jeansamuel.swagg.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jeansamuel.swagg.dto.ArticleDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;

import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

import static org.jeansamuel.swagg.utils.Constants.APP_ROOT;


@Api(APP_ROOT + "/articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un article", notes = "Cette méthode permet d'enregistrer ou modifier un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet article est crée ou modifier"),
            @ApiResponse(code = 404, message = "L'objet article n'est pas valide")
    })
    ArticleDto save(@RequestBody ArticleDto articleDto) throws InvalidEntityException;

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de chercher un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article à ete trouvé dans la base de donnée"),
            @ApiResponse(code = 404, message = "Aucun article dans la base de donnée avec l'ID")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id) throws EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article", notes = "Cette méthode permet de chercher un article", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article à ete trouvé dans la base de donnée"),
            @ApiResponse(code = 404, message = "Aucun article dans la base de donnée avec l'ID")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle")String codeArticle) throws EntityNotFoundException;

    @GetMapping(value = APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoie la liste des articles", notes = "Cette méthode permet de chercher et renvoyer les articles qui existent dans la BDD", responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des articles ou liste vide")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "supprimer un article", notes = "Cette méthode permet de permet de supprimer un article par ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article à ete supprimé dans la BDD")
    })
    void delete(@PathVariable("idArticle") Integer id);

}
