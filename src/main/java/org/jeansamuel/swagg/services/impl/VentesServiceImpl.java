package org.jeansamuel.swagg.services.impl;


import lombok.extern.slf4j.Slf4j;
import org.jeansamuel.swagg.dao.ArticleRepository;
import org.jeansamuel.swagg.dao.LigneVenteRepository;
import org.jeansamuel.swagg.dao.VentesRepository;
import org.jeansamuel.swagg.dto.LigneVenteDto;
import org.jeansamuel.swagg.dto.VentesDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.ErrorCodes;
import org.jeansamuel.swagg.exception.InvalidEntityException;
import org.jeansamuel.swagg.model.Article;
import org.jeansamuel.swagg.model.LigneVente;
import org.jeansamuel.swagg.model.Ventes;
import org.jeansamuel.swagg.services.VentesService;
import org.jeansamuel.swagg.validator.VentesValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VentesServiceImpl implements VentesService {

    private ArticleRepository articleRepository;
    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VentesServiceImpl(ArticleRepository articleRepository, VentesRepository ventesRepository, LigneVenteRepository ligneVenteRepository) {
        this.articleRepository = articleRepository;
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override

    //validation
    public VentesDto save(VentesDto ventesDto) throws InvalidEntityException, EntityNotFoundException {
        List<String> errors = VentesValidator.validate(ventesDto);
        if(!errors.isEmpty()){
            log.error("Vente n'est pas valide");
            throw new InvalidEntityException("", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();
        ventesDto.getLigneVente().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()){
                articleErrors.add("Aucun article avec l'ID" + ligneVenteDto.getArticle().getId() + " n'a ete trouvé dans la BDD");
            }
        });

        if (!articleErrors.isEmpty()){
            log.error("One or more Article are not Found in the DB, {}", errors);
            throw new InvalidEntityException("Un ou plusieurs articles n'ont pas été trouve dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }
        Ventes savedVentes = ventesRepository.save(VentesDto.toEntity(ventesDto));

        ventesDto.getLigneVente().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVentes(savedVentes);
            ligneVenteRepository.save(ligneVente);
        });
        return VentesDto.fromEntity(savedVentes);
    }

    @Override
    public VentesDto findById(Integer id) throws EntityNotFoundException {
       if(id == null){
           log.error("VENTES ID is NULL");
           return null;
       }
       return ventesRepository.findById(id)
               .map(VentesDto::fromEntity)
               .orElseThrow(() -> new EntityNotFoundException("Aucune vente n'a été trouvé dans la base de donnée", ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VentesDto findByCode(String code) throws EntityNotFoundException {
        if(!StringUtils.hasLength(code)){
            log.error("Vente CODE is NULL");
            return null;
        }
        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() ->
                        new EntityNotFoundException(" Aucune vente  n'a été trouvé avec le code" +code, ErrorCodes.VENTE_NOT_VALID));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
                .map(VentesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if( id == null){
            log.error("Vente ID is NULL");
            return;
        }
        ventesRepository.deleteById(id);

    }
}
