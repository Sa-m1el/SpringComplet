package org.jeansamuel.swagg.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.jeansamuel.swagg.dao.EntrepriseRepository;
import org.jeansamuel.swagg.dto.ClientDto;
import org.jeansamuel.swagg.dto.EntrepriseDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.ErrorCodes;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.model.Client;
import org.jeansamuel.swagg.model.Entreprise;
import org.jeansamuel.swagg.services.EntrepriseService;
import org.jeansamuel.swagg.validator.ClientValidator;
import org.jeansamuel.swagg.validator.EntrepriseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) throws InvalidEntityException{
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if(!errors.isEmpty()){
            log.error("Article is not valid {}", entrepriseDto);
            throw new InvalidEntityException("L'entreprise n'est pas valide", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        //return ArticleDto.fromEntity(articleRepository.save(ArticleDto.toEntity(articleDto)));
        Entreprise savedEntreprise = entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto));
        return EntrepriseDto.fromEntity(savedEntreprise );
    }

    @Override
    public EntrepriseDto findById(Integer id) throws EntityNotFoundException {
        if ( id == null){
            log.error("Client ID is null");
            return null;
        }

        Optional<Entreprise> entreprise = entrepriseRepository.findById(id);
        //  ArticleDto articleDto = ArticleDto.fromEntity(article.get());
        return Optional.of(EntrepriseDto.fromEntity(entreprise.get())).orElseThrow(() ->
                new EntityNotFoundException
                        ("Aucune entreprise avec l'ID =" + id + " n'a été trouvé dans la BDD", ErrorCodes.ENTREPRISE_NOT_FOUND));
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if ( id == null){
            log.error("Client ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
