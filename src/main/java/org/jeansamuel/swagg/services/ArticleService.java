package org.jeansamuel.swagg.services;

import org.jeansamuel.swagg.dto.ArticleDto;
import org.jeansamuel.swagg.exception.EntityNotFoundException;
import org.jeansamuel.swagg.exception.InvalidEntityException;

import java.util.List;


public interface ArticleService {

    ArticleDto save(ArticleDto articleDto) throws InvalidEntityException;

    ArticleDto findById(Integer id) throws EntityNotFoundException;

    ArticleDto findByCodeArticle(String codeArticle) throws EntityNotFoundException;

    List<ArticleDto> findAll();

    void delete(Integer id);



}
