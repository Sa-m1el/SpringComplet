package org.jeansamuel.swagg.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeansamuel.swagg.dao.ArticleRepository;
import org.jeansamuel.swagg.dao.ClientRepository;
import org.jeansamuel.swagg.dao.CommandeClientRepository;
import org.jeansamuel.swagg.dao.LigneCommandeClientRepository;
import org.jeansamuel.swagg.dto.CommandeClientDto;
import org.jeansamuel.swagg.dto.LigneCommandeClientDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.ErrorCodes;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.model.Article;
import org.jeansamuel.swagg.model.Client;
import org.jeansamuel.swagg.model.CommandeClient;
import org.jeansamuel.swagg.model.LigneCommandeClient;
import org.jeansamuel.swagg.services.CommandeClientService;
import org.jeansamuel.swagg.validator.CommandeClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;

    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository, ClientRepository clientRepository, ArticleRepository articleRepository, LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }


    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) throws InvalidEntityException, EntityNotFoundException {
        List<String> errors = CommandeClientValidator.validate(commandeClientDto);

        if( !errors.isEmpty()){
            log.error("Commande client n'est pas valide");
            throw new InvalidEntityException("La commande client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }

        //verification du client
        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if (client.isEmpty()){
            log.warn("Client with ID {} was not found in DB", commandeClientDto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID" + commandeClientDto.getClient().getId() + "n'a été trouvé", ErrorCodes.CLIENT_NOT_VALID);
        }
        //verification de l'article
        List<String> articleErrors = new ArrayList<>();

        if (commandeClientDto.getLigneCommandeClients() != null){
            commandeClientDto.getLigneCommandeClients().forEach(ligneCmdClt ->{
                if (ligneCmdClt.getArticle() != null){
                    Optional<Article> article = articleRepository.findById(ligneCmdClt.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'ID" + ligneCmdClt.getArticle().getId() + "n'existe pas");
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
       CommandeClient savedCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));

        if (commandeClientDto.getLigneCommandeClients() != null) {
            commandeClientDto.getLigneCommandeClients().forEach(ligneCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneCmdClt);
                ligneCommandeClient.setCommandeClient(savedCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }
        return CommandeClientDto.fromEntity(savedCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) throws EntityNotFoundException {
        if( id == null){
            log.error("Commande client ID is NULL");
            return null;
        }
        return commandeClientRepository.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        " Aucune commande client n'a ete trouve avec l'id " + id , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    //une méthode à été crée dans le repository
    public CommandeClientDto findByCode(String code) throws EntityNotFoundException {
        if( !StringUtils.hasLength(code)){
            log.error("Commande client CODE is NULL");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        " Aucune commande client n'a ete trouve avec le CODE " + code , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("Commande client ID is null");
            return;
        }
        commandeClientRepository.deleteById(id);
    }
}
