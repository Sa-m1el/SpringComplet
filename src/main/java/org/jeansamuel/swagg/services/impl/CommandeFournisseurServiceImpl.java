package org.jeansamuel.swagg.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeansamuel.swagg.dao.*;

import org.jeansamuel.swagg.dto.CommandeFournisseurDto;
import org.jeansamuel.swagg.dto.LigneCommandeClientDto;
import org.jeansamuel.swagg.dto.LigneCommandeFournisseurDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.ErrorCodes;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.model.*;
import org.jeansamuel.swagg.services.CommandeFournisseurService;
import org.jeansamuel.swagg.validator.CommandeClientValidator;
import org.jeansamuel.swagg.validator.CommandeFournisseurValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {
    private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
    private CommandeFournisseurRepository commandeFournisseurRepository;
    private FournisseurRepository fournisseurRepository;
    private ArticleRepository articleRepository;

    @Autowired
    public CommandeFournisseurServiceImpl(LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository, CommandeFournisseurRepository commandeFournisseurRepository, ArticleRepository articleRepository, FournisseurRepository fournisseurRepository) {
        this.commandeFournisseurRepository = commandeFournisseurRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeFournisseurRepository = ligneCommandeFournisseurRepository;
    }
    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFournisseurDto) throws InvalidEntityException, EntityNotFoundException {
        List<String> errors = CommandeFournisseurValidator.validate(commandeFournisseurDto);

        if( !errors.isEmpty()){
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.COMMANDE_FOURNISSEUR_NOT_VALID, errors);
        }

        //verification du FOURNISSEUR
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(commandeFournisseurDto.getFournisseur().getId());
        if (fournisseur.isEmpty()){
            log.warn("Fournisseur with ID {} was not found in DB", commandeFournisseurDto.getFournisseur().getId());
            throw new EntityNotFoundException("Aucun fournisseur avec l'ID" + commandeFournisseurDto.getFournisseur().getId() + "n'a été trouvé", ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }
        //verification de l'article
        List<String> articleErrors = new ArrayList<>();

        if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null){
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligneCmdFrN ->{
                if (ligneCmdFrN.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligneCmdFrN.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'ID" + ligneCmdFrN.getArticle().getId() + "n'existe pas");
                    }

                }else{
                    articleErrors.add("Impossible d'enrégistrer une commande avec un article NULL");
                }
            });
        }
        if(!articleErrors.isEmpty()){
            log.warn("");
            throw new InvalidEntityException("l'article n'existe pas dans la base de donnée", ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }
        //enregistrer une ligne de commande client
        CommandeFournisseur savedCmdFrN = commandeFournisseurRepository.save(CommandeFournisseurDto.toEntity(commandeFournisseurDto));

        if (commandeFournisseurDto.getLigneCommandeFournisseurs() != null) {
            commandeFournisseurDto.getLigneCommandeFournisseurs().forEach(ligneCmdFrN -> {
                LigneCommandeFournisseur ligneCommandeFournisseur = LigneCommandeFournisseurDto.toEntity(ligneCmdFrN);
                ligneCommandeFournisseur.setCommandeFournisseur(savedCmdFrN);
                ligneCommandeFournisseurRepository.save(ligneCommandeFournisseur);
            });
        }
        return CommandeFournisseurDto.fromEntity(savedCmdFrN);
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) throws EntityNotFoundException {
        if( id == null){
            log.error("Commande client ID is NULL");
            return null;
        }
        return commandeFournisseurRepository.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        " Aucune commande fournisseur n'a ete trouve avec l'id " + id , ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    //une méthode à été crée dans le repository
    public CommandeFournisseurDto findByCode(String code) throws EntityNotFoundException {
        if( !StringUtils.hasLength(code)){
            log.error("Commande fournisseur CODE is NULL");
            return null;
        }
        return commandeFournisseurRepository.findCommandeFournisseurByCode(code)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        " Aucune commande fournisseur n'a ete trouve avec le CODE " + code , ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return commandeFournisseurRepository.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Commande fournisseur ID is null");
            return;
        }
        commandeFournisseurRepository.deleteById(id);
    }
}
