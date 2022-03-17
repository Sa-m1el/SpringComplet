package org.jeansamuel.swagg.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeansamuel.swagg.dao.ClientRepository;
import org.jeansamuel.swagg.dto.CategoryDto;
import org.jeansamuel.swagg.dto.ClientDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.ErrorCodes;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.model.Category;
import org.jeansamuel.swagg.model.Client;
import org.jeansamuel.swagg.services.ClientService;
import org.jeansamuel.swagg.validator.CategoryValidator;
import org.jeansamuel.swagg.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto save(ClientDto clientDto) throws InvalidEntityException {
        List<String> errors = ClientValidator.validate(clientDto);
        if(!errors.isEmpty()){
            log.error("Article is not valid {}", clientDto);
            throw new InvalidEntityException("Le client n'est pas valide", ErrorCodes.CLIENT_NOT_VALID, errors);
        }
        //return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
        Client savedClient = clientRepository.save(ClientDto.toEntity(clientDto));
        return ClientDto.fromEntity(savedClient );
    }

    @Override
    public ClientDto findById(Integer id) throws EntityNotFoundException {
        if ( id == null){
            log.error("Client ID is null");
            return null;
        }

        Optional<Client> client = clientRepository.findById(id);
        //  ArticleDto articleDto = ArticleDto.fromEntity(article.get());
        return Optional.of(ClientDto.fromEntity(client.get())).orElseThrow(() ->
                new EntityNotFoundException
                        ("Aucun client avec l'ID =" + id + " n'a été trouvé dans la BDD", ErrorCodes.CLIENT_NOT_FOUND));
    }

    @Override
    public List<ClientDto> findAll() {
        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if ( id == null){
            log.error("Client ID is null");
            return;
        }
        clientRepository.deleteById(id);

    }
}
